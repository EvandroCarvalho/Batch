package com.springbatch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbatch.model.CustomerDTO;
import com.springbatch.util.RestTemplateHelper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Component
@RequiredArgsConstructor
@Slf4j
public class RESTCustomerReader implements ItemReader<CustomerDTO> {
	
	@Value("${rest.api}")
    private String apiUrl;
    private CustomerDTO t;

    private int nextCustomerIndex;
    private List<CustomerDTO> customerData;

    @Override
    public CustomerDTO read() throws Exception {
        log.info("Reading the information of the next customer");
        customerData = RestTemplateHelper.getForList(CustomerDTO.class, apiUrl);

        CustomerDTO nextCustomer = null;

        if (nextCustomerIndex < customerData.size()) {
            nextCustomer = customerData.get(nextCustomerIndex);
            nextCustomerIndex++;
            log.info("Found customer: {}", nextCustomer);
        }else {
        	nextCustomer = null;
        	nextCustomerIndex = 0;
        }

        return nextCustomer;
    }


}

