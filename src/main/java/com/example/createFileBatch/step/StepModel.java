package com.example.createFileBatch.step;

import com.example.createFileBatch.Listeners.Listener;
import com.example.createFileBatch.Processor.Processor;
import com.example.createFileBatch.feign.CallCustomerService;
import com.example.createFileBatch.model.Customer;
import com.example.createFileBatch.reader.ReaderModel;
import com.example.createFileBatch.writer.HeadersCSV;
import com.example.createFileBatch.writer.WriterGeneratCSVFile;
import com.example.createFileBatch.writer.WriterModel;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StepModel {

    @Autowired
    private StepBuilderFactory step;

    @Autowired
    private ReaderModel readerModel;

    @Autowired
    private WriterModel writerModel;

    @Autowired
    private WriterGeneratCSVFile writerGeneratCSVFile;

    @Autowired
    private CallCustomerService customerService;

    @Autowired
    private Processor processor;


    @Value("${file.resource}")
    private String resource;

    private Resource outputResource;

    @Autowired
    HeadersCSV headersCSV;

    @Autowired
    DelimitedLineAggregator delimitedLineAggregator;

    @Autowired
    Listener listener;






    public Step stepTest() {
        return step.get("stepTest")
              //  .listener(listener)
                .<Customer, Customer> chunk(1)
                //.reader(new ListItemReader<>(customerService.list()))
                .reader(readerModel)
                .processor(processor)
                .writer(writerGeneratCSVFile)
                .build();
    }
}
