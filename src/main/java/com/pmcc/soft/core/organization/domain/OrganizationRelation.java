package com.pmcc.soft.core.organization.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfo;

public class OrganizationRelation {
	
	private String id;
	@JsonView(View.RestView.class)
	private String organizationInfoId;
	
	private String   organizationRelationId;
	
	private String relation;
	
	private String organOrder;
	
	private PersonInfo createPerson;
	
	private Timestamp createDate;
	@JsonView(View.RestView.class)
	private String orgCname;

	public String getOrgCname() {
		return orgCname;
	}

	public void setOrgCname(String orgCname) {
		this.orgCname = orgCname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getOrganizationInfoId() {
		return organizationInfoId;
	}

	public void setOrganizationInfoId(String organizationInfoId) {
		this.organizationInfoId = organizationInfoId;
	}

	public String getOrganizationRelationId() {
		return organizationRelationId;
	}

	public void setOrganizationRelationId(String organizationRelationId) {
		this.organizationRelationId = organizationRelationId;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOrganOrder() {
		return organOrder;
	}

	public void setOrganOrder(String organOrder) {
		this.organOrder = organOrder;
	}

	public PersonInfo getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(PersonInfo createPerson) {
		this.createPerson = createPerson;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
	

}
