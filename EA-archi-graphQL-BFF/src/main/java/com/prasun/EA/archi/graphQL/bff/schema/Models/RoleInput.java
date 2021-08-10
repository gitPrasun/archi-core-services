package com.prasun.EA.archi.graphQL.bff.schema.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleInput {

	 private String id;
	 private String userId;
	 private String capabilityAccess;
	 private String serviceAccess;
	 private String modelId;
}
