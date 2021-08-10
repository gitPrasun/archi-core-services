package com.prasun.EA.archi.graphQL.bff.viewModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CredentialsInputDto {

	String userName;
	String password;
	
	public CredentialsInputDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CredentialsInputDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
