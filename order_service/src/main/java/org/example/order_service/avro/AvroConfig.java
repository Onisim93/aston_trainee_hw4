package org.example.order_service.avro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AvroConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
