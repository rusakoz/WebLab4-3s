package org.lab4.wed.weblab4.mapper;

import org.lab4.wed.weblab4.db.dto.UserCreateEditDto;
import org.lab4.wed.weblab4.db.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, Users>{

    @Override
    public Users map(UserCreateEditDto object) {
        Users user = new Users();
        user.setName(object.getName());
        user.setPassword(object.getPassword());
        user.setDateOfCreation(object.getDateOfCreation());
        return user;
    }
    
}
