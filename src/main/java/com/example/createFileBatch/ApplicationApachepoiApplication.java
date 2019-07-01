package com.example.createFileBatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableBatchProcessing
@SpringBootApplication
public class ApplicationApachepoiApplication {

    BeanFactoryPostProcessor context;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationApachepoiApplication.class, args);
    }

}
