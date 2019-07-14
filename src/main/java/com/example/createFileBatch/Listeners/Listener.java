package com.example.createFileBatch.Listeners;

import com.example.createFileBatch.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import javax.batch.api.listener.JobListener;

@Slf4j
@Component
public class Listener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("###JOB###: " + stepExecution.getExitStatus());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("###JOB###: " + stepExecution.getExitStatus());
        return null;
    }
}
