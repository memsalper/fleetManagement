package com.works.fleet_management.restController;

import com.works.fleet_management.core.utilities.paths.ApiPaths;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.request.BagDto;
import com.works.fleet_management.model.request.PackageDto;
import com.works.fleet_management.services.PackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = ApiPaths.PackageController.CONTROLLER)
@Api(value = "Package Api documentation")
public class PackageRestController {
    private final PackageService packageService;

    public PackageRestController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "New Package adding method")
    public Result save(@RequestBody PackageDto packageDto){
        return packageService.save(packageDto);
    }
}
