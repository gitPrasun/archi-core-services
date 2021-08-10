package com.prasun.archi.ext.core.pojo.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.prasun.archi.ext.utility.pojo.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ModelId.class)
public class Models {

	@Id
    @GeneratedValue
	private UUID id;
	@Id
	private int version;
	
	private String name;
	private String note;
	private String purpose;
	
	private String created_by;
	private String checkedin_by;
	
	private String deleted_by;
	
	private String checksum;
	
	private Timestamp created_on;
	private Timestamp checkedin_on;
	private Timestamp deleted_on;
}
