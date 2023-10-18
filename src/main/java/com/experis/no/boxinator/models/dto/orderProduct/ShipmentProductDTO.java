package com.experis.no.boxinator.models.dto.orderProduct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentProductDTO {
    public ShipmentProductDTO(int productId, int quantity, int shipmentId) {
        this.shipmentId = shipmentId;
        this.productId = productId;
        this.quantity = quantity;
    }
    private int shipmentId;
    private int productId;
    private int quantity;
}
