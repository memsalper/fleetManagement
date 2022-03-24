package com.works.fleet_management.restController;


import com.works.fleet_management.core.utilities.paths.ApiPaths;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.request.PackageDto;
import com.works.fleet_management.model.request.PackagesToBagDto;
import com.works.fleet_management.services.PackagesToBagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = ApiPaths.PackagesToBagController.CONTROLLER)
@Api(value = "PackagesToBag assign Api documentation")
public class PackagesToBagRestController {
    private final PackagesToBagService packagesToBagService;

    public PackagesToBagRestController(PackagesToBagService packagesToBagService) {
        this.packagesToBagService = packagesToBagService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "New assign method")
    public Result save(@RequestBody PackagesToBagDto packagesToBagDto){
        return packagesToBagService.save(packagesToBagDto);
    }
}
