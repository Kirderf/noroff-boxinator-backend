package com.experis.no.boxinator.services.shipment;

import com.experis.no.boxinator.models.Shipment;
import com.experis.no.boxinator.services.CrudService;

import java.util.Collection;

public interface ShipmentService extends CrudService<Shipment, Integer> {
    Collection<Shipment> findByEmail(String email);

    Collection<Shipment> findByUserID(String userID);
}
