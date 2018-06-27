package com.pmcc.soft.core.organization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.OrganizationRelationDao;
import com.pmcc.soft.core.organization.domain.OrganizationRelation;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Transactional
@Service
public class OrganizationRelationManager {
	
	@Autowired
	private OrganizationRelationDao organizationRelationDao;

	public void save(OrganizationRelation organizationRelation) {

		String id = organizationRelation.getId();

		if (id != null && !id.trim().equals("")) {
			organizationRelationDao.update(organizationRelation);

		} else {
			organizationRelation.setId(UUIDGenerator.getUUID());
			organizationRelationDao.insert(organizationRelation);
		}
	}

	public List<OrganizationRelation> query(OrganizationRelation organizationRelation) {

		return organizationRelationDao.query();

	}

	public void delete(OrganizationRelation organizationRelation) {

		organizationRelationDao.delete(organizationRelation);

	}
	public OrganizationRelation queryOrgRById(String oid) {
		return organizationRelationDao.queryOrgRById(oid);
	}

	public  OrganizationRelation queryORbyOrgId(String ORGANIZATIONID ){
	return organizationRelationDao.queryORbyOrgId(ORGANIZATIONID);
}
	public  List<OrganizationRelation> queryOrgRbyOrgFoid(String foid ){
		return organizationRelationDao.queryOrgRbyOrgFoid(foid);
	}
}
