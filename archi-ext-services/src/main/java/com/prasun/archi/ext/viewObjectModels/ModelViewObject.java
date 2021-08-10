package com.prasun.archi.ext.viewObjectModels;

import java.util.List;

import com.prasun.archi.ext.core.pojo.models.ref.Elements;
import com.prasun.archi.ext.core.pojo.models.ref.Properties;
import com.prasun.archi.ext.utility.pojo.models.MetaProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelViewObject {

	private String id;
	private String name;
	private String note;
	private String purpose;
	private List<MetaProperties> metaProperties;
	private List<Properties>properties;
	private List<Elements>domain;
	private List<Elements> capabilities;
	private String createdBy;
}
