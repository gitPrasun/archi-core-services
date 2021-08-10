package com.prasun.archi.core.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasun.archi.core.models.Elements;
import com.prasun.archi.core.models.Relationships;
import com.prasun.archi.core.models.repository.ElementRepository;
import com.prasun.archi.core.models.repository.RelationshipRepository;

@Service
public class RelationshipService {
	
	@Autowired
	private RelationshipRepository repository;
	
	@Autowired
	private ElementRepository erepository;
	
	//public 
	public List<Relationships> getAll(){
		return repository.findAll();
	}
	
	
	
	public Relationships addRelation(Relationships relation) {
		
		Relationships toReturn = null;
		
		// check if it exists
		Relationships oldRelation = this.getRelation(relation.getSourceId(), relation.getTargetId(), relation.getRelationClass());
		if(oldRelation!=null) {
			toReturn = oldRelation;
			relation.setCheckedin_on(Timestamp.valueOf(LocalDateTime.now()));
			relation.setVersion(relation.getAccess_type()+1);
			//TODO: do nothing for now decide later
			System.out.println("found old relation");
		}else {
			System.out.println("new relation");
			relation.setCreated_on(Timestamp.valueOf(LocalDateTime.now()));
			relation.setCheckedin_on(Timestamp.valueOf(LocalDateTime.now()));
			relation.setVersion(1);
			toReturn  = repository.save(relation);
		}
		
		
		
		return toReturn;
	}
	
	public Relationships getRelation(String sourceId, String targetId,String relationClass) {
		return repository.findBySourceIdAndTargetIdAndRelationClass(sourceId, targetId,relationClass);
	}
	
	public boolean removeRelation(String sourceId, String targetId,String relationClass) {
		Relationships rel = this.getRelation(sourceId, targetId, relationClass);
		
		if(rel!=null) {
			repository.delete(rel);
			return true;
		}
		return false;
	}
	
	public List<Elements> getRelatedElements(String id){
		List<Elements> relatedElements = new ArrayList<Elements>();
		for(Relationships r: repository.findBySourceId(id)) {
			relatedElements.addAll(erepository.findById(UUID.fromString(r.getTargetId())));
		}
		for(Relationships r: repository.findByTargetId(id)) {
			relatedElements.addAll(erepository.findById(UUID.fromString(r.getSourceId())));
		}
		return relatedElements;
	}
	
	public List<Relationships> getRelations(String id){
		List<Relationships> relations = new ArrayList<Relationships>();
		relations.addAll(repository.findBySourceId(id));
		relations.addAll(repository.findByTargetId(id));
		return relations;
		
	}

}
