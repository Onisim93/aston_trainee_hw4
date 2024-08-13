package org.example.restaurant_service.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "meals")
@Entity
public class MealEntity {

    @Id
    @SequenceGenerator(name = "meal_seq", sequenceName = "meal_sequence", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "meal_seq")
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private RestaurantEntity restaurant;
}
