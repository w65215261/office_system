package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.ContactGroupInfo;

@Repository
public class ContactGroupInfoDao extends SqlSessionDaoSupport{
	
public static final String NAME_SPACE = "com/pmcc/soft/core/organization/contactGroupInfoMapper";
	
	/**
	 * 查询所有
	 * @param cii
	 * @return
	 */
	public List<ContactGroupInfo> query(ContactGroupInfo cgi){
		return getSqlSession().selectList(NAME_SPACE + ".query",cgi);
	}
	
	/**
	 * 新增
	 * @param ei
	 */
	public void insert(ContactGroupInfo cgi){
		getSqlSession().insert(NAME_SPACE + ".insert",cgi);
	}
	
	/**
	 * 删除
	 * @param ei
	 */
	public void delete(ContactGroupInfo cgi){
		String id = cgi.getId();
		getSqlSession().delete(NAME_SPACE + ".delete",id);
	}
	
	/**
	 * 修改
	 * @param ei
	 */
	public void update(ContactGroupInfo cgi) {
		getSqlSession().update(NAME_SPACE + ".update", cgi);

	}
}
