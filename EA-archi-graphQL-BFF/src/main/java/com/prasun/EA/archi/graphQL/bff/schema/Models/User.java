package com.prasun.EA.archi.graphQL.bff.schema.Models;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	  private UUID id;
	  private String name;
	  private String email;
	  private String org;
	  private String subOrg;
	  private String username;
	  private String password;
}
