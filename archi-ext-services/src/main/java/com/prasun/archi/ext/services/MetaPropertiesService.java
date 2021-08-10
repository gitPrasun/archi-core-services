package com.prasun.archi.ext.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasun.archi.ext.core.pojo.repository.EnterpriseEntityView;
import com.prasun.archi.ext.core.pojo.repository.MetaPropertyRepository;
import com.prasun.archi.ext.utility.pojo.models.ArchiBaseEntities;
import com.prasun.archi.ext.utility.pojo.models.MetaProperties;


@Service
public class MetaPropertiesService {

	@Autowired
	MetaPropertyRepository repository;
	
	
	
	public MetaPropertiesService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public List<ArchiBaseEntities> getAllBaseEntities(){
		return Arrays.asList(ArchiBaseEntities.values());
	}
	
	public Set<String> getEnterpriseEntities(String modelRef){
		Set uniqueEEView = new HashSet();
		
		for(EnterpriseEntityView ee : repository.getMetaPropertyByModelRef(modelRef)) {
			uniqueEEView.add(ee.getEnterpriseEntity());
		}
		
		return uniqueEEView ; //repository.getMetaPropertyByModelRef(modelRef);
		
	}
	
	public List<MetaProperties> getMetaPropertiesByModel(String modelRef){
		return repository.findByModelRef(modelRef);
	}
	
	public List<MetaProperties> getMetaPropertiesByModelAndEntity(String modelRef,String entity){
		return repository.findByModelRefAndEnterpriseEntity(modelRef, entity);
	}
	
	public MetaProperties getMetaPropertiesByModelAndNme(String modelRef,String name){
		return repository.findByModelRefAndName(modelRef, name);
	}
	
	public List<MetaProperties> addMetaProperty(List<MetaProperties> metaProperties) {
		List<MetaProperties> respMetaProperties = new ArrayList<MetaProperties>();
		for(MetaProperties mp:metaProperties) {
			respMetaProperties.add(repository.save(mp));
		}
		return respMetaProperties;
	}
	
	public MetaProperties modifyMetaProperty(MetaProperties mProp) {
		if(repository.existsById(mProp.getMetaPropertyId())) {
			return repository.save(mProp);
		}
		return null;
	}
	
	public void deleteMetaProperties(String uuid) {
		repository.deleteById(UUID.fromString(uuid));
	}
	
}
