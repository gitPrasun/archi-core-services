package com.prasun.archi.ext.utility.pojo.models;

import java.util.List;
import java.util.Map;

public class MetaModel {
	
	public String ArchiEntity;
	public List<String> ArchiProperties;
	public List<Map<String,List<String>>> restrictions;
	public String getArchiEntity() {
		return ArchiEntity;
	}
	public void setArchiEntity(String archiEntity) {
		ArchiEntity = archiEntity;
	}
	public List<String> getArchiProperties() {
		return ArchiProperties;
	}
	public void setArchiProperties(List<String> archiProperties) {
		ArchiProperties = archiProperties;
	}
	public List<Map<String, List<String>>> getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(List<Map<String, List<String>>> restrictions) {
		this.restrictions = restrictions;
	}
	
}

