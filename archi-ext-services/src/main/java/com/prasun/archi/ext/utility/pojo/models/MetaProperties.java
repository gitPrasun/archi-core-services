package com.prasun.archi.ext.utility.pojo.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MetaProperties {
	
	@Id
	@GeneratedValue
	private UUID metaPropertyId;
	
	private ArchiBaseEntities baseType;
	
	private String enterpriseEntity;
	private int version;
	
	private String name;
	private enum type {TEXT_UNRESTRICTED, DOUBLE_UNRESTRISTRICTED,INTEGER_UNRESTRICTED,RESTRICTED}
	private String context;
	
	@ElementCollection
	private List<String> restrictedValues;
	private int cardinality;
	private int optionality;
	private String strDefault;
	private double numRangeMin;
	private double numRangeMax;
	
	private Timestamp lastUpdated;
	private String lastUpdatedBy;
	
	private String modelRef;

	
	
	

}
