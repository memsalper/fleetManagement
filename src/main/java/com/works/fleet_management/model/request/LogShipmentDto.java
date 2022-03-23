package com.works.fleet_management.model.request;

import com.works.fleet_management.model.abstarcts.IDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogShipmentDto implements IDto {
    private String barcode;
    private Integer deliveryPointPointId;
}
