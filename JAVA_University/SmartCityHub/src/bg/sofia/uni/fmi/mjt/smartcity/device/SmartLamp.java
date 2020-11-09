package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.LocalDateTime;

public non-sealed class SmartLamp extends Device {
    private int idNumber;
    public static int number;

    public SmartLamp(String name, double powerConsumption, LocalDateTime localDateTime) {
        super(name, powerConsumption, localDateTime);
        idNumber = number;
        number++;
    }

    @Override
    public String getId() {
        return getType().getShortName() + "-" + this.getName() + "-" + idNumber;
    }

    @Override
    public LocalDateTime getInstallationDateTime() {
        return super.getInstallationDateTime();
    }

    @Override
    public DeviceType getType() {
        return DeviceType.LAMP;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getPowerConsumption() {
        return super.getPowerConsumption();
    }

    @Override
    public int compareTo(Device o) {
        return super.compareTo(o);
    }
}
