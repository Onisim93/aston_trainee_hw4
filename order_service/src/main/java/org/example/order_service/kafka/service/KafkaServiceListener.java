package org.example.order_service.kafka.service;

import com.example.OrderData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.order_service.dto.OrderDto;
import org.example.order_service.facade.OrderFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceListener {

    private final OrderFacade orderFacade;

    @KafkaListener(topics = "paymentResponse", groupId = "orders")
    public void listenPayment(ConsumerRecord<String, OrderData> orderData) {
        OrderData data = orderData.value();
        log.info("received record in orderS from paymentS: {}", data);
        OrderDto orderDto = new OrderDto(data);
        orderFacade.update(orderDto);
        log.info("Order updated by payment service, order: {}", orderDto);
    }

    @KafkaListener(topics = "restaurantResponse", groupId = "orders")
    public void listenRestaurant(ConsumerRecord<String, OrderData> orderData) {
        log.info("received record in orderS from restaurantS: {}", orderData);

        OrderData data = orderData.value();
        OrderDto orderDto = new OrderDto(data);
        orderFacade.update(orderDto);
        log.info("Order updated by restaurant service, order: {}", orderDto);

    }
}
