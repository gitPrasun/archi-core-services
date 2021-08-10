package com.prasun.archi.ext.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.services.CapabilityService;
import com.prasun.archi.ext.viewObjectModels.Strategy.Capability;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CapabilityController {

	@Autowired
	CapabilityService service;

	@Operation(summary = "GET All Capabilitis for all Models ")
	@GetMapping("/capabilities")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Capability> getAll() {
		return service.getAllCapabilities();
	}

	@Operation(summary = "Create a single  Capability")
	@PostMapping("/capability")
	@ResponseStatus(HttpStatus.CREATED)
	public Capability addCapability(@RequestBody Capability capability) {
		return service.addCapability(capability);
	}

	@Operation(summary = "GET Capability filtered by properties")
	@PostMapping("/capability/properties/model/{modelId}/{modelVersion}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Capability> findCapabilityByProperty(@RequestBody List<Properties> propertiesInput,
			@PathVariable String modelId, @PathVariable int modelVersion) {
		return service.getCapabilityByProperty(propertiesInput, modelId, modelVersion);
	}

	@Operation(summary = "Create Capabilities in bulk")
	@PostMapping("/capabilities")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Capability> addCapabilitiesInBulk(@RequestBody List<Capability> capability) {
		List<Capability> returnCapabilities = new ArrayList<Capability>();
		for (Capability each : capability) {
			returnCapabilities.add(service.addCapability(each));
		}
		return returnCapabilities;
	}

	@Operation(summary = "Associate Service to capability")
	@GetMapping("/capability/{cid}/BusinessService/{sid}")
	@ResponseStatus(HttpStatus.FOUND)
	public Capability addServiceToCapability(@PathVariable String cid, @PathVariable String sid) {
		return service.associateServiceToCapability(cid, cid, "RealizationRelationship");
	}

	@Operation(summary = "GET Capability filtered by name in all models")
	@GetMapping("/capability/{name}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Capability> getCapabilityByName(@PathVariable String name) {
		return service.getCapabilitiesByName(name);
	}

	@Operation(summary = "GET Capability filtered by capability names for a model")
	@PostMapping("/capabilities/findBulkByName/model/{modelId}/{modelVersion}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Capability> finCapabilityInModelInBulk(@RequestBody List<Capability> capability,
			@PathVariable String modelId, @PathVariable int modelVersion) {
		List<Capability> returnList = new ArrayList<Capability>();
		for (Capability c : capability) {
			List<Capability> found = service.getCapabilitiesInModelByName(c.getName(), modelId, modelVersion);
			if (found != null)
				returnList.add(found.get(0));
		}

		return returnList;
	}

	@GetMapping("/capability/{id}/{version}")
	@ResponseStatus(HttpStatus.FOUND)
	public Capability getCapability(@PathVariable String id, @PathVariable int version) {
		return service.getCapabilityById(id, version);
	}

}
