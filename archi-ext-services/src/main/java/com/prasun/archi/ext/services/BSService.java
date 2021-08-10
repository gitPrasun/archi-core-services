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
import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.core.pojo.repository.EiMRepository;
import com.prasun.archi.ext.core.pojo.repository.RiMRepository;
import com.prasun.archi.ext.viewObjectModels.Business.BusinessService;
import com.prasun.archi.ext.viewObjectModels.Strategy.Capability;

@Service
public class BSService {

	@Value("${app.archi.core.service.url}")
	private String url;
	RestTemplate restTemplate = new RestTemplate();

	private UUID effectiveModelId;
	private int effectiveModelVersion;

	@Autowired
	PropertyService propService;

	@Autowired
	EiMRepository eimRepository;

	@Autowired
	RiMRepository rimRepository;

	public BSService(UUID effectiveModelId, int effectiveModelVersion) {
		super();
		this.effectiveModelId = effectiveModelId;
		this.effectiveModelVersion = effectiveModelVersion;
	}

	public BSService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<BusinessService> getAllBusinessServices() {
		BusinessService[] cList = restTemplate.getForObject(url + "/elements/class?value={val}",
				BusinessService[].class, "BusinessService");
		return Arrays.asList(cList);

	}

	public BusinessService getBusinessServiceById(String id, int version) {
		BusinessService bs = restTemplate.getForObject(url + "/element/{Id}/{Version}", BusinessService.class, id,
				version);
		return bs;

	}

	public BusinessService getBusinessServiceByName(String name) {
		return restTemplate.getForObject(url + "/element/{class}/{name}", BusinessService.class, "BusinessService",
				name);
	}

	public List<BusinessService> getBusinessServiceInModelByName(String name, String modelId, int modelVersion) {
		List<BusinessService> retBs = new ArrayList<BusinessService>();
		BusinessService[] bsWithSameNames = restTemplate.getForObject(url + "/element/{class}/{name}",
				BusinessService[].class, "BusinessService", name);
		if (bsWithSameNames != null && bsWithSameNames.length > 0) {
			for (BusinessService bs : bsWithSameNames) {
				ElementsInModel eim = eimRepository.findByElementIdAndElementVersionAndModelIdAndModelVersion(
						bs.getId().toString(), bs.getVersion(), modelId, modelVersion);
				if (eim != null)
					retBs.add(bs);
			}

		}
		return retBs;
	}

	public BusinessService addBusinessService(BusinessService bs) {

		if (bs.getId() == null)
			bs.setCreated_on(Timestamp.valueOf(LocalDateTime.now()));
		else
			bs.setCheckedin_on(Timestamp.valueOf(LocalDateTime.now()));
		
		HttpEntity<Elements> request = new HttpEntity<>((Elements) bs);
		BusinessService newBusinessService = restTemplate.postForObject(url + "/element", request,
				BusinessService.class);
		// TO: add default properties
		if (bs.getId() == null) {
			for (String propName : propService.getProperties("BusinessService")) {
				Properties p = new Properties();
				p.setParentId(newBusinessService.getId().toString());
				p.setName(propName);
				p.setParentVersion(newBusinessService.getVersion());

				HttpEntity<Properties> prequest = new HttpEntity<>(p);
				restTemplate.postForObject(url + "/property", prequest, Properties.class);

				ElementsInModel eim = new ElementsInModel();
				eim.setElementId(newBusinessService.getId().toString());
				eim.setElementVersion(newBusinessService.getVersion());
				// TODO: to be injected with Model
				eim.setModelId(effectiveModelId.toString());
				eim.setModelVersion(this.effectiveModelVersion);

				eimRepository.save(eim);

				newBusinessService.addProperty(p);
			}
		}

		return newBusinessService;

	}

}
