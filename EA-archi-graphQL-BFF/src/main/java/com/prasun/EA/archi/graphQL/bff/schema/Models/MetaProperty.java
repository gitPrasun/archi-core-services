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
public class MetaProperty {

	  private UUID id;
	  private String modelRefId;
	  private String baseType;
	  private String enterpriseEntity;
	  
	  private String name;
	  private String context;
	  private List<String> restrictedValues;
	  
	  private String cardinality;
	  private String optionality;
	  private String defaultValue;
	  private String min;
	  private String max;
}
