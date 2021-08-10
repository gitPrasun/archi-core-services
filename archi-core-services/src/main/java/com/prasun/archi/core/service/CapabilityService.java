package com.prasun.archi.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasun.archi.core.models.Elements;

@Service
public class CapabilityService {

	@Autowired
	private ElementService eService;
	
	@Autowired
	private PropertyService pService;
	
	@Autowired
	private RelationshipService rService;
	
	
	List<Elements> getAll(){
		return null;
	}
}
