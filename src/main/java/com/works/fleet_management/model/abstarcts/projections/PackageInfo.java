package com.works.fleet_management.model.abstarcts.projections;

import com.works.fleet_management.entities.enums.PackageAndBagStatus;

public interface PackageInfo {
    String getPackageBarcode();

    PackageAndBagStatus getPackageStatus();

    DeliveryPointInfo getDeliveryPoint();

    interface DeliveryPointInfo {
        Long getPointId();
    }
}
