package com.pmcc.soft.core.organization.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmcc.soft.core.organization.domain.OrganizationRelation;
import com.pmcc.soft.core.organization.manager.OrganizationRelationManager;

@Controller
@RequestMapping("organizationrelation")
public class OrganizationRelationController {
	
	@Autowired
	OrganizationRelationManager organizationRelationManager;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(OrganizationRelation organizationRelation) {
		organizationRelationManager.save(organizationRelation);
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, OrganizationRelation organizationRelation) {

		organizationRelationManager.delete(organizationRelation);
		return null;
	}

}
