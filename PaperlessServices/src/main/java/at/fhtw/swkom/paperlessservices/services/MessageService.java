package at.fhtw.swkom.paperlessservices.services;

import at.fhtw.swkom.paperlessservices.config.RabbitMQConfig;
import at.fhtw.swkom.paperlessservices.repositories.DocumentRepository;
import at.fhtw.swkom.paperlessservices.services.dto.Document;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
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
    private final SearchIndexService searchIndexService;

    @Autowired
    public MessageService(RabbitTemplate rabbit, DocumentRepository documentRepository, SearchIndexService searchIndexService) {
        this.rabbit = rabbit;
        this.documentRepository = documentRepository;
        this.searchIndexService = searchIndexService;
    }

    @RabbitListener(queues = RabbitMQConfig.ECHO_IN_QUEUE_NAME)
    public void processMessage(String documentId) {
        log.info("Recieved Id: " + documentId);

        try {
            Document document = documentRepository.getDocumentById(Integer.parseInt(documentId));
            MultipartFile storedDocument = new MinIOService().getDocumentFile(document.getOriginalFileName().orElse(""));
            String extractedText = new OCRService().performOcr(storedDocument);
            log.debug("Extracted text: " + extractedText);
            //create doc for elasticsearch
            document.setContent(JsonNullable.of(extractedText));

            log.debug("Perform indexing of document with id: " + documentId);
            searchIndexService.indexDocument(document);

            log.debug("update document with id: " + documentId);
            documentRepository.updateDocumentContentById(extractedText, document.getOriginalFileName().orElse("-"), Integer.parseInt(documentId));
            rabbit.convertAndSend(RabbitMQConfig.ECHO_OUT_QUEUE_NAME, documentId + "-" + 0);
            log.info("Sent Message: " + documentId + " got processed");
        } catch (Exception e) {
            log.error(e.getMessage());
            rabbit.convertAndSend(RabbitMQConfig.ECHO_OUT_QUEUE_NAME, documentId + "-" + 1);
        }
    }
}
