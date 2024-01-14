package at.fhtw.swkom.paperless.services.documents;

import at.fhtw.swkom.paperless.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.exceptions.*;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumentRepository;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200ResponseResultsInner;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200ResponseResultsInnerNotesInner;
import at.fhtw.swkom.paperless.services.dto.UpdateDocument200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentRequest;
import at.fhtw.swkom.paperless.services.elasticsearch.SearchIndexService;
import at.fhtw.swkom.paperless.services.minio.MinIOService;
import at.fhtw.swkom.paperless.services.rabbitmq.RabbitMQService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class DocumentsService {

    private final DocumentsDocumentRepository documentsDocumentRepository;
    private final SearchIndexService searchIndexService;
    private final MinIOService minIOService;
    private final RabbitMQService rabbitMQService;

    @Autowired
    public DocumentsService(DocumentsDocumentRepository documentsDocumentRepository, SearchIndexService searchIndexService, MinIOService minIOService, RabbitMQService rabbitMQService) {
        this.documentsDocumentRepository = documentsDocumentRepository;
        this.searchIndexService = searchIndexService;
        this.minIOService = minIOService;
        this.rabbitMQService = rabbitMQService;
    }

    public List<GetDocuments200ResponseResultsInner> getDocuments200ResponseResultsInnerList(String query) throws Exception {
        try {
            List<GetDocuments200ResponseResultsInner> documents = new ArrayList<>();
            log.debug("Query String: " + query);
            List<DocumentsDocument> documentList = new ArrayList<>();

            if (query != "" && query != null) {
                List<Integer> ids = searchIndexService.searchDocuments(query);
                for (Integer id : ids) {
                    DocumentsDocument document = documentsDocumentRepository.findById(id).orElse(null);
                    if (document != null) {
                        documentList.add(document);
                    }
                }

            } else {
                documentList = documentsDocumentRepository.findAll();
            }

            for (DocumentsDocument entity : documentList) {
                List tags = new ArrayList(Arrays.asList(3, 3));
                List notes = new ArrayList(Arrays.asList(new GetDocuments200ResponseResultsInnerNotesInner(7, "note", "created", entity.getId(), 1)));
                documents.add(new GetDocuments200ResponseResultsInner(entity.getId(), 2, 7, 9, entity.getTitle(), entity.getContent(), tags, entity.getCreated().toString(), entity.getCreated().toString(), entity.getModified().toString(), entity.getAdded().toString(), 2, entity.getOriginalFilename(), entity.getArchiveFilename(), 4, true, notes));
            }

            log.info("Found " + documentList.size() + " results");
            return documents;
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public void uploadDocument(List<MultipartFile> documents) throws Exception {
        try {
            for (MultipartFile document : documents) {
                // create Document Entity
                DocumentsDocument documentsDocument = new DocumentsDocument();
                documentsDocument.setOriginalFilename(document.getOriginalFilename());
                documentsDocument.setFilename(document.getOriginalFilename());
                documentsDocument.setTitle(document.getOriginalFilename());
                documentsDocument.setCreated(OffsetDateTime.now());
                documentsDocument.setAdded(OffsetDateTime.now());
                documentsDocument.setModified(OffsetDateTime.now());

                DocumentsDocument savedDocumentsDocument = this.documentsDocumentRepository.save(documentsDocument);
                log.info("Saved Document with id " + savedDocumentsDocument.getId() + " and filename " + savedDocumentsDocument.getFilename() + " to database");
                minIOService.uploadDocument(document);
                log.info("Saved Document with id " + savedDocumentsDocument.getId() + " and filename " + savedDocumentsDocument.getFilename() + " to minio");
                rabbitMQService.sendMessageToQueue(savedDocumentsDocument.getId().toString());
                log.info("Send document id " + savedDocumentsDocument.getId() + " to paperlessservices");
                return;
            }
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<org.springframework.core.io.Resource> downloadDocument(Integer id, Boolean original) throws Exception {
        try {
            DocumentsDocument document = documentsDocumentRepository.getById(id);
            if (document == null) {
                log.warn("Document with id " + id + " not found");
                throw new DocumentNotFoundException("Document with id " + id + " not found");
            }
            String fileName = document.getFilename();
            InputStream stream = minIOService.getFileInputStream(fileName);

            log.debug("document found and returned");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(stream));
        } catch (DocumentNotFoundException | EntityNotFoundException e) {
            log.warn(e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public void deleteDocument(Integer id) throws Exception {
        try {
            DocumentsDocument documentToDelete = documentsDocumentRepository.getReferenceById(id);
            if (documentToDelete == null) {
                log.warn("Document with id " + id + " not found");
                throw new DocumentNotFoundException("Document with id " + id + " not found");
            } else if (documentToDelete.getArchiveFilename() == null) {
                log.warn("Document with id " + id + " not yet processed");
                throw new DocumentNotProcessedException("Document with id " + id + " not yet processed");
            }
            documentsDocumentRepository.deleteById(id);
            minIOService.deleteDocumentByFilename(documentToDelete.getFilename());
            searchIndexService.deleteDocumentById(id);
            log.info("document with id " + id + " got deleted");
        } catch (DocumentNotFoundException | EntityNotFoundException e) {
            log.warn(e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public UpdateDocument200Response updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest) throws Exception {
        try {
            DocumentsDocument documentToUpdate = documentsDocumentRepository.getReferenceById(id);
            if (documentToUpdate == null) {
                throw new DocumentNotFoundException("Document with id " + id + " not found");
            }
            if (updateDocumentRequest.getAdded() != null) {
                try {
                    documentToUpdate.setAdded(OffsetDateTime.parse(updateDocumentRequest.getAdded()));
                } catch (Exception e) {
                }
            }
            if (updateDocumentRequest.getArchivedFileName() != null) {
                documentToUpdate.setArchiveFilename(updateDocumentRequest.getArchivedFileName());
            }
            if (updateDocumentRequest.getContent() != null) {
                documentToUpdate.setContent(updateDocumentRequest.getContent());
            }
            if (updateDocumentRequest.getCreatedDate() != null) {
                try {
                    documentToUpdate.setCreated(OffsetDateTime.parse(updateDocumentRequest.getCreatedDate()));
                } catch (Exception e) {
                }
            }
            if (updateDocumentRequest.getModified() != null) {
                try {
                    documentToUpdate.setModified(OffsetDateTime.parse(updateDocumentRequest.getModified()));
                } catch (Exception e) {
                }
            }
            if (updateDocumentRequest.getOriginalFileName() != null) {
                documentToUpdate.setOriginalFilename(updateDocumentRequest.getOriginalFileName());
            }
            if (updateDocumentRequest.getTitle() != null) {
                documentToUpdate.setTitle(updateDocumentRequest.getTitle());
            }
            documentsDocumentRepository.save(documentToUpdate);
            UpdateDocument200Response updateDocument200Response = new UpdateDocument200Response();
            log.info("Updated document with id " + id);
            return updateDocument200Response;
        } catch (DocumentNotFoundException | EntityNotFoundException e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<org.springframework.core.io.Resource> previewDocument(Integer id) throws Exception {
        try {
            DocumentsDocument document = documentsDocumentRepository.getById(id);
            if (document == null) {
                log.warn("Document with id " + id + " not found");
                throw new DocumentNotFoundException("Document with id " + id + " not found");
            }
            String fileName = document.getFilename();
            log.debug("retrieve document with id " + id);

            InputStream stream = minIOService.getFileInputStream(fileName);

            log.debug("document found and returned");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName);

            // Set content type based on the document type, adjust as needed
            String contentType = "application/pdf";
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(new InputStreamResource(stream));
        } catch (DocumentNotFoundException | EntityNotFoundException e) {
            log.warn(e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e){
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
