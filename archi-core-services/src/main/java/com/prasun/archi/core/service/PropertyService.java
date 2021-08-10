package com.prasun.archi.core.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasun.archi.core.models.Elements;
import com.prasun.archi.core.models.Properties;
import com.prasun.archi.core.models.PropertyHistory;
import com.prasun.archi.core.models.repository.ElementRepository;
import com.prasun.archi.core.models.repository.PropertyHistoryRepository;
import com.prasun.archi.core.models.repository.PropertyRepository;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository repository;

	@Autowired
	private ElementRepository e_repository;
	
	@Autowired
	private PropertyHistoryRepository ph_repository;

	public List<Properties> getAll() {
		return repository.findAll();
	}
	
	public List<String> getParentByNameValue(List<Properties> properties){
		Set<String> uniqueParents = new HashSet<String>();
		for(Properties p:properties) {
			for(Properties found: repository.findByNameAndValue(p.getName(), p.getValue())) {
				uniqueParents.add(found.getParentId());
			}
		}
		
		return  uniqueParents.stream().toList();
		
	}

	public int addProperties(List<Properties> properties, String type) {
		List<Elements> eligibleElements = e_repository.findByElementClass(type);
		int countOfUpdates = 0;
		for(Elements e:eligibleElements) {
			for(Properties p: properties) {
				p.setParentId(e.getId().toString());
				p.setParentVersion(e.getVersion());
				Properties getProp = repository.findByParentIdAndParentVersionAndName(p.getParentId(), p.getParentVersion(), p.getName());
				if(getProp==null) {
					repository.save(p);
					ph_repository.save(new PropertyHistory(p,Timestamp.valueOf(LocalDateTime.now())));
					countOfUpdates++;
				}
				
			}
		}
		return countOfUpdates;
	}
	public int addPropertiesToParent(List<Properties> properties , String parentRef){
		return this.addPropertiesToParent(properties, parentRef, 1);
	}
	
	public int addPropertiesToParent(List<Properties> properties , String parentRef, int ParentVersion){
		int updateCount=0;
		for(Properties p:properties) {
			p.setParentId(parentRef);
			p.setParentVersion(ParentVersion);
			repository.save(p);
			ph_repository.save(new PropertyHistory(p,Timestamp.valueOf(LocalDateTime.now())));
			updateCount++;
		}
		return updateCount;
	}
	public Properties addProperty(Properties property) {
		// property.se
		Elements e = (Elements) e_repository.findByIdAndVersion(UUID.fromString(property.getParentId()),
				property.getParentVersion());
		if (e != null) {
			Properties newProperty = repository.save(property);
			ph_repository.save(new PropertyHistory(newProperty,Timestamp.valueOf(LocalDateTime.now())));
			return newProperty;
		}
		return null;
	}

	public Properties getPropertyById(String parentId, int parentVersion, String propName) {
		return repository.findByParentIdAndParentVersionAndName(parentId, parentVersion, propName);
	}
	
	public List<Properties> getPropertyById(String parentId) {
		return repository.findByParentId(parentId);
	}
	
	public List<PropertyHistory> getPropertyHistory(String parentId, int parentVersion, String propName){
		return ph_repository.findByParentIdAndParentVersionAndName(parentId, parentVersion, propName);
	}

	public Properties setValue(String parentId, int parentVersion, String propName, String value) {
		Properties p = this.getPropertyById(parentId, parentVersion, propName);
		if (p != null) {
			//p.set
			p.setValue(value);
			ph_repository.save(new PropertyHistory(p,Timestamp.valueOf(LocalDateTime.now())));
			repository.save(p);
			return p;
		}
		return null;

	}
	
	public boolean deleteProperty(String parentId, int parentVersion, String propName) {
		Properties p = this.getPropertyById(parentId, parentVersion, propName);
		if(p!=null) {
			repository.delete(p);
			return true;
		}
		
		
		return false;
	}
	

}
