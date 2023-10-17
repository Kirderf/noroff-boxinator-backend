package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class ShipmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
