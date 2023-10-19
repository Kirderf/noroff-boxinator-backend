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
    @Deprecated
    public ShipmentProduct findById(Integer integer) {
        return null;
    }

    @Override
    @Deprecated
    public Collection<ShipmentProduct> findAll() {
        return null;
    }

    @Override
    public ShipmentProduct add(ShipmentProduct entity) {
        return ordersProductRepository.save(entity);
    }

    @Override
    @Deprecated
    public ShipmentProduct update(ShipmentProduct entity) {
        return null;
    }

    @Override
    @Deprecated
    public void delete(Integer integer) {

    }

    @Override
    @Deprecated
    public boolean exists(Integer integer) {
        return false;
    }
}
