package com.example.createFileBatch.writer;

import com.example.createFileBatch.Listeners.Listener;
import com.example.createFileBatch.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class WriterGeneratCSVFile implements ItemWriter {

    @Value("${file.resource}")
    private String resource;

    private Resource outputResource;

    @Autowired
    HeadersCSV headersCSV;

    @Autowired
    private DelimitedLineAggregator delimitedLineAggregator;

    private Long jobId;


//    public FlatFileItemWriter<Customer> writer() {
//        outputResource = new FileSystemResource(resource);
//        log.info("####################OutputFile: " + outputResource.getFilename());
//
//        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
//
//        writer.setResource(outputResource);
//
//        writer.setHeaderCallback(headersCSV);
//
//        writer.setAppendAllowed(true);
//        writer.setShouldDeleteIfEmpty(true);
//        writer.setShouldDeleteIfExists(true);
//
//        writer.setLineAggregator(delimitedLineAggregator);
//        return writer;
//    }

    @Override
    public void write(List items) throws Exception {
        log.info("#######WRITER########");
        log.info("#######SIZE########: " + items.size());
        ExecutionContext excution = new ExecutionContext();

        log.info("######JOBID#####: " + jobId);


        String fileName = "output" + jobId + ".csv"; //String.valueOf(LocalDateTime.now().hashCode()).concat(".csv");




        outputResource = new FileSystemResource(resource + fileName);
        log.info("####################OutputFile: " + resource.concat(fileName + jobId + ".csv"));

        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();

        writer.setResource(outputResource);

        writer.setHeaderCallback(headersCSV);

        writer.setAppendAllowed(true                );
        writer.setShouldDeleteIfEmpty(true);
        writer.setShouldDeleteIfExists(true);

        writer.setLineAggregator(delimitedLineAggregator);
        writer.open(excution);
        writer.write(items);
        writer.close();

        //log.info("#######retorno: " + retorno);
    }

    @BeforeStep
    public void getInterStedData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        log.info("##########Stepexecution");
        this.jobId = jobExecution.getJobId();
    }

}
