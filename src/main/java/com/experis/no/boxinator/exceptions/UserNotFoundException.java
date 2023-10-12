package com.experis.no.boxinator.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String id) {
        super("User was not found with ID" + id);
    }
}
