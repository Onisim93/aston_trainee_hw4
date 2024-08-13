package org.example.order_service.exception;

public class ExceptionMessageHelper {

    private static String notFoundMessage = "Entity with id = %d not found";

    public static String getNotFoundMessage(Long id) {
        return String.format(notFoundMessage, id);
    }
}
