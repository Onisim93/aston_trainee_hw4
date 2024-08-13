package org.example.payment_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.payment_service.dto.UserDto;
import org.example.payment_service.repository.UserRepository;
import org.example.payment_service.service.UserService;
import org.springframework.stereotype.Service;

import static org.example.payment_service.service.mappep.UserMapper.USER_MAPPER;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        return USER_MAPPER.toDto(repository.save(USER_MAPPER.toEntity(userDto)));
    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        return USER_MAPPER.toDto(repository.save(USER_MAPPER.toEntity(userDto)));
    }

    @Override
    public UserDto findById(Long id) {
        return USER_MAPPER.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<UserDto> findAll() {
        return USER_MAPPER.toDtoList(repository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
