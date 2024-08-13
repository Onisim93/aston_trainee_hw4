package org.example.payment_service.kafka.service;

import com.example.OrderData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class KafkaServiceProducer {

    private final KafkaTemplate<String, OrderData> kafkaTemplate;

    public void send(String topic, OrderData orderData) {
        kafkaTemplate.send(topic, orderData);
    }
}
