package org.example.restaurant_service.service;

import org.example.restaurant_service.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto create(RestaurantDto restaurantDto);
    RestaurantDto update(RestaurantDto restaurantDto);
    RestaurantDto getById(Long id);
    void deleteById(Long id);
    List<RestaurantDto> getAll();
}
