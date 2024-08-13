package org.example.order_service.kafka.service;

import com.example.OrderData;
import com.example.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order_service.dto.OrderDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaServiceProducer {

    private final KafkaTemplate<String, OrderData> kafkaTemplate;

    public void send(String topic, OrderDto orderDto) {
        kafkaTemplate.send(topic, convertToData(orderDto));
    }

    private OrderData convertToData(OrderDto orderDto) {
        OrderData orderData = new OrderData();
        if (orderDto.getId() != null) {
            orderData.setId(orderDto.getId());
        }
        if (orderDto.getUserId() != null) {
            orderData.setUserId(orderDto.getUserId());
        }
        if (orderDto.getRestaurantId() != null) {
            orderData.setRestaurantId(orderDto.getRestaurantId());
        }
        if (orderDto.getStatus() != null) {
            orderData.setStatus(orderDto.getStatus());
        }
        if (orderDto.getTotalSum() != null) {
            orderData.setTotalSum(orderDto.getTotalSum());
        }
        if (orderDto.getMeals() != null && !orderDto.getMeals().isEmpty()) {
            orderData.setMeals(orderDto.getMeals().stream().map(m -> new OrderItem(m.getMealId(), m.getQuantity())).toList());
        }

        return orderData;
    }
}
