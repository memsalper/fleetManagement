package com.works.fleet_management.repositories;

import com.works.fleet_management.entities.DeliveryPoint;
import com.works.fleet_management.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {
    Optional<Package> findByPackageBarcode(String packageBarcode);
    <T> List<T> findAllBy(Class<T> clz);
}