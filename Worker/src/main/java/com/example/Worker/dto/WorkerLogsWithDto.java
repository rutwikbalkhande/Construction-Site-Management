package com.example.Worker.dto;


import com.example.Worker.entity.WorkLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerLogsWithDto {

    private Long id;
    private String name;
    private String skill;
    private String phone;
    private String status;

    List<WorkLog> worklogs;
}
