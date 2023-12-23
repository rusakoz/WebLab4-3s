package org.lab4.wed.weblab4.db.repository;

import org.lab4.wed.weblab4.db.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Boolean existsByName(String name);
    
}
