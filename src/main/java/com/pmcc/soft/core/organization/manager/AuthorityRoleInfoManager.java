package com.pmcc.soft.core.organization.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.AuthorityMenuInfoDao;
import com.pmcc.soft.core.organization.dao.AuthorityRoleInfoDao;
import com.pmcc.soft.core.organization.dao.MenuItemInfoDao;
import com.pmcc.soft.core.organization.domain.AuthorityMenuInfo;
import com.pmcc.soft.core.organization.domain.AuthorityRoleInfo;
import com.pmcc.soft.core.organization.domain.MenuItemInfo;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Transactional
@Service
public class AuthorityRoleInfoManager {

	@Autowired
	private AuthorityRoleInfoDao authorityRoleInfoDao;

	@Autowired
	private AuthorityMenuInfoDao authorityMenuInfoDao;

	@Autowired
	private MenuItemInfoDao menuItemInfoDao;

	public List<AuthorityRoleInfo> query(AuthorityRoleInfo object) {
		return authorityRoleInfoDao.query(object);
	}

	
	public AuthorityRoleInfo find(String id) {
		return authorityRoleInfoDao.find(id);
	}

	public List<AuthorityRoleInfo> findByUserId(String id) {
		return authorityRoleInfoDao.findByUserId(id);
	}

	public void save(AuthorityRoleInfo object) {
		String id = object.getId();
		if (id != null && !"".equals(id)) {
			authorityRoleInfoDao.update(object);
			authorityMenuInfoDao.deleteByRole(id);

		} else {
			object.setId(UUIDGenerator.getUUID());
			authorityRoleInfoDao.insert(object);

		}
		String menuItems =object.getMenuItems();
		boolean hasMenu=false;
		if(menuItems!=null && !menuItems.trim().equals("")){
			hasMenu=true;
		}
		if(hasMenu){
			String[] menuIds = menuItems.split(",");
			AuthorityMenuInfo authorityMenuInfo = null;
			MenuItemInfo menuItemInfo = null;
			Map<String,MenuItemInfo> allMenuItemInfos = new HashMap<String,MenuItemInfo>();
			for (String oid : menuIds) {
				boolean flag= true;
				while(flag){
					menuItemInfo = menuItemInfoDao.findById(oid);
					allMenuItemInfos.put(menuItemInfo.getId(), menuItemInfo);
					if(menuItemInfo.getParentOid().equals("-1")){
						flag=false;
					}else{
						oid = menuItemInfo.getParentOid();
					}
				}
			}
			
			Iterator<MenuItemInfo> it = allMenuItemInfos.values().iterator();
			while (it.hasNext()) {
				authorityMenuInfo = convert2AuthorityMenuInfo(object, it.next());
				authorityMenuInfoDao.insert(authorityMenuInfo);
			}
		}

	}

	private AuthorityMenuInfo convert2AuthorityMenuInfo(AuthorityRoleInfo a,
			MenuItemInfo c) {
		AuthorityMenuInfo b = new AuthorityMenuInfo();
		b.setId(UUIDGenerator.getUUID());
		b.setMenu(c.getId());
		b.setMenuOrder(c.getMenuOrder());
		b.setParentMenu(c.getParentOid());
		b.setRole(a.getId());
		b.setRptPerson(a.getRptPerson());
		b.setCreateTime(a.getCreateTime());
		return b;
	}

	public void delete(String id) {
		authorityRoleInfoDao.delete(id);
	}
}
