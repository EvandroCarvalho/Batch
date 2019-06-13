//package com.springbatch.util;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.CollectionType;
//
//import lombok.SneakyThrows;
//
//@Component
//public class RestTemplateHelper {
//	
//	private RestTemplate restTemplate = new RestTemplate();
//	private ObjectMapper mapper = new ObjectMapper();
//	
//	
//	public <T> List<T> getForList(Class<T> clazz, String url) throws JsonParseException, JsonMappingException, IOException {
//		System.out.println("classe :" + t.getClass());
//		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//		System.out.println(response.getBody());
//		List<T> list = (List<T>) mapper.readValue(response.getBody(), t.getClass() );
//		return list;
//	}
//
//	@SneakyThrows
//	private <T> T readValue(ResponseEntity<String> response, CollectionType collectionType) {
//		T result = null;
//		if(response.getStatusCode() == HttpStatus.OK) {
//			result = mapper.readValue(response.getBody(), collectionType);
//			System.out.println(result);
//			return result;
//		} else {
//			throw new Exception("Erro a ser tratado");
//		}
//	}
//
//}
