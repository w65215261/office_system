package com.pmcc.soft.core.organization.domain;

import java.util.List;

public class CheckBoxMenuNode extends MenuNode{

	private boolean checked;
	
	private List<MenuNode> children;
	
	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
