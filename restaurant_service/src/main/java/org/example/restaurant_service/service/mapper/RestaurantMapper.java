package org.example.restaurant_service.service.mapper;

import org.example.restaurant_service.dto.MealDto;
import org.example.restaurant_service.dto.RestaurantDto;
import org.example.restaurant_service.model.MealEntity;
import org.example.restaurant_service.model.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = MealMapper.class)
public interface RestaurantMapper {

    RestaurantMapper RESTAURANT_MAPPER = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDto toDto(RestaurantEntity entity);

    @Mapping(target = "meals", ignore = true)
    RestaurantEntity toEntity(RestaurantDto dto);

    List<RestaurantDto> toDtoList(List<RestaurantEntity> entities);

    List<MealDto> toMealDtoList(List<MealEntity> entities);
}
