package com.springbatch.step;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.jsr.configuration.xml.StepFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springbatch.model.CustomerDTO;
import com.springbatch.processor.LoggingCustomerProcessor;
import com.springbatch.reader.RESTCustomerReader;
import com.springbatch.writer.LoggingCustomerWriter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StepOne {
	public static final String API_URL = "rest.api";
	
	@Autowired
	private StepBuilderFactory stepFactory;
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	Environment environment;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	LoggingCustomerWriter writer;
	@Autowired
	LoggingCustomerProcessor processor;
	@Autowired
	RESTCustomerReader reader;


//	
//	@Bean
//	@SneakyThrows
//	protected JobRepository createJobRepository() {
//		log.info("INFO: config data base");
//		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//		factory.setDataSource(dataSource);
//		factory.setTransactionManager(new ResourcelessTransactionManager());
//		factory.setIsolationLevelForCreate("ISOLATION_REPEATABLE_READ");
//		factory.setMaxVarCharLength(2);
//		factory.setTablePrefix("BATCH_");
//		return factory.getObject();
//	}
	
	@Bean
	public Step executeStepOne() {
		return stepFactory.get("restCustomerSep")
				.<CustomerDTO,CustomerDTO> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}
