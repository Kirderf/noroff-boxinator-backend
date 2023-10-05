package com.experis.no.boxinator.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(int id) {
        super("Product was not found with ID" + id);
    }
}
