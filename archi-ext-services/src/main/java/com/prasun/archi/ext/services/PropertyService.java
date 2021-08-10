package com.prasun.archi.ext.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasun.archi.ext.core.pojo.repository.MetaPropertyRepository;
import com.prasun.archi.ext.utility.pojo.models.MetaModel;
import com.prasun.archi.ext.utility.pojo.models.MetaProperties;

import ch.qos.logback.classic.Logger;

//import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

@Service
public class PropertyService {
	
	@Autowired
	MetaPropertyRepository repository;
	
	String [] cProperties = {"dummy1" , "dummy2"};
	String [] bsProperties = {"dummy3" , "dummy4"};
	String [] zProperties = {"dummy5" , "dummy6"};
	
	public List<MetaProperties> getMetaPropsByModel(String modelRef){
		return repository.findByModelRef(modelRef);
	}
	
	public List<MetaProperties> getMetaPropsByModelAndEntity(String modelRef, String enterpriseEntity){
		return repository.findByModelRefAndEnterpriseEntity(modelRef, enterpriseEntity);
	}
	
	public MetaProperties getMetaPropsByModelAndName(String modelRef, String name) {
		return repository.findByModelRefAndName(modelRef, name);
	}
	
	public String[] getProperties(String entity) {
		
		if (entity.equalsIgnoreCase("BusinessService")) {
			return bsProperties;
			
		}else if (entity.equalsIgnoreCase("Capability")){
			
			return cProperties;
		}else {
			
			return zProperties;
		}
		
	}
	
	public boolean getMetamodelAndValidate() {
		JSONObject jsonSchema = new JSONObject(
			      new JSONTokener(ObjectSchema.class.getResourceAsStream("/schema.json")));
			    JSONObject jsonSubject = new JSONObject(
			      new JSONTokener(ObjectSchema.class.getResourceAsStream("/metaModel.json")));
			    try {
			    Schema schema = SchemaLoader.load(jsonSchema);
			    schema.validate(jsonSubject);
			    
			    JSONArray entities= (JSONArray) jsonSubject.get("Entities");
			    Map<String,MetaModel> model = new HashMap<String, MetaModel>();
			    
			    for(Object entity: entities) {
			    	if(entity instanceof JSONObject) {
			    		System.out.println(((JSONObject) entity).get("ArchiEntity").toString());
//			    		for(Object prop: ((JSONArray) entity).get("ArchiProperties")) {
//			    			
//			    		}
//			    		
			    	}
			    	;
			    }
			    
			    } catch(Exception e) {
			    	System.out.println(e.getMessage());
			    	return false;
			    }
			   return true;
	}

}
