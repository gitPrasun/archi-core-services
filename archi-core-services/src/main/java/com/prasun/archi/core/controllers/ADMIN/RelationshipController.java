package com.prasun.archi.core.controllers.ADMIN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasun.archi.core.models.Elements;
import com.prasun.archi.core.models.Relationships;
import com.prasun.archi.core.service.RelationshipService;

@RestController
public class RelationshipController {

//	private final RelationshipRepository repository;
//
//	public RelationshipController(RelationshipRepository repository) {
//		super();
//		this.repository = repository;
//	}
	@Autowired
	private RelationshipService service;

	@PostMapping("/relationship")
	public Relationships addElement(@RequestBody Relationships relation) {
		return service.addRelation(relation);

	}

	@GetMapping("/relationship/{source_id}/{target_id}/{relation_class}")
	public Relationships getRelation(@PathVariable String sourceId, @PathVariable String targetId,
			@PathVariable String relationClass) {
		return service.getRelation(sourceId, targetId, relationClass);
	}
	
	@GetMapping("/relationship")
	public List<Relationships> getAllRelations(){
		return service.getAll();
	}
	
	@GetMapping("/relationship/related/entities/{elementId}")
	public List<Elements> getRelatedElements(@PathVariable String elementId){
		return service.getRelatedElements(elementId);
	}
	
	@GetMapping("relationship/related/relations/{elementId}")
	public List<Relationships> getRelatedRelations(@PathVariable String elementId){
		return service.getRelations(elementId);
	}
	

	@DeleteMapping("/relationship/{source_id}/{target_id}/{relation_class}")
	public boolean deleteElement(@PathVariable String sourceId, @PathVariable String targetId,
			@PathVariable String relationClass) {
		return service.removeRelation(sourceId, targetId, relationClass);

	}

}
