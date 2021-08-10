package com.prasun.archi.ext.core.pojo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prasun.archi.ext.core.pojo.models.ref.Elements;

@Entity
@Table(name = "elements_in_model")
public class ElementsInModel {

	public ElementsInModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ElementsInModel(Elements e, String modelId, int modelVersion) {
		super();

		this.elementId = e.getId().toString();
		this.elementVersion = e.getVersion();
		//this.parent_folder_id = "root";
		this.modelId = modelId;
		this.modelVersion = modelVersion;

	}

	@Id
	@GeneratedValue
	@Column(name = "cin_id")
	private int id;

	@Column(name = "element_id")
	private String elementId;

	@Column(name = "element_version")
	private int elementVersion;
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

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public int getElementVersion() {
		return elementVersion;
	}

	public void setElementVersion(int elementVersion) {
		this.elementVersion = elementVersion;
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
