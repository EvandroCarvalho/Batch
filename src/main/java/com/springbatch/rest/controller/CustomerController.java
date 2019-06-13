package com.springbatch.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatch.model.CustomerDTO;

@RestController
@RequestMapping(path = "customers")
public class CustomerController {

	@GetMapping(path = "/list")
	public ResponseEntity<List<CustomerDTO>> listAll(){
		List<CustomerDTO> list = Arrays
				.asList(new CustomerDTO("Evandro", "123", "34343", "address1"),
						new CustomerDTO("Carvalho", "4567", "323232", "address2"),
						new CustomerDTO("Junior", "12345", "123", "address3"));
		return ResponseEntity.ok(list);
	}
}
