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
import java.util.HashSet;
import java.util.Set;


@Table(name = "packages",indexes = {@Index(name="idx_package_barcode", columnList="package_barcode", unique = true)})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Package extends BaseEntity implements Serializable {

    @Column(name = "package_barcode", length = 20, nullable = false, unique = true)
    private String packageBarcode;

    @Convert(converter = PackageAndBagStatusConverter.class)
    private PackageAndBagStatus packageStatus;

    @JoinColumn(name="delivery_point_id", nullable = false, referencedColumnName = "point_id")
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    private DeliveryPoint deliveryPoint;

    @Column(name = "volumetric_weight",columnDefinition = "numeric(15,2)", nullable = false)
    private Float volumetricWeight;


}
