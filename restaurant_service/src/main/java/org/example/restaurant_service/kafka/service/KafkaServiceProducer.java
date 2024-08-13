package org.example.restaurant_service.kafka.service;

import com.example.OrderData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceProducer {

    private final KafkaTemplate<String, OrderData> kafkaTemplate;

    public void send(String topic, OrderData orderData) {
        kafkaTemplate.send(topic, orderData);
    }

}
