package com.works.fleet_management.core.utilities.paths;


public class ApiPaths {

    private static final String BASE_PATH = "/api";

    public static final class VehicleController{
        public static final String CONTROLLER = BASE_PATH+"/vehicle";
    }

    public static final class DeliveryPointController{
        public static final String CONTROLLER = BASE_PATH+"/deliveryPoint";
    }

    public static final class BagController{
        public static final String CONTROLLER = BASE_PATH+"/bag";
    }

    public static final class PackageController{
        public static final String CONTROLLER = BASE_PATH+"/package";
    }

    public static final class PackagesToBagController{
        public static final String CONTROLLER = BASE_PATH+"/packagesToBag";
    }

}
