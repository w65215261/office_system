package com.pmcc.soft.core.organization.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.common.View;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrganPersonRelation extends DiscussBaseModel {

	private String id;
	@JsonView(View.RestView.class)
	private String personInfoId;

	private String organizationInfoId;

	private String organizationInfoCNName;

	private String userPosition;

	private String rptPerson;

	private Timestamp createDate;
	@JsonView(View.RestView.class)
	private String userCname;
	private  String signTime;
	private String duty;
	private List list=new ArrayList();

	private PersonManage personManage;

	public PersonManage getPersonManage() {
		return personManage;
	}

	public void setPersonManage(PersonManage personManage) {
		this.personManage = personManage;
	}

	public List getList() {
		return list;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getUserCname() {
		return userCname;
	}

	public void setUserCname(String userCname) {
		this.userCname = userCname;
	}

	public String getOrganizationInfoCNName() {
		return organizationInfoCNName;
	}

	public void setOrganizationInfoCNName(String organizationInfoCNName) {
		this.organizationInfoCNName = organizationInfoCNName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPersonInfoId(String personInfoId) {
		this.personInfoId = personInfoId;
	}

	public void setOrganizationInfoId(String organizationInfoId) {
		this.organizationInfoId = organizationInfoId;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public void setRptPerson(String rptPerson) {
		this.rptPerson = rptPerson;
	}

	public String getPersonInfoId() {
		return personInfoId;
	}

	public String getOrganizationInfoId() {
		return organizationInfoId;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public String getRptPerson() {
		return rptPerson;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
