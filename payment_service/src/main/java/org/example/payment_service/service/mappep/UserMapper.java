package org.example.payment_service.service.mappep;

import org.example.payment_service.dto.UserDto;
import org.example.payment_service.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity user);
    UserEntity toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<UserEntity> userEntities);
}
