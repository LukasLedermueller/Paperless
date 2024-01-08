package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumentRepository;
import at.fhtw.swkom.paperless.services.documents.DocumentsService;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200Response;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200ResponseResultsInner;
import at.fhtw.swkom.paperless.services.rabbitmq.RabbitMQService;
import at.fhtw.swkom.paperless.services.minio.MinIOService;

import java.time.OffsetDateTime;

import io.minio.GetObjectArgs;
import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.InputStreamResource;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
public class ApiApiController implements ApiApi {

    private final NativeWebRequest request;
    private final DocumentsDocumentRepository documentsDocumentRepository;
    private final DocumentsService documentsService;

    @Autowired
    public ApiApiController(NativeWebRequest request, DocumentsDocumentRepository documentsDocumentRepository, DocumentsService documentsService) {
        this.request = request;
        this.documentsDocumentRepository = documentsDocumentRepository;
        this.documentsService = documentsService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType,
                                               List<Integer> tags, Integer correspondent, List<MultipartFile> documents) {
        try {
            for (MultipartFile document : documents) {
                System.out.println("------------ document start -----------");
                System.out.println(document);
                System.out.println("------------ document end -----------");
                DocumentsDocument documentsDocument = new DocumentsDocument();
                documentsDocument.setOriginalFilename(document.getOriginalFilename());
                documentsDocument.setFilename(document.getOriginalFilename());
                documentsDocument.setTitle(document.getOriginalFilename());
                documentsDocument.setCreated(OffsetDateTime.now());
                documentsDocument.setAdded(OffsetDateTime.now());
                documentsDocument.setModified(OffsetDateTime.now());

                DocumentsDocument savedDocumentsDocument = this.documentsDocumentRepository.save(documentsDocument);
                System.out.println("------------ savedDocumentsDocument start -----------");
                System.out.println("id: " + savedDocumentsDocument.getId());
                System.out.println("original filename: " + savedDocumentsDocument.getOriginalFilename());
                System.out.println("filename: " + savedDocumentsDocument.getFilename());
                System.out.println("------------ savedDocumentsDocument end -----------");
                new MinIOService().uploadDocument(document);
                new RabbitMQService().sendMessageToQueue(savedDocumentsDocument.getId().toString());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        List all = new ArrayList(Arrays.asList(5,5));

        List<GetDocuments200ResponseResultsInner> documents = documentsService.getDocuments200ResponseResultsInnerList(query);
        GetDocuments200Response response = new GetDocuments200Response(documents.size(), 6, 1, all, documents);
        return ResponseEntity.ok(response);
    };

    @Override
    public ResponseEntity<org.springframework.core.io.Resource> downloadDocument(Integer id, Boolean original) {
        try {
            return documentsService.uploadDocument(id, original);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
    }

}
