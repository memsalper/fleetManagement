package com.works.fleet_management.restController;

import com.works.fleet_management.core.utilities.conctants.Messages;
import com.works.fleet_management.core.utilities.paths.ApiPaths;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.core.utilities.results.SuccessDataResult;
import com.works.fleet_management.model.request.shipmentRequest.ShipmentsDto;
import com.works.fleet_management.model.request.VehicleDto;
import com.works.fleet_management.services.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = ApiPaths.VehicleController.CONTROLLER)
@Api(value = "Vehicle Api documentation")
public class VehicleRestController {
    private final VehicleService vehicleService;

    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "New Vehicle adding method")
    public Result save(@RequestBody VehicleDto vehicleDto){
        return vehicleService.save(vehicleDto);
    }

    @PostMapping("/packageAndBagUnload")
    @ApiOperation(value = "Package and Bag unload method")
    public Result packageAndBagUnload(@RequestBody ShipmentsDto shipmentsDto){
        return vehicleService.packageAndBagUnload(shipmentsDto);
    }

    @PostMapping("/packageAndBagLoad")
    @ApiOperation(value = "Package and Bag load method")
    public Result packageAndBagLoad(@RequestBody ShipmentsDto shipmentsDto){
        return vehicleService.packageAndBagLoad(shipmentsDto);
    }
}
