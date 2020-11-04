package bg.sofia.uni.fmi.mjt.netflix.exceptions;

public class ContentUnavailableException extends RuntimeException{

    public ContentUnavailableException() {
    }

    public ContentUnavailableException(String message) {
        super(message);
    }

    public ContentUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentUnavailableException(Throwable cause) {
        super(cause);
    }

}
