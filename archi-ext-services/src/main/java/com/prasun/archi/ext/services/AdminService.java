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
import com.prasun.archi.ext.core.pojo.models.RelationshipsInModel;
import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Elements.ARCHIENTITYTYPE;
import com.prasun.archi.ext.core.pojo.repository.EiMRepository;
import com.prasun.archi.ext.core.pojo.repository.RiMRepository;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.core.pojo.models.ref.Relationships;

@Service
public class AdminService {

	@Value("${app.archi.core.service.url}")
	private String url;

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	EiMRepository eimRepository;

	@Autowired
	RiMRepository rimRepository;

	@Autowired
	CapabilityService cservice;

	@Autowired
	BSService bservice;

	@Autowired
	RelationshipService rservice;

	public int addPropertyToAll(List<Properties> p, ARCHIENTITYTYPE Entitytype) {
		// TODO: Get all the elements of type EntityTYpe

		List<Elements> subjectElements;
		HttpEntity<List<Properties>> prequest = new HttpEntity<>(p);
		int countupdated = 0;
		switch (Entitytype) {
		case CAPABILITY:
			countupdated = restTemplate.postForObject(url + "/properties/" + "Capability", prequest, Integer.class);
			break;
		case BUSINESSSERVICE:
			countupdated = restTemplate.postForObject(url + "/properties/" + "BusinessService", prequest,
					Integer.class);
			break;
		case INITIATIVE:
			countupdated = restTemplate.postForObject(url + "/properties/" + "Initiative", prequest, Integer.class);
			break;
		default:
			break;

		}

		// TODO:Add Property in Bulk ( possibly in single request
		return countupdated;
	}
	
	//Assume these properties do not have any parent references
	public List<Properties> addPropertiesToElement(List<Properties> p, String parentRef){
		HttpEntity<List<Properties>> prequest = new HttpEntity<>(p);
		int countupdated = restTemplate.postForObject(url + "/properties/parent", prequest, Integer.class, parentRef);
		if(p.size()==countupdated) {
			return p;
		}
		return null;
	}

	public int moveAllOrphanToModel(String modelId, int modelVersion) {
		// TODO: incomplete
		List<Elements> allElements = new ArrayList<Elements>();
		List<Relationships> allRelations = new ArrayList<Relationships>();
		
		int countOfUpdates = 0;

		allElements.addAll(cservice.getAllCapabilities());
		allElements.addAll(bservice.getAllBusinessServices());

		allRelations = Arrays.asList(rservice.getAllRelations());

		for (Elements e : allElements) {
			if (eimRepository.findByElementIdAndElementVersionAndModelIdAndModelVersion(e.getId().toString(),
					e.getVersion(), modelId, modelVersion) != null) {
				eimRepository.save(new ElementsInModel(e, modelId,modelVersion));
				countOfUpdates++;
			}
		}
		
		for (Relationships r:allRelations) {
			if(rimRepository.findByRelationshipIdAndRelationshipVersionAndModelIdAndModelVersion(r.getId().toString(), r.getVersion(), modelId, modelVersion)!=null) {
				rimRepository.save(new RelationshipsInModel(r,modelId,modelVersion));
				countOfUpdates++;
			}
		}
		return countOfUpdates;

		// allRelations.addAll(rservice.)
		

	}

}
