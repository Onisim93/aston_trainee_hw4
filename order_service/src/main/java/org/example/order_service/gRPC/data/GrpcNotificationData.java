package org.example.order_service.gRPC.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrpcNotificationData {
    String idempotencyKey;
    String message;
}
