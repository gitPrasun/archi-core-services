package com.prasun.archi.ext.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prasun.archi.ext.core.pojo.models.ref.Elements.ARCHIENTITYTYPE;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.services.AdminService;




@RestController
public class AdminController {
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	//String url = "http://localhost:8080";
	//RestTemplate restTemplate = new RestTemplate();
	

	
	@Autowired AdminService service;
	
	@PostMapping("/admin/properties/{elClass}")
	@ResponseStatus(HttpStatus.CREATED)
	public int addPropertiesinBulk(@RequestBody List<Properties> properties, @PathVariable ARCHIENTITYTYPE elClass) {
		//System.out.println("reached ..");
		return service.addPropertyToAll(properties, elClass);
		
	}
	
	@PatchMapping("/admin/model/{modelId}/{modelVersion}")
	@ResponseStatus(HttpStatus.OK)
	public int addModeltoEverything(@PathVariable String modelId, @PathVariable int modelVersion) {
		logger.debug(modelId);
		return service.moveAllOrphanToModel(modelId, modelVersion);
	}

}
