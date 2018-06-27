package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.OrganizationInfo;

@Repository
public class OrganizationInfoDao extends SqlSessionDaoSupport {

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/organizationMapper";


	public List<OrganizationInfo> query() {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrg");
	}
	
	public List<OrganizationInfo> queryTwo(OrganizationInfo object) {
		return getSqlSession().selectList(NAME_SPACE + ".queryTwo",object);
	}

	public void insert(OrganizationInfo object) {
		getSqlSession().insert(NAME_SPACE + ".insertOrg", object);
	}
	public int insertOrg(OrganizationInfo object) {
		int res=0;
		res=getSqlSession().insert(NAME_SPACE + ".insertOrg", object);
		return res;
	}

	public void delete(OrganizationInfo object) {
		String  id = object.getId();
		getSqlSession().delete(NAME_SPACE + ".deleteOrg", id);
	}

	public void update(OrganizationInfo object) {
		getSqlSession().update(NAME_SPACE + ".updateOrg", object);

	}
	
	public OrganizationInfo queryOrgById(String id) {
		OrganizationInfo object=new OrganizationInfo();
		object.setId(id);
		List<OrganizationInfo> list=getSqlSession().selectList(NAME_SPACE + ".queryTwo",object);
		return list.get(0);
	}
	
	/**
	 * 机构编码唯一性验证
	 * @return
	 */
	public List<OrganizationInfo> queryOrgCode(String orgCode) {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrgCode", orgCode);
	}
	public OrganizationInfo queryOrgCname(String oid) {
		return getSqlSession().selectOne(NAME_SPACE + ".queryOrgCname",oid);
	}

	public List<OrganizationInfo> queryOrgByOrgId(OrganizationInfo organizationInfo) {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrgByOrgId",organizationInfo);
	}
	public List<OrganizationInfo> queryBussiness(OrganizationInfo organizationInfo) {
		return getSqlSession().selectList(NAME_SPACE + ".queryBussiness",organizationInfo);
	}
	public void updateCompany(OrganizationInfo object) {
		getSqlSession().update(NAME_SPACE + ".updateCompany", object);

	}
	public OrganizationInfo queryOrgByOrgId(String orgId) {
		return getSqlSession().selectOne(NAME_SPACE + ".queryOrgByOrgId",orgId);
	}

	/**
	 * 通过人员id查询机构
	 * @author LvXL
	 * @param org
	 * @return
     */
	public List<OrganizationInfo> queryOrgByPersonId(OrganizationInfo org) {
		return getSqlSession().selectList(NAME_SPACE + ".queryOrgByPersonId", org);
	}

}
