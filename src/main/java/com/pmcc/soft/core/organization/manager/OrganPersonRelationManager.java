package com.pmcc.soft.core.organization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.OrganPersonRelationDao;
import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.utils.UUIDGenerator;
@Transactional
@Service
public class OrganPersonRelationManager {

	@Autowired
	 OrganPersonRelationDao organPersonRelationDao;
	@Autowired
	OrganizationInfoManager  organizationInfoManager;
	
	public void update(OrganPersonRelation organPersonRelation){
		organPersonRelationDao.update(organPersonRelation);
	}
	
	
	public void save(OrganPersonRelation organPersonRelation){
		
		String id = organPersonRelation.getId();
		
		if (id != null && !id.trim().equals("")) {
			organPersonRelationDao.update(organPersonRelation);

		} else {
			organPersonRelation.setId(UUIDGenerator.getUUID());
			organPersonRelationDao.insert(organPersonRelation);
		}
		
	}
	
	
	

	public List<OrganPersonRelation> query(OrganPersonRelation organPersonRelation) {

		return organPersonRelationDao.query();

	}

	public void delete(OrganPersonRelation organPersonRelation) {

		organPersonRelationDao.delete(organPersonRelation);

	}
	

	public OrganPersonRelation queryByPersonId(String PersonID) {
		OrganPersonRelation result =organPersonRelationDao.queryByPersonId(PersonID).get(0);
		
		OrganizationInfo oinfo=new OrganizationInfo();
		oinfo.setId(result.getOrganizationInfoId());
		result.setOrganizationInfoCNName(organizationInfoManager.find(oinfo).getOrgCname());
		return result;

	}



	/**
	 * 通过组织机构id查找<br>
	 * sunyongxing 2014-09-26
	 * @param orgOid 组织机构id
	 * @return
	 */
	public List<OrganPersonRelation> findByOrgId(String orgOid) {
		return organPersonRelationDao.findByOrgId(orgOid);
	}
	
	/**
	 * 通过人员id查找<br>
	 * sunyongxing 2014-10-13
	 * @param orgOid 组织机构id
	 * @return
	 */
	public List<OrganPersonRelation> findByPersonId(String personId){
		return organPersonRelationDao.queryByPersonId(personId);
	}
	public OrganPersonRelation queryOrg(String oid){
		return organPersonRelationDao.queryOrg(oid);
	}
	public List<OrganPersonRelation> queryPersonByOrg(OrganPersonRelation organPersonRelation){
		return organPersonRelationDao.queryPersonByOrg(organPersonRelation);
	}
	public OrganPersonRelation findOrgByPersonId(String personId){
		return organPersonRelationDao.queryByPersonId(personId).get(0);
	}
}
