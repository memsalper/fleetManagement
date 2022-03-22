package com.works.fleet_management.services;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.core.utilities.results.ErrorResult;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.Bag;
import com.works.fleet_management.entities.DeliveryPoint;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.projections.BagsInfo;
import com.works.fleet_management.model.request.PackageDto;
import com.works.fleet_management.repositories.DeliveryPointRepository;
import com.works.fleet_management.repositories.PackageRepository;
import com.works.fleet_management.services.abstracts.IPackageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PackageService implements IPackageService {
    private final PackageRepository packageRepository;
    private final DeliveryPointRepository deliveryPointRepository;

    public PackageService(PackageRepository packageRepository, DeliveryPointRepository deliveryPointRepository) {
        this.packageRepository = packageRepository;
        this.deliveryPointRepository = deliveryPointRepository;
    }

    @Override
    public DataResult<List<BagsInfo>> getAll() {
        return null;
    }

    @Override
    public Package updateState(String packageBarcode, PackageAndBagStatus packageAndBagStatus) {
        Optional<Package> optionalPackage = packageRepository.findByPackageBarcode(packageBarcode);
        if(!optionalPackage.isPresent()){
            return null;
        }
        Package newPackage= optionalPackage.get();
        newPackage.setPackageStatus(packageAndBagStatus);

        return packageRepository.save(newPackage);

    }

    @Override
    public Result save(PackageDto packageDto) {
        Optional<DeliveryPoint> optionalPoint = deliveryPointRepository.findByPointId(packageDto.getDeliveryPointPointId());
        if(!optionalPoint.isPresent()){
            return new ErrorResult(Messages.errorNoRecordFoundDelivery);
        }
        Package aPackage=new Package();
        aPackage.setDeliveryPoint(optionalPoint.get());
        aPackage.setPackageBarcode(packageDto.getPackageBarcode());
        aPackage.setPackageStatus(PackageAndBagStatus.CREATED);
        aPackage.setVolumetricWeight(packageDto.getVolumetricWeight());
        packageRepository.save(aPackage);
        return new SuccessDataResult<>( packageDto, Messages.successSaved);
    }
}
