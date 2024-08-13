package org.example.order_service.gRPC;

import com.example.order_service.notification.NotificationRequest;
import com.example.order_service.notification.NotificationResponse;
import com.example.order_service.notification.NotificationServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.order_service.gRPC.data.GrpcNotificationData;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    @GrpcClient("notification-service")
    private NotificationServiceGrpc.NotificationServiceBlockingStub notificationServiceStub;

    public void sendNotification(GrpcNotificationData data) {
        try {
            NotificationRequest request = NotificationRequest.newBuilder()
                    .setIdempotencyKey(data.getIdempotencyKey())
                    .setMessage(data.getMessage())
                    .build();

            NotificationResponse response = notificationServiceStub.sendNotification(request);
            log.info("Notification sent successfully, status: {}", response.getStatus());
        } catch (NullPointerException e) {
            log.error("Connect to notification server failed.");
        }
    }


    private void checkArguments(String message, Long orderId, Long userId) {
        if (orderId == null || userId == null || message == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }
}
