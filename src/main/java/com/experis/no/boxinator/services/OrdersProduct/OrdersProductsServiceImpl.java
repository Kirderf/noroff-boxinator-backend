package com.experis.no.boxinator.services.OrdersProduct;

import com.experis.no.boxinator.exceptions.OrderProductNotFoundException;
import com.experis.no.boxinator.models.OrderProduct;
import com.experis.no.boxinator.repositories.OrdersProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrdersProductsServiceImpl implements OrdersProductsService{
    private final OrdersProductRepository ordersProductRepository;

    public OrdersProductsServiceImpl(OrdersProductRepository ordersProductRepository) {
        this.ordersProductRepository = ordersProductRepository;
    }

    public OrderProduct findById(OrderProduct.OrdersProductsId integer) {
        return ordersProductRepository.findById(integer).orElseThrow(() -> new OrderProductNotFoundException(integer));
    }

    @Override
    public OrderProduct findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<OrderProduct> findAll() {
        return null;
    }

    @Override
    public OrderProduct add(OrderProduct entity) {
        return null;
    }

    @Override
    public OrderProduct update(OrderProduct entity) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }
}
