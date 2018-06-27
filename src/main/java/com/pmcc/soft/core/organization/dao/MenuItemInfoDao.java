package com.pmcc.soft.core.organization.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.MenuItemInfo;

@Repository
public class MenuItemInfoDao extends SqlSessionDaoSupport{

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/menuItemInfoMapper";


	public List<MenuItemInfo> query(String parendId) {
		return getSqlSession().selectList(NAME_SPACE + ".queryMenu",parendId);
	}
	public List<MenuItemInfo> queryList(MenuItemInfo object) {
		return getSqlSession().selectList(NAME_SPACE + ".query",object);
	}

	public void save(MenuItemInfo object) {
		getSqlSession().insert(NAME_SPACE + ".insert", object);
	}
     

	public void update(MenuItemInfo object) {
		getSqlSession().update(NAME_SPACE + ".update", object);
	}
	
	public void delete(MenuItemInfo object) {
		// TODO Auto-generated method stub
		getSqlSession().delete(NAME_SPACE + ".delete", object);
	}

	public List<MenuItemInfo> getAuthority(String url,String userId){
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("url",url);
		paramMap.put("userId",userId);
		return getSqlSession().selectList(NAME_SPACE+".getAuthority",paramMap);

	}
	
	public MenuItemInfo findById(String id) {
		return getSqlSession().selectOne(NAME_SPACE + ".findById",id);
	}
}
