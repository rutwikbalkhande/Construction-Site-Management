package com.example.Worker;

import com.example.Worker.service.WorkerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkerApplicationTests {

	@Autowired
       private WorkerService workerService;
	@Test
	void contextLoads() {


	}

}
