package com.prasun.archi.core.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.core.models.Properties;

public interface PropertyRepository extends JpaRepository<Properties, String> {

	Properties findByParentIdAndParentVersionAndName(String parentId,int parentVersion, String name);
	List<Properties> findByParentId(String parentId);
	List<Properties> findByNameAndValue(String name, String value);
}
