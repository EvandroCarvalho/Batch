package com.springbatch.jobLauncher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Component
public class JobCustomerLauncher extends QuartzJobBean {
	private Job job;
	@Qualifier("restrestCustomerJob")
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;
	
	@Override
	@SneakyThrows
	protected void executeInternal(JobExecutionContext context) {
        ApplicationContext applicationContext = (ApplicationContext)
                context.getScheduler().getContext().get("applicationContext");

        jobLocator = (JobLocator) applicationContext.getBean(JobLocator.class);
        jobLauncher = (JobLauncher) applicationContext.getBean(JobLauncher.class);
		job = jobLocator.getJob("restCustomerJob");
        log.info("Starting restCustomerJob");
		jobLauncher.run(job, execution());

	}

	private JobParameters execution() {
		Map<String, JobParameter> parameters = new HashMap<>();
		JobParameter parameter = new JobParameter(new Date());
		parameters.put("currentTime", parameter);
		return new JobParameters(parameters);
	}

}
