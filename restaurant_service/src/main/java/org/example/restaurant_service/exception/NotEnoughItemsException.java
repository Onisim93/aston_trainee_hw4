package org.example.restaurant_service.exception;

public class NotEnoughItemsException extends RuntimeException {
    public NotEnoughItemsException(String message) {
        super(message);
    }
}
