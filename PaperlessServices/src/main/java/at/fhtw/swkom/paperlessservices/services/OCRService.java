package at.fhtw.swkom.paperlessservices.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class OCRService {

    // @Value("${ocr.tessdata}")
    private String TESSERACT_DATA_PATH = "/usr/share/tesseract-ocr/4.00/tessdata/";

    public String performOcr(MultipartFile file) throws Exception {
        try {
            PDDocument document = Loader.loadPDF(file.getBytes());
            String strippedText = extractTextFromScannedDocument(document);

            JSONObject object = new JSONObject();
            object.put("fileName", file.getOriginalFilename());
            object.put("text", strippedText);

            JSONObject jsonObject = new JSONObject(object.toString());
            String textValue = jsonObject.getString("text");

            return textValue;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    private String extractTextFromScannedDocument(PDDocument document)
            throws IOException, TesseractException {

        PDFRenderer pdfRenderer = new PDFRenderer(document);
        StringBuilder out = new StringBuilder();

        ITesseract _tesseract = new Tesseract();
        _tesseract.setDatapath(TESSERACT_DATA_PATH);
        _tesseract.setLanguage("eng");

        for (int page = 0; page < document.getNumberOfPages(); page++)
        {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

            // Create a temp image file
            File temp = File.createTempFile("tempfile_" + page, ".png");
            ImageIO.write(bim, "png", temp);

            String result = _tesseract.doOCR(temp);
            out.append(result);

            // Delete temp file
            temp.delete();

        }

        return out.toString();

    }
}
