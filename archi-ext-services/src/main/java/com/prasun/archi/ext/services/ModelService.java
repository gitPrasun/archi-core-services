package com.prasun.archi.ext.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
import com.prasun.archi.ext.core.pojo.repository.ModelRepository;
import com.prasun.archi.ext.utility.pojo.models.MetaProperties;
import com.prasun.archi.ext.viewObjectModels.AbstractElementWrapper;
import com.prasun.archi.ext.viewObjectModels.UserModelVO;
import com.prasun.archi.ext.viewObjectModels.Strategy.Capability;

@Service
public class ModelService {

	@Value("${app.archi.core.service.url}")
	private String url ;
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	ModelRepository repository;
	
	@Autowired
	EiMRepository eimRepository;
	
	@Autowired
	BSService bService;
	
	@Autowired
	CapabilityService cService;
	
	@Autowired
	MetaPropertiesService mPropertiesService;
	
	@Autowired
	PropertyService propertyService;
	
	
	
	public Models createModel(Models model) {
		model.setCreated_on(Timestamp.valueOf(LocalDateTime.now()));
		model.setVersion(1);
		model.setCheckedin_on(Timestamp.valueOf(LocalDateTime.now()));
		return repository.save(model);
	}
	
	public List<Models> getAll(){
		
		return repository.findAll();
		
	}
	
	public void UpdateModelLog(String modelId, String updatedBy) {
		Models model=repository.findById(UUID.fromString(modelId));
		model.setCheckedin_by(updatedBy);
		model.setCheckedin_on(Timestamp.valueOf(LocalDateTime.now()));
		repository.save(model);
	}
	
	public UserModelVO getCompleteModelDetail(String modelId){

		Properties [] properties ;

		UserModelVO myModel = new UserModelVO();
		Models myModelSmall = getbyId(UUID.fromString(modelId));
		myModel.setId(myModelSmall.getId());
		
		//Models myModel =  (UserModelVO) getbyId(UUID.fromString(modelId));
		//myModel.setId(myModel.getId());
		
		List<ElementsInModel> eims = eimRepository.findByModelId(modelId); 
		List<String> elIds = new ArrayList<String>();
		Elements[] elements;
		
		for(ElementsInModel eim:eims) {
			elIds.add(eim.getElementId());
		}
		
		//TODO: Fetch Elements
		HttpEntity<List<String>> prequest = new HttpEntity<>(elIds);
		elements = restTemplate.postForObject(url+"/elements", prequest,Elements[].class);
		
		for(Elements el: elements) {
			if(el.getClass().equals("Capability")) {
				myModel.addCapability((AbstractElementWrapper) el);

			}else if(el.getClass().equals("BusinessService")) {
				myModel.addService((AbstractElementWrapper) el);

			}
		}
		//TODO: Fetch Model Properties
		properties = restTemplate.getForObject(url+"/property/{parentId}",Properties[].class, modelId);
		myModel.setProperties(Arrays.asList(properties));
		
		
		//TODO: Fetch Model meta-Properties
		myModel.setMetaProperties(mPropertiesService.getMetaPropertiesByModel(modelId));

		
		return myModel;
	}
	
	public Models getbyId(UUID id) {
		return repository.findById(id);
	}
	
	//public 
}
