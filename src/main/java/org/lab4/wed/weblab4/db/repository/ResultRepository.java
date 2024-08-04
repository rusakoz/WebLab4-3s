package org.lab4.wed.weblab4.db.repository;

import java.util.List;

import org.lab4.wed.weblab4.db.entity.Results;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Results, Long>{

    List<Results> findByUserId(long userId);

}
