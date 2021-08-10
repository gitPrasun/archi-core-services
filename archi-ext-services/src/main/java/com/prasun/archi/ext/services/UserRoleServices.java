package com.prasun.archi.ext.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.prasun.archi.ext.core.pojo.repository.RoleRepository;
import com.prasun.archi.ext.core.pojo.repository.UserRepository;
import com.prasun.archi.ext.utility.pojo.models.Role;
import com.prasun.archi.ext.utility.pojo.models.User;

import lombok.extern.slf4j.Slf4j;

//import lombok.extern.slf4j.Slf4j;



@Slf4j

@Service
public class UserRoleServices {
	
	@Autowired 
	RoleRepository rRepository;
	
	@Autowired
	UserRepository uRepository;
	
	public User addUser(User user) {
		return uRepository.save(user);
	}
	
	public void removeUser(User user) {
		 uRepository.delete(user);
	}
	
	public Role addRole(Role role) {
		log.info("Got:"+role.getUserId());
		return rRepository.save(role);
	}
	
	public List<Role> addOrModifyRolesBulk(List<Role> roles){
		List<Role> returnRoles = new ArrayList<Role>();
		for(Role r :roles) {
			returnRoles.add(this.addRole(r));
		}
		return returnRoles;
	}
	
	public void deleteRole(Role role) {
		rRepository.delete(role);
	}
	
	public List<Role> getRoleByUserId(String userId){
		return rRepository.findByUserId(userId);
	}
	public Role getRoleByUserModel(String userId,String modelId) {
		return rRepository.findByUserIdAndModelId(userId, modelId);
	}
	
	public List<Role> getRoleByUserModel(List<Role> userRoles) {
		List<Role> returnRoles = new ArrayList<Role>();
		for(Role eachRoleInput:userRoles) {
			Role r = rRepository.findByUserIdAndModelId(eachRoleInput.getUserId(), eachRoleInput.getModelId());
			if(r!=null) {returnRoles.add(r);}
		}
		return returnRoles;
	}
	
}
