package com.example.Worker.service;

import com.example.Worker.dto.WorkerLogsWithDto;
import com.example.Worker.entity.Worker;

import java.util.List;

public interface WorkerService {



    Worker createWorker(Worker worker);

    List<Worker> getAllWorkers();

    Worker getWorkerById(Long id);

    Worker updateWorker(Long id, Worker worker);

    void deleteWorker(Long id);

    WorkerLogsWithDto findByName(String name);

    List<Worker> getBySkill(String skill);

    List<Worker> getBySiteId(Long siteId);


}
