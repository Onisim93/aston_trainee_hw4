package org.example.order_service.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic paymentRequest() {
        return TopicBuilder.name("paymentRequest")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentResponse() {
        return TopicBuilder.name("paymentResponse")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic restaurantRequest() {
        return TopicBuilder.name("paymentRequest")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic restaurantResponse() {
        return TopicBuilder.name("paymentResponse")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
