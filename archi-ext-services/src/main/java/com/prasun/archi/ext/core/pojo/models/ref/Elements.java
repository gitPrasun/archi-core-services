package com.prasun.archi.ext.core.pojo.models.ref;

import java.sql.Timestamp;
import java.util.UUID;



public class Elements {


	public enum ARCHIENTITYTYPE {CAPABILITY, BUSINESSSERVICE, INITIATIVE}
	private UUID id;
	private int version;
	
	private String elementClass;
	
	private String name;
	private String documentation;
	private String type;
	
	private String created_by;
	private String checkedin_by;
	private String deleted_by;
	
	private String checksum;
	
	private Timestamp created_on;
	private Timestamp checkedin_on;
	private Timestamp deleted_on;
	
	
	
	
	
	public Elements() {
		super();
	}
	public Elements(int version, String elementClass, String name, String documentation, String type, String created_by,
			String checkedin_by, String deleted_by, String checksum, Timestamp created_on, Timestamp checkedin_on,
			Timestamp deleted_on) {
		super();
		this.version = version;
		this.elementClass = elementClass;
		this.name = name;
		this.documentation = documentation;
		this.type = type;
		this.created_by = created_by;
		this.checkedin_by = checkedin_by;
		this.deleted_by = deleted_by;
		this.checksum = checksum;
		this.created_on = created_on;
		this.checkedin_on = checkedin_on;
		this.deleted_on = deleted_on;
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
	public String getElementClass() {
		return elementClass;
	}
	public void setElementClass(String elementClass) {
		this.elementClass = elementClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	
	
}
