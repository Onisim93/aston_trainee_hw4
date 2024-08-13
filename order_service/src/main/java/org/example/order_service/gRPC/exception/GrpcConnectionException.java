package org.example.order_service.gRPC.exception;

public class GrpcConnectionException extends RuntimeException {
    public GrpcConnectionException(String message) {
        super(message);
    }
}
