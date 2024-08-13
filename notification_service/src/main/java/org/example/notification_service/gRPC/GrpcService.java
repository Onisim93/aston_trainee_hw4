package org.example.notification_service.gRPC;


import com.example.notification_service.notification.NotificationRequest;
import com.example.notification_service.notification.NotificationResponse;
import com.example.notification_service.notification.NotificationServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notification_service.model.Notification;
import org.example.notification_service.service.NotificationService;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@net.devh.boot.grpc.server.service.GrpcService
public class GrpcService extends NotificationServiceGrpc.NotificationServiceImplBase {

    private final NotificationService notificationService;


    @Override
    public void sendNotification(NotificationRequest request, StreamObserver<com.example.notification_service.notification.NotificationResponse> responseObserver) {

        NotificationResponse response;

        if (notificationService.isExist(request.getIdempotencyKey())) {
            log.info("Notification with idempotency key {} already exists", request.getIdempotencyKey());
            response = NotificationResponse.newBuilder()
                    .setStatus("ALREADY EXISTS")
                    .build();
        }
        else {
            Notification notification = Notification.builder()
                    .idempotencyKey(UUID.fromString(request.getIdempotencyKey()))
                    .text(request.getMessage())
                    .created(LocalDateTime.now())
                    .build();

            notificationService.create(notification);
            log.info("New notification: {} successfully delivered", notification.getText());

            response = NotificationResponse.newBuilder()
                    .setStatus("SUCCESSFUL DELIVERED")
                    .build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
