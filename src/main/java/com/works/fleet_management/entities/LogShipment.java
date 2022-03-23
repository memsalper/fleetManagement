package com.works.fleet_management.entities;


import com.works.fleet_management.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "log_shipments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogShipment extends BaseEntity implements Serializable {
    @JoinColumn(name="delivery_point_id", nullable = false, referencedColumnName = "point_id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    private DeliveryPoint deliveryPoint;

    @Column(name = "barcode", length = 20, nullable = false)
    private String barcode;

}
