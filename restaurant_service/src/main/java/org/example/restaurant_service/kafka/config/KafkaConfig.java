package org.example.restaurant_service.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic restaurantRequestDlt() {
        return TopicBuilder.name("restaurantRequest.DLT")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic restaurantResponseDlt() {
        return TopicBuilder.name("restaurantResponse.DLT")
                .partitions(1)
                .replicas(1)
                .build();
    }

    /*@Bean
    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(KafkaTemplate<String, String> kafkaTemplate) {
        return new DeadLetterPublishingRecoverer(kafkaTemplate,
                (r, e) -> new TopicPartition(r.topic() + ".DLT", r.partition()));
    }
    @Bean
    public CommonErrorHandler errorHandler(DeadLetterPublishingRecoverer deadLetterPublishingRecoverer) {
        var backOff = new ExponentialBackOffWithMaxRetries(3);
        backOff.setInitialInterval(1000L);
        backOff.setMultiplier(2.0);
        backOff.setMaxInterval(5000L);
        return new DefaultErrorHandler(deadLetterPublishingRecoverer, backOff);
    }*/
}
