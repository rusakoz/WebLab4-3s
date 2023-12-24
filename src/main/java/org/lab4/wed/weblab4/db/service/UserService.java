package org.lab4.wed.weblab4.db.service;

import java.util.Optional;

import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.dto.UserReadDto;
import org.lab4.wed.weblab4.db.repository.UserRepository;
import org.lab4.wed.weblab4.mapper.UserCreateEditMapper;
import org.lab4.wed.weblab4.mapper.UserReadMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    
    public Optional<UserReadDto> findById(Long id){
        return repository.findById(id).map(userReadMapper::map);
    }

    public Optional<UserReadDto> findByName(String name){
        return repository.findByName(name).map(userReadMapper::map);
    }

    public Boolean existsByName(String name){
        return repository.existsByName(name);
    }

    public UserReadDto create(UserCreateEditDto userDto){
        return Optional.of(userDto)
            .map(userCreateEditMapper::map)
            .map(repository::save)
            .map(userReadMapper::map)
            .orElseThrow();
    }
}
