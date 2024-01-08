package at.fhtw.swkom.paperless.services.documents;

import at.fhtw.swkom.paperless.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumentRepository;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200ResponseResultsInner;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200ResponseResultsInnerNotesInner;
import at.fhtw.swkom.paperless.services.elasticsearch.SearchIndexService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class DocumentsService {

    private final DocumentsDocumentRepository documentsDocumentRepository;
    private final SearchIndexService searchIndexService;

    @Autowired
    public DocumentsService(DocumentsDocumentRepository documentsDocumentRepository, SearchIndexService searchIndexService) {
        this.documentsDocumentRepository = documentsDocumentRepository;
        this.searchIndexService = searchIndexService;
    }

    public List<GetDocuments200ResponseResultsInner> getDocuments200ResponseResultsInnerList(String query) {
        List<GetDocuments200ResponseResultsInner> documents = new ArrayList<>();
        log.debug("Query String: " + query);
        List<DocumentsDocument> documentList = new ArrayList<>();

        if(query != "" && query != null) {
            List<Integer> ids = searchIndexService.searchDocuments(query);
            for(Integer id: ids) {
                DocumentsDocument document = documentsDocumentRepository.findById(id).orElse(null);
                if(document != null) {
                    documentList.add(document);
                }
            }

        } else {
            documentList = documentsDocumentRepository.findAll();
        }

        for (DocumentsDocument entity : documentList) {
            List tags = new ArrayList(Arrays.asList(3,3));
            List notes = new ArrayList(Arrays.asList(new GetDocuments200ResponseResultsInnerNotesInner(7, "note", "created", entity.getId(), 1)));
            documents.add(new GetDocuments200ResponseResultsInner(entity.getId(), 2, 7, 9, entity.getTitle(), entity.getContent(), tags, entity.getCreated().toString(), entity.getCreated().toString(), entity.getModified().toString(), entity.getAdded().toString(), 2, entity.getOriginalFilename(), entity.getArchiveFilename(), 4, true, notes));
        }

        log.info("Found " + documentList.size() + " results");
        return documents;
    }

    public ResponseEntity<org.springframework.core.io.Resource> uploadDocument(Integer id, Boolean original) throws Exception {
        String minioEndpoint = "http://MinIO:9000";
        String accessKey = "minioadmin";
        String secretKey = "minioadmin";
        String bucketName = "documents";
        DocumentsDocument document = documentsDocumentRepository.getById(id);
        String fileName = document.getFilename();
        log.debug("retrieve document with id " + id);

        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(accessKey, secretKey)
                .build();

        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();

        InputStream stream = minioClient.getObject(getObjectArgs);

        log.debug("document found and returned");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(stream));
    }
}
