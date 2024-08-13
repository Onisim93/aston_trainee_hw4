package org.example.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> meals;

    @Column(name = "total_sum")
    private Double totalSum;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = OrderStatus.PENDING;
        }
    }
}
