package com.works.fleet_management.model.abstarcts.projections;

public interface BagsInfo {
    String getBagBarcode();

    DeliveryPointsInfo getDeliveryPoint();

    interface DeliveryPointsInfo {
        Long getId();
    }
}
