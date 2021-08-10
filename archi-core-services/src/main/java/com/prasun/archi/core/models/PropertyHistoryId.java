package com.prasun.archi.core.models;

import java.io.Serializable;
import java.sql.Timestamp;



public class PropertyHistoryId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4000560917508244177L;
	
	String parentId;
	int parentVersion;
	String name;
	Timestamp lastModified;
	public PropertyHistoryId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PropertyHistoryId(String parentId, int parentVersion, String name, Timestamp lastModified) {
		super();
		this.parentId = parentId;
		this.parentVersion = parentVersion;
		this.name = name;
		this.lastModified = lastModified;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastModified == null) ? 0 : lastModified.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + parentVersion;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyHistoryId other = (PropertyHistoryId) obj;
		if (lastModified == null) {
			if (other.lastModified != null)
				return false;
		} else if (!lastModified.equals(other.lastModified))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (parentVersion != other.parentVersion)
			return false;
		return true;
	}
	
	
	

}
