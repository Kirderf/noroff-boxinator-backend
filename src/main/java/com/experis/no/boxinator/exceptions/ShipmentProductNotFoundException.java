package com.experis.no.boxinator.exceptions;

import com.experis.no.boxinator.models.ShipmentProduct;
import jakarta.persistence.EntityNotFoundException;

public class ShipmentProductNotFoundException extends EntityNotFoundException {
    public ShipmentProductNotFoundException(ShipmentProduct.ShipmentProductsId id) {
        super("OrderProduct was not found with shipment ID " + id.getShipmentId() + "or product ID " + id.getProductsId());
    }
}
