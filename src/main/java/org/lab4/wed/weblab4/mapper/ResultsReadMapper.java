package org.lab4.wed.weblab4.mapper;

import org.lab4.wed.weblab4.db.dto.ResultsReadDto;
import org.lab4.wed.weblab4.db.entity.Results;
import org.springframework.stereotype.Component;

@Component
public class ResultsReadMapper implements Mapper<Results, ResultsReadDto>{

    @Override
    public ResultsReadDto map(Results object) {
        return new ResultsReadDto(object.getId(), object.getX(), object.getY(), object.getR(), 
                                    object.isHit(), object.getDate(), object.getExecTime(), object.getUserId());
    }
    
}
