package com.example.createFileBatch.configuration;

import com.example.createFileBatch.model.Customer;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelimitedConfig {

    @Bean
    DelimitedLineAggregator<Customer> delimitedLineAggregator() {
        DelimitedLineAggregator delimitedLineAggregator = new DelimitedLineAggregator<Customer>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(new BeanWrapperFieldExtractor<Customer>() {
            {
                setNames(new String[]{"name", "age", "address"});
            }
        });
        return delimitedLineAggregator;
    }
}
