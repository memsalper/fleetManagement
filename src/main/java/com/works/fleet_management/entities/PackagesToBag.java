package com.works.fleet_management.entities;

import com.works.fleet_management.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "packages_to_bag")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackagesToBag extends BaseEntity implements Serializable {


    @Column(name = "package_barcode", length = 20, nullable = false)
    private String packageBarcode;

    @Column(name = "bag_barcode", length = 20, nullable = false)
    private String bagBarcode;
}
