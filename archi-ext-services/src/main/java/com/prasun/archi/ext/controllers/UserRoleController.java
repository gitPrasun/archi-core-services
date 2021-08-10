package com.prasun.archi.ext.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prasun.archi.ext.services.UserRoleServices;
import com.prasun.archi.ext.utility.pojo.models.Role;
import com.prasun.archi.ext.utility.pojo.models.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserRoleController {
	
	@Autowired
	UserRoleServices service;
	
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		return service.addUser(user);
	}

	@PostMapping("/role")
	@ResponseStatus(HttpStatus.CREATED)
	public Role createRole(@RequestBody Role role) {
		log.info("Got access: as: "+role.getCapabilityAccess());
		return service.addRole(role);
	}
	
	@DeleteMapping("/user")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@RequestBody User user) {
		 service.removeUser(user);;
	}
	
	@DeleteMapping("/role")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@RequestBody Role role) {
		 service.deleteRole(role);
	}
	
	@GetMapping("/role/user/{userId}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Role> getRolesByUser(@PathVariable String userId){
		return service.getRoleByUserId(userId);
	}
	
	@PostMapping("/role/usermodel")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Role> getRolesByUser(@RequestBody List<Role> userRoles){
		return service.getRoleByUserModel(userRoles);
	}
	
	@PostMapping("/roles")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Role> createRolesBulk(@RequestBody List<Role> userRoles){
		List<Role> returnRole = new ArrayList<Role>();
		for(Role r: userRoles) {
			returnRole.add(service.addRole(r));
		}
		return returnRole;
	}

}
