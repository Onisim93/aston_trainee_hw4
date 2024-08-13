package org.example.restaurant_service.service.mapper;

import org.example.restaurant_service.dto.MealDto;
import org.example.restaurant_service.model.MealEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MealMapper {
    MealMapper MEAL_MAPPER = Mappers.getMapper(MealMapper.class);

    MealDto toDto(MealEntity mealEntity);

    @Mapping(target = "restaurant", ignore = true)
    MealEntity toEntity(MealDto mealDto);

    List<MealDto> toDtoList(List<MealEntity> mealEntities);
}
