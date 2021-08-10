package com.prasun.archi.ext.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prasun.archi.ext.core.pojo.models.Models;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.services.ModelCompositeService;
import com.prasun.archi.ext.services.ModelService;
import com.prasun.archi.ext.services.UserRoleServices;
import com.prasun.archi.ext.viewObjectModels.ModelViewObject;
import com.prasun.archi.ext.viewObjectModels.UserModelVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class ModelController {

	@Autowired
	ModelService service;
	
	@Autowired
	ModelCompositeService mcService;
	
	@PostMapping("/model")
	@ResponseStatus(HttpStatus.CREATED)
	Models createModel (@RequestBody Models model) {
		log.info("received"+model.getName());
		return service.createModel(model);
	}
	
	@PostMapping("/model/composite")
	@ResponseStatus(HttpStatus.CREATED)
	ModelViewObject createModelComposite (@RequestBody ModelViewObject model) {
		//log.info("received"+model.getName());
		return mcService.createAll(model);
	}
	
//	@PostMapping("/model/properties")
//	@ResponseStatus(HttpStatus.CREATED)
//	ModelViewObject createModelComposite (@RequestBody List<Properties> propertyInpits) {
//		//log.info("received"+model.getName());
//		return mcService.createAll(model);
//	}
	
	@GetMapping("/models")
	@ResponseStatus(HttpStatus.FOUND)
	List<Models> getAllModels () {
		return service.getAll();
	}
	
	@GetMapping("/models/{modelId}")
	@ResponseStatus(HttpStatus.FOUND)
	Models getAllModels (@PathVariable String modelId) {
		return service.getbyId(UUID.fromString(modelId));
	}
	@GetMapping("/models/detail/{modelId}")
	@ResponseStatus(HttpStatus.FOUND)
	UserModelVO getModelDetails (@PathVariable String modelId) {
		return service.getCompleteModelDetail(modelId);
	}
	
//	@GetMapping("/model/{modelId}")
//	Models getModel
}
