package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Entity
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column() //TODO @Formula("(SELECT price FROM product where o.product_id = id)")
    @Formula("(SELECT price FROM product as p where p.id = id) * 1")
    private float price;
    @Column( nullable = false)
    private float quantity;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
}
