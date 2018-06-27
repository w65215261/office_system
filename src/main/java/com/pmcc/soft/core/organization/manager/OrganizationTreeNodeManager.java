package com.pmcc.soft.core.organization.manager;

import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.OrganizationRelationDao;
import com.pmcc.soft.core.organization.dao.OrganizationTreeNodeDao;
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
public class OrganizationTreeNodeManager {
@Autowired
	OrganizationTreeNodeDao organizationTreeNodeDao;
public List<OrganizationTreeNode> query(){
	return organizationTreeNodeDao.query();
}

}
