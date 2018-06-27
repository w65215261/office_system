package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.AuthorityRoleInfo;

@Repository
public class AuthorityRoleInfoDao extends SqlSessionDaoSupport {

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/authorityRoleInfoMapper";

	public List<AuthorityRoleInfo> query(AuthorityRoleInfo object) {
		return this.getSqlSession().selectList(NAME_SPACE + ".query", object);
	}

	public void insert(AuthorityRoleInfo object) {
		this.getSqlSession().insert(NAME_SPACE + ".insert", object);
	}

	public void update(AuthorityRoleInfo object) {
		this.getSqlSession().update(NAME_SPACE + ".update", object);
	}

	public AuthorityRoleInfo find(String id) {
		return this.getSqlSession().selectOne(NAME_SPACE + ".find", id);
	}

	public List<AuthorityRoleInfo> findByUserId(String id) {
		return this.getSqlSession().selectList(NAME_SPACE + ".findByUserId", id);
	}


	public void delete(String id) {
		this.getSqlSession().update(NAME_SPACE + ".delete", id);
	}

}
