package com.experis.no.boxinator.services.orders;

import com.experis.no.boxinator.exceptions.OrdersNotFoundException;
import com.experis.no.boxinator.models.Orders;
import com.experis.no.boxinator.repositories.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders findById(Integer integer) {
        return ordersRepository.findById(integer).orElseThrow(() -> new OrdersNotFoundException(integer));
    }

    @Override
    public Collection<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Collection<Orders> findAllByUserId(String userId) {
        return ordersRepository.getOrderByUserId(userId);
    }

    @Override
    public Orders add(Orders entity) {
        return ordersRepository.save(entity);
    }

    @Override
    public Orders update(Orders entity) {
        return ordersRepository.save(entity);
    }

    @Override
    public void delete(Integer integer) {
        ordersRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return ordersRepository.existsById(integer);
    }
}
