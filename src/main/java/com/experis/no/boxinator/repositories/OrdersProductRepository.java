package com.experis.no.boxinator.repositories;

import com.experis.no.boxinator.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersProductRepository extends JpaRepository<OrderProduct,Integer> {
    Optional<OrderProduct> findById(OrderProduct.OrdersProductsId integer);
}
