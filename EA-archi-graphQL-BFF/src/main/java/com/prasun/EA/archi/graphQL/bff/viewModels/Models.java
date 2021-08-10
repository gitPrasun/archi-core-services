package com.prasun.EA.archi.graphQL.bff.viewModels;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Models {


	private UUID id;
	private int version;
	
	private String name;
	private String note;
	private String purpose;
	
	private String created_by;
	private String checkedin_by;
	private List<AbstractElementWrapper> domain;
	private List<AbstractElementWrapper> capabilities;
	private List<AbstractElementWrapper> services;
	
	private List<Properties> properties;
	private List<MetaProperties> metaProperties;
	
	
	
	
	
	public Models() {
		super();
		// TODO Auto-generated constructor stub
		this.domain = new ArrayList<AbstractElementWrapper>();
		this.capabilities = new ArrayList<AbstractElementWrapper>();
		this.properties = new ArrayList<Properties>();
		this.metaProperties = new ArrayList<MetaProperties>();
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCheckedin_by() {
		return checkedin_by;
	}
	public void setCheckedin_by(String checkedin_by) {
		this.checkedin_by = checkedin_by;
	}
	public String getDeleted_by() {
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public Timestamp getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}
	public Timestamp getCheckedin_on() {
		return checkedin_on;
	}
	public void setCheckedin_on(Timestamp checkedin_on) {
		this.checkedin_on = checkedin_on;
	}
	public Timestamp getDeleted_on() {
		return deleted_on;
	}
	public void setDeleted_on(Timestamp deleted_on) {
		this.deleted_on = deleted_on;
	}
	private String deleted_by;
	
	private String checksum;
	
	private Timestamp created_on;
	private Timestamp checkedin_on;
	private Timestamp deleted_on;





	public List<AbstractElementWrapper> getDomain() {
		return domain;
	}
	public void setDomain(List<AbstractElementWrapper> domain) {
		this.domain = domain;
	}
	public List<AbstractElementWrapper> getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(List<AbstractElementWrapper> capabilities) {
		this.capabilities = capabilities;
	}
	public List<AbstractElementWrapper> getServices() {
		return services;
	}
	public void setServices(List<AbstractElementWrapper> services) {
		this.services = services;
	}
	public List<Properties> getProperties() {
		return properties;
	}
	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}
	public List<MetaProperties> getMetaProperties() {
		return metaProperties;
	}
	public void setMetaProperties(List<MetaProperties> metaProperties) {
		this.metaProperties = metaProperties;
	}
}
