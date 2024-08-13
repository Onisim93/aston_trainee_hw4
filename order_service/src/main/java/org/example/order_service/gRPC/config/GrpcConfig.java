package org.example.order_service.gRPC.config;

import org.example.order_service.gRPC.GrpcClientInterceptor;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration({
        net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration.class
})
public class GrpcConfig {


    @Bean
    public GrpcClientInterceptor dynamicMetadataClientInterceptor() {
        return new GrpcClientInterceptor();
    }
}
