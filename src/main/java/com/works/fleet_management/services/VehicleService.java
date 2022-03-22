package com.works.fleet_management.services;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.ErrorResult;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.Bag;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.entities.Vehicle;
import com.works.fleet_management.model.request.VehicleDto;
import com.works.fleet_management.model.request.shipmentRequest.ShipmentsDto;
import com.works.fleet_management.repositories.BagRepository;
import com.works.fleet_management.repositories.PackageRepository;
import com.works.fleet_management.repositories.VehicleRepository;
import com.works.fleet_management.services.abstracts.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    private final BagRepository bagRepository;
    private final PackageRepository packageRepository;

    public VehicleService(VehicleRepository vehicleRepository, BagRepository bagRepository, PackageRepository packageRepository) {
        this.vehicleRepository = vehicleRepository;
        this.bagRepository = bagRepository;
        this.packageRepository = packageRepository;
    }

    @Override
    public Result save(VehicleDto vehicleDto) {
        Vehicle vehicle= new Vehicle();
        vehicle.setPlate(vehicleDto.getPlate());
        vehicleRepository.save(vehicle);
        return new SuccessDataResult<>( vehicleDto, Messages.successSaved);
    }

    @Override
    public Result shipments(ShipmentsDto shipmentsDto) {

        Optional<Vehicle> optionalVehicle=vehicleRepository.findByPlate(shipmentsDto.getPlate());
        if(!optionalVehicle.isPresent()){
            return new ErrorResult(Messages.errorNoRecordFoundVehicle);
        }
        ShipmentsDto newShipment= new ShipmentsDto();

        newShipment.setPlate(shipmentsDto.getPlate());

        shipmentsDto.getRoute().forEach(route->{
            switch (route.getDeliveryPoint()){
                case 1:
                    route.getDeliveries().forEach(deliveriesDetail->{
                        if(deliveriesDetail.getBarcode().substring(0,1).equals("C")){
                            Optional<Bag> optionalBag=bagRepository.findByBagBarcode(deliveriesDetail.getBarcode());
                            if(!optionalBag.isPresent()){
                                return;
                            }

                        }else{
                            Optional<Package> optionalPackage = packageRepository.findByPackageBarcode(deliveriesDetail.getBarcode());
                            if(!optionalPackage.isPresent()){
                                return;
                            }

                        }
                    });
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        });



        return null;
    }
}
