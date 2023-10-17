package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class OrderProduct {
    @EmbeddedId
    private OrdersProductsId id = new OrdersProductsId();

    @ManyToOne
    @MapsId("ordersId")
    private Orders orders;

    @ManyToOne
    @MapsId("productsId")
    private Product product;

    private int quantity;

    public int getProductId() {
        return this.id.productsId;
    }

    @Embeddable
    @Getter
    @Setter
    public static class OrdersProductsId implements Serializable {

        private int ordersId;
        private int productsId;


        public OrdersProductsId() {

        }
    }
}
