package com.works.fleet_management.repositories;

import com.works.fleet_management.entities.DeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, UUID> {
    Optional<DeliveryPoint> findByPointId(Integer pointId);
    DeliveryPoint findByPointIdIs(Integer pointId);
    <T> List<T> findAllBy(Class<T> clz);
}