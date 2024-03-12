package by.bulaukin.orderservice.service;

import by.bulaukin.orderservice.model.OrdersEvent;

import java.util.Optional;

public interface OrdersService {

    OrdersEvent save(OrdersEvent event);
    Optional<OrdersEvent> findByProduct(String product);
    Optional<OrdersEvent> findById(Long id);

}
