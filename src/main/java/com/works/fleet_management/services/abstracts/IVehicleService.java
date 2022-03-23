package com.works.fleet_management.services.abstracts;

import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.entities.Bag;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.model.request.VehicleDto;
import com.works.fleet_management.model.request.shipmentRequest.DeliveriesDetailDto;
import com.works.fleet_management.model.request.shipmentRequest.ShipmentDetailDto;
import com.works.fleet_management.model.request.shipmentRequest.ShipmentsDto;
import com.works.fleet_management.services.abstracts.base.IBaseService;

public interface IVehicleService extends IBaseService<VehicleDto> {
    Result packageAndBagUnload(ShipmentsDto shipmentsDto);
    Result packageAndBagLoad(ShipmentsDto shipmentsDto);
    Bag checkBagDeliveryPoint(String barcode, Integer deliveryPoint);
    Package checkPackageDeliveryPoint(String barcode, Integer deliveryPoint, Boolean packageInBag);
}
