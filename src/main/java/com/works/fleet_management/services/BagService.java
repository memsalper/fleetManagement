package com.works.fleet_management.services;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.core.utilities.results.ErrorResult;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.Bag;
import com.works.fleet_management.entities.DeliveryPoint;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.model.abstarcts.projections.BagsInfo;
import com.works.fleet_management.model.request.BagDto;
import com.works.fleet_management.repositories.BagRepository;
import com.works.fleet_management.repositories.DeliveryPointRepository;
import com.works.fleet_management.repositories.PackageRepository;
import com.works.fleet_management.services.abstracts.IBagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class BagService implements IBagService {
    private final BagRepository bagRepository;
    private final PackageRepository packageRepository;
    private final PackageService packageService;
    private final DeliveryPointRepository deliveryPointRepository;

    public BagService(BagRepository bagRepository, PackageRepository packageRepository, PackageService packageService, DeliveryPointRepository deliveryPointRepository) {
        this.bagRepository = bagRepository;
        this.packageRepository = packageRepository;
        this.packageService = packageService;
        this.deliveryPointRepository = deliveryPointRepository;
    }


    @Override
    public Result save(BagDto bagDto) {
        Optional<DeliveryPoint> byPointId = deliveryPointRepository.findByPointId(bagDto.getDeliveryPointPointId());
        if(!byPointId.isPresent()){
            return new ErrorResult(Messages.errorNoRecordFoundDelivery);
        }

        Bag bag=new Bag();
        /*
        bagDto.getPackagesToBag().forEach(aPackage->{
            Optional<Package> byPackageBarcode=packageRepository.findByPackageBarcode(aPackage.getPackageBarcode());
            Set<Package> packageList;
            if(!byPackageBarcode.isPresent()){
                Package newPackage=new Package();
                newPackage.setPackageStatus(bagDto.getPackageStatus());
                newPackage.setPackageBarcode(aPackage.getPackageBarcode());
                newPackage.setVolumetricWeight(aPackage.getVolumetricWeight());
                if(bagDto.getDeliveryPointPointId()!= aPackage.getDeliveryPointPointId()){
                    ErrorResult hata = new ErrorResult("Hata");
                    return hata;
                }
                newPackage.setDeliveryPoint(byPointId.get());
            }
        });

         bag.setPackagesToBag();*/

        bag.setBagBarcode(bagDto.getBagBarcode());
        bag.setDeliveryPoint(byPointId.get());
        bag.setState(bagDto.getPackageStatus());
        bagRepository.save(bag);
        return new SuccessDataResult<>( bagDto, Messages.successSaved);
    }

    @Override
    public DataResult<List<BagsInfo>> getAll() {
        return null;
    }
}
