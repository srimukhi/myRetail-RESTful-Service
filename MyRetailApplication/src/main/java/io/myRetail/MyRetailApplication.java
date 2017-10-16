package io.myRetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class MyRetailApplication {

	//start point for the application
	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}
	
	//builds an instance of RestTemplate
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
}
