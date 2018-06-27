package com.pmcc.soft.core.organization.domain;

import java.sql.Date;

import com.pmcc.soft.core.common.BaseModel;

/**
 * 通讯录分组
 * @author Administrator
 *
 */
public class ContactGroupInfo extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String groupName;//组名
	
	private PersonManage createUser;//创建人
	
	private Date createDate;//创建日期
	
	private int delFlag;//删除标志

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	
}
