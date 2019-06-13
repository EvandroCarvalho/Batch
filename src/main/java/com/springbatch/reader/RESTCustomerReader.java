package com.springbatch.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springbatch.model.CustomerDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class RESTCustomerReader implements ItemReader<CustomerDTO> {
	
	@Value("${rest.api}")
    private String apiUrl;
    @Autowired
    private final RestTemplate restTemplate;

    private int nextCustomerIndex;
    private List<CustomerDTO> customerData;

    @Override
    public CustomerDTO read() throws Exception {
        log.info("Reading the information of the next student");
        customerData = fetchCustomerDataFromAPI();

        CustomerDTO nextCustomer = null;

        if (nextCustomerIndex < customerData.size()) {
            nextCustomer = customerData.get(nextCustomerIndex);
            nextCustomerIndex++;
            log.info("Found student: {}", nextCustomer);
        }else {
        	nextCustomer = null;
        	nextCustomerIndex = 0;
        }

        return nextCustomer;
    }

    private List<CustomerDTO> fetchCustomerDataFromAPI() {
        log.debug("Fetching student data from an external API by using the url: {}", apiUrl);

        ResponseEntity<CustomerDTO[]> response = restTemplate.getForEntity(apiUrl, CustomerDTO[].class);
        CustomerDTO[] studentData = response.getBody();
        log.debug("Found {} students", studentData.length);

        return Arrays.asList(studentData);
    }
}

