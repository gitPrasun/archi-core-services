package com.prasun.archi.ext.viewObjectModels.Business;

import java.util.ArrayList;
import java.util.List;

import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.core.pojo.models.ref.Relationships;
import com.prasun.archi.ext.viewObjectModels.AbstractElementWrapper;

public class BusinessService extends AbstractElementWrapper {

	 

	public BusinessService() {
		super();
		this.setElementClass("BusinessService");
		this.properties = new ArrayList<Properties>();
		this.relatedTo = new ArrayList<Relationships>();
		this.relatedElements = new ArrayList<Elements>();
	}

	

}
