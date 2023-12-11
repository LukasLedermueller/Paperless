package at.fhtw.swkom.paperlessservices.services;

import at.fhtw.swkom.paperlessservices.config.RabbitMQConfig;
import at.fhtw.swkom.paperlessservices.repositories.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class MessageService {
    private final RabbitTemplate rabbit;
    private final DocumentRepository documentRepository;

    @Autowired
    public MessageService(RabbitTemplate rabbit, DocumentRepository documentRepository) {
        this.rabbit = rabbit;
        this.documentRepository = documentRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.ECHO_IN_QUEUE_NAME)
    public void processMessage(String documentName) {
        log.info("Recieved DocumentName: " + documentName);

        try {

            MultipartFile document = new MinIOService().getDocumentFile(documentName);
            String extractedText = new OCRService().performOcr(document);
            log.info("Extracted text: " + extractedText);
            documentRepository.updateDocumentContentByFilename(extractedText, documentName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        rabbit.convertAndSend(RabbitMQConfig.ECHO_OUT_QUEUE_NAME, documentName + " got processed");
        log.info("Sent Message: " + documentName + " got processed");
    }
}
