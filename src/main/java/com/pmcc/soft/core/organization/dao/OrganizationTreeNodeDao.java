package com.pmcc.soft.core.organization.dao;

import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.OrganizationTreeNode;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationTreeNodeDao extends SqlSessionDaoSupport {

	public static final String NAME_SPACE = "com/pmcc/soft/core/organization/organizationTreeNodeMapper";


	public List<OrganizationTreeNode> query() {
		return getSqlSession().selectList(NAME_SPACE + ".query");
	}
	

}
