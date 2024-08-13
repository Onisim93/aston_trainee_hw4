package org.example.restaurant_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurants")
@Entity
public class RestaurantEntity {

    @Id
    @SequenceGenerator(name = "rest_seq", sequenceName = "restaurant_sequence", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "rest_seq")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<MealEntity> meals;
}
