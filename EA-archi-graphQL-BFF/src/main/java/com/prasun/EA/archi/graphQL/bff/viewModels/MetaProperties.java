package com.prasun.EA.archi.graphQL.bff.viewModels;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaProperties {
	

	private UUID metaPropertyId;
	
	private ArchiBaseEntities baseType;
	
	private String enterpriseEntity;
	private int version;
	
	private String name;
	private enum type {TEXT_UNRESTRICTED, DOUBLE_UNRESTRISTRICTED,INTEGER_UNRESTRICTED,RESTRICTED}
	private String context;
	
	private List<String> restrictedValues;
	private int cardinality;
	private int optionality;
	private String strDefault;
	private double numRangeMin;
	private double numRangeMax;
	
	private Timestamp lastUpdated;
	private String lastUpdatedBy;
	
	private String modelRef;

	public MetaProperties() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MetaProperties(ArchiBaseEntities baseType, String enterpriseEntity, int version, String name, String context,
			List<String> restrictedValues, int cardinality, int optionality, String strDefault, double numRangeMin,
			double numRangeMax, Timestamp lastUpdated, String lastUpdatedBy, String modelRef) {
		super();
		this.baseType = baseType;
		this.enterpriseEntity = enterpriseEntity;
		this.version = version;
		this.name = name;
		this.context = context;
		this.restrictedValues = restrictedValues;
		this.cardinality = cardinality;
		this.optionality = optionality;
		this.strDefault = strDefault;
		this.numRangeMin = numRangeMin;
		this.numRangeMax = numRangeMax;
		this.lastUpdated = lastUpdated;
		this.lastUpdatedBy = lastUpdatedBy;
		this.modelRef = modelRef;
	}

	public ArchiBaseEntities getBaseType() {
		return baseType;
	}

	public void setBaseType(ArchiBaseEntities baseType) {
		this.baseType = baseType;
	}

	public String getEnterpriseEntity() {
		return enterpriseEntity;
	}

	public void setEnterpriseEntity(String enterpriseEntity) {
		this.enterpriseEntity = enterpriseEntity;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<?> getRestrictedValues() {
		return restrictedValues;
	}

	public void setRestrictedValues(List<String> restrictedValues) {
		this.restrictedValues = restrictedValues;
	}

	public int getCardinality() {
		return cardinality;
	}

	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}

	public int getOptionality() {
		return optionality;
	}

	public void setOptionality(int optionality) {
		this.optionality = optionality;
	}

	public String getStrDefault() {
		return strDefault;
	}

	public void setStrDefault(String strDefault) {
		this.strDefault = strDefault;
	}

	public double getNumRangeMin() {
		return numRangeMin;
	}

	public void setNumRangeMin(double numRangeMin) {
		this.numRangeMin = numRangeMin;
	}

	public double getNumRangeMax() {
		return numRangeMax;
	}

	public void setNumRangeMax(double numRangeMax) {
		this.numRangeMax = numRangeMax;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getModelRef() {
		return modelRef;
	}

	public void setModelRef(String modelRef) {
		this.modelRef = modelRef;
	}

	public UUID getMetaPropertyId() {
		return metaPropertyId;
	}
	
	
	
	

}
