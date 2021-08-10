package com.prasun.EA.archi.graphQL.bff.schema.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelRole {
 
	private Model model;
	private List<Role> roles;
}
