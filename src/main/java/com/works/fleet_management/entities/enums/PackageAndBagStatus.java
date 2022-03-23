package com.works.fleet_management.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageAndBagStatus {
    CREATED(1),
    LOADED_INTO_BAG(2),
    LOADED(3),
    UNLOADED(4);

    @JsonValue
    private final Integer tag;
}
