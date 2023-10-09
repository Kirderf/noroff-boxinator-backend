package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "countries_name")
    private Countries countries;

    @Column(length = 50, nullable = false)
    private String destination;
    @Column(length = 50, nullable = false)
    private String billingAddress;
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
}

