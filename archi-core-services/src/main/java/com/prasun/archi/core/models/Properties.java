package com.prasun.archi.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PropertyId.class)
public class Properties {

	@Id
	@Column(name = "parent_id")
	private String parentId;
	@Id
	@Column(name = "parent_version")
	private int parentVersion;
	private int rank;
	private int pos;
	
	
	@Id
	private String name;
	private String value;
	
	
	
	public Properties() {
		super();
	}
	public Properties(String parent_id, int parent_version, int rank, String name, String value) {
		super();
		this.parentId = parent_id;
		this.parentVersion = parent_version;
		this.rank = rank;
		this.name = name;
		this.value = value;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parent_id) {
		this.parentId = parent_id;
	}
	public int getParentVersion() {
		return parentVersion;
	}
	public void setParentVersion(int parent_version) {
		this.parentVersion = parent_version;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	
	
}
