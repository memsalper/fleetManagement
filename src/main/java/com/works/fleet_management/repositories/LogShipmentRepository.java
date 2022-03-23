package com.works.fleet_management.repositories;

import com.works.fleet_management.entities.LogShipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogShipmentRepository extends JpaRepository<LogShipment, UUID> {
}