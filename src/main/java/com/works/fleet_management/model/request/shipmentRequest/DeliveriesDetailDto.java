package com.works.fleet_management.model.request.shipmentRequest;

import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveriesDetailDto {
    private String barcode;
    private PackageAndBagStatus state;
}
