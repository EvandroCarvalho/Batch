package com.springbatch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springbatch.model.CustomerDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobCompletionNotificateListener extends JobExecutionListenerSupport {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.STARTED) {
			log.info("INFO: iniciando aplicação");
		}
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			jdbcTemplate.query("SELECT name, cpf, phone, address FROM customer",
					(rs,row) -> new CustomerDTO(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4))
					).forEach(customer -> log.info("Found <" + customer + "> in the database"));
		}
	}

}
