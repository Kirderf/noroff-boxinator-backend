package com.experis.no.boxinator.services.orders;

import com.experis.no.boxinator.models.Orders;
import com.experis.no.boxinator.services.CrudService;

import java.util.Collection;

public interface OrdersService extends CrudService<Orders, Integer> {
    Collection<Orders> findAllByUserId(String userId);
}
