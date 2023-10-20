package com.experis.no.boxinator.services.shipment;

import com.experis.no.boxinator.exceptions.ShipmentNotFoundException;
import com.experis.no.boxinator.models.Shipment;
import com.experis.no.boxinator.models.ShipmentHistory;
import com.experis.no.boxinator.repositories.ShipmentRepository;
import com.experis.no.boxinator.services.shipmenthistory.ShipmentHistoryService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final ShipmentHistoryService shipmentHistoryService;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, ShipmentHistoryService shipmentHistoryService) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentHistoryService = shipmentHistoryService;
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
        Shipment shipmentInDB = findById(entity.getId());
        if (shipmentInDB.getStatus() != entity.getStatus()) {
            ShipmentHistory history = new ShipmentHistory();
            history.setShipment(shipmentInDB);
            history.setStatus(shipmentInDB.getStatus());
            history.setTimestamp(shipmentInDB.getTimestamp());
            shipmentHistoryService.updateShipmentHistory(history);
        }
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

    @Override
    public Collection<Shipment> findByEmailAndGuest(String email) {
        return shipmentRepository.findShipmentsByEmailIgnoreCaseAndUserNull(email);
    }

    @Override
    public Collection<Shipment> findByEmail(String email) {
        return shipmentRepository.findShipmentsByEmailIgnoreCase(email);
    }

    @Override
    public Collection<Shipment> findByUserID(String userID) {
        return shipmentRepository.findShipmentsByUserId(userID);
    }
}
