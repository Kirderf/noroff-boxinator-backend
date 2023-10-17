package com.experis.no.boxinator.repositories;

import com.experis.no.boxinator.models.ShipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ShipmentHistoryRepository extends JpaRepository<ShipmentHistory, Integer> {
    Collection<ShipmentHistory> getAllByShipment_Id(int shipment_id);
}
