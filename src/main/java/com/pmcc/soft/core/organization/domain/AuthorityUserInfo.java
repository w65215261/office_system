package com.pmcc.soft.core.organization.domain;

import java.util.Date;

public class AuthorityUserInfo {
	private String id;
	private AuthorityRoleInfo role;
	private PersonManage person;
	private String rptPerson;
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AuthorityRoleInfo getRole() {
		return role;
	}

	public void setRole(AuthorityRoleInfo role) {
		this.role = role;
	}

	public PersonManage getPerson() {
		return person;
	}

	public void setPerson(PersonManage person) {
		this.person = person;
	}

	public String getRptPerson() {
		return rptPerson;
	}

	public void setRptPerson(String rptPerson) {
		this.rptPerson = rptPerson;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
