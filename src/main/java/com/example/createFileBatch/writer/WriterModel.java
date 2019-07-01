package com.example.createFileBatch.writer;

import com.example.createFileBatch.model.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriterModel implements ItemWriter<Customer> {

    @Override
    public void write(List<? extends Customer> items) throws Exception {
        System.out.println("Lista: "  + items.toString());
    }
}
