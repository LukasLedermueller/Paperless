package at.fhtw.swkom.paperless.services.documents;

import at.fhtw.swkom.paperless.services.dto.GetDocuments200ResponseResultsInner;
import at.fhtw.swkom.paperless.services.dto.UpdateDocument200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentRequest;
import at.fhtw.swkom.paperless.services.elasticsearch.SearchIndexService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import at.fhtw.swkom.paperless.services.dto.Document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// preparation: delete each container and restart - stop PaperlessREST container
// in application.yaml: change hosts to localhost - also in RabbitMQConfig
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class DocumentsServiceTest {

    @Autowired
    DocumentsService documentsService;
    @Autowired
    SearchIndexService searchIndexService;

    @BeforeEach
    void setUp() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Order(1)
    @Test
    void getDocuments200ResponseResultsInnerListEmpty() throws Exception {
        List<GetDocuments200ResponseResultsInner> allDocuments = documentsService.getDocuments200ResponseResultsInnerList(null);
        List<GetDocuments200ResponseResultsInner> filteredDocuments = documentsService.getDocuments200ResponseResultsInnerList("test123");
        assertEquals(allDocuments.size(), 0);
        assertEquals(filteredDocuments.size(), 0);
    }

    @Order(2)
    @Test
    void uploadDocument() throws Exception {
        List<MultipartFile> list = new ArrayList<>();
        list.add(setFile());
        documentsService.uploadDocument(list);
    }
    @Order(3)
    @Test
    void getDocuments200ResponseResultsInnerListOneDocument() throws Exception {
        System.out.println("sleep 10s");
        Thread.sleep(10000);
        List<GetDocuments200ResponseResultsInner> allDocuments = documentsService.getDocuments200ResponseResultsInnerList("");
        List<GetDocuments200ResponseResultsInner> filteredDocuments = documentsService.getDocuments200ResponseResultsInnerList("test.pdf");
        List<GetDocuments200ResponseResultsInner> notFoundDocument = documentsService.getDocuments200ResponseResultsInnerList("123.pdf");
        assertEquals(allDocuments.size(), 1);
        assertEquals(filteredDocuments.size(), 1);
        assertEquals(notFoundDocument.size(), 0);
        assertEquals(filteredDocuments.get(0).getContent(), "test\n");

        Optional<Document> document = searchIndexService.getDocumentById(0);
        assertTrue(document.isPresent());
    }
    @Order(4)
    @Transactional
    @Test
    void updateDocument() throws Exception {
        UpdateDocumentRequest request = new UpdateDocumentRequest();
        request.setTitle("newTitle");
        assertThrows(EntityNotFoundException.class, () -> {
            documentsService.updateDocument(1, request);
        });
        UpdateDocument200Response response = documentsService.updateDocument(0, request);
        assertNotNull(response);
        List<GetDocuments200ResponseResultsInner> updatedDocuments = documentsService.getDocuments200ResponseResultsInnerList("test.pdf");
        assertEquals(updatedDocuments.get(0).getTitle(), "newTitle");
    }

    @Order(5)
    @Transactional
    @Test
    void downloadDocument() throws Exception {
        assertThrows(EntityNotFoundException.class, () -> {
            documentsService.downloadDocument(1, true);
        });
        ResponseEntity<org.springframework.core.io.Resource> response = documentsService.downloadDocument(0, true);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Order(6)
    @Transactional
    @Test
    void deleteDocument() throws Exception {
        assertThrows(EntityNotFoundException.class, () -> {
            documentsService.deleteDocument(1);
        });
        List<GetDocuments200ResponseResultsInner> allDocuments = documentsService.getDocuments200ResponseResultsInnerList(null);
        assertEquals(allDocuments.size(), 1);
        documentsService.deleteDocument(0);
        allDocuments = documentsService.getDocuments200ResponseResultsInnerList("");
        assertEquals(allDocuments.size(), 0);
        assertThrows(EntityNotFoundException.class, () -> {
            documentsService.downloadDocument(0, true);
        });
        Optional<Document> document = searchIndexService.getDocumentById(0);
        assertFalse(document.isPresent());

    }

    // helper
    private MultipartFile setFile() throws IOException {
        FileInputStream input = new FileInputStream("test.pdf");
        MultipartFile file = new MockMultipartFile("file", "test.pdf", "plain/text", input);
        return file;
    }

}