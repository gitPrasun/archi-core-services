package com.prasun.archi.ext.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prasun.archi.ext.core.pojo.models.ElementsInModel;
import com.prasun.archi.ext.core.pojo.models.Models;
import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.core.pojo.repository.EiMRepository;
import com.prasun.archi.ext.utility.pojo.models.MetaProperties;
import com.prasun.archi.ext.utility.pojo.models.Role;
import com.prasun.archi.ext.viewObjectModels.ModelViewObject;
import com.prasun.archi.ext.viewObjectModels.Strategy.Domain;

@Service
public class ModelCompositeService {

	@Autowired
	MetaPropertiesService metaPropService;
	
	@Autowired
	ModelService modelService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	CapabilityService capabilityService;
	
	@Autowired
	EiMRepository eimRepository;
	
	@Autowired
	UserRoleServices erService;
	
	@Value("${app.archi.core.service.url}")
	private String url ;
	RestTemplate restTemplate = new RestTemplate();
	
	public ModelViewObject createAll(ModelViewObject mvo) {
		//Later Needs to Add Domains Also
		ModelViewObject mvoOut = new ModelViewObject();
		List<Properties> modelProperties = new ArrayList<Properties>();
		
		Models modelToCreate = new Models();
		//TODO: need to replace with modelmapper later
		modelToCreate.setName(mvo.getName());
		modelToCreate.setNote(mvo.getNote());
		modelToCreate.setPurpose(mvo.getPurpose());
		modelToCreate.setCreated_by(mvo.getCreatedBy());
		
		//Create Model		
		Models modelCreated= modelService.createModel(modelToCreate);
		mvo.setId(modelCreated.getId().toString());
		
		if(modelCreated!=null) {
			//CreateProperties
			modelProperties = mvo.getProperties();
			adminService.addPropertiesToElement(mvo.getProperties(), modelCreated.getId().toString());
			
			//CreateModelMetaData
			List<MetaProperties> mmp = mvo.getMetaProperties();
			mmp.forEach(mp->mp.setModelRef(modelCreated.getId().toString()));
			mvo.setMetaProperties( metaPropService.addMetaProperty(mmp));
			//metaPropService
			
			//Create Domains 
			//TODO : Modelmapper to rescue again
			List<Elements> domainsToAdd = mvo.getDomain();
			Elements[] createdElements = null;
			
			HttpEntity<List<Elements>> prequest = new HttpEntity<>(domainsToAdd);
			createdElements = restTemplate.postForObject(url+"/elements/bulk", prequest,Elements[].class);
			mvo.setDomain(Arrays.asList(createdElements) );
			
			//TODO: Check if the following block is used somewhere
//			for(Elements eachDomainElement:mvo.getDomain()) {
//				Elements d = eachDomainElement;
//				HttpEntity<List<Elements>> prequest = new HttpEntity<>(domainsToAdd);
//				createdElements = restTemplate.postForObject(url+"/elements/bulk", prequest,Elements[].class);
//				//d.setProperties(eachDomainElement.get);
//				//domainsToAdd.add(new Domain());
//			}
			for(Elements el:createdElements) {
				ElementsInModel eim = new ElementsInModel();
				eim.setElementId(el.getId().toString());
				eim.setElementVersion(el.getVersion());
				//TODO: to be injected with Model
				eim.setModelId(modelCreated.getId().toString());
				eim.setModelVersion(modelCreated.getVersion());
				eimRepository.save(eim);
			}
			//create default roles for creator
			Role creatorRole = new Role();
			creatorRole.setCapabilityAccess("ALL");
			creatorRole.setServiceAccess("ALL");
			creatorRole.setUserId(mvo.getCreatedBy());
			creatorRole.setModelId(modelCreated.getId().toString());
			erService.addRole(creatorRole);
			
			
			return mvo;
			
		}
		
		return null;
		
		
	}
	
}
