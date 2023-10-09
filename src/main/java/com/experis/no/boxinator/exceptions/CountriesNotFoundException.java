package com.experis.no.boxinator.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CountriesNotFoundException extends EntityNotFoundException{
    public CountriesNotFoundException(String id){
        super("Country was not found with ID" + id);
    }
}
