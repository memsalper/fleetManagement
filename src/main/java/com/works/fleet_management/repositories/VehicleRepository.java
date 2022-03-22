package com.works.fleet_management.repositories;

import com.works.fleet_management.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByPlate(String plate);
}