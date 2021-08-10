package com.prasun.EA.archi.graphQL.bff.resolvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import com.prasun.EA.archi.graphQL.bff.schema.Models.CredentialsInput;
import com.prasun.EA.archi.graphQL.bff.schema.Models.Element;
import com.prasun.EA.archi.graphQL.bff.schema.Models.MetaProperty;
import com.prasun.EA.archi.graphQL.bff.schema.Models.Model;
import com.prasun.EA.archi.graphQL.bff.schema.Models.PropertyInput;
import com.prasun.EA.archi.graphQL.bff.viewModels.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Query implements GraphQLQueryResolver {

	@Value("${app.archi.ext.service.url}")
	private String url ;
	
	//private final String REST_URL = "http://localhost:8080/archi-bff";
	@Autowired
	private RestTemplate restTemplate;
	
	public String status() {
		return "up";
	}
	
	public String login(CredentialsInput cred) {
		return "loggedin";
	}
	
	public List<Model> userModels(String userId){
		log.info(url+"/role/user/{userId}");
		List<Model> models = new ArrayList<Model>();
		Role[] roles =  restTemplate.getForObject(url + "/role/user/{userId}" , Role[].class, userId);
		
		HashSet<String> roleModels = new HashSet<String>();
		
		for(int i=0;i<roles.length; i++) {
			roleModels.add(roles[i].getModelId());
		}
		
		
		//TODO:for eachModel
		for(String modelId :roleModels) {
			Model model = restTemplate.getForObject(url + "/models/detail/{modelId}", Model.class,modelId);
			models.add(model);
		}
		
		return models;
	}
	
	public List<Element> allowedDomains(String modelId){
		return null;
		
	}
	public List<Element> allowedCapabilities(String modelId){
		return null;
	}
	public List<Element> allowedServices(String modelId){
		return null;
	}


	
	public List<Element> capabilitiesWhere(String modelId, List<PropertyInput> properties){
		return null;
	}
	public List<Element> servicesWhere(String modelId, List<PropertyInput> properties){
		return null;
	}
	
	public List<MetaProperty> metaPropertiesWhere(String modelId, List<String> elementTypes){
		return null;
	}
	
	
}
