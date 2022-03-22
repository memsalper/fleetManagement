package com.works.fleet_management.entities;

import com.works.fleet_management.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Table(name = "delivery_points")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPoint extends BaseEntity implements Serializable {

    @Column(name = "point_name", length = 100, nullable = false)
    private String pointName;

    //@Column(name = "point_id",columnDefinition = "serial")
    @Generated(GenerationTime.INSERT)
    @Column(name = "point_id", insertable = false ,columnDefinition = "serial")
    private Long pointId;

    @OneToMany(mappedBy = "deliveryPoint")
    private Set<Bag> bags;

    @OneToMany(mappedBy = "deliveryPoint")
    private Set<Package> packages;
}
