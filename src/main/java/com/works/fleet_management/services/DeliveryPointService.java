package com.works.fleet_management.services;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.results.DataResult;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.entities.DeliveryPoint;
import com.works.fleet_management.model.abstarcts.projections.DeliveryPointInfo;
import com.works.fleet_management.model.request.DeliveryPointDto;
import com.works.fleet_management.repositories.DeliveryPointRepository;
import com.works.fleet_management.services.abstracts.IDeliveryPointService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPointService implements IDeliveryPointService {
    private  final DeliveryPointRepository deliveryPointRepository;

    public DeliveryPointService(DeliveryPointRepository deliveryPointRepository) {
        this.deliveryPointRepository = deliveryPointRepository;
    }

    @Override
    public DataResult<List<DeliveryPointInfo>> getAll() {
        return null;
    }

    @Override
    public Result save(DeliveryPointDto deliveryPointDto) {
        DeliveryPoint deliveryPoint= new DeliveryPoint();
        deliveryPoint.setPointName(deliveryPointDto.getPointName());
        deliveryPointRepository.saveAndFlush(deliveryPoint);
        return new SuccessDataResult<>( deliveryPointDto, Messages.successSaved);
    }
}
