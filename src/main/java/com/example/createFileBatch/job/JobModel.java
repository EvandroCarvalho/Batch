package com.example.createFileBatch.job;


import com.example.createFileBatch.step.StepModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobModel {

    @Autowired
    private JobBuilderFactory job;

    @Autowired
    private StepModel stepModel;

    @Bean
    public Job jobTest() {
        return job.get("jobTest")
                .incrementer(new RunIdIncrementer())
                .flow(stepModel.stepTest())
                .end()
                .build();
    }

}
