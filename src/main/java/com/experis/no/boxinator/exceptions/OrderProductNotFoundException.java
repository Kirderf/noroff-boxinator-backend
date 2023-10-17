package com.experis.no.boxinator.exceptions;

import com.experis.no.boxinator.models.OrderProduct;
import jakarta.persistence.EntityNotFoundException;

public class OrderProductNotFoundException extends EntityNotFoundException {
    public OrderProductNotFoundException(OrderProduct.OrdersProductsId id) {
        super("OrderProduct was not found with order ID " + id.getOrdersId() + "or product ID " + id.getProductsId());
    }
}
