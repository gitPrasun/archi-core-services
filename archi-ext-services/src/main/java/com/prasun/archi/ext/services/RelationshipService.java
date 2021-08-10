package com.prasun.archi.ext.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prasun.archi.ext.core.pojo.models.ref.Relationships;
import com.prasun.archi.ext.viewObjectModels.Strategy.Capability;

@Service
public class RelationshipService {

	@Value("${app.archi.core.service.url}")
	private String url;

	RestTemplate restTemplate = new RestTemplate();
	
	public Relationships[] getAllRelations(){
		return restTemplate.getForObject(url+"/relationship", Relationships[].class);
	}
}
