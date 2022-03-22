package com.works.fleet_management.repositories;

import com.works.fleet_management.entities.DeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, UUID> {
    Optional<DeliveryPoint> findByPointId(Long pointId);
}