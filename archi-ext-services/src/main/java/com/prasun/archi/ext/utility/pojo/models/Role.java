package com.prasun.archi.ext.utility.pojo.models;

import java.util.UUID;

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
public class Role {

	//public static enum String  {RO, CR,UPD,CR_UPD,DELETE, ALL}
	
	@Id
	@GeneratedValue
	private UUID id;
	//@ManyToOne
	private String userId;
	
	//@ManyToOne
	private String modelId;
	private String capabilityAccess;
	private String serviceAccess;

	
	
	
}
