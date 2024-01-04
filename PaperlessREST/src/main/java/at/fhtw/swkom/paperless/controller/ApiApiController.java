package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumentRepository;
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

    @Autowired
    public ApiApiController(NativeWebRequest request, DocumentsDocumentRepository documentsDocumentRepository) {
        this.request = request;
        this.documentsDocumentRepository = documentsDocumentRepository;
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

    /* @Override
    public ResponseEntity<List<DocumentsDocument>> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        List<DocumentsDocument> list = documentsDocumentRepository.findAll();
        System.out.println(list.size());
        return ResponseEntity.ok(list);
    }; */
    @Override
    public ResponseEntity<org.springframework.core.io.Resource> downloadDocument(Integer id, Boolean original) {
        try {
            String minioEndpoint = "http://MinIO:9000";
            String accessKey = "minioadmin";
            String secretKey = "minioadmin";
            String bucketName = "documents";
            DocumentsDocument document = documentsDocumentRepository.getById(id);
            String fileName = document.getFilename();
            System.out.println(id);
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioEndpoint)
                    .credentials(accessKey, secretKey)
                    .build();
            // Perform the getObject operation
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build();

            InputStream stream = minioClient.getObject(getObjectArgs);

            // Set headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

            // Return the file as a response entity
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(stream));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            return ResponseEntity
                    .status(500)
                    .body(null);
        }
    }

}
