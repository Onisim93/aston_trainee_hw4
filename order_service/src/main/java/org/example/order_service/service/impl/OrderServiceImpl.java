package org.example.order_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.order_service.dto.OrderDto;
import org.example.order_service.exception.EntityNotFoundException;
import org.example.order_service.exception.ExceptionMessageHelper;
import org.example.order_service.model.OrderEntity;
import org.example.order_service.model.OrderStatus;
import org.example.order_service.repository.OrderRepository;
import org.example.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.order_service.service.mapper.OrderMapper.INSTANCE;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Transactional
    @Override
    public OrderDto create(OrderDto orderDto) {
        return INSTANCE.toDto(repository.save(INSTANCE.toEntity(orderDto)));
    }

    @Transactional
    @Override
    public OrderDto update(OrderDto orderDto) {
        OrderEntity entity = repository.findById(orderDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageHelper.getNotFoundMessage(orderDto.getId())));
        entity.setStatus(OrderStatus.valueOf(orderDto.getStatus()));
        return INSTANCE.toDto(entity);
    }

    @Override
    public OrderDto getById(Long id) {
        return INSTANCE.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageHelper.getNotFoundMessage(id))));
    }

    @Override
    public List<OrderDto> getAll() {
        return INSTANCE.toDto(repository.findAll());
    }
}
