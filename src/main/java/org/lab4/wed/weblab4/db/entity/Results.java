package org.lab4.wed.weblab4.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "results", schema = "s338963")
public class Results implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double x;
    private double y;
    private double r;
    private boolean hit;
    private LocalDateTime date;
    @Column(name = "exec_time")
    private long execTime;

}
