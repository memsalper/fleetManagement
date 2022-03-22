package com.works.fleet_management.entities;

import com.works.fleet_management.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "vehicles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseEntity{

    @Column(name = "plate", length = 11, nullable = false, unique = true)
    private String plate;

}
