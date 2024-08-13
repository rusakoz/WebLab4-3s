package org.lab4.wed.weblab4.model;

import java.util.List;

import org.lab4.wed.weblab4.db.dto.ResultsReadDto;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource()
@Component
// OPI lab4
public class BrainLogic {

    @ManagedOperation
    public void counterMissPoints(List<ResultsReadDto> listResult) {
        listResult.stream().filter(e -> e.hit() == false).count();
    }

    @ManagedOperation
    public void counterPointsAll(List<ResultsReadDto> listResult) {
        listResult.size();
    }

    @ManagedOperation
    public Boolean checkAreaLimits(double X, double Y, double R) {
        return (Math.abs(X) <= R/2 && Math.abs(Y) <= R && X >= 0 &&  Y <= 0) ||
                (Math.pow(X, 2) + Math.pow(Y, 2) <= Math.pow(R, 2) && X <= 0 && Y <= 0) ||
                (Math.abs(Y) + Math.abs(X) <= R && X >= 0 && Y >= 0);
    }

    @ManagedOperation()
    public Boolean mainLogic(List<ResultsReadDto> listResult, double X, double Y, double R) {
        counterMissPoints(listResult);
        counterPointsAll(listResult);
        return checkAreaLimits(X, Y, R);
    }
}
