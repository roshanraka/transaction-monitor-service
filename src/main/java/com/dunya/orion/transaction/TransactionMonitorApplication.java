package com.dunya.orion.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dunya.orion.transaction.contract.ErrorResponse;

@SpringBootApplication
public class TransactionMonitorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TransactionMonitorApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public ErrorResponse response() {
		return new ErrorResponse();
	}
	
	@Bean
	public ResponseEntity<ErrorResponse> responseEnity() {
		return new ResponseEntity<ErrorResponse>(HttpStatus.CONTINUE);
	}
}
