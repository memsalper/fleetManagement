package com.works.fleet_management.repositories;

import com.works.fleet_management.entities.PackagesToBag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PackagesToBagRepository extends JpaRepository<PackagesToBag, UUID> {
    <T> List<T> findByBagBarcode(String bagBarcode,Class<T> clz);
    Optional<PackagesToBag> findByPackageBarcode(String packageBarcode);


}