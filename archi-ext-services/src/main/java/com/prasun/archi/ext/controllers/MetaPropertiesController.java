package com.prasun.archi.ext.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prasun.archi.ext.core.pojo.repository.EnterpriseEntityView;
import com.prasun.archi.ext.services.MetaPropertiesService;
import com.prasun.archi.ext.utility.pojo.models.ArchiBaseEntities;
import com.prasun.archi.ext.utility.pojo.models.MetaProperties;


@RestController
public class MetaPropertiesController {

	@Autowired
	MetaPropertiesService mpService;

	
	@GetMapping("/MetaProperties/ArchiBaseEntities")
	@ResponseStatus(HttpStatus.FOUND)
	public List<ArchiBaseEntities> getAll() {
		return mpService.getAllBaseEntities();
	}
	
//	@GetMapping("/MetaProperties/{modelRef}")
//	@ResponseStatus(HttpStatus.FOUND)
//	public List<MetaProperties> getMetaPropertiesByModel(@PathVariable String modelRef){
//		return mpService.getMetaPropertiesByModel(modelRef);
//	}
	
	@GetMapping("/MetaProperties/EnterpriseEntities/{modelRef}")
	@ResponseStatus(HttpStatus.FOUND)
	public Set<String> getEnterpriseEntitiesByModel(@PathVariable String modelRef){
		return mpService.getEnterpriseEntities(modelRef);
	}
	
	@GetMapping("/MetaProperties/{modelRef}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<MetaProperties> getMetaPropertiesByModel(@PathVariable String modelRef){
		return mpService.getMetaPropertiesByModel(modelRef);
	}
	
	
	@GetMapping("/MetaProperties/{modelRef}/{entity}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<MetaProperties> getMetaPropertiesForEntityByModel(@PathVariable String modelRef, @PathVariable String entity){
		return mpService.getMetaPropertiesByModelAndEntity(modelRef, entity);
	}
	
	@PostMapping("/MetaProperties")
	@ResponseStatus(HttpStatus.CREATED)
	public List<MetaProperties> createMetaProperty(@RequestBody List<MetaProperties> metaProperties){
		return mpService.addMetaProperty(metaProperties);
	}
	
	@PostMapping("/MetaProperties/bulkFindByNameModel")
	@ResponseStatus(HttpStatus.FOUND)
	public List<MetaProperties> getMetaPropertyBulkByName(@RequestBody List<MetaProperties> metaProperties){
		List<MetaProperties> returnMetaProperties = new ArrayList<MetaProperties>();
		for(MetaProperties mp:metaProperties) {
			returnMetaProperties.add(mpService.getMetaPropertiesByModelAndNme(mp.getModelRef(), mp.getName()));
		}
		return mpService.addMetaProperty(metaProperties);
	}
	
	@PutMapping("/MetaProperties")
	@ResponseStatus(HttpStatus.OK)
	public MetaProperties modifyMetaPropertytoModel(@RequestBody MetaProperties metaProperty) {
		return mpService.modifyMetaProperty(metaProperty);
	}
	
	@DeleteMapping("/MetaProperties/{uuid}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteMetaProperty(@PathVariable String uuid) {
		 mpService.deleteMetaProperties(uuid);
	}
	
}
