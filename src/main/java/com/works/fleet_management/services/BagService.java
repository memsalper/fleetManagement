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
import com.works.fleet_management.model.abstarcts.projections.LogShipmentInfo;
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

        bag.setBagBarcode(bagDto.getBagBarcode());
        bag.setDeliveryPoint(byPointId.get());
        bag.setPackageStatus(PackageAndBagStatus.CREATED);
        bagDto.setPackageStatus(PackageAndBagStatus.CREATED);
        bagRepository.save(bag);
        return new SuccessDataResult<>( bagDto, Messages.successSaved);
    }

    @Override
    public Bag updateState(String bagBarcode, PackageAndBagStatus packageAndBagStatus) {
        Optional<Bag> optionalBag = bagRepository.findByBagBarcode(bagBarcode);
        if(!optionalBag.isPresent()){
            return null;
        }
        Bag newBag= optionalBag.get();
        newBag.setPackageStatus(packageAndBagStatus);

        return bagRepository.save(newBag);
    }

    @Override
    public DataResult<List<BagsInfo>> getAll() {
        return new SuccessDataResult<>(bagRepository.findAllBy(BagsInfo.class),Messages.successListed);
    }
}
