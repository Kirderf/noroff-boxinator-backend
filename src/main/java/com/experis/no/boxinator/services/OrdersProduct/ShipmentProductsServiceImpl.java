package com.experis.no.boxinator.services.OrdersProduct;

import com.experis.no.boxinator.exceptions.ShipmentProductNotFoundException;
import com.experis.no.boxinator.models.ShipmentProduct;
import com.experis.no.boxinator.repositories.ShipmentProductsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShipmentProductsServiceImpl implements ShipmentProductsService {
    private final ShipmentProductsRepository ordersProductRepository;

    public ShipmentProductsServiceImpl(ShipmentProductsRepository ordersProductRepository) {
        this.ordersProductRepository = ordersProductRepository;
    }

    public ShipmentProduct findById(ShipmentProduct.ShipmentProductsId integer) {
        return ordersProductRepository.findById(integer).orElseThrow(() -> new ShipmentProductNotFoundException(integer));
    }

    @Override
    public ShipmentProduct findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<ShipmentProduct> findAll() {
        return null;
    }

    @Override
    public ShipmentProduct add(ShipmentProduct entity) {
        return null;
    }

    @Override
    public ShipmentProduct update(ShipmentProduct entity) {
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
