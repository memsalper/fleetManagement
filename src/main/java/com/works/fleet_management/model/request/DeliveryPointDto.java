package com.works.fleet_management.model.request;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class DeliveryPointDto implements IDto {
    @NotNull(message = Messages.validationNotNullPointName)
    private String pointName;
}
