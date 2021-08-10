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
public class Role {
	
	 private UUID id;
	 private String userId;
	 private String modelId;
	 private String capabilityAccess;
	 private String serviceAccess;

}
