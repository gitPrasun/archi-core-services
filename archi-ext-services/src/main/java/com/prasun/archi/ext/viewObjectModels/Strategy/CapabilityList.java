package com.prasun.archi.ext.viewObjectModels.Strategy;

import java.util.ArrayList;
import java.util.List;

public class CapabilityList {
	private List<Capability> capabilities;

	public CapabilityList() {
		super();
		this.capabilities= new ArrayList();
	}

	public List<Capability> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<Capability> capabilities) {
		this.capabilities = capabilities;
	}
	
	
	
}
