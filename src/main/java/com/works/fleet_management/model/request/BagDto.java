package com.works.fleet_management.model.request;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.IDto;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ApiModel(value="Bag Api Doc",description="Model")
public class BagDto implements IDto {
    @NotNull(message = Messages.validationNotNullBarcode)
    private String bagBarcode;
    private PackageAndBagStatus packageStatus;
    @Min(value = 1, message = Messages.validationMin+" {min}")
    @Max(value = 3, message = Messages.validationMax+" {max}")
    private Integer deliveryPointPointId;
}
