package at.fhtw.swkom.paperless.exceptions;

public class DocumentNotProcessedException extends Exception {

    public DocumentNotProcessedException() {
        super();
    }
    public DocumentNotProcessedException(String message) {
        super(message);
    }
}
