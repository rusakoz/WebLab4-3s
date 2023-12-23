package org.lab4.wed.weblab4.mapper;

import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, Users>{

    @Override
    public Users map(UserCreateEditDto object) {
        Users user = new Users();
        user.setName(object.name());
        user.setPassword(object.password());
        user.setDateOfCreation(object.dateOfCreation());
        return user;
    }
    
}
