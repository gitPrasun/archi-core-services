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
import org.springframework.web.reactive.function.client.WebClient;

import com.prasun.archi.ext.core.pojo.models.ElementsInModel;
import com.prasun.archi.ext.core.pojo.models.RelationshipsInModel;
import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.core.pojo.models.ref.Relationships;
import com.prasun.archi.ext.core.pojo.repository.EiMRepository;
import com.prasun.archi.ext.core.pojo.repository.RiMRepository;
import com.prasun.archi.ext.viewObjectModels.Business.BusinessService;
import com.prasun.archi.ext.viewObjectModels.Strategy.Capability;
import com.prasun.archi.ext.viewObjectModels.Strategy.CapabilityList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DomainService {

	@Value("${app.archi.core.service.url}")
	private String url;
	RestTemplate restTemplate = new RestTemplate();

	private UUID effectiveModelId;
	private int effectiveModelVersion;

	public DomainService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DomainService(UUID effectiveModelId, int effectiveModelVersion) {
		super();
		this.effectiveModelId = effectiveModelId;
		this.effectiveModelVersion = effectiveModelVersion;
	}

	@Autowired
	PropertyService propService;

	@Autowired
	EiMRepository eimRepository;

	@Autowired
	RiMRepository rimRepository;

	public Capability getDomainById(String id, int version) {
		// TODO: Include header also later
		// HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
		// HttpEntity<String> request = new HttpEntity<>(new Foo("bar"));
		// TODO: to be modified
		Capability capability = restTemplate.getForObject(url + "/element/{Id}/{Version}", Capability.class, id,
				version);

		return this.getCompleteCapability(capability);
	}

//	public Capability[] getCapabilityForModel(String modelId){
//		List<ElementsInModel> eims = eimRepository.findByModelId(modelId); 
//		List<String> elIds = new ArrayList<String>();
//		for(ElementsInModel eim:eims) {
//			elIds.add(eim.getElementId());
//		}
//		
//		HttpEntity<List<String>> prequest = new HttpEntity<>(elIds);
//		return restTemplate.postForObject(url+"/elements", prequest,Capability[].class);
//	}

	public List<Capability> getAllCapabilities() {
		Capability[] cList = restTemplate.getForObject(url + "/elements/class?value={val}", Capability[].class,
				"Capability");
		return Arrays.asList(cList);

	}

	public List<Capability> getCapabilitiesByName(String name) {
		Capability[] cList = restTemplate.getForObject(url + "/elements/classname?class=Capability&name={name}",
				Capability[].class, name);
		for (int i = 0; i < cList.length; i++) {
			cList[i] = this.getCompleteCapability(cList[i]);
		}
		return Arrays.asList(cList);

	}

	public List<Capability> getCapabilitiesInModelByName(String name, String modelId, int modelVersion) {
		List<Capability> retC = new ArrayList<Capability>();
		Capability[] cList = restTemplate.getForObject(url + "/elements/classname?class=Capability&name={name}",
				Capability[].class, name);
		for (int i = 0; i < cList.length; i++) {
			ElementsInModel eim = eimRepository.findByElementIdAndElementVersionAndModelIdAndModelVersion(
					cList[i].getId().toString(), cList[i].getVersion(), modelId, modelVersion);
			if (eim != null)
				retC.add(this.getCompleteCapability(cList[i]));
		}
		return retC;

	}

	public Capability addCapability(Capability capability, String modelId, int modelversion) {
		ElementsInModel eim = new ElementsInModel(capability, modelId, modelversion);
		eimRepository.save(eim);
		return this.addCapability(capability);
	}

	public Capability addCapability(Capability capability) {

		if (capability.getId() == null)
			capability.setCreated_on(Timestamp.valueOf(LocalDateTime.now()));
		else
			capability.setCheckedin_on(Timestamp.valueOf(LocalDateTime.now()));
		//TODO: also need to updated the checkedOn_by in this case above
		
		HttpEntity<Elements> request = new HttpEntity<>((Elements) capability);
		Capability newCapability = restTemplate.postForObject(url + "/element", request, Capability.class);
		// TO: add default properties
		if (capability.getId() == null) {
			for (String propName : propService.getProperties("Capability")) {
				Properties p = new Properties();
				p.setParentId(newCapability.getId().toString());
				p.setName(propName);
				p.setParentVersion(newCapability.getVersion());

				HttpEntity<Properties> prequest = new HttpEntity<>(p);
				restTemplate.postForObject(url + "/property", prequest, Properties.class);

				ElementsInModel eim = new ElementsInModel();
				eim.setElementId(newCapability.getId().toString());
				eim.setElementVersion(newCapability.getVersion());
				// TODO: to be injected with Model
				eim.setModelId(effectiveModelId.toString());
				eim.setModelVersion(this.effectiveModelVersion);

				eimRepository.save(eim);
				// eimRepository.

				newCapability.addProperty(p);
			}
		}

		return (Capability) newCapability;
	}

	public Capability associateServiceToCapability(String CapabilityId, String BusinessServiceId, String relation) {
		Relationships newRel = new Relationships();
		newRel.setRelationClass(relation);
		newRel.setSourceId(CapabilityId);
		newRel.setTargetId(BusinessServiceId);
		newRel.setCreated_on(Timestamp.valueOf(LocalDateTime.now()));

		HttpEntity<Relationships> request = new HttpEntity<>(newRel);
		Relationships builtRelation = restTemplate.postForObject("/relationship", request, Relationships.class);

		Capability exitsingCapability = new Capability();

		exitsingCapability = restTemplate.getForObject(url + "/element/{id}/{version}", Capability.class, CapabilityId,
				1);

		RelationshipsInModel relationsInModel = new RelationshipsInModel();
		relationsInModel.setRelationshipId(builtRelation.getId().toString());
		relationsInModel.setRelationshipVersion(builtRelation.getVersion());
		// TODO: to be injected with Model
		relationsInModel.setModelId(this.effectiveModelId.toString());
		relationsInModel.setModelVersion(this.effectiveModelVersion);

		rimRepository.save(relationsInModel);

		return this.getCompleteCapability(exitsingCapability);

	}

	public Capability getCompleteCapability(Capability exitsingCapability) {

		// exitsingCapability.setProperties(Arrays.asList(props));
		// TODO: fetch Capability Properties
		Properties[] props = restTemplate.getForObject(url + "/property/{parentId}", Properties[].class,
				exitsingCapability.getId().toString());
		exitsingCapability.setProperties(Arrays.asList(props));

		// TODO: fetch Capabilities services

		BusinessService[] bizS = restTemplate.getForObject(url + "/relationship/related/entities/{elementId}",
				BusinessService[].class, exitsingCapability.getId().toString());
		exitsingCapability.setRelatedElements(Arrays.asList(bizS));

		// TODO: Add them to capability

		Relationships[] rels = restTemplate.getForObject(url + "/relationship/related/relations/{elementId}",
				Relationships[].class, exitsingCapability.getId().toString());
		exitsingCapability.setRelatedTo(Arrays.asList(rels));

		return exitsingCapability;
	}

//	public Flux<Capability> getAllCapabilities(){
//		
//		Flux<Capability> capabilities = WebClient.create(url)
//	      .get()
//	      .uri("/elements/class/{elementCalss}", "Capability") //TODO:to be implemented new controller
//	      .retrieve()
//	      .bodyToFlux(Capability.class);
//		
//		
//		return capabilities;
//	}
//	
//	public Mono<Capability> getCapabilityById(String id){
//		
//		Mono<Capability> capabilityFlux = 
//				WebClient.create(url)
//			      .get()
//			      .uri("/elements/{id}", id) //TODO:to be implemented new controller
//			      .retrieve()
//			      .bodyToMono(Capability.class);
//		
//		capabilityFlux.subscribe();
//		
//		Flux<Properties> properties = 
//				WebClient.create(url)
//			      .get()
//			      .uri("/elements/class/{elementCalss}", "Capability") //TODO:to be implemented new controller
//			      .retrieve()
//			      .bodyToFlux(Properties.class);
//		
//		
//		
//		return null;
//	}
}
