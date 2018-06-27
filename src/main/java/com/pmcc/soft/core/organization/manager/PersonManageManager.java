package com.pmcc.soft.core.organization.manager;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.organization.web.AuthorityMenuController;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.SystemParamsUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.OrganPersonRelationDao;
import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.PersonManageDao;
import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.EncryptMD5;
import com.pmcc.soft.core.utils.UUIDGenerator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Transactional
@Service
public class PersonManageManager {
	@Autowired
	PersonManageDao personManageDao;
	@Autowired
	OrganizationInfoDao organizationInfoDao;
	@Autowired
	OrganPersonRelationDao organPersonRelationDao;
	@Autowired
	OrganPersonRelationManager organPersonRelationManager;
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;

	public void save(PersonManage personManage,String reportUser) {
		String id = personManage.getId();
		if (id != null && !id.toString().equals("")) {
			
			personManage.setCreateDate(new Timestamp(System.currentTimeMillis()));
			personManageDao.update(personManage);

			OrganPersonRelation organRe = new OrganPersonRelation();
			organRe=organPersonRelationManager.queryByPersonId(personManage.getId());
			organRe.setOrganizationInfoId(personManage.getPersonUintId());
			organRe.setRptPerson(reportUser);
			organPersonRelationManager.save(organRe);
		} else {
			personManage.setId(UUIDGenerator.getUUID());
//			String md5pwd = EncryptMD5.getMD5(personManage.getMd5Pwd().trim().getBytes());
//			personManage.setMd5Pwd(md5pwd);
			personManage.setDelFlag("0");
			personManage.setCreateDate(new Timestamp(System.currentTimeMillis()));
			personManageDao.insert(personManage);
			
			OrganPersonRelation organRe = new OrganPersonRelation();
			organRe.setPersonInfoId(personManage.getId());
			organRe.setOrganizationInfoId(personManage.getPersonUintId());
			organRe.setUserPosition("");
			organRe.setRptPerson(reportUser);
			organRe.setCreateDate(new Timestamp(System.currentTimeMillis()));
			organPersonRelationManager.save(organRe);

		}

	}

	public int delete(PersonManage personManage) {
		int res = 0;
		res += personManageDao.delete(personManage);
		return res;

	}
	
	public List<PersonManage> queryByRoomId(PersonManage personManage){
		return personManageDao.queryByRoomId(personManage);
	}
	public PersonManage find(PersonManage personManage) {
		//find initPage =1 不进行分页操作  by zhangyanchang
		personManage.setInitPage(1);
		if (personManageDao.find(personManage) != null
				&& personManageDao.find(personManage).size() > 0) {

			return personManageDao.find(personManage).get(0);
		} else {
			return null;
		}

	}

	
	public PersonManage queryPersonById(String oid) {
		List<PersonManage> per = personManageDao.queryPersonById(oid);
		String a = "1";
		if (per != null && per.size()>0){
			return per.get(0);
		}
		return null;
//		return personManageDao.queryPersonById(oid).get(0);
	}

	public String queryCompanyIdByPersonId(String oid) {
		PersonManage personManage = personManageDao.queryCompanyIdByPersonId(oid);
		return personManage.getCompanyId();
	}


	
	public List<PersonManage> query(PersonManage personManage) {
//		String orgOid = request.getParameter("groupOid");
//		List<PersonManage> pList = personManageDao.query(personManage);
//		if (orgOid != null) {
//			OrganizationInfo org = new OrganizationInfo();
//			org.setId(orgOid);
//			org = organizationInfoDao.queryOrgById(org.getId());
//			for (PersonManage p : pList) {
//				p.setDepartment(org.getOrgCname());
//				pList.add(p);
//			}
//		}
		return personManageDao.query(personManage);

	}
	
	public List<PersonManage> queryForOrgan(PersonManage personManage) {
		return personManageDao.queryForOrgan(personManage);

	}

	/**
	 * 用户修改密码
	 * sunyongxing 2014-10-10
	 * @param p
	 */
	public int updatePassword(PersonManage p) {
		int res = 0;
		String userPassword = p.getMd5Pwd();
		String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
		//加密
		Blowfish bf = new Blowfish(passwordKey);
		String encrypt = bf.encrypt(p.getMd5Pwd().trim());
		p.setMd5Pwd(encrypt);
		res = personManageDao.update(p);
		return res;
	}
	
	/**
	 * 关联查询（通过组织机构id）
	 * 2014-11-13 sunyongxing
	 * @param p
	 * @return
	 */
	public List<PersonManage> queryRelation(PersonManage p){
		String sql = "SELECT * FROM ORGANIZATION c WHERE c.OID='"+p.getId()+"'";
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
		if(list.size()>0){
			p.setDepartment(list.get(0).get("ORG_CNAME"));
		}
		
		return personManageDao.queryRelation(p);
	}

	public int update(PersonManage object) {
		int res = 0;
		res =personManageDao.update(object);
		return res;

	}
	public List<PersonManage> findByOid(String oid){
		List<PersonManage> personManages = personManageDao.findByOid(oid);
		if (personManages != null && personManages.size()>0){
			return personManages;
		}
		return null;
	}
	public PersonManage findPersonCnameByOid(String oid){
		return  personManageDao.queryPersonCnameById(oid);
	}
	public void insertPerson(PersonManage personManage){
		  personManageDao.insert(personManage);
	}
	public  List<PersonManage>queryBussiness(PersonManage personManage){
		return personManageDao.queryBussiness(personManage);
	}
	public int updateCompany(PersonManage personManage) {
		int res = 0;
		res += personManageDao.updateCompany(personManage);
		return res;

	}
	public  List<PersonManage>checkPersonName(PersonManage personManage){
		return personManageDao.checkPersonName(personManage);
	}
}
