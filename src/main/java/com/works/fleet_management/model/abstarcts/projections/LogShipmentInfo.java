package com.works.fleet_management.model.abstarcts.projections;

import java.util.UUID;

public interface LogShipmentInfo {
    UUID getId();

    String getBarcode();

    DeliveryPointInfo getDeliveryPoint();
}
