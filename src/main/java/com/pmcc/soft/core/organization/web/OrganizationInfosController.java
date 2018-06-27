package com.pmcc.soft.core.organization.web;

import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.organization.dao.AuthorityRoleInfoDao;
import com.pmcc.soft.core.organization.dao.AuthorityUserInfoDao;
import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.OrganizationRelationDao;
import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.organization.manager.*;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Controller
@RequestMapping("organizations")
public class OrganizationInfosController {
	@Autowired
	private OrganizationInfoDao organizationInfoDao;
	@Autowired
	private OrganizationRelationDao organizationRelationDao;
	@Autowired
	OrganizationInfoManager organizationInfoManager;
	@Resource
	private PublicExcuteSqlService publicExcuteSqlService;
	@Autowired
	private PersonManageManager personManageManager;
	@Autowired
	private OrganPersonRelationManager organPersonRelationManager;
	@Autowired
	private AuthorityUserInfoDao authorityUserInfoDao;
	@Autowired
	private AuthorityRoleInfoDao authorityRoleInfoDao;
	@Autowired
	private OrganizationRelationManager organizationRelationManager;
	@Autowired
	private OrganizationInfosManager organizationInfosManager;
	/**
	 * 企业注册
	 */
	@RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
	@ResponseBody
	public  int insertCompany(HttpServletRequest request,PersonManage personManage){
		int res=0;
		String orgCname=request.getParameter("company");
		OrganizationInfos organizationInfos=new OrganizationInfos();
		organizationInfos.setOrgCname(orgCname);
		organizationInfos.setDelFlag(2);
		Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		organizationInfos.setCreateDate(now);
		organizationInfos.setOrgType("1");
		String oid=UUIDGenerator.getUUID();
		organizationInfos.setId(oid);
		String userCname=request.getParameter("usercname");
		String userEname=request.getParameter("userename");
		String password=request.getParameter("password");
		String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
		Blowfish bf = new Blowfish(passwordKey);
		String encrypt = bf.encrypt(password.trim());
		organizationInfos.setMd5Pwd(encrypt);
		organizationInfos.setUserCname(userCname);
		organizationInfos.setUserEname(userEname);

		res=organizationInfosManager.insertCompany(organizationInfos);

		return  res;
	}
	@RequestMapping(value = "/bussiness", method = RequestMethod.GET)
	public ModelAndView bussiness() {
		ModelAndView model =new ModelAndView("bussiness/bussiness");
      /*  model.addObject("type",type);*/
		return model;

	}
	@RequestMapping(value = "/queryBussiness", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object>  queryBussiness(HttpServletRequest request,OrganizationInfos organizationInfos,PersonManage personManage) {
		String sEcho = request.getParameter("sEcho");
		Map<String, Object> map = new HashMap<String, Object>();
		organizationInfos.setInitPage(0);
		List<OrganizationInfos> companyList=organizationInfosManager.queryBussiness(organizationInfos);
		map.put("sEcho",sEcho);
		map.put("aaData", companyList);
		organizationInfos.setInitPage(1);
		int size= organizationInfosManager.queryBussiness(organizationInfos).size();
		map.put("iTotalDisplayRecords",size);
		map.put("iTotalRecords", size);
		return map;
	}
	@Transactional
	@RequestMapping(value = "/updateCompany", method = RequestMethod.GET)
	@ResponseBody
	public String  updateCompany(HttpServletRequest request,PersonManage personManage,OrganizationInfos organizationInfos,OrganizationInfo organizationInfo) {
		String ids=request.getParameter("ids");
		String [] str=ids.split(",");
		Date date=new Date();
		String flag="success";
		for (int i = 0; i < str.length; i++) {
			organizationInfos.setId(str[i]);
			organizationInfos.setActivationTime(date);
			if (organizationInfosManager.queryById(str[i]).size() > 0) {
				OrganizationInfos organizationInfos1 = organizationInfosManager.queryById(str[i]).get(0);
				int res = organizationInfosManager.updateCompany(organizationInfos);
				if (res > 0) {
					organizationInfo.setOrgCname(organizationInfos1.getOrgCname());
					organizationInfo.setDelFlag(0);
					Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
					organizationInfo.setCreateDate(now);
					organizationInfo.setOrgType("1");
					String oid = UUIDGenerator.getUUID();
					organizationInfo.setId(oid);
					OrganizationInfo org=new OrganizationInfo();
					List<OrganizationInfo> orgList=organizationInfoManager.queryBussiness(org);
					Integer managerUnitId=Integer.parseInt(orgList.get(0).getManageUnitId())+1;
					organizationInfo.setManageUnitId(managerUnitId.toString());
					int res1 = organizationInfoManager.insertOrg(organizationInfo);
					AuthorityRoleInfo authorityRoleInfo1 = new AuthorityRoleInfo();
					authorityRoleInfo1.setRoleCode("companyAdmin");
					String roleId = authorityRoleInfoDao.query(authorityRoleInfo1).get(0).getId();
					if (res1 > 0) {
						OrganizationRelation organizationRelation = new OrganizationRelation();
						organizationRelation.setId(UUIDGenerator.getUUID());
						organizationRelation.setOrganizationInfoId(oid);
						String rootOrgId = "00000000000000000000000000000001";
						String paranID = organizationRelationManager.queryOrgRbyOrgFoid(rootOrgId).get(0).getId();
						organizationRelation.setOrganizationRelationId(paranID);
						organizationRelation.setRelation("1");
						organizationRelation.setOrganOrder("1");
						organizationRelation.setCreateDate(now);
						int re = 0;
						re = organizationRelationDao.insertOrg(organizationRelation);
						if (re > 0) {
							personManage.setUserCname(organizationInfos1.getUserCname());
							personManage.setUserEname(organizationInfos1.getUserEname());
							personManage.setMd5Pwd(organizationInfos1.getMd5Pwd());
							personManage.setDelFlag("0");
							personManage.setCompanyId(oid);
							String userId = UUIDGenerator.getUUID();
							personManage.setId(userId);
							personManage.setCreateDate(now);
							personManageManager.insertPerson(personManage);
							OrganPersonRelation organPersonRelation = new OrganPersonRelation();
//				organPersonRelation.setId(UUIDGenerator.getUUID());
							organPersonRelation.setPersonInfoId(userId);
							organPersonRelation.setOrganizationInfoId(oid);
							organPersonRelation.setCreateDate(now);
							organPersonRelationManager.save(organPersonRelation);
							AuthorityUserInfo authorityUserInfo = new AuthorityUserInfo();
							authorityUserInfo.setId(UUIDGenerator.getUUID());
							AuthorityRoleInfo authorityRoleInfo = authorityRoleInfoDao.find(roleId);
							PersonManage personManage1 = personManageManager.findPersonCnameByOid(userId);
							authorityUserInfo.setRole(authorityRoleInfo);
							authorityUserInfo.setPerson(personManage1);
							authorityUserInfo.setCreateTime(now);
							authorityUserInfoDao.insert(authorityUserInfo);
						}
					}
				}
			}
		}
			return flag;

	}
	@RequestMapping(value = "/checkPersonName", method = RequestMethod.GET)
	@ResponseBody
	public String  checkPersonName(HttpServletRequest request,PersonManage personManage) {
		String personEname=request.getParameter("personName");
		personManage.setUserEname(personEname);
		String flag="";
		List<PersonManage> personManageList=personManageManager.checkPersonName(personManage);
		if(personManageList.size()>0){
			flag="error";
		}else{
			flag="success";
		}
		return flag;
	}
}
