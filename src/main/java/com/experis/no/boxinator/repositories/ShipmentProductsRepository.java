package com.experis.no.boxinator.repositories;

import com.experis.no.boxinator.models.ShipmentProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentProductsRepository extends JpaRepository<ShipmentProduct, Integer> {
    Optional<ShipmentProduct> findById(ShipmentProduct.ShipmentProductsId integer);
}
