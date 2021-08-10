package com.prasun.EA.archi.graphQL.bff.viewModels;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {

	public static enum ACCESS_TYPE  {RO, CR,UPD,CR_UPD,DELETE, ALL}
	private UUID id;
	//@ManyToOne
	private String userId;
	
	//@ManyToOne
	private String modelId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public ACCESS_TYPE getCapability() {
		return capability;
	}
	public void setCapability(ACCESS_TYPE capability) {
		this.capability = capability;
	}
	public ACCESS_TYPE getBizservice() {
		return Bizservice;
	}
	public void setBizservice(ACCESS_TYPE bizservice) {
		Bizservice = bizservice;
	}
	private ACCESS_TYPE capability;
	private ACCESS_TYPE Bizservice;

	
	
	
}
