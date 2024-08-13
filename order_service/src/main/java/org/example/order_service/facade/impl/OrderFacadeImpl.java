package org.example.order_service.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order_service.dto.OrderDto;
import org.example.order_service.facade.OrderFacade;
import org.example.order_service.gRPC.NotificationService;
import org.example.order_service.gRPC.data.GrpcNotificationData;
import org.example.order_service.kafka.service.KafkaServiceProducer;
import org.example.order_service.model.OrderStatus;
import org.example.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final KafkaServiceProducer kafkaServiceProducer;
    private final NotificationService notificationService;


    @Override
    public OrderDto create(OrderDto orderDto)  {
        OrderDto created = orderService.create(orderDto);

        kafkaServiceProducer.send("paymentRequest", created);

        GrpcNotificationData notificationData = createNotification(orderDto);

        notificationService.sendNotification(notificationData);
        notificationService.sendNotification(notificationData);

        return created;
    }

    @Override
    public OrderDto update(OrderDto orderDto)  {
        OrderDto updated = orderService.update(orderDto);

        if (OrderStatus.valueOf(updated.getStatus()) == OrderStatus.PAID) {
            kafkaServiceProducer.send("restaurantRequest", orderDto);
        }
        else if(OrderStatus.valueOf(updated.getStatus()) == OrderStatus.CANCELLING) {
            kafkaServiceProducer.send("paymentRequest", orderDto);
        }

        return updated;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderService.getById(id);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    private GrpcNotificationData createNotification(OrderDto orderDto) {
        String message = String.format("User with id: %d created an order with id: %d", orderDto.getUserId(), orderDto.getId());

        return GrpcNotificationData.builder()
                .message(message)
                .idempotencyKey(UUID.randomUUID().toString())
                .build();
    }
}
