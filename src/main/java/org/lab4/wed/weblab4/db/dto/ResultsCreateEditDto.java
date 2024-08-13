package org.lab4.wed.weblab4.db.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResultsCreateEditDto {
    private double x;
    private double y;
    private double r;
    private boolean hit;
    private LocalDateTime date;
    private long execTime;
    private long userId;
} 
