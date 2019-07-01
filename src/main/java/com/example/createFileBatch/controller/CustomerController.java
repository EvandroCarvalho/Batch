package com.example.createFileBatch.controller;


import com.example.createFileBatch.model.Address;
import com.example.createFileBatch.model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/v1")

public class CustomerController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @GetMapping
    public ResponseEntity find() {
        System.out.println("CALL SERVICE FIND");
        Address address = new Address().builder()
                .street("Stree1")
                .city("city1")
                .number("1")
                .build();
        Customer customer = new Customer().builder()
                .name("Name1")
                .age("1")
                .address(address)
                .build();
        Address address2 = new Address().builder()
                .street("Street2")
                .city("city2")
                .number("2")
                .build();
        Customer customer2 = new Customer().builder()
                .name("Name2")
                .age("2")
                .address(address2)
                .build();
        return ResponseEntity.ok(Arrays.asList(customer, customer2));
    }

    @PostMapping
    public ResponseEntity executeJob() throws Exception {
        jobLauncher.run(job, execution());
        return ResponseEntity.ok().build();
    }

    private JobParameters execution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        return new JobParameters(parameters);
    }

}