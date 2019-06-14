package com.springbatch.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestTemplateHelper {
	
    private static RestTemplate restTemplate;
    private static ObjectMapper objectMapper;

    @Autowired
    public RestTemplateHelper(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }
	
    public static <T> List<T> getForList(Class<T> clazz, String url) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return readValue(response, collectionType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("No data found {}", url);
            } else {
                log.info("rest client exception", exception.getMessage());
            }
        }
        return null;
    }

	@SneakyThrows
	private static <T> T readValue(ResponseEntity<String> response, CollectionType collectionType) {
		T result = null;
		if(response.getStatusCode() == HttpStatus.OK) {
			result = objectMapper.readValue(response.getBody(), collectionType);
			System.out.println(result);
			return result;
		} else {
			throw new Exception("Erro a ser tratado");
		}
	}

}
