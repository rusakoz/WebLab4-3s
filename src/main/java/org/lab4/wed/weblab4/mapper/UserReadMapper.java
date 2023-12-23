package org.lab4.wed.weblab4.mapper;

import org.lab4.wed.weblab4.db.dto.UserReadDto;
import org.lab4.wed.weblab4.db.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<Users, UserReadDto>{

    @Override
    public UserReadDto map(Users object) {
        return new UserReadDto(object.getId(), object.getName(), object.getPassword(), object.getDateOfCreation());
    }
}
