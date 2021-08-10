package com.prasun.EA.archi.graphQL.bff.schema.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsInput {

	  private String username;
	  private String password;
}
