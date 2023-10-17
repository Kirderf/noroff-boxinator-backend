package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class ShipmentProduct {
    @EmbeddedId
    private ShipmentProductsId id = new ShipmentProductsId();

    @ManyToOne
    @MapsId("shipmentId")
    private Shipment shipment;

    @ManyToOne
    @MapsId("productsId")
    private Product product;

    private int quantity;

    @Embeddable
    @Getter
    @Setter
    public static class ShipmentProductsId implements Serializable {

        private int shipmentId;
        private int productsId;


        public ShipmentProductsId() {

        }
    }
}
