package bg.sofia.uni.fmi.mjt.smartcity.hub;

import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDevice;
import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SmartCityHub {
    private Map<String, SmartDevice> cityHub = new LinkedHashMap<>();

    public void register(SmartDevice device) throws DeviceAlreadyRegisteredException {
        if (device == null) {
            throw new IllegalArgumentException("The argument is illegal!");
        }
        if (cityHub.containsKey(device.getId())) {
            throw new DeviceAlreadyRegisteredException("The device is already registered!");
        }
        cityHub.put(device.getId(), device);
    }

    public void unregister(SmartDevice device) throws DeviceNotFoundException {
        if (device == null) {
            throw new IllegalArgumentException("The argument is illegal!");
        }
        if (!cityHub.containsKey(device.getId())) {
            throw new DeviceNotFoundException("The device not found!");
        }
        cityHub.remove(device.getId());
    }

    public SmartDevice getDeviceById(String id) throws DeviceNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("The argument is illegal!");
        }
        if (!cityHub.containsKey(id)) {
            throw new DeviceNotFoundException("The device not found!");
        }
        return cityHub.get(id);
    }

    public int getDeviceQuantityPerType(DeviceType type) {
        if (type == null) {
            throw new IllegalArgumentException("The argument should not be null!");
        }
        int counter = 0;
        for (SmartDevice device : cityHub.values()) {
            if (device.getType().equals(type)) {
                counter++;
            }
        }
        return counter;

    }

    public Collection<String> getTopNDevicesByPowerConsumption(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The argument should be bigger or equal to 0!");
        }
        List<Map.Entry<String, SmartDevice>> newList =
                new LinkedList<Map.Entry<String, SmartDevice>>(cityHub.entrySet());
        Collections.sort(newList, new Comparator<Map.Entry<String, SmartDevice>>() {
            @Override
            public int compare(Map.Entry<String, SmartDevice> o1, Map.Entry<String, SmartDevice> o2) {
                double hours1 = Duration.between(o1.getValue().getInstallationDateTime(), LocalDateTime.now()).toHours();
                double hours2 = Duration.between(o2.getValue().getInstallationDateTime(), LocalDateTime.now()).toHours();
                double result1 = o1.getValue().getPowerConsumption() * hours1;
                double result2 = o2.getValue().getPowerConsumption() * hours2;
                return ((Double) result2).compareTo((Double) result1);
            }
        });
        Map<String, SmartDevice> newMap = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, SmartDevice> entry : newList) {
            newMap.put(entry.getKey(), entry.getValue());
            count++;
            if (count == n) break;
        }
        return newMap.keySet();
    }

    public Collection<SmartDevice> getFirstNDevicesByRegistration(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The argument should be bigger or equal to 0!");
        }
        Map<String, SmartDevice> newMap = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, SmartDevice> entry : cityHub.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue());
            count++;
            if (count == n) break;
        }
        return newMap.values();
    }
}
