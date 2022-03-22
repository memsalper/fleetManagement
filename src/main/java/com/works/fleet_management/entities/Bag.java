package com.works.fleet_management.entities;

import com.works.fleet_management.entities.base.BaseEntity;
import com.works.fleet_management.entities.enums.PackageAndBagStatus;
import com.works.fleet_management.entities.enums.converter.PackageAndBagStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "bags",indexes = {@Index(name="idx_bag_barcode", columnList="bag_barcode", unique = true)})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bag extends BaseEntity implements Serializable {
    @Column(name = "bag_barcode", length = 20, nullable = false, unique = true)
    private String bagBarcode;


    @Column(name ="state", columnDefinition = "SMALLINT", nullable = false)
    @Convert(converter = PackageAndBagStatusConverter.class)
    private PackageAndBagStatus packageStatus;

    @JoinColumn(name="delivery_point_id", nullable = false, referencedColumnName = "point_id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    private DeliveryPoint deliveryPoint;



}
