package org.example.order_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem {

    @Column(name = "meal_id")
    private Long mealId;
    @Column(name = "quantity")
    private Integer quantity;

    public OrderItem(com.example.OrderItem orderItem) {
        this.mealId = orderItem.getMealId();
        this.quantity = orderItem.getQuantity();
    }
}
