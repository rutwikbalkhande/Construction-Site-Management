package com.example.Worker.service;

import com.example.Worker.entity.WorkLog;
import com.example.Worker.entity.Worker;

import java.util.List;

public interface WorkLogService {

    WorkLog saveWorkLog(WorkLog workLog);

    List<WorkLog> allDataByWorkerId(Long workerId);  // use workerId to fetch workerlog working data

    List<WorkLog> findBySiteId(Long siteId);

     String delete(Long workerId);


}
