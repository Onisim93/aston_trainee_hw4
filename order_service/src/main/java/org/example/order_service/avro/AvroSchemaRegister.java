package org.example.order_service.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class AvroSchemaRegister implements CommandLineRunner {

    @Value("classpath:avro/OrderData.avsc")
    private Resource userSchemaResource;

    @Value("${avro.schema.registry.url}")
    private String schemaRegistryUrl;

    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;


    @Override
    public void run(String... args) throws Exception {
        registerSchema("orderData", userSchemaResource);
    }

    public void registerSchema(String subject, Resource schemaFilePath) throws IOException {
        InputStream schemaInputStream = schemaFilePath.getInputStream();
        String schemaJson = new String(schemaInputStream.readAllBytes(), StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String payload = createPayload(schemaJson);

        log.info("Payload: {}", payload);

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                schemaRegistryUrl,
                HttpMethod.POST,
                request,
                String.class,
                subject
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Schema registered successfully: " + response.getBody());
        } else {
            System.err.println("Failed to register schema: " + response.getBody());
        }
    }

    private String createPayload(String schemaJson) throws JsonProcessingException {
        // Создание объекта JSON
        SchemaPayload payload = new SchemaPayload(schemaJson);
        return objectMapper.writeValueAsString(payload);
    }

    static class SchemaPayload {
        private final String schema;

        public SchemaPayload(String schema) {
            this.schema = schema;
        }

        public String getSchema() {
            return schema;
        }
    }

}
