package com.works.fleet_management.model.request.shipmentRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ShipmentDetailDto{
    private Long deliveryPoint;
    private List<DeliveriesDetailDto> deliveries;
}
