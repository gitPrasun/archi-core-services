package com.prasun.archi.core.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PropertyHistoryId.class)
public class PropertyHistory {
	@Id
	String parentId;
	@Id
	int parentVersion;
	@Id
	String name;
	String value;
	@Id
	Timestamp lastModified;
	
	
	

	public PropertyHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public PropertyHistory(Properties p, Timestamp lastModified) {
		super();
		this.setName(p.getName());
		this.setParentId(p.getParentId());
		this.setParentVersion(p.getParentVersion());
		this.setValue(p.getValue());
		
		this.lastModified = lastModified;
	}



	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	public int getParentVersion() {
		return parentVersion;
	}



	public void setParentVersion(int parentVersion) {
		this.parentVersion = parentVersion;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}
	 

}
