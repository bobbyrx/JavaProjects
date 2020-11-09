package bg.sofia.uni.fmi.mjt.smartcity.hub;

public class DeviceAlreadyRegisteredException extends RuntimeException {
    public DeviceAlreadyRegisteredException() {
    }

    public DeviceAlreadyRegisteredException(String message) {
        super(message);
    }

    public DeviceAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
}
