package org.lab4.wed.weblab4.db.service;

import java.time.LocalDate;
import java.util.Optional;

import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.dto.UserReadDto;
import org.lab4.wed.weblab4.db.repository.UserRepository;
import org.lab4.wed.weblab4.mapper.UserCreateEditMapper;
import org.lab4.wed.weblab4.mapper.UserReadMapper;
import org.lab4.wed.weblab4.security.BcryptEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto){
        return Optional.of(userDto)
            .map(userCreateEditMapper::map)
            .map(repository::save)
            .map(userReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public UserReadDto registration(UserCreateEditDto userDto){
        userDto.setPassword(BcryptEncoder.encode(userDto.getPassword()));
        userDto.setDateOfCreation(LocalDate.now());
        return create(userDto);
    }
}
