package org.example.restaurant_service.kafka.service;

import com.example.OrderData;
import com.example.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.restaurant_service.dto.MealDto;
import org.example.restaurant_service.exception.NotEnoughItemsException;
import org.example.restaurant_service.kafka.data.OrderStatus;
import org.example.restaurant_service.service.MealService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.example.restaurant_service.kafka.data.OrderStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaServiceListener {

    private final MealService mealService;
    private final KafkaServiceProducer kafkaServiceProducer;

    @KafkaListener(topics = "restaurantRequest", groupId = "restaurants")
    public void listen(ConsumerRecord<String, OrderData> orderData) {

        OrderData order = orderData.value();
        log.info("Received order to restaurant. order: {}", order);
        try {
            if (OrderStatus.valueOf(order.getStatus().toString()) == OrderStatus.PAID) {
                for (OrderItem item : order.getMeals()) {
                    MealDto mealDto = mealService.getById(item.getMealId());
                    checkBalance(mealDto.getQuantity(), item.getQuantity());
                }

                for (OrderItem item : order.getMeals()) {
                    MealDto mealDto = mealService.getById(item.getMealId());
                    mealDto.setQuantity(mealDto.getQuantity() - item.getQuantity());
                    mealService.update(mealDto);
                }
                order.setStatus(APPROVED.name());
            }
        }
        catch (NotEnoughItemsException e) {
            log.info(e.getMessage());
            order.setStatus(CANCELLING.name());
        }

        kafkaServiceProducer.send("restaurantResponse", order);
    }

    private void checkBalance(Integer balance, Integer quantity) {
        if (balance < quantity) {
            String msg = "items balance less than " + quantity;
            throw new NotEnoughItemsException(msg);
        }
    }
}
