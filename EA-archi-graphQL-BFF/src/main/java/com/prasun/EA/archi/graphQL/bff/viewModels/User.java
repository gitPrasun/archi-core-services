package com.prasun.EA.archi.graphQL.bff.viewModels;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private UUID id;
	
//	@OneToMany(mappedBy="user")
//	@JoinColumn(name="role_id", referencedColumnName = "role_id", insertable = false, updatable = false)    
//	private List<Role> userRole;
	
	private String name;
	private String email;
	private String org;
	private String subOrg;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getSubOrg() {
		return subOrg;
	}
	public void setSubOrg(String subOrg) {
		this.subOrg = subOrg;
	}
	
	
	

}
