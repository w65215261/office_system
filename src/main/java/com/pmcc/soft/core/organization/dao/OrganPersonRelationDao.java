package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
@Repository
public class OrganPersonRelationDao extends SqlSessionDaoSupport{

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/organPersonRelationMapper";
	
	public List<OrganPersonRelation> query() {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrgR");
	}
	
	public List<OrganPersonRelation> queryByPersonId(String PersonId) {
		return getSqlSession().selectList(NAME_SPACE + ".queryByPersonId",PersonId);
	}
	public void insert(OrganPersonRelation object) {
		getSqlSession().insert(NAME_SPACE + ".insertOrgR", object);
	}

	public void delete(OrganPersonRelation object) {
		String  id = object.getId();
		getSqlSession().delete(NAME_SPACE + ".deleteOrgR", id);
	}

	public void update(OrganPersonRelation object) {
		getSqlSession().update(NAME_SPACE + ".updateOrgR", object);

	}
	
	
	/**
	 * 通过组织机构id查找<br>
	 * sunyongxing 2014-09-26
	 * @param orgOid 组织机构id
	 * @return
	 */
	public List<OrganPersonRelation> findByOrgId(String orgOid) {
		return getSqlSession().selectList(NAME_SPACE + ".findByOrgId",orgOid);
	}

	public OrganPersonRelation queryOrg(String oid) {
		return getSqlSession().selectOne(NAME_SPACE + ".queryOrgRr",oid);
	}

	public List<OrganPersonRelation> queryPersonByOrg(OrganPersonRelation object) {
		return getSqlSession().selectList(NAME_SPACE + ".queryPersonByOrg",object);
	}
}
