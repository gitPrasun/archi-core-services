package com.prasun.archi.ext.core.pojo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.ext.core.pojo.models.RelationshipsInModel;

public interface RiMRepository extends JpaRepository<RelationshipsInModel, String> {

	public RelationshipsInModel findByRelationshipIdAndRelationshipVersionAndModelIdAndModelVersion(String relationId,
			int relationVersion, String modelId, int modelVersion);
}
