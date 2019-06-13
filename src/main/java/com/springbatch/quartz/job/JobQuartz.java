package com.springbatch.quartz.job;

import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.springbatch.jobLauncher.JobCustomerLauncher;

import lombok.SneakyThrows;

@Configuration
public class JobQuartz {
	
	@Autowired
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;
	
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}
	
	@Bean
	public JobDetail jobCustomerDetail() {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "restCustomerJob");
		jobDataMap.put("jobLauncher", jobLauncher);
		jobDataMap.put("jobLocator", jobLocator);
		return JobBuilder.newJob(JobCustomerLauncher.class)
				.withIdentity("customer job")
				.setJobData(jobDataMap)
				.storeDurably()
				.build();
				
	}
	
	@Bean
	public Trigger jobCustomerTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(10)
				.repeatForever();
		return TriggerBuilder
				.newTrigger()
				.forJob(jobCustomerDetail())
				.withIdentity("job customer trigger")
				.withSchedule(scheduleBuilder)
				.build();
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(jobCustomerTrigger());
		scheduler.setQuartzProperties(quartzProperties());
		scheduler.setJobDetails(jobCustomerDetail());
	    scheduler.setApplicationContextSchedulerContextKey("applicationContext");
		return scheduler;
		
		
	}
	@SneakyThrows
	private Properties quartzProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
	

			
}
