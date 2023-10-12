package com.experis.no.boxinator.repositories;

import com.experis.no.boxinator.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {

    Collection<Shipment> findShipmentsByEmailIgnoreCase(String email);
    @Query("SELECT s FROM Shipment s WHERE s.order.user.id = :userId")
    Collection<Shipment> findShipmentsByUserId(@Param("userId") String userId);
}
