package com.example.demo.rest;

import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestTemplateService {
	private static final int READ_TIMEOUT = 6;
	private static final int CONNECT_TIMEOUT = 6;
	private static final int MAX_CONNECTION_POOL = 10;
	
	public String get(String param) {
		String strUrl = "https://www.google.com/search?q=" + param;
		
		String responseBody = sendRequest("GET", strUrl, new HttpHeaders(), new HashMap<String, Object>());
		return responseBody;
	}
	
	private String convertJson(Object object) {
		String result = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
		try {
			result = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		
		return result;
	}

	public RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(1000 * READ_TIMEOUT); // ms
		factory.setConnectTimeout(1000 * CONNECT_TIMEOUT); // ms

		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(MAX_CONNECTION_POOL)
				.setMaxConnPerRoute(MAX_CONNECTION_POOL).build(); // connection pool
		factory.setHttpClient(httpClient); //

		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}

	public String sendRequest(String method, String strUrl, HttpHeaders headers, HashMap<String, Object> map) {
		boolean isDone = false;
		
		// 1. get instance
		RestTemplate restTemplate = getRestTemplate();

		// 2. set header
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Accept", "application/json");
		headers.add("charset", "utf-8");

		// 3. set the body - convert object to json string
		String requestJson = convertJson(map);
		HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, headers);

		// 4. send data by post
		ResponseEntity<String> responseEntity = null;
		
		if("GET".equals(method)) {
			responseEntity = restTemplate.getForEntity(strUrl, String.class);
		} else if("POST".equals(method)) {
			responseEntity = restTemplate.postForEntity(strUrl, httpEntity, String.class);
		}
		
		// 5. response data
		HttpStatus httpStatus = responseEntity.getStatusCode();
		String responseBody = responseEntity.getBody();
		log.info("responseBody -> {}", responseBody);
		
		// Http Status Code = GET : 200, POST : 201
		if(httpStatus == HttpStatus.OK || httpStatus == HttpStatus.CREATED) {
			isDone = true;
		}
		log.info("response result -> {}, {}", httpStatus, isDone);
		
		return responseBody;
	}

}
