package at.fhtw.swkom.paperless.exceptions;

public class RabbitMQException extends Exception {

    public RabbitMQException() {
        super();
    }
    public RabbitMQException(String message) {
        super(message);
    }
}
