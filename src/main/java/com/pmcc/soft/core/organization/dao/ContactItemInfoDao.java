package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.ContactItemInfo;

@Repository
public class ContactItemInfoDao extends SqlSessionDaoSupport{

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/contactItemInfoMapper";
	
	/**
	 * 查询所有
	 * @param cii
	 * @return
	 */
	public List<ContactItemInfo> query(ContactItemInfo cii){
		return getSqlSession().selectList(NAME_SPACE + ".query",cii);
	}
	
	/**
	 * 新增
	 * @param ei
	 */
	public void insert(ContactItemInfo cii){
		getSqlSession().insert(NAME_SPACE + ".insert",cii);
	}
	
	/**
	 * 删除
	 * @param ei
	 */
	public void delete(ContactItemInfo cii){
		String id = cii.getId();
		getSqlSession().delete(NAME_SPACE + ".delete",id);
	}
	
	/**
	 * 修改
	 * @param ei
	 */
	public void update(ContactItemInfo cii) {
		getSqlSession().update(NAME_SPACE + ".update", cii);

	}
}
