package at.fhtw.swkom.paperless.services.documents;

import at.fhtw.swkom.paperless.services.elasticsearch.SearchIndexService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DocumentsServiceUnitTest {
    @Autowired
    DocumentsService documentsService;
    @Autowired
    SearchIndexService searchIndexService;

    @Transactional
    @Test
    void getEntityNotFoundExceptionFromDownloadDocument() throws Exception {
        assertThrows(EntityNotFoundException.class, () -> {
            documentsService.downloadDocument(10, true);
        });
    }

    @Transactional
    @Test
    void downloadDocument() throws Exception {
        uploadFile();
        ResponseEntity<org.springframework.core.io.Resource> response = documentsService.downloadDocument(0, true);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    private void uploadFile() throws Exception{
        List<MultipartFile> list = new ArrayList<>();
        list.add(setFile());
        documentsService.uploadDocument(list);
    }
    private MultipartFile setFile() throws IOException {
        FileInputStream input = new FileInputStream("test.pdf");
        MultipartFile file = new MockMultipartFile("file", "test.pdf", "plain/text", input);
        return file;
    }
}