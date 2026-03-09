package com.example.Worker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "work_logs")
@Data
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;
    private Long siteId;

    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private Double hoursWorked;

}