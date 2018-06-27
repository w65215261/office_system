package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.PersonManage;

@Repository
public class PersonManageDao extends SqlSessionDaoSupport {
	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/personManageMapper";
	
	
	public List<PersonManage>  query(PersonManage object){
		return getSqlSession().selectList(NAME_SPACE + ".query",object);
		
	}
	
	public List<PersonManage>  queryForOrgan(PersonManage object){
		return getSqlSession().selectList(NAME_SPACE + ".queryforOrgan",object);
	}
	
	public List<PersonManage>  find(PersonManage object){
		return getSqlSession().selectList(NAME_SPACE + ".query",object);
		
	}
	
	public void insert(PersonManage object) {
		getSqlSession().insert(NAME_SPACE + ".insert", object);
	}
	
	public int delete(PersonManage object){
		int res = 0;
		String id = object.getId();
		res = getSqlSession().delete(NAME_SPACE+".delete", id);
		return res;
	}
	
	public int update(PersonManage object) {
		int res = 0;
		res = getSqlSession().update(NAME_SPACE + ".update", object);
		return res;

	}
	
	public List<PersonManage> queryPersonById(String id) {
		//PersonManage p = new PersonManage();
		//p.setId(id);
		return getSqlSession().selectList(NAME_SPACE+".findById",id);
	}

	public PersonManage queryCompanyIdByPersonId(String id) {
		return getSqlSession().selectOne(NAME_SPACE + ".queryCompanyIdByPersonId", id);
	}

	/**
	 * 关联查询（通过组织机构id）
	 * 2014-11-13 sunyongxing
	 * @param p
	 * @return
	 */
	public List<PersonManage> queryRelation(PersonManage p){
		return getSqlSession().selectList(NAME_SPACE + ".queryRelation",p);
	}
	public List<PersonManage> queryByRoomId(PersonManage p){
		return getSqlSession().selectList(NAME_SPACE + ".queryAndRoom",p);
	}
	public List<PersonManage> findByOid(String id){
		return this.getSqlSession().selectList(NAME_SPACE + ".findByOid", id);
	}

	public PersonManage queryPersonCnameById(String id) {
		//PersonManage p = new PersonManage();
		//p.setId(id);
		return getSqlSession().selectOne(NAME_SPACE+".findById",id);
	}
	public List<PersonManage> queryBussiness(PersonManage personManage){
		return getSqlSession().selectList(NAME_SPACE+".queryBussiness",personManage);
	}
	public int updateCompany(PersonManage personManage){
		int res = 0;
		res = getSqlSession().update(NAME_SPACE + ".updateCompany", personManage);
		return res;
	}
	public List<PersonManage> checkPersonName(PersonManage personManage){
		return getSqlSession().selectList(NAME_SPACE+".checkPersonName",personManage);
	}
}
