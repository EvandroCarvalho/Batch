package com.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.springbatch.model.CustomerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingCustomerProcessor implements ItemProcessor<CustomerDTO, CustomerDTO> {

	@Override
	public CustomerDTO process(CustomerDTO item) {
		System.out.println("passou pelo processor");
		log.info("Processing customerinformation: {} customers", item);
		return item;
	}

}
