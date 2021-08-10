package com.prasun.archi.ext.viewObjectModels.Strategy;

import java.util.ArrayList;
import java.util.List;

import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.core.pojo.models.ref.Relationships;
import com.prasun.archi.ext.viewObjectModels.AbstractElementWrapper;

public class Capability extends AbstractElementWrapper {

	
	public Capability() {
		super();
		this.setElementClass("Capability");
		this.properties = new ArrayList<Properties>();
		this.relatedTo = new ArrayList<Relationships>();
		this.relatedElements = new ArrayList<Elements>();
	}

//	public void initProperties(Properties p) {
//		this.properties.add(p);
//	}

}
