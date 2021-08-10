package com.prasun.EA.archi.graphQL.bff.resolvers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.prasun.EA.archi.graphQL.bff.schema.Models.Element;
import com.prasun.EA.archi.graphQL.bff.schema.Models.ElementInput;
import com.prasun.EA.archi.graphQL.bff.schema.Models.MetaProperty;
import com.prasun.EA.archi.graphQL.bff.schema.Models.MetaPropertyInput;
import com.prasun.EA.archi.graphQL.bff.schema.Models.Model;
import com.prasun.EA.archi.graphQL.bff.schema.Models.ModelInput;
import com.prasun.EA.archi.graphQL.bff.schema.Models.ModelRole;
import com.prasun.EA.archi.graphQL.bff.schema.Models.Role;
import com.prasun.EA.archi.graphQL.bff.schema.Models.RoleInput;
import com.prasun.EA.archi.graphQL.bff.schema.Models.ServicesInput;
import com.prasun.EA.archi.graphQL.bff.schema.Models.User;
import com.prasun.EA.archi.graphQL.bff.schema.Models.UserInput;
import com.prasun.EA.archi.graphQL.bff.viewModels.MetaProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mutation implements GraphQLMutationResolver {

	@Value("${app.archi.ext.service.url}")
	private String url ;
	
	//private final String REST_URL = "http://localhost:8080/archi-bff";
	@Autowired
	private RestTemplate restTemplate;
	
	public User createUsers(List<UserInput> users) {
		User createdUser = null;
		for(UserInput user:users) {
			HttpEntity<UserInput> request = new HttpEntity<>(user);
			createdUser = restTemplate.postForObject(url+"/user", request, User.class);
		}
		return createdUser;
	}
	
	
	public Model createModelWith(String createdBy, ModelInput modelData) {
		HttpEntity<ModelInput> request = new HttpEntity<>(modelData);
		log.info(request.toString());
		 return restTemplate.postForObject(url+"/model/composite", request, Model.class);
	}
	
	
	//NOT TO BE USED
//	public ModelRole createModelWith(String createdBy,List<RoleInput> roles,ModelInput modelData) {
//		ModelRole createdModelRole = null;
//		//List<MetaPropertyInput> mpi = modelData.getMetaProperties();
//		Model createdModel = null;
//		List<Role> rolesCreated = null;
//		//List<MetaProperty> mprop = null;
//
//		
//		HttpEntity<ModelInput> request = new HttpEntity<>(modelData);
//		log.info(request.toString());
//		createdModel = restTemplate.postForObject(url+"/model/composite", request, Model.class);
//		
////		for(MetaPropertyInput mp:modelData.getMetaProperties()) {
////			mp.setModelRefId(createdModel.getId().toString());
////			
////			//log.info(request.toString());
////			 restTemplate.postForObject(url+"/MetaProperties", mpRequest, MetaProperty.class);
////			mprop.add(null)
////		}
////		for(int i=0;i< modelData.getMetaProperties().size();i++) {
////			modelData.getMetaProperties().get(i).setModelRefId(createdModel.getId().toString());
////		}
////		
////		HttpEntity<List<MetaPropertyInput>> mpRequest = new HttpEntity<>(modelData.getMetaProperties());
////		
////		createdModel.setMetaProperties(mprop);
//		
//		if (createdModel!=null) {
//			rolesCreated = new ArrayList<Role>();
//			createdModelRole = new ModelRole();
//			createdModelRole.setModel(createdModel);
//			for(RoleInput role:roles) {
//				role.setUserId(createdBy);
//				role.setModelId(createdModel.getId().toString());
//				log.info(role.getUserId());
//				log.info(role.getModelId());
//				
//				HttpEntity<RoleInput> requestRole = new HttpEntity<>(role);
//				
//				log.info("Sending:"+requestRole.toString());
//				
//				Role justNow = restTemplate.postForObject(url+"/role", requestRole, Role.class);
//				log.info(justNow.toString());
//				log.info("id"+justNow.getId().toString());
//				log.info("userId"+justNow.getId().toString());
//				rolesCreated.add(justNow);
//			}
//			createdModelRole.setRoles(rolesCreated);
//			
//		}
//		
//		return createdModelRole;
//	}
	public Model updateModelWith(String modelId, String updatedBy,List<RoleInput> roles, List<MetaPropertyInput> metaProperties) {
		//Get the model
		Model model = restTemplate.getForObject(url + "/models/detail/{modelId}", Model.class,modelId);
		
		//update Roles or create new roles
		//List<Role> newRoles = new ArrayList<Role>();
		HttpEntity<List<RoleInput>> requestRole = new HttpEntity<>(roles);
		
		Role[] existingRoles = restTemplate.postForObject(url + "/role/usermodel",requestRole, Role[].class,modelId);
		
		for(Role r:existingRoles) {
			for(RoleInput rn:roles) {
				if(r.getModelId().equals(rn.getModelId()) && r.getUserId().equals(rn.getUserId())) {
					rn.setId(r.getId().toString());
				}
			}
		}
		HttpEntity<List<RoleInput>> addOrUpdateRole = new HttpEntity<>(roles);
		log.info("Going to update: "+addOrUpdateRole.toString());
		Role[] addedorUpdatedRoles=restTemplate.postForObject(url + "/roles",addOrUpdateRole, Role[].class,modelId);
		
		
		
		//Update MetaProperties
		
		HttpEntity<List<MetaPropertyInput>> requestMetaProps = new HttpEntity<>(metaProperties);
		MetaProperties[] foundMetaProps = restTemplate.postForObject(url + "/MetaProperties/bulkFindByNameModel",requestMetaProps, MetaProperties[].class);
		for(MetaProperties mp:foundMetaProps) {
			for(MetaPropertyInput mpNew:metaProperties) {
				if(mp.getName().equals(mpNew.getName())) {
					mpNew.setMetaPropertyId(mp.getMetaPropertyId().toString());
				}
			}
		}
		
		HttpEntity<List<MetaPropertyInput>> addOrUpdatemp = new HttpEntity<>(metaProperties);
		log.info("Going to update: "+addOrUpdateRole.toString());
		MetaProperties[] addedorUpdatedmp=restTemplate.postForObject(url + "/MetaProperties",addOrUpdatemp, MetaProperties[].class);
		//Update Model
		
		return null;
	
	}
	public List<Element> createCapabilities(String modelId ,String createdBy, List<ElementInput> capabilities){
		//TODO: may need to remove if the below works
		//get the model
		//Model model = restTemplate.getForObject(url + "/models/detail/{modelId}", Model.class,modelId);
		
		//Add New  Capabilities
		
		//Update Model & version ?
		return null;
	}

	public List<Element> updateCapabilityWith(String modelId ,String updatedBy,List<ElementInput> changeCapabilities){
		//get the model
		Model model = restTemplate.getForObject(url + "/models/detail/{modelId}", Model.class,modelId);
		
		//Get the capabilities 
		HttpEntity<List<ElementInput>> requestCapa = new HttpEntity<>(changeCapabilities);
		
		Element[] existingCapabilities = restTemplate.postForObject(url + "/capabilities/findBulkByName/model/{modelId}/{modelVersion}",requestCapa, Element[].class,modelId,1);
		for(Element eCa:existingCapabilities) {
			for(ElementInput newCa:changeCapabilities) {
				if(eCa.getName().equals(newCa.getName())) {
					newCa.setId(eCa.getId().toString());
				}
			}
		}
		
		//Update Capabilities
		HttpEntity<List<ElementInput>> addOrUpdateC = new HttpEntity<>(changeCapabilities);
		log.info("Going to update: "+addOrUpdateC.toString());
		Element[] addedorUpdatedmp=restTemplate.postForObject(url + "/capabilities",addOrUpdateC, Element[].class);

				
		//Update Model & version ?
		return null;
	}
	public List<Element>  updateServicesWith(String modelId ,String updatedBy,List<ServicesInput> changeServices){
		//get the model
		Model model = restTemplate.getForObject(url + "/models/detail/{modelId}", Model.class,modelId);
		
		//Get the Services 
		HttpEntity<List<ServicesInput>> requestCapa = new HttpEntity<>(changeServices);
		
		
		Element[] existingServices = restTemplate.postForObject(url + "/BusinessService/model{modelId}/{modelVersion}",requestCapa, Element[].class,modelId,1);
		for(Element eCa:existingServices) {
			for(ServicesInput newCa:changeServices) {
				if(eCa.getName().equals(newCa.getName())) {
					newCa.setId(eCa.getId().toString());
				}
			}
		}
		
		//Update Capabilities
		HttpEntity<List<ServicesInput>> addOrUpdateC = new HttpEntity<>(changeServices);
		log.info("Going to update: "+addOrUpdateC.toString());
		Element[] addedorUpdatedmp=restTemplate.postForObject(url + "/BusinessServices",addOrUpdateC, Element[].class);

		
		//Update services
				
		//Update Model & version ?
		
		return null;
	}
	public List<Element> createServices(String modelId ,String createdBy ,List<ServicesInput> services){
		//TODO: may need to remove if the above works
		//get the model
		
		//Add New services
		
		//Update Model & version ?
	return null;
}
	

}
