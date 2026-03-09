package com.example.Worker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workers")
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String skill;
    private String phone;

    private String status; // AVAILABLE / ASSIGNED
}
