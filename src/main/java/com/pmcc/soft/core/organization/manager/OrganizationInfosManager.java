package com.pmcc.soft.core.organization.manager;

import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.OrganizationInfosDao;
import com.pmcc.soft.core.organization.dao.OrganizationRelationDao;
import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrganizationInfosManager {

	@Autowired
	private OrganizationInfosDao organizationInfosDao;

	public int insertCompany(OrganizationInfos organizationInfos){
		int res=0;
				res=organizationInfosDao.insertCompany(organizationInfos);
		return res;
	}
public List<OrganizationInfos> queryBussiness(OrganizationInfos organizationInfos){
	return organizationInfosDao.queryBussiness(organizationInfos);
}
	public int updateCompany(OrganizationInfos organizationInfos){
		int res=organizationInfosDao.updateCompany(organizationInfos);
		return res;
	}
	public  List<OrganizationInfos> queryById(String  id) {
		return organizationInfosDao.queryById(id);
	}
}
