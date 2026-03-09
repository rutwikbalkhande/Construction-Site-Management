package com.example.Worker.controller;

import com.example.Worker.dto.WorkerLogsWithDto;
import com.example.Worker.entity.Worker;
import com.example.Worker.service.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
@RequiredArgsConstructor
@Slf4j
public class WorkerController {

    @Autowired
    private  WorkerService workerService;

    // ✅ CREATE Worker
    @PostMapping("/create")
    public ResponseEntity<Worker> create(@RequestBody Worker worker) {

        log.info("Request received to create worker: {}", worker.getName());

        Worker savedWorker = workerService.createWorker(worker);

        log.info("Worker created successfully with id: {}", savedWorker.getId());

        return new ResponseEntity<>(savedWorker, HttpStatus.CREATED);
    }

    // ✅ GET ALL Workers
    @GetMapping("/all")
    public ResponseEntity<List<Worker>> getAll() {

        log.info("Fetching all workers");

        List<Worker> workers = workerService.getAllWorkers();

        log.info("Total workers found: {}", workers.size());

        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    // ✅ GET Worker by ID
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Long id) {

        log.info("Fetching worker with id: {}", id);

        Worker worker = workerService.getWorkerById(id);

        log.info("Worker found: {}", worker.getName());

        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    // ✅ GET Worker + Logs by Name
    @GetMapping("/name")
    public ResponseEntity<WorkerLogsWithDto> getWorkerDataByName(
            @RequestParam String name) {

        log.info("Fetching worker details for name: {}", name);

        WorkerLogsWithDto response = workerService.findByName(name);

        log.info("Worker data + logs fetched successfully for: {}", name);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ✅ GET Workers by Skill
    @GetMapping("/skill")
    public ResponseEntity<List<Worker>> getBySkill(
            @RequestParam String skill) {

        log.info("Fetching workers with skill: {}", skill);

        List<Worker> workers = workerService.getBySkill(skill);

        log.info("Workers found with skill {} → {}", skill, workers.size());

        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    // ✅ UPDATE Worker (Partial)
    @PatchMapping("/update/{id}")
    public ResponseEntity<Worker> update(
            @PathVariable Long id,
            @RequestBody Worker worker) {

        log.info("Updating worker with id: {}", id);

        Worker updatedWorker = workerService.updateWorker(id, worker);

        log.info("Worker updated successfully with id: {}", id);

        return new ResponseEntity<>(updatedWorker, HttpStatus.OK);
    }

    // ✅ DELETE Worker
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        log.warn("Deleting worker with id: {}", id);

        workerService.deleteWorker(id);

        log.warn("Worker deleted successfully with id: {}", id);

        return new ResponseEntity<>("Worker deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/site/{siteId}")
    public ResponseEntity<List<Worker>> getBySiteId(@PathVariable Long siteId) {

        log.info("Fetching workers for siteId: {}", siteId);

        List<Worker> workers = workerService.getBySiteId(siteId);

        return ResponseEntity.ok(workers);
    }
}