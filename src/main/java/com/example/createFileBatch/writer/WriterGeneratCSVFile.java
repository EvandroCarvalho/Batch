package com.example.createFileBatch.writer;

import com.example.createFileBatch.model.Customer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class WriterGeneratCSVFile {

    @Value("${file.resource}")
    private String resource;

    private Resource outputResource;

    @Autowired
    HeadersCSV headersCSV;

    @Autowired
    private DelimitedLineAggregator delimitedLineAggregator;
    

    public FlatFileItemWriter<Customer> writer() {
    	outputResource = new FileSystemResource(resource);

        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();

        writer.setResource(outputResource);

        writer.setHeaderCallback(headersCSV);

        writer.setAppendAllowed(true);
        writer.setShouldDeleteIfEmpty(true);
        writer.setShouldDeleteIfExists(true);

        writer.setLineAggregator(delimitedLineAggregator);
        return writer;
    }

}
