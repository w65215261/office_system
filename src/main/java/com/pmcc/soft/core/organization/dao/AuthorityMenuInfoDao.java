package com.pmcc.soft.core.organization.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.AuthorityMenuInfo;

@Repository
public class AuthorityMenuInfoDao extends SqlSessionDaoSupport {

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/authorityMenuInfoMapper";

	public void insert(AuthorityMenuInfo object) {
		this.getSqlSession().insert(NAME_SPACE + ".insert", object);
	}

	public void deleteByRole(String role) {
		this.getSqlSession().delete(NAME_SPACE + ".deleteByRole", role);
	}

	public List<AuthorityMenuInfo> query(AuthorityMenuInfo object) {
		return this.getSqlSession().selectList(NAME_SPACE + ".query", object);
	}
	
	public List<AuthorityMenuInfo> queryByParentMenu(AuthorityMenuInfo object) {
		return this.getSqlSession().selectList(NAME_SPACE + ".queryByParentMenu", object);
	}
	
	public List<AuthorityMenuInfo> queryByParentMenuMap(Map<String,Object> param) {
		return this.getSqlSession().selectList(NAME_SPACE + ".queryByParentMenuMap", param);
	}

}
