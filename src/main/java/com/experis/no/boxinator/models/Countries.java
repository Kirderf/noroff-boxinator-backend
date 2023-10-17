package com.experis.no.boxinator.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Countries {
    @Id
    @Column(nullable = false)
    private String shortName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private int shippingCost;
}
