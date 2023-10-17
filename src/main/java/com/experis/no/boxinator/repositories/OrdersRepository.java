package com.experis.no.boxinator.repositories;

import com.experis.no.boxinator.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Collection<Orders> getOrderByUserId(String userId);
}
