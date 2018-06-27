package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.OrganizationRelation;

@Repository
public class OrganizationRelationDao extends SqlSessionDaoSupport{
	
	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/organizationRelationMapper";


	public List<OrganizationRelation> query() {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrgR");
	}

	public void insert(OrganizationRelation object) {
		getSqlSession().insert(NAME_SPACE + ".insertOrgR", object);
	}

	public void delete(OrganizationRelation object) {
		String  id = object.getId();
		getSqlSession().delete(NAME_SPACE + ".deleteOrgR", id);
	}

	public void update(OrganizationRelation object) {
		getSqlSession().update(NAME_SPACE + ".updateOrgR", object);

	}
	
	public List<OrganizationRelation> queryOrgRbyOrgId(String  ORGANIZATIONID) {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrgRbyOrgId",ORGANIZATIONID);
	}
	
	public OrganizationRelation queryOrgRoot() {
		return  getSqlSession().selectOne(NAME_SPACE+".queryOrgRoot");
	}
	
	public List<OrganizationRelation> queryOrgRbyOrgFoid(String  foid) {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrgRbyOrgFoid",foid);
	}

	public OrganizationRelation queryOrgRById(String oid) {
		return  getSqlSession().selectOne(NAME_SPACE+".queryOrgRById",oid);
	}
public OrganizationRelation queryORbyOrgId(String ORGANIZATIONID){
	return getSqlSession().selectOne(NAME_SPACE + ".queryOrgRbyOrgId",ORGANIZATIONID);
}
	public int insertOrg(OrganizationRelation object) {
		int res=0;
		res=getSqlSession().insert(NAME_SPACE + ".insertOrgR", object);
		return res;
	}
}
