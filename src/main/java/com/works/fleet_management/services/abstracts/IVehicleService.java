package com.works.fleet_management.services.abstracts;

import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.request.VehicleDto;
import com.works.fleet_management.model.request.shipmentRequest.ShipmentsDto;
import com.works.fleet_management.services.abstracts.base.IBaseService;

public interface IVehicleService extends IBaseService<VehicleDto> {
    Result shipments(ShipmentsDto shipmentsDto);
}
