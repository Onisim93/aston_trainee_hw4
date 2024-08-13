package org.example.notification_service.gRPC;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration({
        net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration.class
})
public class GrpcConfig {

}
