package com.prasun.archi.ext.viewObjectModels;

import java.util.ArrayList;
import java.util.List;

import com.prasun.archi.ext.core.pojo.models.Models;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.utility.pojo.models.MetaProperties;

public class UserModelVO extends Models {
	
	private List<AbstractElementWrapper> capabilities;
	private List<AbstractElementWrapper> services;
	private List<Properties> properties;
	private List<MetaProperties> metaProperties;
	
	
	
	
	
	public UserModelVO() {
		super();
		// TODO Auto-generated constructor stub
		this.capabilities = new ArrayList<AbstractElementWrapper>();
		this.services = new ArrayList<AbstractElementWrapper>();
		this.properties =new ArrayList<Properties>();
		this.metaProperties =  new ArrayList<MetaProperties>();
		
	}
	
	public void addCapability(AbstractElementWrapper c) {
		this.capabilities.add(c);
	}
	
	public void addService(AbstractElementWrapper s) {
		this.services.add(s);
	} 
	
	public void addProperty(Properties p) {
		this.properties.add(p);
	}
	
	public void addMetaProperty(MetaProperties mp) {
		this.metaProperties.add(mp);
	}
	
	public List<AbstractElementWrapper> getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(List<AbstractElementWrapper> capabilities) {
		this.capabilities = capabilities;
	}
	public List<AbstractElementWrapper> getSevrices() {
		return services;
	}
	public void setSevrices(List<AbstractElementWrapper> sevrices) {
		this.services = sevrices;
	}
	public List<Properties> getProperties() {
		return properties;
	}
	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}
	public List<MetaProperties> getMetaProperties() {
		return metaProperties;
	}
	public void setMetaProperties(List<MetaProperties> metaProperties) {
		this.metaProperties = metaProperties;
	}
	
	
	

}
