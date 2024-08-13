package org.example.restaurant_service.exception;

public class LowAccountBalanceException extends RuntimeException {
    public LowAccountBalanceException(String message) {
        super(message);
    }
}
