package com.pmcc.soft.core.organization.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.PersonInfo;

@Repository
public class PersonInfoDao extends SqlSessionDaoSupport {
	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/personInfoMapper";


	public List<PersonInfo> query(PersonInfo object) {
		return getSqlSession().selectList(NAME_SPACE + ".query",object);
	}

	public void insert(PersonInfo object) {
		getSqlSession().insert(NAME_SPACE + ".insert", object);
	}

	public void delete(PersonInfo object) {
		String  id = object.getId();
		getSqlSession().delete(NAME_SPACE + ".delete", id);
	}

	public void update(PersonInfo object) {
		getSqlSession().update(NAME_SPACE + ".update", object);

	}
}
