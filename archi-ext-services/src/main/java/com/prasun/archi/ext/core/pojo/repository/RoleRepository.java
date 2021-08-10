package com.prasun.archi.ext.core.pojo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.ext.utility.pojo.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	List<Role> findByUserId(String userId);
	Role findByUserIdAndModelId(String userId,String modelId);
}
