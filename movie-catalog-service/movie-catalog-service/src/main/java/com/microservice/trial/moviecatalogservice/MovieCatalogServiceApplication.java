package com.microservice.trial.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTEmplate() {
		return new RestTemplate(getClientHttpRequestFactory());
	}

	// @Bean
	// public WebClient.Builder webClient() {
	// return WebClient.builder();
	// }

	// SimpleClientHttpRequestFactory
	// HttpComponentsClientHttpRequestFactory

	@Bean
	public HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(3000);
		// Read timeout
		clientHttpRequestFactory.setReadTimeout(3000);
		return clientHttpRequestFactory;
	}
}
