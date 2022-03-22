package com.works.fleet_management.entities.enums.converter;

import com.works.fleet_management.entities.enums.PackageAndBagStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class PackageAndBagStatusConverter implements AttributeConverter<PackageAndBagStatus,Integer> {

    @Override
    public Integer convertToDatabaseColumn(PackageAndBagStatus packageStatus) {
        return packageStatus.getTag();
    }

    @Override
    public PackageAndBagStatus convertToEntityAttribute(Integer integer) {
        return Arrays.stream(PackageAndBagStatus.values()).filter(x->x.getTag().equals(integer)).findFirst().orElse(null);
    }
}
