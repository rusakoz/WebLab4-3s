package org.lab4.wed.weblab4.mapper;

import org.lab4.wed.weblab4.db.dto.ResultsCreateEditDto;
import org.lab4.wed.weblab4.db.entity.Results;
import org.springframework.stereotype.Component;

@Component
public class ResultsCreateEditMapper implements Mapper<ResultsCreateEditDto, Results> {

    @Override
    public Results map(ResultsCreateEditDto object) {
        Results results = new Results();
        results.setX(object.getX());
        results.setY(object.getY());
        results.setR(object.getR());
        results.setDate(object.getDate());
        results.setExecTime(object.getExecTime());
        results.setHit(object.isHit());
        results.setUserId(object.getUserId());
        return results;
    }
    
}
