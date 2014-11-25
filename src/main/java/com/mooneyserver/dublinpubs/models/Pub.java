package com.mooneyserver.dublinpubs.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamedQueries({
      @NamedQuery(name = PersistenceConstants.QUERY_FIND_ALL_PUBS, query = "SELECT p FROM Pub p")
})
@Entity
@Table(name = PersistenceConstants.TABLE_PUBS)
@Data
public class Pub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Max(value = 90, message = "Valid latitudes must be below 90 degrees")
    @Min(value = 0, message = "Valid latitudes must be above 0 degrees")
    @Column(precision = 8, scale = 5)
    private BigDecimal latitude;

    @NotNull
    @Max(value = 180, message = "Valid longitudes must be below 180 degrees")
    @Min(value = -180, message = "Valid longitudes must be above -180 degrees")
    @Column(precision = 8, scale = 5)
    private BigDecimal longitude;

    private String review;
}