package com.prasun.archi.ext.core.pojo.models;

import java.io.Serializable;
import java.util.UUID;

public class ModelId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3823129229824123834L;
	
	private UUID id;
	private int version;
	public ModelId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModelId(UUID id, int version) {
		super();
		this.id = id;
		this.version = version;
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
		ModelId other = (ModelId) obj;
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
