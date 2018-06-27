package com.pmcc.soft.core.organization.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pmcc.soft.core.organization.domain.AuthorityRoleInfo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pmcc.soft.core.organization.domain.AuthorityUserInfo;

@Repository
public class AuthorityUserInfoDao extends SqlSessionDaoSupport {

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/authorityUserInfoMapper";

	public List<AuthorityUserInfo> query(String person,String role) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("person", person);
		if(role!=null){
			params.put("role", role);
		}
		return this.getSqlSession().selectList(NAME_SPACE + ".query", params);
	}

	public void insert(AuthorityUserInfo userInfo) {
		this.getSqlSession().insert(NAME_SPACE + ".insert", userInfo);
	}
	
	public AuthorityUserInfo findByid(String person){	
		return this.getSqlSession().selectOne(NAME_SPACE + ".findById", person);
	}
	

	public void deleteByUser(String role) {
		this.getSqlSession().insert(NAME_SPACE + ".deleteByUser", role);
	}
	
	public int hasMenu(String person) {
		List<AuthorityUserInfo> res = query(person,null);

		if (res != null) {
			AuthorityRoleInfo role = res.get(0).getRole();
			if(role != null){
				return (res.size());
			}else{
				return (-1);
			}
		}
		return (-1);
	}

}
