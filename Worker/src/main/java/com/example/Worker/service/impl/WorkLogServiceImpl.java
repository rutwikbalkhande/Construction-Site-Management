package com.example.Worker.service.impl;

import com.example.Worker.entity.WorkLog;
import com.example.Worker.entity.Worker;
import com.example.Worker.repository.WorkLogRepository;
import com.example.Worker.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkLogServiceImpl implements WorkLogService {

    @Autowired
    private WorkLogRepository repository;

    @Override
    public WorkLog saveWorkLog(WorkLog log) {

        // ✅ Validation
        if (log.getWorkDate() == null || log.getStartTime() == null || log.getEndTime() == null) {
            throw new RuntimeException("WorkDate, StartTime and EndTime are required");
        }

        // ✅ Combine Date + Time
        LocalDateTime startDateTime = LocalDateTime.of(log.getWorkDate(), log.getStartTime());
        LocalDateTime endDateTime = LocalDateTime.of(log.getWorkDate(), log.getEndTime());

        // ✅ Handle Night Shift (cross midnight)
        if (endDateTime.isBefore(startDateTime)) {
            endDateTime = endDateTime.plusDays(1);
        }

        // ✅ Calculate Duration
        Duration duration = Duration.between(startDateTime, endDateTime);

        double hoursWorked = duration.toMinutes() / 60.0;

        // ✅ Optional Safety Check
        if (hoursWorked < 0 || hoursWorked > 24) {
            throw new RuntimeException("Invalid working duration");
        }

        // ✅ Set calculated value
        log.setHoursWorked(hoursWorked);

        return repository.save(log);
    }

    @Override
    public List<WorkLog> allDataByWorkerId(Long workerId) {
        return repository.findByWorkerId(workerId);
    }

    @Override
    public List<WorkLog> findBySiteId(Long siteId){
        return repository.findBySiteId(siteId);
    }

    @Override
    public String delete(Long workerId) {
       repository.deleteById(workerId);
        return "worker delete By ID:"+ workerId;
    }


}