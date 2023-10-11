package com.experis.no.boxinator.services.shipmenthistory;

import com.experis.no.boxinator.models.ShipmentHistory;
import com.experis.no.boxinator.services.CrudService;

import java.util.Collection;

public interface ShipmentHistoryService extends CrudService<ShipmentHistory,Integer> {
    Collection<ShipmentHistory> findAllByShipmentID(Integer id);
}
