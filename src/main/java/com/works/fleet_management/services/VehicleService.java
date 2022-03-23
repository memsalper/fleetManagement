package com.works.fleet_management.services;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.ErrorResult;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.*;
import com.works.fleet_management.entities.Package;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.model.abstarcts.projections.PackagesListInBag;
import com.works.fleet_management.model.abstarcts.projections.PackagesToBagInfo;
import com.works.fleet_management.model.request.LogShipmentDto;
import com.works.fleet_management.model.request.VehicleDto;
import com.works.fleet_management.model.request.shipmentRequest.DeliveriesDetailDto;
import com.works.fleet_management.model.request.shipmentRequest.ShipmentDetailDto;
import com.works.fleet_management.model.request.shipmentRequest.ShipmentsDto;
import com.works.fleet_management.repositories.*;
import com.works.fleet_management.services.abstracts.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    private final BagRepository bagRepository;
    private final PackageRepository packageRepository;
    private final PackagesToBagRepository packagesToBagRepository;
    private final LogShipmentService logShipmentService;


    public VehicleService(VehicleRepository vehicleRepository, BagRepository bagRepository, PackageRepository packageRepository, PackagesToBagRepository packagesToBagRepository, LogShipmentService logShipmentService) {
        this.vehicleRepository = vehicleRepository;
        this.bagRepository = bagRepository;
        this.packageRepository = packageRepository;
        this.packagesToBagRepository = packagesToBagRepository;
        this.logShipmentService = logShipmentService;
    }

    @Override
    public Result save(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(vehicleDto.getPlate());
        vehicleRepository.save(vehicle);
        return new SuccessDataResult<>(vehicleDto, Messages.successSaved);
    }

    @Override
    public Result packageAndBagUnload(ShipmentsDto shipmentsDto) {

        Optional<Vehicle> optionalVehicle = vehicleRepository.findByPlate(shipmentsDto.getPlate());
        if (!optionalVehicle.isPresent()) {
            return new ErrorResult(Messages.errorNoRecordFoundVehicle);
        }
        //tüm listeyi çek
        for (ShipmentDetailDto route : shipmentsDto.getRoute()) {
            for (DeliveriesDetailDto deliveriesDetail : route.getDeliveries()) {

                //Eğer Bag ise
                if (deliveriesDetail.getBarcode().startsWith("C")) {
                    BagUnload(route, deliveriesDetail);
                } else {
                    PackageUnload(route, deliveriesDetail);
                }
            }
        }

        return new SuccessDataResult<>(shipmentsDto, Messages.successListed);
    }

    private void PackageUnload(ShipmentDetailDto route, DeliveriesDetailDto deliveriesDetail) {
        Optional<PackagesToBag> optionalPackagesToBag = packagesToBagRepository.findByPackageBarcode(deliveriesDetail.getBarcode());
        //Package'ın bağlı olduğu Bag'e ait diğer Package'ler aynı DeliveryPoint'e sahip ise ilgili Bag'i ve Package'ları "Unloaded" yapılır
        if (optionalPackagesToBag.isPresent()) {
            List<PackagesListInBag> packagesListInBag = packagesToBagRepository.findByBagBarcode(optionalPackagesToBag.get().getBagBarcode(), PackagesListInBag.class);
            List<DeliveriesDetailDto> packageList = route.getDeliveries().stream().filter(pckg -> pckg.getBarcode().startsWith("P")).collect(Collectors.toList());
            
            // Bag'e bağlı Packagelar ile route'a ait Packagelar kıyaslanır hepsi aynı route da ise "Unloaded" yapılır
            boolean keep = false;
            for (PackagesListInBag pckg : packagesListInBag) {
                keep = packageList.stream().anyMatch(p -> p.getBarcode().equals(pckg.getPackageBarcode()));
                if (!keep) break;
            }
            if (keep) {
                Bag newBag = checkBagDeliveryPoint(optionalPackagesToBag.get().getBagBarcode(), route.getDeliveryPoint());
                if (newBag != null) {
                    newBag.setPackageStatus(PackageAndBagStatus.UNLOADED);
                    bagRepository.save(newBag);
                    packagesListInBag.forEach(pckg -> {
                        Package newPackageInBag = checkPackageDeliveryPoint(pckg.getPackageBarcode(), route.getDeliveryPoint(), true);
                        if (newPackageInBag != null) {
                            newPackageInBag.setPackageStatus(PackageAndBagStatus.UNLOADED);
                            packageRepository.save(newPackageInBag);
                            deliveriesDetail.setState(PackageAndBagStatus.UNLOADED);
                        } else {
                            //log tutulur
                            logShipmentService.save(new LogShipmentDto(pckg.getPackageBarcode(), route.getDeliveryPoint()));
                            mapToDto(route, pckg.getPackageBarcode());
                        }
                    });
                }
            }
        } else { //Bag'den bağımsız bir Package ise "Unloaded" işlemi burada yapılır
            Package newPackage = checkPackageDeliveryPoint(deliveriesDetail.getBarcode(), route.getDeliveryPoint(), false);
            if (newPackage != null) {
                newPackage.setPackageStatus(PackageAndBagStatus.UNLOADED);
                packageRepository.save(newPackage);
                deliveriesDetail.setState(PackageAndBagStatus.UNLOADED);
            } else {
                logShipmentService.save(new LogShipmentDto(deliveriesDetail.getBarcode(), route.getDeliveryPoint()));
                deliveriesDetail.setState(PackageAndBagStatus.LOADED);
            }
        }
    }

    private void BagUnload(ShipmentDetailDto route, DeliveriesDetailDto deliveriesDetail) {
        Bag newBag = checkBagDeliveryPoint(deliveriesDetail.getBarcode(), route.getDeliveryPoint());
        if (newBag != null) {
            newBag.setPackageStatus(PackageAndBagStatus.UNLOADED);
            bagRepository.save(newBag);
            deliveriesDetail.setState(PackageAndBagStatus.UNLOADED);

            List<PackagesToBagInfo> packagesToBagList = packagesToBagRepository.findByBagBarcode(deliveriesDetail.getBarcode(), PackagesToBagInfo.class);

            //Bag a bağlı Package işlemleri bu kısımda yapılıyor
            for (PackagesToBagInfo packagesToBag : packagesToBagList) {
                Package newPackage = checkPackageDeliveryPoint(packagesToBag.getPackageBarcode(), route.getDeliveryPoint(), true);
                if (newPackage != null) {
                    newPackage.setPackageStatus(PackageAndBagStatus.UNLOADED);
                    packageRepository.save(newPackage);
                   
                    mapToDto(route, newPackage.getPackageBarcode());
                }else {
                    logShipmentService.save(new LogShipmentDto(packagesToBag.getPackageBarcode(), route.getDeliveryPoint()));
                }
            }
        } else {
            mapToDto(route, deliveriesDetail.getBarcode());
        }
    }
    
    //Response stateleri güncelleniyor
    private void mapToDto(ShipmentDetailDto route, String barcode) {
        route.getDeliveries().stream()
                .filter(pckg -> pckg.getBarcode().equals(barcode))
                .findFirst()
                .ifPresent(pckg -> pckg.setState(PackageAndBagStatus.LOADED));
    }

    @Override
    public Result packageAndBagLoad(ShipmentsDto shipmentsDto) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByPlate(shipmentsDto.getPlate());
        if (!optionalVehicle.isPresent()) {
            return new ErrorResult(Messages.errorNoRecordFoundVehicle);
        }


        //Bag, Bag'e bağlı Packagelar ve bağımsız Packagelar "Loaded" state e çekiliyor
        for (ShipmentDetailDto route : shipmentsDto.getRoute()) {
            for (DeliveriesDetailDto deliveriesDetail : route.getDeliveries()) {
                //Eğer Bag ise
                if (deliveriesDetail.getBarcode().substring(0, 1).equals("C")) {
                    Optional<Bag> optionalBag = bagRepository.findByBagBarcode(deliveriesDetail.getBarcode());
                    if (!optionalBag.isPresent()) {
                        break;
                    }

                    Bag newBag = optionalBag.get();
                    newBag.setPackageStatus(PackageAndBagStatus.LOADED);
                    bagRepository.save(newBag);

                    deliveriesDetail.setState(PackageAndBagStatus.LOADED);


                    /*List<PackagesToBagInfo> packagesToBagList = packagesToBagRepository.findByBagBarcode(deliveriesDetail.getBarcode(), PackagesToBagInfo.class);

                    //Bag a bağlı package işlemleri bu kısımda yapılıyor
                    for (PackagesToBagInfo packagesToBag : packagesToBagList) {
                        Optional<Package> optionalPackage = packageRepository.findByPackageBarcode(packagesToBag.getPackageBarcode());
                        if (!optionalPackage.isPresent()) {
                            break;
                        }

                        Package newPackage = optionalPackage.get();
                        newPackage.setPackageStatus(PackageAndBagStatus.LOADED);
                        packageRepository.save(newPackage);

                        mapToDto(route, newPackage.getPackageBarcode());

                    }*/
                } else {
                    Optional<Package> optionalPackage = packageRepository.findByPackageBarcode(deliveriesDetail.getBarcode());
                    if (!optionalPackage.isPresent()) {
                        break;
                    }
                    Package newPackage = optionalPackage.get();

                    Optional<PackagesToBag> optionalPackagesToBag = packagesToBagRepository.findByPackageBarcode(newPackage.getPackageBarcode());

                    //Package'ın bağlı olduğu Bag'e ait diğer Package'ler aynı DeliveryPoint'e sahip ise ilgli Bag'i Loaded yap
                    if (optionalPackagesToBag.isPresent()) {
                        List<PackagesListInBag> packagesListInBag = packagesToBagRepository.findByBagBarcode(optionalPackagesToBag.get().getBagBarcode(), PackagesListInBag.class);
                        List<DeliveriesDetailDto> packageList = route.getDeliveries().stream().filter(pckg -> pckg.getBarcode().substring(0, 1).equals("P")).collect(Collectors.toList());
                        boolean b = false;
                        for (PackagesListInBag pckg : packagesListInBag) {
                            b = packageList.stream().anyMatch(p -> p.getBarcode().equals(pckg.getPackageBarcode()));
                            if (!b)
                                break;
                        }

                        if (b) {
                            Optional<Bag> optionalBag = bagRepository.findByBagBarcode(optionalPackagesToBag.get().getBagBarcode());
                            if (optionalBag.isPresent()) {
                                Bag newBag = optionalBag.get();
                                newBag.setPackageStatus(PackageAndBagStatus.LOADED);
                                bagRepository.save(newBag);
                            }
                        }

                    }


                    newPackage.setPackageStatus(PackageAndBagStatus.LOADED);
                    packageRepository.save(newPackage);

                    deliveriesDetail.setState(PackageAndBagStatus.LOADED);

                }
            }
        }
        return new SuccessDataResult<>(shipmentsDto, Messages.successListed);
    }


    //Bag doğru yere mi gidiyor kontrol et ve logla
    @Override
    public Bag checkBagDeliveryPoint(String barcode, Integer deliveryPoint) {
        Optional<Bag> optionalBag = bagRepository.findByBagBarcode(barcode);
        if (optionalBag.isEmpty()) return null;

        switch (optionalBag.get().getDeliveryPoint().getPointId()) {
            case 2:
            case 3:
                if(deliveryPoint != optionalBag.get().getDeliveryPoint().getPointId()) {
                    logShipmentService.save(new LogShipmentDto(barcode, deliveryPoint));
                    return null;
                }
                return optionalBag.get();
            default:
                logShipmentService.save(new LogShipmentDto(barcode, deliveryPoint));
                return null;
        }
    }


    //Package doğru yere mi gidiyor kontrol et ve logla
    @Override
    public Package checkPackageDeliveryPoint(String barcode, Integer deliveryPoint, Boolean packageInBag) {
        Optional<Package> optionalPackage = packageRepository.findByPackageBarcode(barcode);
        if (optionalPackage.isEmpty()) return null;

        switch (optionalPackage.get().getDeliveryPoint().getPointId()) {
            case 1:
                return packageInBag ? null : (deliveryPoint == optionalPackage.get().getDeliveryPoint().getPointId() ? optionalPackage.get() : null);
            case 2:
                return deliveryPoint == optionalPackage.get().getDeliveryPoint().getPointId() ? optionalPackage.get() : null;
            case 3:
                return packageInBag ? (deliveryPoint == optionalPackage.get().getDeliveryPoint().getPointId() ? optionalPackage.get() : null) : null;
            default:
                return null;
        }

    }

}
