package at.fhtw.swkom.paperless.services.rabbitmq;

import at.fhtw.swkom.paperless.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.exceptions.RabbitMQException;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumentRepository;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class RabbitMQService {

        private RabbitMQConfig config = new RabbitMQConfig();
        RabbitTemplate rabbitTemplate = config.rabbitTemplate();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            try {
                String[] numbers = message.split("-");

                int id = Integer.parseInt(numbers[0]);
                int errorCode = Integer.parseInt(numbers[1]);
                if (errorCode == 0) {
                    log.info("\nYou received: " + id + "got processed");
                } else {
                    log.warn("An error occurred while processing document with id " + id);
                }
            } catch (Exception e) {
                log.error("An error occurred while recieving message from PaperlessServices");
            }
        };

    public void sendMessageToQueue(String message) throws RabbitMQException {
        try {
            config.connectionFactory()
                    .createConnection()
                    .createChannel(false)
                    .basicConsume(RabbitMQConfig.ECHO_OUT_QUEUE_NAME, true, deliverCallback, consumerTag -> {
                    });
            log.info("You entered: " + message);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ECHO_IN_QUEUE_NAME, message);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RabbitMQException(e.getMessage());
        }
    }
}


