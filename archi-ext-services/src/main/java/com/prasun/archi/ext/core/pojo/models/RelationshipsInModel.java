package com.prasun.archi.ext.core.pojo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prasun.archi.ext.core.pojo.models.ref.Relationships;

@Entity
@Table(name = "relationships_in_model")
public class RelationshipsInModel {
	
	
	
	public RelationshipsInModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public RelationshipsInModel(Relationships r,
			String modelId, int modelVersion) {
		super();
		this.relationshipId = r.getId().toString();
		this.relationshipVersion = r.getVersion();

		this.modelId = modelId;
		this.modelVersion = modelVersion;

	}




	@Id
	@GeneratedValue
	@Column(name = "cin_id")
	private int id;
	
	@Column(name = "relationship_id")
	private String relationshipId;
	
	@Column(name = "relationship_version")
	private int relationshipVersion;
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
	public String getRelationshipId() {
		return relationshipId;
	}
	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}
	public int getRelationshipVersion() {
		return relationshipVersion;
	}
	public void setRelationshipVersion(int relationshipVersion) {
		this.relationshipVersion = relationshipVersion;
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
