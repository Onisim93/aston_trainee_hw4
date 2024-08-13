package org.example.order_service.service.mapper;

import org.example.order_service.dto.OrderDto;
import org.example.order_service.model.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(OrderEntity order);
    OrderEntity toEntity(OrderDto orderDto);

    List<OrderDto> toDto(List<OrderEntity> orderEntities);

}
