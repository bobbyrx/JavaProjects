package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract sealed class Device implements SmartDevice, Comparable<Device>
        permits SmartTrafficLight, SmartLamp, SmartCamera {

    private String name;
    private double powerConsumption;
    private LocalDateTime localDateTime;

    public Device(String name, double powerConsumption, LocalDateTime localDateTime) {
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.localDateTime = localDateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public int compareTo(Device o) {
        double hours1 = Duration.between(this.getInstallationDateTime(), LocalDateTime.now()).toHours();
        double hours2 = Duration.between(o.getInstallationDateTime(), LocalDateTime.now()).toHours();
        double result1 = this.getPowerConsumption() * hours1;
        double result2 = o.getPowerConsumption() * hours2;
        return ((Double) result1).compareTo((Double) result2);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPowerConsumption() {
        return this.powerConsumption;
    }

    @Override
    public LocalDateTime getInstallationDateTime() {
        return this.localDateTime;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public DeviceType getType() {
        return null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
