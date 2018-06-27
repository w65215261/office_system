package com.pmcc.soft.core.organization.manager;

import java.util.*;

import com.pmcc.soft.core.organization.dao.AuthorityUserInfoDao;
import com.pmcc.soft.core.organization.dao.MenuItemInfoDao;
import com.pmcc.soft.core.organization.domain.AuthorityUserInfo;
import com.pmcc.soft.core.organization.domain.MenuItemInfo;
import com.pmcc.soft.week.domain.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pmcc.soft.core.organization.dao.AuthorityMenuInfoDao;
import com.pmcc.soft.core.organization.domain.AuthorityMenuInfo;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Transactional
@Service
public class AuthorityMenuInfoManager {

	@Autowired
	private AuthorityMenuInfoDao authorityMenuInfoDao;
	@Autowired
	private MenuItemInfoManager menuItemInfoManager;
	@Autowired
	private AuthorityUserInfoDao authorityUserInfoDao;
	@Autowired
	private MenuItemInfoDao menuItemInfoDao;
	public void insert(AuthorityMenuInfo object) {
		object.setId(UUIDGenerator.getUUID());
		authorityMenuInfoDao.insert(object);
	}

	public void deleteByRole(String role) {
		authorityMenuInfoDao.deleteByRole(role);
	}

	public List<AuthorityMenuInfo> query(AuthorityMenuInfo object) {
		return authorityMenuInfoDao.query(object);
	}
	public List<AuthorityMenuInfo> queryByParentMenu(AuthorityMenuInfo object) {
		return authorityMenuInfoDao.queryByParentMenu(object);
	}
	public List<AuthorityMenuInfo> queryByParentMenuMap(Map<String,Object> param) {
		return authorityMenuInfoDao.queryByParentMenuMap(param);
	}
	public boolean checked(AuthorityMenuInfo object) {
		// 存在权限菜单
		boolean res = false;
		List<AuthorityMenuInfo> menus = authorityMenuInfoDao.query(object);
		if (menus != null && menus.size() > 0) {
			res = true;
		}

		// 确定为最后一级
		boolean res1 = false;
		List<AuthorityMenuInfo> menus1 = authorityMenuInfoDao.queryByParentMenu(object);
		if (menus1 != null && menus1.size() > 0) {
			res1 = true;
		}
		return (res && (!res1));
	}


	public TreeNode  queryAllMenuMap(Map<String,Object> param,TreeNode treeNode){
		TreeNode node=new TreeNode();
		//先查出根节点
		if(param.get("parentMenu").equals("-1")){

			List<AuthorityMenuInfo> menuInfoList=	authorityMenuInfoDao.queryByParentMenuMap(param);
			Map<String, AuthorityMenuInfo> res = new LinkedHashMap<String, AuthorityMenuInfo>();
			for (AuthorityMenuInfo authorityMenuInfo : menuInfoList) {
				res.put(authorityMenuInfo.getMenu(), authorityMenuInfo);
			}
			Iterator<AuthorityMenuInfo> iit = res.values().iterator();

			AuthorityMenuInfo menuInfo = null;
				while (iit.hasNext()) {
					menuInfo = iit.next();
					//去掉不需要显示的菜单
					if (menuInfo.getMenuItems().getIsShow() == 1) {
						continue;
					}
					node.setText(menuInfo.getMenuItems().getMenuCname());
					node.setHref(menuInfo.getMenuItems().getMenuUrl());
					node.setIcon(menuInfo.getMenuItems().getMenuIcon());
					if(menuItemInfoManager.hasChild(menuInfo.getMenuItems().getId())){
						param.put("parentMenu", menuInfo.getMenuItems().getId());
						queryAllMenuMap(param,node);
					}
				}
				return node;


		}else{

			List<AuthorityMenuInfo> menuInfoList=	authorityMenuInfoDao.queryByParentMenuMap(param);
			Map<String, AuthorityMenuInfo> res = new LinkedHashMap<String, AuthorityMenuInfo>();
			for (AuthorityMenuInfo authorityMenuInfo : menuInfoList) {
				res.put(authorityMenuInfo.getMenu(), authorityMenuInfo);
			}
			Iterator<AuthorityMenuInfo> iit = res.values().iterator();

			AuthorityMenuInfo menuInfo = null;
			while (iit.hasNext()) {
				menuInfo = iit.next();
				TreeNode nodeTmp=new TreeNode();
				if(menuInfo.getMenuItems().getIsShow()==1){
					continue;
				}
				nodeTmp.setText(menuInfo.getMenuItems().getMenuCname());
				nodeTmp.setHref(menuInfo.getMenuItems().getMenuUrl());
				nodeTmp.setIcon(menuInfo.getMenuItems().getMenuIcon());
				treeNode.getNodes().add(nodeTmp);
				if(menuItemInfoManager.hasChild(menuInfo.getMenuItems().getId())){
					param.put("parentMenu", menuInfo.getMenuItems().getId());
					queryAllMenuMap(param,nodeTmp);
				}

			}


			return node;
		}
	}

	/**
	 * 检查一个用户是不是有某种权限
	 * @param url
	 * @param uuid
	 * @return
	 */
	public boolean hasAuthority(String url,String userId) {

		List<MenuItemInfo> menuItemInfoList= menuItemInfoDao.getAuthority(url, userId);

		if(menuItemInfoList==null||menuItemInfoList.size()==0){
			return false;
		}else {
			return true;
		}

	}


}
