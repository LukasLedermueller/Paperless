package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.exceptions.DocumentNotFoundException;
import at.fhtw.swkom.paperless.exceptions.DocumentNotProcessedException;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumentRepository;
import at.fhtw.swkom.paperless.services.documents.DocumentsService;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200Response;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200ResponseResultsInner;
import at.fhtw.swkom.paperless.services.dto.UpdateDocument200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentRequest;
import at.fhtw.swkom.paperless.services.rabbitmq.RabbitMQService;
import at.fhtw.swkom.paperless.services.minio.MinIOService;

import java.time.OffsetDateTime;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jakarta.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-09T11:20:43.687138Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin
public class DocumentController implements ApiApi {

    private final NativeWebRequest request;
    private final DocumentsDocumentRepository documentsDocumentRepository;
    private final DocumentsService documentsService;

    @Autowired
    public DocumentController(NativeWebRequest request, DocumentsDocumentRepository documentsDocumentRepository, DocumentsService documentsService) {
        this.request = request;
        this.documentsDocumentRepository = documentsDocumentRepository;
        this.documentsService = documentsService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> deleteDocument(Integer id) {
        try {
            documentsService.deleteDocument(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DocumentNotProcessedException e) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType,
                                               List<Integer> tags, Integer correspondent, List<MultipartFile> documents) {
        try {
            documentsService.uploadDocument(documents);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        List all = new ArrayList(Arrays.asList(5,5));

        try {
            List<GetDocuments200ResponseResultsInner> documents = documentsService.getDocuments200ResponseResultsInnerList(query);
            GetDocuments200Response response = new GetDocuments200Response(documents.size(), 6, 1, all, documents);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UpdateDocument200Response> updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest) {
        try {
            return ResponseEntity.ok().body(documentsService.updateDocument(id, updateDocumentRequest));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
    }

    @Override
    public ResponseEntity<org.springframework.core.io.Resource> downloadDocument(Integer id, Boolean original) {
        try {
            return documentsService.downloadDocument(id, original);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
    }

    @Override
    public ResponseEntity<Resource> getDocumentPreview(Integer id) {
        try {
            return documentsService.previewDocument(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
    }


}
