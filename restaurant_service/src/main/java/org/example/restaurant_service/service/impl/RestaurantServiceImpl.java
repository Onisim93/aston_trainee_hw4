package org.example.restaurant_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.restaurant_service.dto.RestaurantDto;
import org.example.restaurant_service.repository.RestaurantRepository;
import org.example.restaurant_service.service.RestaurantService;
import org.springframework.stereotype.Service;
import static org.example.restaurant_service.service.mapper.RestaurantMapper.RESTAURANT_MAPPER;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Transactional
    @Override
    public RestaurantDto create(RestaurantDto restaurantDto) {
        return RESTAURANT_MAPPER.toDto(repository.save(RESTAURANT_MAPPER.toEntity(restaurantDto)));
    }

    @Transactional
    @Override
    public RestaurantDto update(RestaurantDto restaurantDto) {
        return RESTAURANT_MAPPER.toDto(repository.save(RESTAURANT_MAPPER.toEntity(restaurantDto)));
    }

    @Override
    public RestaurantDto getById(Long id) {
        return RESTAURANT_MAPPER.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<RestaurantDto> getAll() {
        return RESTAURANT_MAPPER.toDtoList(repository.findAll());
    }
}
