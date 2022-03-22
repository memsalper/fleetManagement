package com.works.fleet_management.restController;


import com.works.fleet_management.core.utilities.paths.ApiPaths;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.request.DeliveryPointDto;
import com.works.fleet_management.services.DeliveryPointService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = ApiPaths.DeliveryPointController.CONTROLLER)
public class DeliveryPointRestController {
    private final DeliveryPointService deliveryPointService;

    public DeliveryPointRestController(DeliveryPointService deliveryPointService) {
        this.deliveryPointService = deliveryPointService;
    }

    @PostMapping("/save")
    public Result save(@RequestBody DeliveryPointDto deliveryPointDto){
        return deliveryPointService.save(deliveryPointDto);
    }
}
