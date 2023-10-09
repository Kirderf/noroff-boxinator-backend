package com.experis.no.boxinator.services.OrdersProduct;

import com.experis.no.boxinator.exceptions.OrderProductNotFoundException;
import com.experis.no.boxinator.models.OrderProduct;
import com.experis.no.boxinator.repositories.OrdersProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersProductService {
    private final OrdersProductRepository ordersProductRepository;

    public OrdersProductService(OrdersProductRepository ordersProductRepository) {
        this.ordersProductRepository = ordersProductRepository;
    }

    public OrderProduct findById(OrderProduct.OrdersProductsId integer) {
        return ordersProductRepository.findById(integer).orElseThrow(() -> new OrderProductNotFoundException(integer));
    }
}
