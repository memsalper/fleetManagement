package com.works.fleet_management.restController;

import com.works.fleet_management.core.utilities.paths.ApiPaths;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.request.BagDto;
import com.works.fleet_management.services.BagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = ApiPaths.BagController.CONTROLLER)
@Api(value = "Bag Api documentation")
public class BagRestController {

    private final BagService bagService;

    public BagRestController(BagService bagService) {
        this.bagService = bagService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "New Bag adding method")
    public Result save(@RequestBody BagDto bagDto){
        return bagService.save(bagDto);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Bag listing method")
    public Result getAll(){
        return bagService.getAll();
    }
}
