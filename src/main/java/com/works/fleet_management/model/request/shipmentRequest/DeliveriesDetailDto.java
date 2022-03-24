package com.works.fleet_management.model.request.shipmentRequest;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeliveriesDetailDto {
    @NotNull(message = Messages.validationNotNullBarcode)
    private String barcode;
    private PackageAndBagStatus state;
}
