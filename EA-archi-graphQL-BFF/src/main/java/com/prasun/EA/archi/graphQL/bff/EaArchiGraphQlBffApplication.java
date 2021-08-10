package com.prasun.EA.archi.graphQL.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.prasun.EA.archi.graphQL.bff.resolvers.Mutation;
import com.prasun.EA.archi.graphQL.bff.resolvers.Query;

@SpringBootApplication
public class EaArchiGraphQlBffApplication {

	@Bean
	public Query query() {
		return new Query();
	}

	@Bean
	public Mutation mutation() {
		return new Mutation();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EaArchiGraphQlBffApplication.class, args);
	}

}
