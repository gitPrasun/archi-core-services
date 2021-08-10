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
public class ModelInput {

	private String name;
	private String note;
	private String purpose;
	private List<MetaPropertyInput> metaProperties;
	private List<PropertyInput>properties;
	private List<ElementInput>domain;
	private List<ElementInput> capabilities;
	private String createdBy;
}
