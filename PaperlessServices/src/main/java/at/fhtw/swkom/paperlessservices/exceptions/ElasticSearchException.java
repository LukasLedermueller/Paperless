package at.fhtw.swkom.paperlessservices.exceptions;

public class ElasticSearchException extends Exception {

    public ElasticSearchException() {
        super();
    }
    public ElasticSearchException(String message) {
        super(message);
    }
}
