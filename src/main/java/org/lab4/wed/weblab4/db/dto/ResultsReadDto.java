package org.lab4.wed.weblab4.db.dto;

import java.time.LocalDateTime;


public record ResultsReadDto(Long id, double x, double y, double r, boolean hit, 
                                LocalDateTime date, long execTime, long userId) {
    
}
