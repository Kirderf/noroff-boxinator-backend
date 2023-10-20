package com.experis.no.boxinator.services.shipmenthistory;

import com.experis.no.boxinator.exceptions.ShipmentHistoryNotFoundException;
import com.experis.no.boxinator.models.ShipmentHistory;
import com.experis.no.boxinator.repositories.ShipmentHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShipmentHistoryServiceImpl implements ShipmentHistoryService {
    private final ShipmentHistoryRepository shipmentHistoryRepository;

    public ShipmentHistoryServiceImpl(ShipmentHistoryRepository shipmentHistoryRepository) {
        this.shipmentHistoryRepository = shipmentHistoryRepository;
    }

    @Override
    public ShipmentHistory findById(Integer integer) {
        return shipmentHistoryRepository.findById(integer).orElseThrow(() -> new ShipmentHistoryNotFoundException(integer));
    }

    @Override
    public Collection<ShipmentHistory> findAll() {
        return shipmentHistoryRepository.findAll();
    }

    @Override
    public ShipmentHistory add(ShipmentHistory entity) {
        return shipmentHistoryRepository.save(entity);
    }

    @Override
    public ShipmentHistory update(ShipmentHistory entity) {
        return shipmentHistoryRepository.save(entity);
    }

    @Override
    public void delete(Integer integer) {
        shipmentHistoryRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return shipmentHistoryRepository.existsById(integer);
    }

    @Override
    public Collection<ShipmentHistory> findAllByShipmentID(Integer id) {
        return shipmentHistoryRepository.getAllByShipment_Id(id);
    }

    @Override
    public ShipmentHistory updateShipmentHistory(ShipmentHistory shipmentHistory) {
        if (!shipmentHistoryRepository.existsShipmentHistoryByShipment_IdAndStatus(shipmentHistory.getShipment().getId(),shipmentHistory.getStatus())){
            return shipmentHistoryRepository.save(shipmentHistory);
        }
        return null;
    }


}
