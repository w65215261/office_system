package com.pmcc.soft.core.organization.domain;

import java.util.Date;

import com.pmcc.soft.core.common.BaseModel;

public class MenuItemInfo extends BaseModel{
	private static final long serialVersionUID = 1L;
	private String id;
	
	private String parentOid;
	
	private String menuEName;
	
	private String menuCname;
	
	private String menuUrl;
	
	private String menuType;
	
	private int isShow;
	
	private int menuOrder;
	
	private String menuIcon;
	
	private String systemOid;
	
	private String remark;
	
	
	private Date createTime;
	
	private String state;
	private int delFlag;
	//用来显示‘是否显示’字段
	private String isShowMsg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentOid() {
		return parentOid;
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid;
	}

	public String getMenuEName() {
		return menuEName;
	}

	public void setMenuEName(String menuEName) {
		this.menuEName = menuEName;
	}

	public String getMenuCname() {
		return menuCname;
	}

	public void setMenuCname(String menuCname) {
		this.menuCname = menuCname;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getSystemOid() {
		return systemOid;
	}

	public void setSystemOid(String systemOid) {
		this.systemOid = systemOid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getIsShowMsg() {
		return isShowMsg;
	}

	public void setIsShowMsg(String isShowMsg) {
		this.isShowMsg = isShowMsg;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
