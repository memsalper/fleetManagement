package com.works.fleet_management.model.request;

import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDto implements IDto {
    private String plate;
}
