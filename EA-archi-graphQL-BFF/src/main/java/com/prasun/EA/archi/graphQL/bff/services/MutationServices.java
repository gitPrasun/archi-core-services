package com.prasun.EA.archi.graphQL.bff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class MutationServices {
	@Value("${app.archi.ext.service.url}")
	private String url ;
	
	@Autowired
	private RestTemplate restTemplate;
	
	

}
