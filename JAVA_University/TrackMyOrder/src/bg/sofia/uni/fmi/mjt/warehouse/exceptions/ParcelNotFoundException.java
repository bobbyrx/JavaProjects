package bg.sofia.uni.fmi.mjt.warehouse.exceptions;

public class ParcelNotFoundException extends RuntimeException {

    public ParcelNotFoundException(String message) {
        super(message);
    }

    public ParcelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParcelNotFoundException(Throwable cause) {
        super(cause);
    }
}
