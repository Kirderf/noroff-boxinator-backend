package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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

    @Column(length = 50)
    private String deliveryInstruction;
    @Column(nullable = false)
    private Boolean gift;
}

