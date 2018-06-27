package com.pmcc.soft.core.organization.dao;

import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.OrganizationInfos;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationInfosDao extends SqlSessionDaoSupport {

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/organizationInfosMapper";



	public void insert(OrganizationInfos object) {
		getSqlSession().insert(NAME_SPACE + ".insertCompany", object);
	}
	public int insertCompany(OrganizationInfos object) {
		int res=0;
		res=getSqlSession().insert(NAME_SPACE + ".insertCompany", object);
		return res;
	}
	public List<OrganizationInfos> queryBussiness(OrganizationInfos organizationInfos) {
		return getSqlSession().selectList(NAME_SPACE + ".queryBussiness",organizationInfos);
	}
	public int updateCompany(OrganizationInfos object) {
		return getSqlSession().update(NAME_SPACE + ".updateCompany", object);
	}
	public  List<OrganizationInfos> queryById(String  id) {
		return getSqlSession().selectList(NAME_SPACE + ".queryById", id);
	}
}
