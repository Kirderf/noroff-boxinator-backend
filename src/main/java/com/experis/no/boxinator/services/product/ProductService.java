package com.experis.no.boxinator.services.product;

import com.experis.no.boxinator.models.Product;
import com.experis.no.boxinator.services.CrudService;

import java.util.Collection;

public interface ProductService extends CrudService<Product,Integer> {
    Collection<Product> getAllActive();
}
