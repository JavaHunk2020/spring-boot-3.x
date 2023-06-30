package com.kuebiko.controller;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceService {
	
	@Scheduled(cron = "0 */1 * * * *")
    public void runSpringBatchExampleJob() {
        System.out.println("Spring Batch example job was started"+new Date());
    }

}
