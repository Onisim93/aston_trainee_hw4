package org.example.restaurant_service.kafka.logging;

import com.example.OrderData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Aspect
@Component
public class KafkaLoggingAspect {

    @Around("execution(* org.springframework.kafka.core.KafkaTemplate.send(..))")
    public Object logKafkaSend(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof CompletableFuture<?>) {
            CompletableFuture<SendResult<String, OrderData>> future = (CompletableFuture<SendResult<String, OrderData>>) result;

            future.thenAccept(metadata ->
                    log.info("Message sent successfully to topic '{}' partition '{}' with offset '{}'",
                            metadata.getRecordMetadata().topic(), metadata.getRecordMetadata().partition(), metadata.getRecordMetadata().offset())).exceptionally(ex -> {
                log.error("Error sending message", ex);
                return null;
            });
        } else {
            log.warn("Kafka send() method returned an unexpected type: {}", result.getClass().getName());
        }

        return result;
    }

}
