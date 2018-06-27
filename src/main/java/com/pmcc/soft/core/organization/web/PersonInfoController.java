package com.pmcc.soft.core.organization.web;

import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.organization.manager.*;
import com.pmcc.soft.core.utils.SystemParamsUtil;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("personInfo")
public class PersonInfoController {
	@Autowired
	PersonInfoManageManager personInfoManageManager;
	@Autowired
	AuthorityRoleInfoManager authorityRoleInfoManager;
	@Autowired
	OrganPersonRelationManager organPersonRelationManager;
	@Autowired
	PersonManageManager personManageManager;
	@Autowired
	AuthorityUserInfoManager authorityUserInfoManager;


	@RequestMapping(value = "/addPerson", method = RequestMethod.GET)
	public String addPerson() {
		return "/personInfo/addPerson";
	}

	/**
	 * 人员信息初始化
	 * @param request
	 * @param pm
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> query(HttpServletRequest request,PersonInfoManage pm,HttpSession session) {
		String sEcho = request.getParameter("sEcho");
		pm.setInitPage(0);
		PersonManage user = (PersonManage)session.getAttribute("loginUser");
		String  userOid =user.getId();
		PersonManage pmg = personManageManager.queryPersonById(userOid);
		pm.setCompanyId(pmg.getCompanyId());
		List<PersonInfoManage> personInfoManages = personInfoManageManager.query(pm);
		String power = "";
		for(PersonInfoManage personInfoManage : personInfoManages){
			power = "";
			String userId = personInfoManage.getId();
			//查找部门信息
			OrganPersonRelation organPersonRelation = organPersonRelationManager.queryByPersonId(userId);
			personInfoManage.setDepartmentId(organPersonRelation.getOrganizationInfoId());
			personInfoManage.setDepartment(organPersonRelation.getOrganizationInfoCNName());
			//查找权限信息
			List<AuthorityRoleInfo> authorityRoleInfos = authorityRoleInfoManager.findByUserId(userId);
			for(AuthorityRoleInfo authorityRoleInfo : authorityRoleInfos){
				if ("".equals(power)) {
					power =authorityRoleInfo.getRoleName();
				} else {
					power += "," + authorityRoleInfo.getRoleName();
				}
			}
			personInfoManage.setPower(power);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sEcho",sEcho);
		pm.setInitPage(1);
		map.put("iTotalRecords",personInfoManageManager.query(pm).size());
		map.put("iTotalDisplayRecords",personInfoManageManager.query(pm).size());
		map.put("aaData", personInfoManages);
		return map;
	}

	/**
	 * 新增和更新
	 * @param pm
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String  save(PersonInfoManage pm,HttpSession session) {
		PersonManage user = (PersonManage)session.getAttribute("loginUser");
		String  userId =user.getId();
		if(pm.getId()==null||"".equals(pm.getId())){
			PersonInfoManage personInfo = personInfoManageManager.queryByCname(pm);
			if(personInfo != null){
				return "repeat";
			}
			int res = 0;
			String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
			//加密
			Blowfish bf = new Blowfish(passwordKey);
			String encrypt = bf.encrypt("123123");
			pm.setMd5Pwd(encrypt);
			pm.setDuty("一般员工");
			pm.setRptPerson(userId);
			pm.setCreateDate(new Date());
			pm.setDelFlag("0");

			//人员所属的公司ID
			String companyId = personManageManager.queryCompanyIdByPersonId(userId);
			pm.setCompanyId(companyId);

			//人员表插入
			int i=personInfoManageManager.insert(pm);

			//人员权限插入
			AuthorityUserInfo userInfo = new AuthorityUserInfo();
			PersonManage person = new PersonManage();
			person.setId(pm.getId());
			userInfo.setCreateTime(new Date());
			userInfo.setRptPerson(userId);
			userInfo.setPerson(person);
			String roleItems = "73e8bd9947874b919f249488012781b2";
			authorityUserInfoManager.save(roleItems,userInfo);



			//人员与部门关系表插入
			OrganPersonRelation organPersonRelation  = new OrganPersonRelation();
			String personInfoId = pm.getId();
			String departmentId = pm.getDepartmentId();
			organPersonRelation.setPersonInfoId(personInfoId);
			organPersonRelation.setOrganizationInfoId(departmentId);
			organPersonRelation.setRptPerson(userId);
			organPersonRelation.setCreateDate(new Timestamp(new Date().getTime()));

			organPersonRelationManager.save(organPersonRelation);

			if(res==i){
				return "fail";
			}else {
				return "success";
			}
		}else{
			PersonInfoManage pmg = personInfoManageManager.queryByOid(pm);
			if(pmg.getCompanyId().equals(pm.getDepartmentId())){
				pm.setSorting(-1);
			}else{
				pm.setCompanyId(pm.getDepartmentId());
			}
			int i= personInfoManageManager.update(pm);
			OrganPersonRelation organPersonRelation = new OrganPersonRelation();
			OrganPersonRelation opr = organPersonRelationManager.queryByPersonId(pm.getId());
			organPersonRelation.setId(opr.getId());
			organPersonRelation.setOrganizationInfoId(pm.getDepartmentId());
			organPersonRelationManager.save(organPersonRelation);
			if(i==1){
				return "success";
			}else{
				return "fail";
			}
		}
	}

	/**
	 * 删除人员
	 * @param oids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String  delete(String oids) {
		String[] ids = oids.split(",");
		OrganPersonRelation organPersonRelation;
		int res = 0;
		for (int i = 0; i < ids.length; i++) {
			res +=personInfoManageManager.delete(ids[i]);
			organPersonRelation = new OrganPersonRelation();
			OrganPersonRelation opr = organPersonRelationManager.queryByPersonId(ids[i]);
			organPersonRelation.setId(opr.getId());
			organPersonRelationManager.delete(organPersonRelation);
			AuthorityUserInfo userInfo = new AuthorityUserInfo();
			PersonManage person = new PersonManage();
			person.setId(ids[i]);
			userInfo.setPerson(person);
			authorityUserInfoManager.save(null, userInfo);
		}
		if(res == ids.length){
			return "success";
		}else{
			return "fail";
		}
	}

	@RequestMapping(value = "/findByOrganizationInfoId", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findByOrganizationInfoId(HttpServletRequest request,PersonInfoManage pm) {
		String sEcho = request.getParameter("sEcho");
		pm.setInitPage(0);
		List<PersonInfoManage> personInfoManages = personInfoManageManager.findByOrganizationInfoId(pm.getDepartment());
		String power = "";
		for(PersonInfoManage personInfoManage : personInfoManages){
			power = "";
			String userId = personInfoManage.getId();
			List<AuthorityRoleInfo> AuthorityRoleInfos = authorityRoleInfoManager.findByUserId(userId);
			for(AuthorityRoleInfo authorityRoleInfo : AuthorityRoleInfos){
				if ("".equals(power)) {
					power =authorityRoleInfo.getRoleName();
				} else {
					power += "," + authorityRoleInfo.getRoleName();
				}
			}
			personInfoManage.setPower(power);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sEcho",sEcho);
		pm.setInitPage(1);
		map.put("iTotalRecords",personInfoManageManager.findByOrganizationInfoId(pm.getDepartment()).size());
		map.put("iTotalDisplayRecords",personInfoManageManager.findByOrganizationInfoId(pm.getDepartment()).size());
		map.put("aaData", personInfoManages);
		return map;
	}

	@RequestMapping(value = "/queryByDepartmentId", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryByDepartmentId(HttpServletRequest request,PersonInfoManage pm) {
		String sEcho = request.getParameter("sEcho");
		pm.setInitPage(0);
		List<PersonInfoManage> personInfoManages = personInfoManageManager.queryByDepartmentId(pm);
		String power = "";
		for(PersonInfoManage personInfoManage : personInfoManages){
			power = "";
			String userId = personInfoManage.getId();
			//查找部门信息
			OrganPersonRelation organPersonRelation = organPersonRelationManager.queryByPersonId(userId);
			personInfoManage.setDepartmentId(organPersonRelation.getOrganizationInfoId());
			personInfoManage.setDepartment(organPersonRelation.getOrganizationInfoCNName());
			//查找权限信息
			List<AuthorityRoleInfo> authorityRoleInfos = authorityRoleInfoManager.findByUserId(userId);
			for(AuthorityRoleInfo authorityRoleInfo : authorityRoleInfos){
				if ("".equals(power)) {
					power =authorityRoleInfo.getRoleName();
				} else {
					power += "," + authorityRoleInfo.getRoleName();
				}
			}
			personInfoManage.setPower(power);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sEcho",sEcho);
		pm.setInitPage(1);
		map.put("iTotalRecords",personInfoManageManager.queryByDepartmentId(pm).size());
		map.put("iTotalDisplayRecords",personInfoManageManager.queryByDepartmentId(pm).size());
		map.put("aaData", personInfoManages);
		return map;
	}


}