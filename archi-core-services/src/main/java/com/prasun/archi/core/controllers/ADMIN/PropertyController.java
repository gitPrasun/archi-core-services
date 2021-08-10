package com.prasun.archi.core.controllers.ADMIN;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasun.archi.core.models.Properties;
import com.prasun.archi.core.models.PropertyHistory;
import com.prasun.archi.core.service.PropertyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class PropertyController {
	
	@Autowired
	private PropertyService service;
	
	
	@Operation(summary = "Get All Properties across all the models")
	@GetMapping("/properties")
	List<Properties> getAll(){
		return service.getAll();
	}
	
	@Operation(summary = "create a single property")
	@PostMapping("/property")
	Properties addproperty(@RequestBody Properties property) {
		return service.addProperty(property);
	}
	
	@Operation(summary = "create a single property for all elements across all models of a specific class")
	@PostMapping("/properties/{elClass}")
	int addProperties(@RequestBody List<Properties> properties,@PathVariable String elClass) {
		 return service.addProperties(properties, elClass);
	}
	
	@Operation(summary = "Add a single property to one element")
	@PostMapping("/properties/parentId/{parentId}")
	int addPropertiesToParent(@RequestBody List<Properties> properties,@PathVariable String parentId) {
		 return service.addPropertiesToParent(properties, parentId);
	}
	
	@Operation(summary = "get a property (value) for an element")
	@GetMapping("/property/{parentId}/{parentVersion}/{name}")
	Properties getProperty(@PathVariable String parentId , @PathVariable int parentVersion , @PathVariable String name) {
		return service.getPropertyById(parentId, parentVersion, name);
	}
	
	@Operation(summary = "get a property history (value) for an element")
	@GetMapping("/property/history/{parentId}/{parentVersion}/{name}")
	List<PropertyHistory> getPropertyHistory(@PathVariable String parentId , @PathVariable int parentVersion , @PathVariable String name) {
		return service.getPropertyHistory(parentId, parentVersion, name);
	}
	
	@Operation(summary = "Updste a property  (value) for an element")
	@PatchMapping("/property/{parentId}/{parentVersion}/{name}")
	Properties patchProperty(@PathVariable String parentId , @PathVariable int parentVersion , @PathVariable String name, 
			@RequestBody Map<String,String> value) {
		return service.setValue(parentId, parentVersion, name, value.get("value"));
	}
	
	@Operation(summary = "get a properties for an element")
	@GetMapping("/property/{parentId}")
	List<Properties> getPropertiesByParentId(@PathVariable String parentId){
		return service.getPropertyById(parentId);
	}
	

}
