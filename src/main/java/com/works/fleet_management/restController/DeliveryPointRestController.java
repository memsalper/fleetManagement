package com.works.fleet_management.restController;


import com.works.fleet_management.core.utilities.paths.ApiPaths;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.request.DeliveryPointDto;
import com.works.fleet_management.services.DeliveryPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = ApiPaths.DeliveryPointController.CONTROLLER)
@Api(value = "Delivery Point Api documentation")
public class DeliveryPointRestController {
    private final DeliveryPointService deliveryPointService;

    public DeliveryPointRestController(DeliveryPointService deliveryPointService) {
        this.deliveryPointService = deliveryPointService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "New Delivery Point adding method")
    public Result save(@RequestBody DeliveryPointDto deliveryPointDto){
        return deliveryPointService.save(deliveryPointDto);
    }
}
