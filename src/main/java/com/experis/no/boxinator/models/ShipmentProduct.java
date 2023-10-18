package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
public class ShipmentProduct {
    @EmbeddedId
    private ShipmentProductsId id = new ShipmentProductsId();

    @ManyToOne
    @MapsId("shipmentsId")
    private Shipment shipment;

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
    public static class ShipmentProductsId implements Serializable {

        private int shipmentsId;
        private int productsId;


        public ShipmentProductsId() {

        }
    }
}
