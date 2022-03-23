package com.works.fleet_management.model.request;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PackageDto implements IDto {
    @NotNull(message = Messages.validationNotNullBarcode)
    private String packageBarcode;
    private PackageAndBagStatus packageStatus;
    @Min(value = 1, message = Messages.validationMin+" {min}")
    @Max(value = 3, message = Messages.validationMax+" {max}")
    private Integer deliveryPointPointId;
    @Min(value = 0, message = Messages.validationMin+" {min}")
    private Float volumetricWeight;
}
