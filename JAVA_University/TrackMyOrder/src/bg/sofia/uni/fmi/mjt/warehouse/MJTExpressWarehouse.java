package bg.sofia.uni.fmi.mjt.warehouse;

import bg.sofia.uni.fmi.mjt.warehouse.exceptions.CapacityExceededException;
import bg.sofia.uni.fmi.mjt.warehouse.exceptions.ParcelNotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class MJTExpressWarehouse<L, P> implements DeliveryServiceWarehouse<L, P> {
    private int capacity;
    private int retentionPeriod;
    private Map<L, Pair<P, LocalDateTime>> warehouseMap = new LinkedHashMap<>();
    private Map<L, P> copyMap = new LinkedHashMap<>();
    private int countCapacity;

    public MJTExpressWarehouse(int capacity, int retentionPeriod) {
        this.capacity = capacity;
        this.retentionPeriod = retentionPeriod;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRetentionPeriod() {
        return retentionPeriod;
    }

    public void setRetentionPeriod(int retentionPeriod) {
        this.retentionPeriod = retentionPeriod;
    }

    @Override
    public void submitParcel(L label, P parcel, LocalDateTime submissionDate) throws CapacityExceededException {
        if (submissionDate == null) {
            throw new IllegalArgumentException("The given date is null!");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        if (submissionDate.isAfter(localDateTime)) {
            throw new IllegalArgumentException("Provided date is a date in the future!");
        }

        if (label == null) {
            throw new IllegalArgumentException("Label is null!");
        }
        if (parcel == null) {
            throw new IllegalArgumentException("Parcel is null!");
        }
        if (countCapacity == this.capacity) {
            boolean isFull = true;
            for (L newLabel : warehouseMap.keySet()) {

                if (Math.abs(Duration
                        .between(warehouseMap.get(newLabel).getSecond(), localDateTime)
                        .toDays()) > this.retentionPeriod) {

                    deliverParcel(newLabel);
                    isFull = false;
                    break;
                    
                }
            }
            if (isFull) {
                throw new CapacityExceededException("There is no capacity left in the warehouse!");
            }
        }
        warehouseMap.put(label, new Pair<>(parcel, submissionDate));
        copyMap.put(label, parcel);
        countCapacity++;
    }

    @Override
    public P getParcel(L label) {
        if (label == null) {
            throw new IllegalArgumentException("Label is null!");
        }

        if (warehouseMap.get(label) == null) {
            return null;
        }

        return warehouseMap.get(label).getFirst();

    }

    @Override
    public P deliverParcel(L label) throws ParcelNotFoundException {
        if (label == null) {
            throw new IllegalArgumentException("Label is null!");
        }

        if (warehouseMap.get(label) == null) {
            throw new ParcelNotFoundException("Parcel not found!");
        }
        Pair<P, LocalDateTime> newPair = warehouseMap.get(label);
        countCapacity--;
        warehouseMap.remove(label);
        copyMap.remove(label);
        return newPair.getFirst();
    }

    @Override
    public double getWarehouseSpaceLeft() {
        return 1 - ((double) Math.round(((double) this.countCapacity / (double) this.capacity) * 100) / 100);
    }

    @Override
    public Map<L, P> getWarehouseItems() {
        try {
            return new LinkedHashMap<>(this.copyMap);
        } catch (NullPointerException ex) {
            return new LinkedHashMap<>();
        }
    }

    @Override
    public Map<L, P> deliverParcelsSubmittedBefore(LocalDateTime before) {
        if (before == null) {
            throw new IllegalArgumentException("The given date is null!");
        }
        Map<L, P> newMap = new LinkedHashMap<>();
        if (before.isAfter(LocalDateTime.now())) {
            return this.getWarehouseItems();
        }
        for (L newLabel : this.getWarehouseItems().keySet()) {
            if (warehouseMap.get(newLabel).getSecond().isBefore(before)) {
                newMap.put(newLabel, deliverParcel(newLabel));
            }
        }
        return newMap;
    }

    @Override
    public Map<L, P> deliverParcelsSubmittedAfter(LocalDateTime after) {
        if (after == null) {
            throw new IllegalArgumentException("The given date is null!");
        }
        Map<L, P> newMap = new LinkedHashMap<>();
        if (after.isAfter(LocalDateTime.now())) {
            return newMap;
        }
        for (L newLabel : this.getWarehouseItems().keySet()) {
            if (warehouseMap.get(newLabel).getSecond().isAfter(after)) {
                newMap.put(newLabel, deliverParcel(newLabel));
            }
        }
        return newMap;
    }
}
