package com.prasun.archi.core.models;

import java.io.Serializable;
import java.util.UUID;

public class ElementId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7088266721196211640L;
	private UUID id;
	private int version;
	
	public ElementId(UUID id, int version) {
		super();
		this.id = id;
		this.version = version;
	}

	public ElementId() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + version;
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
		ElementId other = (ElementId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
	
	

}
