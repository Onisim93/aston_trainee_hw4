package org.example.order_service.service;

import org.example.order_service.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto create(OrderDto orderDto);
    OrderDto update(OrderDto orderDto);
    OrderDto getById(Long id);
    List<OrderDto> getAll();
}
