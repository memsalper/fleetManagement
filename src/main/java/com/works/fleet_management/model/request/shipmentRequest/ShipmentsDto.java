package com.works.fleet_management.model.request.shipmentRequest;

import com.works.fleet_management.core.utilities.conctants.Messages;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ShipmentsDto {
    @NotNull(message = Messages.validationNotNullPlate)
    private String plate;
    private List<ShipmentDetailDto> route;
}


