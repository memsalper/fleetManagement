package com.works.fleet_management.services;


import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.core.utilities.results.ErrorResult;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.Bag;
import com.works.fleet_management.entities.DeliveryPoint;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.entities.PackagesToBag;
import com.works.fleet_management.model.abstarcts.projections.BagsInfo;
import com.works.fleet_management.model.request.PackagesToBagDto;
import com.works.fleet_management.repositories.BagRepository;
import com.works.fleet_management.repositories.PackageRepository;
import com.works.fleet_management.repositories.PackagesToBagRepository;
import com.works.fleet_management.services.abstracts.IPackagesToBagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackagesToBagService implements IPackagesToBagService {
    private final PackagesToBagRepository packagesToBagRepository;
    private final PackageRepository packageRepository;
    private final BagRepository bagRepository;

    public PackagesToBagService(PackagesToBagRepository packagesToBagRepository, PackageRepository packageRepository, BagRepository bagRepository) {
        this.packagesToBagRepository = packagesToBagRepository;
        this.packageRepository = packageRepository;
        this.bagRepository = bagRepository;
    }

    @Override
    public DataResult<List<BagsInfo>> getAll() {
        return null;
    }

    @Override
    public Result save(PackagesToBagDto packagesToBagDto) {
        Optional<Bag> optionalBag = bagRepository.findByBagBarcode(packagesToBagDto.getBagBarcode());
        if(!optionalBag.isPresent()){
            return new ErrorResult(Messages.errorNoRecordFoundBag);
        }

        Optional<Package> optionalPackage = packageRepository.findByPackageBarcode(packagesToBagDto.getPackageBarcode());
        if(!optionalPackage.isPresent()){
            return new ErrorResult(Messages.errorNoRecordFoundPackage);
        }

        PackagesToBag packagesToBag= new PackagesToBag();
        packagesToBag.setBagBarcode(packagesToBagDto.getPackageBarcode());
        packagesToBag.setPackageBarcode(packagesToBagDto.getPackageBarcode());
        packagesToBagRepository.save(packagesToBag);
        return new SuccessDataResult<>( packagesToBagDto, Messages.successSaved);
    }
}
