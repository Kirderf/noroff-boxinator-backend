package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean confirmed;

    @Column(name="products_id")
    @OneToMany(mappedBy = "orders")
    private Set<Products> products;
}
