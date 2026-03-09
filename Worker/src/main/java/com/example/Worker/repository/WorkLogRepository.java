package com.example.Worker.repository;

import com.example.Worker.entity.WorkLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

    List<WorkLog> findByWorkerId(Long workerId);

     List<WorkLog> findBySiteId(Long siteId);
}
