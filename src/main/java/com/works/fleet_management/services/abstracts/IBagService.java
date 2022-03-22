package com.works.fleet_management.services.abstracts;

import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.entities.Bag;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.projections.BagsInfo;
import com.works.fleet_management.model.request.BagDto;
import com.works.fleet_management.services.abstracts.base.IBaseService;

import java.util.List;

public interface IBagService extends IBaseService<BagDto> {
    DataResult<List<BagsInfo>> getAll();
    Bag updateState(String BagBarcode, PackageAndBagStatus packageAndBagStatus);
}
