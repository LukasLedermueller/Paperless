package at.fhtw.swkom.paperlessservices.services;

import at.fhtw.swkom.paperlessservices.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageService {
    private final RabbitTemplate rabbit;

    @Autowired
    public MessageService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @RabbitListener(queues = RabbitMQConfig.ECHO_IN_QUEUE_NAME)
    public void processMessage(String message) {
        log.info("Recieved Message: " + message);

        // Delay
        try {
            Thread.sleep(message.length() * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Delete2ndChar service interrupted");
        }

        rabbit.convertAndSend(RabbitMQConfig.ECHO_OUT_QUEUE_NAME, "Echo " + message);
        log.info("Sent Message: Echo " + message);
    }
}
