package com.example.createFileBatch.feign;

import com.example.createFileBatch.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "custumerService", url = "${url}")
public interface CallCustomerService {

    @GetMapping
    List<Customer> list();
}
