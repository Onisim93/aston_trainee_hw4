package org.example.payment_service.kafka.service;

import com.example.OrderData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.payment_service.dto.UserDto;
import org.example.payment_service.kafka.data.OrderStatus;
import org.example.payment_service.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaServiceListener {
    private final KafkaServiceProducer kafkaServiceProducer;
    private final UserService userService;

    @KafkaListener(topics = {"paymentRequest"}, groupId = "payments")
    public void listen(ConsumerRecord<String, OrderData> orderData) {

        OrderData order = orderData.value();
        log.info("Received order in payment: {}", order);

        if (OrderStatus.valueOf(order.getStatus().toString()) == OrderStatus.PENDING) {


            UserDto userDto = userService.findById(order.getUserId());
            if (checkBalance(userDto.getBalance(), order.getTotalSum())) {
                userDto.setBalance(userDto.getBalance() - order.getTotalSum());
                userService.update(userDto);
                order.setStatus("PAID");
            }
            else {
                order.setStatus("CANCELLED");
            }

            kafkaServiceProducer.send("paymentResponse", order);
        }
        else if (OrderStatus.valueOf(order.getStatus().toString()) == OrderStatus.CANCELLING) {
            UserDto userDto = userService.findById(order.getUserId());
            userDto.setBalance(userDto.getBalance() + order.getTotalSum());
            userService.update(userDto);
            order.setStatus("CANCELLED");

            kafkaServiceProducer.send("paymentResponse", order);
        }
    }

    private boolean checkBalance(Double balance, Double totalSum) {
        return balance >= totalSum;
    }
}

