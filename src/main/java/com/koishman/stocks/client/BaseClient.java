package com.koishman.stocks.service;

import com.koishman.stocks.model.sotck.BatchStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Map;

public abstract class BaseClient {

	private static final Logger log = LoggerFactory.getLogger(BaseClient.class);

	private RestOperations restTemplate;

	@PostConstruct
	public void initRest() {
		restTemplate = new RestTemplate();
	}

	protected abstract HttpHeaders getHttpHeader();

	protected ResponseEntity execute(URI uri, HttpMethod httpMethod, HttpHeaders httpHeaders, ParameterizedTypeReference parameterizedTypeReference) {
		HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
		ResponseEntity<Map<String, BatchStock>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, parameterizedTypeReference);
		return response;
	}

}