package com.works.fleet_management.model.request;


import com.works.fleet_management.model.abstarcts.IDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackagesToBagDto implements IDto {
    private String bagBarcode;
    private String packageBarcode;
}
