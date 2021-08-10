package com.prasun.archi.core.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasun.archi.core.models.ElementId;
import com.prasun.archi.core.models.Elements;
import com.prasun.archi.core.models.repository.ElementRepository;

@Service
public class ElementService {

	@Autowired
	private ElementRepository repository;
	
	public List<Elements> findall(){
//		System.out.println(repository.findAll());
//		List<Elements> all = repository.findAll();
//		
//		for (Elements e : all){
//			System.out.println(e.getName());
//		}
		return (List<Elements>) repository.findAll();
	}
	
//	public List<Elements> findAllbyClassAndIds(List<String> IDs, String elClass){
//		List<UUID> uuids = new ArrayList<UUID>();
//		for(String id:IDs) {
//			uuids.add(UUID.fromString(id));
//		}
//		return repository.findAllByElementClassAndIds(elClass, uuids);
//	}
	
	public List<Elements> findByIds(List<String> IDs){
		List<UUID> uuids = new ArrayList<UUID>();
		List<ElementId> eids = new ArrayList<ElementId>();
		for(String id:IDs) {
			eids.add(new ElementId(UUID.fromString(id),1));
			//uuids.add(UUID.fromString(id));
		}
		return repository.findAllById(eids);
	}
	
	public Elements addElement(Elements element) {
		element.setVersion(1);
		element.setCreated_on(Timestamp.valueOf(LocalDateTime.now()));
		element.setCheckedin_on(Timestamp.valueOf(LocalDateTime.now()));
		
		return (Elements) repository.save(element);
	}
	
	public List<Elements> getElementbyClass(String elClass){
		return repository.findByElementClass(elClass);
		
	}
	
	public List<Elements> getElementbyName (String name, String elClass){
		return repository.findByNameAndElementClass(name, elClass);
	}
	
	public Elements getElementbyId(String id, int version) {
		//ElementId el = new ElementId(UUID.fromString(id),version);
		//repository
		//repository.
		//return (Elements) repository.getOne(el);
		
		return (Elements) repository.findByIdAndVersion(UUID.fromString(id), version);
	}
	
	public boolean deleteElement(String id,int version) {
		Optional<Elements> toBeDeleted = repository.findById(new ElementId(UUID.fromString(id) ,version));
		 if(toBeDeleted !=null) {
			 repository.deleteById(new ElementId(UUID.fromString(id) ,version));
			 return true;
		 }
		 
		 
		 return false;
	}
	
}
