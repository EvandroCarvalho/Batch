package com.springbatch.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.springbatch.listener.JobCompletionNotificateListener;
import com.springbatch.model.CustomerDTO;
import com.springbatch.processor.LoggingCustomerProcessor;
import com.springbatch.reader.RESTCustomerReader;
import com.springbatch.step.StepOne;
import com.springbatch.writer.LoggingCustomerWriter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;;

@Configuration
@EnableBatchProcessing
@Slf4j
public class CustomerJob {
	
	
	@Bean
	Job restCustomerJob(JobBuilderFactory jobBuilderFactory, JobCompletionNotificateListener listener,
			StepOne restCustomerStep) {
		return jobBuilderFactory.get("restCustomerJob")
				.validator(new DefaultJobParametersValidator())
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(restCustomerStep.executeStepOne())
				.build();
	}

}
