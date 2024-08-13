package org.lab4.wed.weblab4.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.lab4.wed.weblab4.db.dto.ResultsReadDto;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component
// OPI lab4
public class AverageClickInterval {

    @ManagedOperation
    public int mainLogic(List<ResultsReadDto> listResult) {
        int averageTime = 0;
        int count = listResult.size() / 2 - 1;
        for (int i = 0; i < count - 1; i++) {
            ResultsReadDto elem = listResult.get(i);
            ResultsReadDto elem2 = listResult.get(i+1);
            ZonedDateTime zone = elem.date().atZone(ZoneId.of("Europe/Moscow"));
            ZonedDateTime zoneTwo = elem2.date().atZone(ZoneId.of("Europe/Moscow"));
            averageTime += zoneTwo.toInstant().toEpochMilli()/1000 - zone.toInstant().toEpochMilli()/1000;
        }
        return averageTime / count / 60;
    }
    
}
