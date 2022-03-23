package com.works.fleet_management.services.abstracts;

import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.model.abstarcts.projections.BagsInfo;
import com.works.fleet_management.model.request.LogShipmentDto;
import com.works.fleet_management.services.abstracts.base.IBaseService;

import java.util.List;

public interface ILogShipmentService extends IBaseService<LogShipmentDto> {
    DataResult<List<BagsInfo>> getAll();
}
