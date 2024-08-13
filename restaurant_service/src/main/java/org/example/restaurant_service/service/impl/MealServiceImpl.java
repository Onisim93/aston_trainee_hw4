package org.example.restaurant_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.restaurant_service.dto.MealDto;
import org.example.restaurant_service.model.MealEntity;
import org.example.restaurant_service.repository.MealRepository;
import org.example.restaurant_service.service.MealService;
import org.springframework.stereotype.Service;
import static org.example.restaurant_service.service.mapper.MealMapper.MEAL_MAPPER;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Transactional
    @Override
    public MealDto create(MealDto mealDto) {
        return MEAL_MAPPER.toDto(repository.save(MEAL_MAPPER.toEntity(mealDto)));
    }

    @Transactional
    @Override
    public MealDto update(MealDto mealDto) {
        MealEntity mealEntity = repository.findById(mealDto.getId()).orElseThrow(EntityNotFoundException::new);
        mealEntity.setQuantity(mealDto.getQuantity());
        return MEAL_MAPPER.toDto(mealEntity);
    }

    @Override
    public MealDto getById(Long id) {
        return MEAL_MAPPER.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<MealDto> getAll() {
        return MEAL_MAPPER.toDtoList(repository.findAll());
    }

    @Override
    public List<MealDto> getAllByRestaurantId(Long restaurantId) {
        return MEAL_MAPPER.toDtoList(repository.findAllByRestaurantId(restaurantId));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
