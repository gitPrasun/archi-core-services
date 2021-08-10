package com.prasun.archi.core.controllers.ADMIN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import com.prasun.archi.core.models.Elements;
import com.prasun.archi.core.models.Properties;
import com.prasun.archi.core.service.ElementService;
import com.prasun.archi.core.service.PropertyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ElementController {
	//private final ElementRepository repository;
	
	@Autowired
	private ElementService service;
	
	@Autowired
	private PropertyService pService;

//	public ElementController(ElementRepository repository) {
//		super();
//		this.repository = repository;
//	}
	
	@Operation(summary = "Get All Elements. Not tenant-aware when directly invoked")
	@GetMapping("/elements")
	public List<Elements> getall(){
		
		return service.findall();
		
	}
	
	@Operation(summary = "Create single Element.")
	@PostMapping("/element")
	@ResponseStatus(HttpStatus.CREATED)
	public Elements addElement(@RequestBody Elements elements) {
		Elements newElement = service.addElement(elements);
		return newElement;
		
	}
	
	@Operation(summary = "Create Elements in bulk together")
	@PostMapping("/elements/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Elements> addElement(@RequestBody List<Elements> elements) {
		List<Elements> newElement = new ArrayList<Elements>();
				
		for (Elements elemnt:elements) {
			newElement.add(service.addElement(elemnt));
		}
		return newElement;
	}
	
	
	
//	@PostMapping("/elements/class")
//	@ResponseStatus(HttpStatus.FOUND)
//	public List<Elements> getBulkElementsByClass(@RequestBody List<String> elementIds,@PathVariable String eClass){
//		return service.findAllbyClassAndIds(elementIds, eClass);
//	}
	
	@Operation(summary = "Get all the elemnets with a list of Ids")
	@PostMapping("/elements")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Elements> getBulkElementsByid(@RequestBody List<String> elementIds){
		return service.findByIds(elementIds);
	}
	
	
	@Operation(summary = "Get specific element detail based upon id & version")
	@GetMapping("/element/{id}/{version}")
	public Elements getElement(@PathVariable String id,@PathVariable int version) {
		Elements newElement = service.getElementbyId(id,version);
		return newElement;
			
	}
	
	@Operation(summary = "Get All Elements of a specific class passed as request parameter")
	@GetMapping("/elements/class")
	public List<Elements> getElement(@RequestParam(name = "value") String elClass) {
		List<Elements> newElement = service.getElementbyClass(elClass);
		return newElement;
			
	}
	
	@Operation(summary = "Get All Elements of a specific class passed as url variable")
	@PostMapping("/elements/class/{className}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Elements> getElementByClass(@PathVariable String className, @RequestBody List<Properties> propertyInputs) {
		List<Properties> foundProperties = new ArrayList<Properties>();
		List<String> parentIds = pService.getParentByNameValue(propertyInputs);
		List<Elements> newElements = service.findByIds(parentIds);
		return newElements.stream()
				   .filter(a->a.getElementClass().equals(className))
				   .collect(Collectors.toList());

			
	}
	
	@Operation(summary = "Get All Elements of a specific class & name passed as request parameter")
	@GetMapping("/elements/classname")
	public List<Elements> getElementByName(@RequestParam(name = "class") String elClass, @RequestParam(name = "name") String  name){
		System.out.println("GOt element of calss:"+elClass+" and name:"+name);
		List<Elements> foundElements = service.getElementbyName(name, elClass);
		return foundElements;
	}
	
	@Operation(summary = "Delete a specific element with a id & version")
	@DeleteMapping("/element/{id}/{version}")
	public boolean deleteElement(@PathVariable String id,@PathVariable int version) {
		return service.deleteElement(id,version);
			
	}
	
	
}
