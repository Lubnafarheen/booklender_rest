package se.group5.booklender_rest.exception;

public class DataDuplicateException extends RuntimeException {
    public DataDuplicateException(String message) {
        super(message);
    }
}
