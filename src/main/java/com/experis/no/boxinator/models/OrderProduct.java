package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
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
    public int getOrderId() {
        return this.id.ordersId;
    }
    @Embeddable
    @Getter
    @Setter
    public static class OrdersProductsId implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        private int ordersId;
        private int productsId;



        public OrdersProductsId(int ordersId, int productsId) {
            super();
            this.ordersId = ordersId;
            this.productsId = productsId;
        }

        public OrdersProductsId() {

        }
    }
}
