package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.LocalDateTime;

public interface SmartDevice {
    String getId();

    String getName();

    double getPowerConsumption();

    LocalDateTime getInstallationDateTime();

    DeviceType getType();

}
