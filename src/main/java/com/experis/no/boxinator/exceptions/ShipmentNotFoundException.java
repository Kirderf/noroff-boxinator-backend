package com.experis.no.boxinator.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ShipmentNotFoundException extends EntityNotFoundException {
    public ShipmentNotFoundException(int id) {
        super("Shipment was not found with ID" + id);
    }
}
