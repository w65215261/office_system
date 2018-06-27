package com.pmcc.soft.core.organization.domain;

import java.util.Date;

import com.pmcc.soft.core.common.BaseModel;

/**
 * 通讯录实体
 * @author Administrator
 *
 */
public class ContactItemInfo extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;//姓名
	
	private String sex;//性别
	
	private String officePhone;
	
	private String mobilePhone;
	
	private String homePhone;
	
	private String email;
	
	private OrganizationInfo unit;
	
	private PersonManage createUser;//创建人
	
	private Date createDate;//创建日期
	
	private int delFlag;//删除标志

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersonManage getCreateUser() {
		return createUser;
	}

	public void setCreateUser(PersonManage createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public OrganizationInfo getUnit() {
		return unit;
	}

	public void setUnit(OrganizationInfo unit) {
		this.unit = unit;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	
}
