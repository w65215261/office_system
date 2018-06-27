package com.pmcc.soft.core.organization.domain;

import java.util.Date;

import com.pmcc.soft.core.common.BaseModel;

/**
 * 角色Domain
 * 
 * @author YanChangZhang
 * 
 */
public class AuthorityRoleInfo extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String roleCode;

	private String roleName;

	private String appSystem;

	private String desc;

	private String remark;

	private String rptPerson;

	private Date createTime;

	private String rptUnit;

	private int delFlag;

	private String menuItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAppSystem() {
		return appSystem;
	}

	public void setAppSystem(String appSystem) {
		this.appSystem = appSystem;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getRptUnit() {
		return rptUnit;
	}

	public void setRptUnit(String rptUnit) {
		this.rptUnit = rptUnit;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(String menuItems) {
		this.menuItems = menuItems;
	}

}
