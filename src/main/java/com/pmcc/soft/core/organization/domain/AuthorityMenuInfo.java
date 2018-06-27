package com.pmcc.soft.core.organization.domain;

import java.util.Date;

public class AuthorityMenuInfo {
	private String id;
	private String role;
	private String menu;
	private String parentMenu;
	private String rptPerson;
	private Date createTime;
	private int menuOrder;
	private MenuItemInfo menuItems;

	public MenuItemInfo getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(MenuItemInfo menuItems) {
		this.menuItems = menuItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
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

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

}
