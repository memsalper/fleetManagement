package com.works.fleet_management.restController;

import com.works.fleet_management.core.utilities.paths.ApiPaths;
import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.request.BagDto;
import com.works.fleet_management.services.BagService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = ApiPaths.BagController.CONTROLLER)
public class BagRestController {

    private final BagService bagService;

    public BagRestController(BagService bagService) {
        this.bagService = bagService;
    }

    @PostMapping("/save")
    public Result save(@RequestBody BagDto bagDto){
        return bagService.save(bagDto);
    }
}
