package com.experis.no.boxinator.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ShipmentHistoryNotFoundException extends EntityNotFoundException {
    public ShipmentHistoryNotFoundException(int id) {
        super("ShipmentHistory was not found with ID" + id);
    }
}
