package com.works.fleet_management.repositories;

import com.works.fleet_management.entities.Bag;
import com.works.fleet_management.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BagRepository extends JpaRepository<Bag, UUID> {
    Optional<Bag> findByBagBarcode(String bagBarcode);
}