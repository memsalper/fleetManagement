package com.works.fleet_management.model.request;

import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class BagDto implements IDto {
    private String bagBarcode;
    private PackageAndBagStatus packageStatus;
    private Integer deliveryPointPointId;
}
