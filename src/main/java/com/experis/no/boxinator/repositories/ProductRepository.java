package com.experis.no.boxinator.repositories;

import com.experis.no.boxinator.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Collection<Product> getProductsByisActiveIs(boolean isActive);
}
