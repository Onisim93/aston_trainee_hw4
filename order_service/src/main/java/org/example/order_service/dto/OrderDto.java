package org.example.order_service.dto;

import com.example.OrderData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.order_service.model.OrderItem;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    private Long id;
    private Long restaurantId;
    private Long userId;
    private String status;
    private Double totalSum;

    private List<OrderItem> meals;

    public OrderDto(OrderData orderData) {
        this.id = orderData.getId();
        this.restaurantId = orderData.getRestaurantId();
        this.userId = orderData.getUserId();
        this.status = orderData.getStatus().toString();
        this.totalSum = orderData.getTotalSum();
        this.meals = orderData.getMeals().stream().map(OrderItem::new).toList();
    }
}
