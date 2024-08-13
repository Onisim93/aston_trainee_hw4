package org.example.restaurant_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestaurantDto {

    private Long id;
    private String name;
    List<MealDto> meals;
}
