package com.prasun.archi.ext.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasun.archi.ext.services.PropertyService;

@RestController
public class SchemaValidate {
	
	@Autowired
	PropertyService pService;
	
	@GetMapping("/validate")
	public boolean validateSchema() {
		return pService.getMetamodelAndValidate();
	}
	

}
