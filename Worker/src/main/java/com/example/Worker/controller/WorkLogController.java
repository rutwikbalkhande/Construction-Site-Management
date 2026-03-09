package com.example.Worker.controller;

import com.example.Worker.entity.WorkLog;
import com.example.Worker.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worklogs")
@RequiredArgsConstructor
public class WorkLogController {

    private final WorkLogService service;

    @PostMapping("/save")
    public WorkLog save(@RequestBody WorkLog workLog) {
        return service.saveWorkLog(workLog);
    }

    @GetMapping("/worker/{workerId}")
    public List<WorkLog> getAllByWorkerId(@PathVariable Long workerId) {
        return service.allDataByWorkerId(workerId);
    }

   @GetMapping("/site/{siteId}")
    public List<WorkLog> findBySiteId(@PathVariable Long siteId){
        return service.findBySiteId(siteId);
    }

    @DeleteMapping("/delete/{workerId}")
    public String delete(@PathVariable Long workerId) {
        return service.delete(workerId);
    }


}