package com.example.createFileBatch.step;

import com.example.createFileBatch.model.Customer;
import com.example.createFileBatch.reader.ReaderModel;
import com.example.createFileBatch.writer.WriterGeneratCSVFile;
import com.example.createFileBatch.writer.WriterModel;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
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



    public Step stepTest() {
        return step.get("stepTest")
                .<Customer, Customer> chunk(1)
                .reader(readerModel)
                .writer(writerGeneratCSVFile.writer())
                .build();
    }
}
