package com.prasun.archi.ext.core.pojo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasun.archi.ext.core.pojo.models.Models;

public interface ModelRepository extends JpaRepository<Models,String>{
	
	Models findById(UUID id);

}
