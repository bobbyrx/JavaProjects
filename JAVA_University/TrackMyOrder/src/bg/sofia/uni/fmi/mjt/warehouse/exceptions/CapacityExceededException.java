package bg.sofia.uni.fmi.mjt.warehouse.exceptions;

public class CapacityExceededException extends RuntimeException {

    public CapacityExceededException(String message) {
        super(message);
    }

    public CapacityExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public CapacityExceededException(Throwable cause) {
        super(cause);
    }
}
