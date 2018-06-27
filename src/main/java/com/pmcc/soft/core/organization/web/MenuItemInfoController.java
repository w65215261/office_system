package com.pmcc.soft.core.organization.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.AuthorityMenuInfo;
import com.pmcc.soft.core.organization.domain.AuthorityUserInfo;
import com.pmcc.soft.core.organization.domain.CheckBoxMenuNode;
import com.pmcc.soft.core.organization.domain.MenuItemInfo;
import com.pmcc.soft.core.organization.domain.MenuNode;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.AuthorityMenuInfoManager;
import com.pmcc.soft.core.organization.manager.AuthorityUserInfoManager;
import com.pmcc.soft.core.organization.manager.MenuItemInfoManager;
import com.pmcc.soft.core.utils.AppUtils;

@Controller
@RequestMapping("MenuItemInfo")
public class MenuItemInfoController {

	@Autowired
	MenuItemInfoManager menuItemInfoManager;
	@Autowired
	AuthorityMenuInfoManager authorityMenuInfoManager;

	@Autowired
	AuthorityUserInfoManager authorityUserInfoManager;

	/**
	 * 获取菜单
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	@ResponseBody
	public List<MenuNode> getMenu(HttpServletRequest request, String id,HttpSession session) {
		PersonManage personManage = (PersonManage)session.getAttribute("loginUser");
		List<String> roles = new ArrayList<String>();
		if (personManage != null) {
			// 过滤角色权限
			List<AuthorityUserInfo> aUsers = authorityUserInfoManager.query(personManage.getId(),null);
			for (int i = 0; i < aUsers.size(); i++) {
				roles.add(aUsers.get(i).getRole().getId());
			}
		}

		List<MenuNode> aList = new ArrayList<MenuNode>();
		if (id == null) {
			id = "-1";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentMenu", id);
		param.put("roles", roles);
		List<AuthorityMenuInfo> list = authorityMenuInfoManager.queryByParentMenuMap(param);
		Map<String, AuthorityMenuInfo> res = new LinkedHashMap<String, AuthorityMenuInfo>();
		for (AuthorityMenuInfo authorityMenuInfo : list) {
			res.put(authorityMenuInfo.getMenu(), authorityMenuInfo);
		}
		Iterator<AuthorityMenuInfo> iit = res.values().iterator();

		AuthorityMenuInfo aMenu = null;
		while (iit.hasNext()) {
			MenuNode menuNode = new MenuNode();
			aMenu = iit.next();
			//去掉不需要显示的菜单
			if(aMenu.getMenuItems().getIsShow()==1){
				continue;
			}
			menuNode.setId(aMenu.getMenu());
			menuNode.setText(aMenu.getMenuItems().getMenuCname());
			menuNode.setUrl(aMenu.getMenuItems().getMenuUrl());
			menuNode.setIconCls(aMenu.getMenuItems().getMenuIcon());
			if (menuItemInfoManager.hasChild(aMenu.getMenuItems().getId())) {
				menuNode.setState("closed");
			} else {
				menuNode.setState("open");
			}
			aList.add(menuNode);
		}

		return aList;

	}

	@RequestMapping(value = "/getMenuAll", method = RequestMethod.POST)
	@ResponseBody
	public List<MenuNode> getMenuAll(HttpServletRequest request, String id) {
		List<MenuNode> aList = new ArrayList<MenuNode>();
		if (id == null) {
			id = "-9";
		}
		List<MenuItemInfo> list = menuItemInfoManager.query(id);
		for (MenuItemInfo it : list) {
			MenuNode menuNode = new MenuNode();
			menuNode.setId(it.getId());
			menuNode.setText(it.getMenuCname());
			menuNode.setUrl(it.getMenuUrl());
			menuNode.setIconCls(it.getMenuIcon());
			if (menuItemInfoManager.hasChild(it.getId())) {
				menuNode.setState("closed");
			} else {
				menuNode.setState("open");
			}
			aList.add(menuNode);
		}
		return aList;

	}

	/**
	 * 获取带ckeckbox的可编辑的菜单树
	 * 
	 * @author YanChangZhang
	 * @param request
	 * @param id
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/getEditMenu", method = RequestMethod.POST)
	@ResponseBody
	public List<MenuNode> getEditMenu(HttpServletRequest request, String id,
			String role) {
		List<MenuNode> aList = new ArrayList<MenuNode>();
		if (id == null) {
			id = "-1";
		}
		List<MenuItemInfo> list = menuItemInfoManager.query(id);
		for (MenuItemInfo it : list) {
			CheckBoxMenuNode menuNode = new CheckBoxMenuNode();
			menuNode.setId(it.getId());
			menuNode.setText(it.getMenuCname());
			menuNode.setUrl(it.getMenuUrl());
			menuNode.setIconCls(it.getMenuIcon());
			menuNode.setChecked(authorityMenuInfoManager.checked(convert2AuthorityMenuInfo(it.getId(), role)));
			if (menuItemInfoManager.hasChild(it.getId())) {
				menuNode.setState("closed");
			} else {
				menuNode.setState("open");
			}
			aList.add(menuNode);
		}
		return aList;

	}

	/**
	 * 获取权限菜单树
	 * 
	 * @author YanChangZhang
	 * @param request
	 * @param id
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/getReadMenu", method = RequestMethod.POST)
	@ResponseBody
	public List<MenuNode> getReadMenu(HttpServletRequest request, String id,
			String role) {

		List<MenuNode> aList = new ArrayList<MenuNode>();
		if (id == null) {
			id = "-1";
		}
		List<AuthorityMenuInfo> list = authorityMenuInfoManager
				.queryByParentMenu(convert2AuthorityMenuInfo(id, role));
		for (AuthorityMenuInfo it : list) {
			MenuNode menuNode = new MenuNode();
			menuNode.setId(it.getMenu());
			menuNode.setText(it.getMenuItems().getMenuCname());
			menuNode.setUrl(it.getMenuItems().getMenuUrl());
			menuNode.setIconCls(it.getMenuItems().getMenuIcon());
			menuNode.setState("closed");
			aList.add(menuNode);
		}
		return aList;

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(MenuItemInfo menuItemInfo) {
		menuItemInfoManager.save(menuItemInfo);
	}
    /**
     * 查询出所有的菜单树
     * @param menuItemInfo
     * @return
     */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<MenuItemInfo>  query(MenuItemInfo menuItemInfo) {
		//当id为空的时候，说明首次加载菜单，不显示id为-1的选项，显示第二级菜单
		if(menuItemInfo.getId()==null){
			//menuItemInfo=menuItemInfoManager.query("-1").get(0);
			menuItemInfo.setId("-1");
		}
		
		return menuItemInfoManager.queryList(menuItemInfo);
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public MenuItemInfo find(Model model, MenuItemInfo menuItemInfo) {
		MenuItemInfo res = menuItemInfoManager.find(menuItemInfo);
		return res;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(MenuItemInfo menuItemInfo) {
		menuItemInfoManager.delete(menuItemInfo);
		return "../../organization/menuItemInfo";
	}



	private AuthorityMenuInfo convert2AuthorityMenuInfo(String parentMenu,
			String role) {
		AuthorityMenuInfo res = new AuthorityMenuInfo();
		res.setMenu(parentMenu);
		res.setParentMenu(parentMenu);
		res.setRole(role);
		return res;
	}
}
