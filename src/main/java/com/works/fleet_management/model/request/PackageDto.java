package com.works.fleet_management.model.request;

import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageDto implements IDto {
    private String packageBarcode;
    private PackageAndBagStatus packageStatus;
    private Long deliveryPointPointId;
    private Float volumetricWeight;
}
