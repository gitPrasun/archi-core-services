package com.prasun.archi.ext.viewObjectModels.Strategy;

import java.util.ArrayList;
import java.util.List;

import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.core.pojo.models.ref.Relationships;

public class Domain extends Capability {
	
	public static final String META_TYPE = "DOMAIN";
	
	private List<Capability> capabilities;
	
	public Domain() {
		super();
		this.setElementClass("Capability");
		this.properties = new ArrayList<Properties>();
		this.relatedTo = new ArrayList<Relationships>();
		this.relatedElements = new ArrayList<Elements>();
		
	}
	
	
	
	

}
