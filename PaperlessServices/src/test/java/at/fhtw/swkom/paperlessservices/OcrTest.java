package at.fhtw.swkom.paperlessservices;

import at.fhtw.swkom.paperlessservices.services.OCRService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OcrTest {
    private OCRService ocrService;

    @Test
    public void testOCR() throws Exception {
        ocrService = new OCRService();

        String result = ocrService.performOcr(setFile());

        assertNotNull(result);
        assertTrue(result.contains("test"));
    }

    private MultipartFile setFile() throws IOException {
        FileInputStream input = new FileInputStream("test.pdf");
        MultipartFile file = new MockMultipartFile("file", "test.pdf", "plain/text", input);
        return file;
    }
}