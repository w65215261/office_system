package com.pmcc.soft.core.organization.dao;

import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonInfoManageDao extends SqlSessionDaoSupport {
	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/personInfoManageMapper";

	public List<PersonInfoManage>  query(PersonInfoManage object){
		return getSqlSession().selectList(NAME_SPACE + ".query",object);
	}
	public List<PersonInfoManage>  queryByName(PersonInfoManage object){
		return getSqlSession().selectList(NAME_SPACE + ".queryByName",object);
	}

	public List<PersonInfoManage>  queryByDepartmentId(PersonInfoManage object){
		return getSqlSession().selectList(NAME_SPACE + ".queryByDepartmentId",object);
	}

	public List<PersonInfoManage>  findByOrganizationInfoId(String organizationInfoId){
		return getSqlSession().selectList(NAME_SPACE + ".findByOrganizationInfoId",organizationInfoId);
	}
	public PersonInfoManage  queryByCname(PersonInfoManage pm){
		return getSqlSession().selectOne(NAME_SPACE + ".queryByCname", pm);
	}


	public int insert(PersonInfoManage pm) {
		int res = 0;
		res = this.getSqlSession().insert(NAME_SPACE + ".insert", pm);
		return res;
	}

	public int update(PersonInfoManage pm) {
		int res = 0;
		res = this.getSqlSession().update(NAME_SPACE + ".update", pm);
		return res;
	}

	public PersonInfoManage  queryByOid(PersonInfoManage pm){
		return getSqlSession().selectOne(NAME_SPACE + ".queryByOid", pm);
	}

	public int delete(String  id) {
		int res = 0;
		res = getSqlSession().delete(NAME_SPACE + ".delete", id);
		return res;
	}
	
}
