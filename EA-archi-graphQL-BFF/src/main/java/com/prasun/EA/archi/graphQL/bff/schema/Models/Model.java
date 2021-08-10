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
public class Model {

	private UUID id;
	private List<Element> domain;
	private List<Element> capabilities;
	private List<Element> services;
	
	//properties: [Property]
	private List<Property> properties;
	
	private List<MetaProperty> metaProperties;
	private int version;
	private String name;
	private String note;
	private String purpose;
	
}
