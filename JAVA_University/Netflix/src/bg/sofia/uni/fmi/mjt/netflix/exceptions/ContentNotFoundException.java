package bg.sofia.uni.fmi.mjt.netflix.exceptions;

public class ContentNotFoundException extends RuntimeException {

    public ContentNotFoundException() {
    }

    public ContentNotFoundException(String message) {
        super(message);
    }

    public ContentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentNotFoundException(Throwable cause) {
        super(cause);
    }

}
