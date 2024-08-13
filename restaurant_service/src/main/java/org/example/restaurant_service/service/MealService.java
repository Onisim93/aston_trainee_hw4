package org.example.restaurant_service.service;

import org.example.restaurant_service.dto.MealDto;

import java.util.List;

public interface MealService {
    MealDto create(MealDto mealDto);
    MealDto update(MealDto mealDto);
    MealDto getById(Long id);
    List<MealDto> getAll();
    List<MealDto> getAllByRestaurantId(Long restaurantId);
    void deleteById(Long id);
}
