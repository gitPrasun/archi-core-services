package com.prasun.archi.ext.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.prasun.archi.ext.services.BSService;
import com.prasun.archi.ext.viewObjectModels.Business.BusinessService;

@RestController
public class BusinessServiceController {

	@Autowired
	BSService service;
	
	@GetMapping("/BusinessService")
	public List<BusinessService>getAll() {
		return service.getAllBusinessServices();
	}
	
	@PostMapping("/BusinessService")
	@ResponseStatus(HttpStatus.CREATED)
	public BusinessService addBizService(@RequestBody BusinessService bizService) {
		return service.addBusinessService(bizService);
	}
	
	@PostMapping("/BusinessServices")
	@ResponseStatus(HttpStatus.CREATED)
	public List<BusinessService> addBizServiceBulk(@RequestBody List<BusinessService> bizService) {
		List<BusinessService> retC = new ArrayList<BusinessService>();
		for (BusinessService bs:bizService) {
			retC.add(service.addBusinessService(bs));
		}
		return retC;
	}
	
	@PostMapping("/BusinessService/model{modelId}/{modelVersion}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<BusinessService> addBizService(@RequestBody List<BusinessService> bizService, @PathVariable String modelId,@PathVariable int modelVersion) {
		List<BusinessService> returnC = new ArrayList<BusinessService>();
		
		for (BusinessService bs:bizService) {
			List<BusinessService> bss = service.getBusinessServiceInModelByName(bs.getName(), modelId, modelVersion);
			if(bss!=null)
				returnC.add(bss.get(0));
		}
		
		return returnC;
	}
	
	@GetMapping("/BusinessService/{id}/{version}")
	@ResponseStatus(HttpStatus.FOUND)
	public BusinessService getBizService(@PathVariable String id,@PathVariable int version) {
		return service.getBusinessServiceById(id, version);
	}
	
	@GetMapping("/BusinessService/{name}")
	@ResponseStatus(HttpStatus.FOUND)
	public BusinessService getBizServicebyName(@PathVariable String name) {
		return service.getBusinessServiceById(name, 1);
	}
	
	
	
}
