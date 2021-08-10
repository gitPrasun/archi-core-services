package com.prasun.EA.archi.graphQL.bff.schema.Models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {

	  private String name;
	  private String email;
	  private String org;
	  private String subOrg;
	  private String username;
	  private String password;
}
