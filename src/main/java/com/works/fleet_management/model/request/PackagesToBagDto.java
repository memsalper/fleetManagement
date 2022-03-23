package com.works.fleet_management.model.request;


import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PackagesToBagDto implements IDto {
    @NotNull(message = Messages.validationNotNullBarcode)
    private String bagBarcode;
    @NotNull(message = Messages.validationNotNullBarcode)
    private String packageBarcode;
}
