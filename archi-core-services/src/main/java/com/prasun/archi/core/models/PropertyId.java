package com.prasun.archi.core.models;

import java.io.Serializable;

public class PropertyId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7830428347214887356L;
	
	private String parentId;
	private int parentVersion;	
	private String name;
	public PropertyId(String parent_id, int parent_version, String name) {
		super();
		this.parentId = parent_id;
		this.parentVersion = parent_version;
		this.name = name;
	}
	
	
	public PropertyId() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		PropertyId other = (PropertyId) obj;
		
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
