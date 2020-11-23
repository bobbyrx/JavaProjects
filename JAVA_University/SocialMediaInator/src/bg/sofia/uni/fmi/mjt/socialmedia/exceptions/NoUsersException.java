package bg.sofia.uni.fmi.mjt.socialmedia.exceptions;

public class NoUsersException extends RuntimeException {

    public NoUsersException(String message) {
        super(message);
    }

    public NoUsersException(String message, Throwable cause) {
        super(message, cause);
    }
}
