package com.prasun.archi.core.models.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.core.models.ElementId;
import com.prasun.archi.core.models.Elements;

public interface ElementRepository extends JpaRepository<Elements, ElementId> {
	
	Elements findByIdAndVersion(UUID id, int version);
	List<Elements> findById(UUID id);
	List<Elements> findByElementClass(String elClass);
	List<Elements> findByNameAndElementClass(String name,String elClass);
	//List<Elements> findAllByElementClassAndIds(String elClass,List<UUID> ids);
	//List<Elements> findAllByIds(List<UUID> ids);

}
