package com.prasun.archi.core.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.core.models.PropertyHistory;
import com.prasun.archi.core.models.PropertyId;

public interface PropertyHistoryRepository extends JpaRepository<PropertyHistory, PropertyId> {
		public List<PropertyHistory> findByParentIdAndParentVersionAndName(String parentId,int parentVersion,String name);
}
