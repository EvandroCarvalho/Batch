package com.springbatch.writer;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.springbatch.model.CustomerDTO;
import com.springbatch.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingCustomerWriter implements ItemWriter<CustomerDTO>{

//	@Override
//	public void write(List<? extends CustomerDTO> items) throws Exception {
//		log.info("Received the information of {} students", items.size());
//		items.forEach(i -> log.debug("Received the information of a customer: {}", i));
//	}
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Bean
	public JdbcBatchItemWriter<CustomerDTO> writer(DataSource dataSource) {
		
		return new JdbcBatchItemWriterBuilder<CustomerDTO>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO customer (name, cpf, phone, address) values(:name, :cpf, :phone, :address)")
				.dataSource(dataSource)
				.build();
	}

	@Override
	public void write(List<? extends CustomerDTO> items) throws Exception {
//		System.out.println("item no wirter: " + items.toString());
//		XmlMapper xmlMapper = new XmlMapper();
//		ObjectMapper mapper = new ObjectMapper();
//		Jaxb2Marshaller marshal = new Jaxb2Marshaller();
//		System.out.println(xmlMapper.writeValueAsString(items.get(0)));
//		System.out.println(mapper.writeValueAsString(items.get(0)));
		System.out.println("writer: " + items.get(0));
		try {
			System.out.println(items.get(0).getClass().toString());
		//	customerRepository.save(items.get(0));			
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}
	}

}
