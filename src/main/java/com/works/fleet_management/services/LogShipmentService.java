package com.works.fleet_management.services;


import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.DeliveryPoint;
import com.works.fleet_management.entities.LogShipment;
import com.works.fleet_management.model.abstarcts.projections.BagsInfo;
import com.works.fleet_management.model.abstarcts.projections.LogShipmentInfo;
import com.works.fleet_management.model.request.LogShipmentDto;
import com.works.fleet_management.repositories.DeliveryPointRepository;
import com.works.fleet_management.repositories.LogShipmentRepository;
import com.works.fleet_management.services.abstracts.ILogShipmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogShipmentService implements ILogShipmentService {
    private final LogShipmentRepository logShipmentRepository;
    private final DeliveryPointRepository deliveryPointRepository;

    public LogShipmentService(LogShipmentRepository logShipmentRepository, DeliveryPointRepository deliveryPointRepository) {
        this.logShipmentRepository = logShipmentRepository;
        this.deliveryPointRepository = deliveryPointRepository;
    }

    @Override
    public DataResult<List<LogShipmentInfo>> getAll() {
        return new SuccessDataResult<>(logShipmentRepository.findAllBy(LogShipmentInfo.class),Messages.successListed);
    }

    @Override
    public Result save(LogShipmentDto logShipmentDto) {
        LogShipment logShipment=new LogShipment();
        logShipment.setBarcode(logShipmentDto.getBarcode());
        DeliveryPoint deliveryPoint=deliveryPointRepository.findByPointIdIs(logShipmentDto.getDeliveryPointPointId());
        logShipment.setDeliveryPoint(deliveryPoint);
        logShipmentRepository.save(logShipment);
        return new SuccessDataResult<>(logShipmentDto, Messages.successSaved);
    }
}
