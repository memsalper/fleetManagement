package com.works.fleet_management.model.request;

import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeliveryPointDto implements IDto {
    private String pointName;
}
