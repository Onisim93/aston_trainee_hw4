package org.example.payment_service.service;

import org.example.payment_service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    UserDto findById(Long id);
    List<UserDto> findAll();
    void deleteById(Long id);
}
