package com.works.fleet_management.services.abstracts;

import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.model.abstarcts.projections.DeliveryPointInfo;
import com.works.fleet_management.model.request.DeliveryPointDto;
import com.works.fleet_management.services.abstracts.base.IBaseService;

import java.util.List;

public interface IDeliveryPointService extends IBaseService<DeliveryPointDto> {
    DataResult<List<DeliveryPointInfo>> getAll();
}
