package com.works.fleet_management.services;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.Vehicle;
import com.works.fleet_management.model.request.VehicleDto;
import com.works.fleet_management.repositories.VehicleRepository;
import com.works.fleet_management.services.abstracts.IVehicleService;
import org.springframework.stereotype.Service;


@Service
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Result save(VehicleDto vehicleDto) {
        Vehicle vehicle= new Vehicle();
        vehicle.setPlate(vehicleDto.getPlate());
        vehicleRepository.save(vehicle);
        return new SuccessDataResult<>( vehicleDto, Messages.successSaved);
    }
}
