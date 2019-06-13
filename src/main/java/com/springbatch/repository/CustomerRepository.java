package com.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbatch.model.CustomerDTO;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDTO, Long>{

}
