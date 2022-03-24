package com.works.fleet_management.model.request.shipmentRequest;

import com.works.fleet_management.core.utilities.conctants.Messages;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class ShipmentDetailDto{
    @Min(value = 1, message = Messages.validationMin+" {min}")
    @Max(value = 3, message = Messages.validationMax+" {max}")
    private Integer deliveryPoint;
    private List<DeliveriesDetailDto> deliveries;
}
