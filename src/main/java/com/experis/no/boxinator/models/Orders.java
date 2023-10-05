package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column( nullable = false)
    private String status; //TODO make enum

    //TODO add timestamp to remove if not made shipment after x amount of time
}
