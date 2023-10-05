package com.experis.no.boxinator.services.shipment;

import com.experis.no.boxinator.exceptions.ShipmentNotFoundException;
import com.experis.no.boxinator.models.Shipment;
import com.experis.no.boxinator.repositories.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShipmentServiceImpl implements ShipmentService{
    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment findById(Integer integer) {
        return shipmentRepository.findById(integer).orElseThrow(() -> new ShipmentNotFoundException(integer));
    }

    @Override
    public Collection<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment add(Shipment entity) {
        return shipmentRepository.save(entity);
    }

    @Override
    public Shipment update(Shipment entity) {
        return shipmentRepository.save(entity);
    }

    @Override
    public void delete(Integer integer) {
        shipmentRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return shipmentRepository.existsById(integer);
    }
}
