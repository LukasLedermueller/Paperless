package at.fhtw.swkom.paperless.exceptions;

public class ElasticSearchException extends Exception {

    public ElasticSearchException() {
        super();
    }
    public ElasticSearchException(String message) {
        super(message);
    }
}
