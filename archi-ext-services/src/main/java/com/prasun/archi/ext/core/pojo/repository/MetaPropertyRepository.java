package com.prasun.archi.ext.core.pojo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.ext.utility.pojo.models.MetaProperties;

public interface MetaPropertyRepository extends JpaRepository<MetaProperties, UUID> {
	
    List<MetaProperties> findByModelRef (String modelRef);
    List<MetaProperties> findByModelRefAndEnterpriseEntity(String modelRef, String enterpriseEntity);
    List<EnterpriseEntityView> getMetaPropertyByModelRef(String modelRef);
    MetaProperties findByModelRefAndName(String modelRef,String name);
}
