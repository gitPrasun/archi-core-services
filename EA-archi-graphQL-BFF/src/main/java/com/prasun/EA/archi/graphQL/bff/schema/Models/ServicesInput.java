package com.prasun.EA.archi.graphQL.bff.schema.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicesInput {

	private String id;
	private String parentCapabilityId;
	private String name;
	private String description;
	private List<PropertyInput> properties;
}

