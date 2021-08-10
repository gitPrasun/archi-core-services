package com.prasun.archi.ext.core.pojo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.ext.core.pojo.models.ElementsInModel;

public interface EiMRepository extends JpaRepository<ElementsInModel, String> {

	public ElementsInModel findByElementIdAndElementVersionAndModelIdAndModelVersion(String elementId,
			int elementVersion, String modelId, int modelVersion);
	
	public List<ElementsInModel> findByModelId(String modelId);

}
