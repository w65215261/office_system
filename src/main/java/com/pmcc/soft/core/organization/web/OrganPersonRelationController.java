package com.pmcc.soft.core.organization.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.OrganizationRelation;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationRelationManager;

@Controller
@RequestMapping("organPersonRelation")
public class OrganPersonRelationController {

	
	@Autowired
	OrganPersonRelationManager organPersonRelationManager;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(OrganPersonRelation organPersonRelation) {
		organPersonRelationManager.save(organPersonRelation);
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, OrganPersonRelation organPersonRelation) {

		organPersonRelationManager.delete(organPersonRelation);
		return null;
	}
	
}
