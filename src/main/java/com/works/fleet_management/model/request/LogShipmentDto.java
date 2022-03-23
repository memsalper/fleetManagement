package com.works.fleet_management.model.request;


import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogShipmentDto implements IDto {
    private String barcode;
    private Integer deliveryPointPointId;
}
