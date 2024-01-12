package at.fhtw.swkom.paperless.exceptions;

public class DocumentNotFoundException extends Exception {

    public DocumentNotFoundException() {
        super();
    }
    public DocumentNotFoundException(String message) {
        super(message);
    }
}
