package com.prasun.archi.core.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.core.models.Relationships;

public interface RelationshipRepository extends JpaRepository<Relationships, String> {
	
	Relationships findBySourceIdAndTargetIdAndRelationClass(String sourceId, String targetId, String relationClass); 
	List<Relationships> findBySourceId(String sourceId);
	List<Relationships> findByTargetId(String targetId);

}
