package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(length = 50, nullable = false)
    private String description;

    @Column(length = 50, nullable = false)
    private int stock;

    @Column(length = 10, nullable = false)
    private float price;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int depth;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private int weight;


}
