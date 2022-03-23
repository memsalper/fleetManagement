package com.works.fleet_management.services.abstracts;

import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.projections.PackageInfo;
import com.works.fleet_management.model.request.PackageDto;
import com.works.fleet_management.services.abstracts.base.IBaseService;

import java.util.List;

public interface IPackageService extends IBaseService<PackageDto> {
    DataResult<List<PackageInfo>> getAll();
    Package updateState(String PackageBarcode, PackageAndBagStatus packageAndBagStatus);
}
