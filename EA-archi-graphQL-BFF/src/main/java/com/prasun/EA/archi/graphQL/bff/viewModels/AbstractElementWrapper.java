package com.prasun.EA.archi.graphQL.bff.viewModels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractElementWrapper extends Elements {
	protected List<Properties> properties;
	protected List<Elements> relatedElements;
	protected List<Relationships> relatedTo;
	
	public void addProperty(Properties p) {
		properties.add(p);
	}
	public void addRelatedElements(Elements r) {
		
		relatedElements.add(r);
	}
	public void addRelation(Relationships r) {
		
		relatedTo.add(r);
	}
	public List<Properties> getProperties() {
		return properties;
	}
	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}
	public List<Elements> getRelatedElements() {
		return relatedElements;
	}
	public void setRelatedElements(List<Elements> relatedElements) {
		this.relatedElements = relatedElements;
	}
	public List<Relationships> getRelatedTo() {
		return relatedTo;
	}
	public void setRelatedTo(List<Relationships> relatedTo) {
		this.relatedTo = relatedTo;
	}
	

	
}
