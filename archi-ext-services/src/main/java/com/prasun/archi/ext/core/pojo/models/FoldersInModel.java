package com.prasun.archi.ext.core.pojo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "folders_in_model")
public class FoldersInModel {
	
	@Id
	@GeneratedValue
	@Column(name = "cin_id")
	private int id;
	
	@Column(name = "folders_id")
	private String foldersId;
	
	@Column(name = "folders_version")
	private int foldersVersion;
	private String parent_folder_id;
	
	@Column(name = "model_id")
	private String modelId;
	
	@Column(name = "model_version")
	private int modelVersion;
	private int rank;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoldersId() {
		return foldersId;
	}
	public void setFoldersId(String foldersId) {
		this.foldersId = foldersId;
	}
	public int getFoldersVersion() {
		return foldersVersion;
	}
	public void setFoldersVersion(int foldersVersion) {
		this.foldersVersion = foldersVersion;
	}
	public String getParent_folder_id() {
		return parent_folder_id;
	}
	public void setParent_folder_id(String parent_folder_id) {
		this.parent_folder_id = parent_folder_id;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public int getModelVersion() {
		return modelVersion;
	}
	public void setModelVersion(int modelVersion) {
		this.modelVersion = modelVersion;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	

}
