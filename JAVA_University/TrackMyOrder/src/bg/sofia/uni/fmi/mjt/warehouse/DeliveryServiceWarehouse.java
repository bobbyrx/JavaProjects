package bg.sofia.uni.fmi.mjt.warehouse;

import bg.sofia.uni.fmi.mjt.warehouse.exceptions.CapacityExceededException;
import bg.sofia.uni.fmi.mjt.warehouse.exceptions.ParcelNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;

public interface DeliveryServiceWarehouse<L, P> {
    void submitParcel(L label, P parcel, LocalDateTime submissionDate) throws CapacityExceededException;

    P getParcel(L label);

    P deliverParcel(L label) throws ParcelNotFoundException;

    double getWarehouseSpaceLeft();

    Map<L, P> getWarehouseItems();

    Map<L, P> deliverParcelsSubmittedBefore(LocalDateTime before);

    Map<L, P> deliverParcelsSubmittedAfter(LocalDateTime after);
}
