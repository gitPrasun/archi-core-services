package com.prasun.EA.archi.graphQL.bff.schema.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyInput {

	private String name;
	private String value;
}
