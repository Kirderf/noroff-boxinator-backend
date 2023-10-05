package com.experis.no.boxinator.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class OrdersNotFoundException extends EntityNotFoundException {
    public OrdersNotFoundException(int id) {
        super("Order was not found with ID" + id);
    }
}
