package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "countries_name")
    private Countries countries;
    @Column(nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String destination;
    @Column(length = 50, nullable = false)
    private String billingAddress;
    @Column(nullable = false)
    private int status;
    @Column(nullable = false)
    private int postalCode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private long phoneNumber;
    @Column()
    private String deliveryInstruction;
    @Column(nullable = false)
    private Boolean gift;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    @OneToMany(mappedBy = "shipment")
    private Set<ShipmentProduct> ordersProducts = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

