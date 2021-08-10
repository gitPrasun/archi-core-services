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
public class Element {

	private UUID id;
	private String name;
	private String description;
	private List<Property> properties;
}
