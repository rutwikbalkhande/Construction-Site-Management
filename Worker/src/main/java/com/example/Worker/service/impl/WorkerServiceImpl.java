package com.example.Worker.service.impl;

import com.example.Worker.dto.WorkerLogsWithDto;
import com.example.Worker.entity.WorkLog;
import com.example.Worker.entity.Worker;
import com.example.Worker.repository.WorkLogRepository;
import com.example.Worker.repository.WorkerRepository;
import com.example.Worker.service.WorkerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepo;
    @Autowired
    private WorkLogRepository worklogRepo;


    @Override
    @CachePut(value = "workers", key = "#result.id")
    public Worker createWorker(Worker worker) {
        return workerRepo.save(worker) ;
    }

    @Override
    @Cacheable(value = "workersAll")
    public List<Worker> getAllWorkers() {
        return workerRepo.findAll();
    }

    @Override
    @Cacheable(value = "workers", key = "#id")
    public Worker getWorkerById(Long id) {
        return workerRepo.findById(id).orElseThrow(()->new RuntimeException("worker not found wirh id: "+ id));
    }

    @Override
    @CachePut(value = "workers", key = "#id")
    public Worker updateWorker(Long id, Worker worker) {
        Worker existing =workerRepo.findById(id).orElseThrow(() -> new RuntimeException("Worker not found with id: " + id));

        if(worker.getName() != null)
        {
           existing.setName (worker.getName());
        }
        if (worker.getSkill() != null) {
            existing.setSkill(worker.getSkill());
        }

        if (worker.getPhone() != null) {
            existing.setPhone(worker.getPhone());
        }


        if (worker.getStatus() != null) {
            existing.setStatus(worker.getStatus());
        }

        return workerRepo.save(existing);
    }

    @Override
    @CacheEvict(value = "workers", key = "#id")       // delete from cache
    public void deleteWorker(Long id) {

        workerRepo.deleteById(id);
    }

    @Override
    @Cacheable(value = "workersByName", key = "#name")
    public WorkerLogsWithDto findByName(String name) {

      Worker worker =  workerRepo.findByName(name).
                 orElseThrow(()->new RuntimeException("worker not Found with name: "+ name));

       List<WorkLog> allWorkerData = worklogRepo.findByWorkerId(worker.getId());

        return new WorkerLogsWithDto(
                worker.getId(),
                worker.getName(),
                worker.getSkill(),
                worker.getPhone(),
                worker.getStatus(),

                allWorkerData
              );
    }

    @Override
    @Cacheable(value = "workersBySkill", key = "#skill")
    public List<Worker> getBySkill(String skill) {
        return workerRepo.findBySkill(skill);
    }

    @Override
    @Cacheable(value = "workersBySite", key = "#siteId")
    public List<Worker> getBySiteId(Long siteId) {

        //1st collect All worker working on that site By siteID
        List<WorkLog> allWorkerData = worklogRepo.findBySiteId(siteId);

        //step2: only get All workerId as Long
        List<Long> workerIds = allWorkerData.stream().map(WorkLog :: getWorkerId).distinct().toList();

        // Step 3: Fetch workers
        List<Worker> siteOnWorker = workerRepo.findAllById(workerIds);
        return siteOnWorker ;
    }


}
