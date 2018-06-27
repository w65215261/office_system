package com.pmcc.soft.core.organization.web;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.utils.*;
import com.pmcc.soft.ydpt.domain.OfUser;
import com.pmcc.soft.ydpt.manager.OfUserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.AuthorityRoleInfo;
import com.pmcc.soft.core.organization.domain.AuthorityUserInfo;
import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.AuthorityRoleInfoManager;
import com.pmcc.soft.core.organization.manager.AuthorityUserInfoManager;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;

@Controller
@RequestMapping("personManage")
public class PersonManageController {
    @Autowired
    PersonManageManager personManageManager;
    @Autowired
    OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    OrganizationInfoManager organizationInfoManager;
    @Autowired
    OfUserManager ofUserManager;
    @Autowired
    AuthorityUserInfoManager authorityUserInfoManager;
    @Resource
    PublicExcuteSqlService excuteSqlService;
    @Resource
    private PublicExcuteSqlService publicExcuteSqlService;

    /**
     * 排序，在前台自动赋值，反序，拿到第一条数据的值加1返回页面（根据所选机构，部门）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectSorting", method = RequestMethod.GET)
    public
    @ResponseBody
    Object selectSorting(HttpServletRequest request) {
        //
        String organId = request.getParameter("personUintId");
        String organSort = "SELECT * FROM PERSON_INFO p WHERE p.OID in (SELECT o.PERSON_INFO_ID FROM ORGAN_PERSON_RELATION_INFO o WHERE o.ORGANIZATION_INFO_ID = '" + organId + "') ORDER BY SORT DESC";
//		String sql = "SELECT * FROM PERSON_INFO ORDER BY SORT DESC";
        List<Map> list = publicExcuteSqlService.executeSql(organSort);
        if (list.size() > 0) {
            PersonManage p = new PersonManage();
            int sort = (int) list.get(0).get("SORT");
            int addSort = sort + 1;
            p.setSorting(addSort);
            request.setAttribute("p", p);
            return p;
        } else {
            return "";
        }

    }

    /**
     * 根据组织机构关联查询人员
     * sunyongxing 2014-10-09修改
     *
     * @param personManage
     * @param request
     * @return
     */
    /*@RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> query(PersonManage personManage,HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
		String orgOid = request.getParameter("groupOid");
		if (orgOid != null) {
			personManage.setInitPage(0);
			personManage.setId(orgOid);//将组织机构id赋给personmange中一个字段，便于关联查询。
			//关联查询
			List<PersonManage> personManages = personManageManager.queryRelation(personManage);
			if(personManages != null && !personManages.isEmpty()){
				OrganizationInfo org = new OrganizationInfo();
				org.setId(orgOid);
				org = organizationInfoManager.queryOrgById(org.getId());
				if(org != null && !org.equals("")){
					for (PersonManage personManage2 : personManages) {
						personManage2.setDepartment(org.getOrgCname());//评审添加人员时使用
						personManage2.setPersonUnitname(org.getOrgCname());//前台展示所属单位
						PersonManage per = personManageManager.queryPersonById(personManage2.getRptPerson());
						if(per != null && !per.equals("")){
							personManage2.setCreatePersonShow(per.getUserCname());//前台展示创建人
						}
					}
					res.put("rows", personManages);
					personManage.setInitPage(1);
					res.put("total", personManageManager.queryRelation(personManage).size());
				}
				
			}else{
				personManage.setInitPage(0);
				res.put("rows", personManageManager.query(personManage));
				personManage.setInitPage(1);
				res.put("total", personManageManager.query(personManage).size());	
			}
		}else{
			personManage.setInitPage(0);
			List<PersonManage> pList = personManageManager.query(personManage);
			OrganizationInfo oi = new OrganizationInfo();
			for (PersonManage personManage2 : pList) {
				OrganPersonRelation opr = organPersonRelationManager.queryByPersonId(personManage2.getId());  
				oi = organizationInfoManager.queryOrgById(opr.getOrganizationInfoId());
				//前台展示所属单位
				personManage2.setPersonUnitname(oi.getOrgCname());
				PersonManage pm = personManageManager.queryPersonById(personManage2.getRptPerson());
				//前台展示创建人
				personManage2.setCreatePersonShow(pm.getUserCname());
			}
			
			res.put("rows", pList);
			personManage.setInitPage(1);
			res.put("total", personManageManager.query(personManage).size());
		}
		personManage.setInitPage(0);
		res.put("rows", personManageManager.query(personManage));
		personManage.setInitPage(1);
		res.put("total", personManageManager.query(personManage).size());
		
		return res;
	}*/
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> query(PersonManage personManage, HttpServletRequest request) {
        Map<String, Object> res = new HashMap<String, Object>();
        String orgOid = request.getParameter("groupOid");
        //查询所选id下的机构，部门等，以"'xxx','xxx'"格式拿到
        List<Map> map = publicExcuteSqlService.executeSql("{call GetOrganIDByParentID('" + orgOid + "')}");
        String str = "'";
        if (map.size() > 0) {
            for (int i = 1; i <= map.size(); i++) {
                String listId = map.get(i - 1).get("ORGANIZATION_ID").toString();
                if (i < map.size()) {
                    str = str + listId + "','";
                } else {
                    str = str + listId + "'";
                }
            }
        } else {
            str = "''";
        }

        //把上面的部门或机构等id拿到查询出里面的人员，获取人员id存入OrganList，xml条件
        String personStr = "'";
        String sql = "SELECT * FROM PERSON_INFO p WHERE p.OID IN (SELECT PERSON_INFO_ID FROM ORGAN_PERSON_RELATION_INFO WHERE ORGANIZATION_INFO_ID IN (" + str + ")) AND p.DEL_FLAG=0";
        List<Map> personList = publicExcuteSqlService.executeSql(sql);
        if (personList.size() > 0) {
            for (int x = 1; x <= personList.size(); x++) {
                String personOid = personList.get(x - 1).get("OID").toString();
                if (x < personList.size()) {
                    personStr = personStr + personOid + "','";
                } else {
                    personStr = personStr + personOid + "'";
                }
            }
            personManage.setOrganList(personStr);
        } else {
            personManage.setOrganList("''");
        }


        if (orgOid != null) {
            personManage.setInitPage(0);
//			personManage.setId(orgOid);//将组织机构id赋给personmange中一个字段，便于关联查询。
            //关联查询
            List<PersonManage> personManages = personManageManager.queryForOrgan(personManage);
            if (personManages != null && !personManages.isEmpty()) {
                OrganizationInfo org = new OrganizationInfo();
                org.setId(orgOid);


                org = organizationInfoManager.queryOrgById(org.getId());
                if (org != null && !org.equals("")) {


                    for (PersonManage personManage2 : personManages) {

                        //根据上面查到的人员的OID 循环查询 人员所在的部门 显示在前台
                        String personOid = personManage2.getId();
                        String seeOrgan = "SELECT * FROM ORGANIZATION c WHERE c.OID=(SELECT o.ORGANIZATION_INFO_ID FROM ORGAN_PERSON_RELATION_INFO o WHERE o.PERSON_INFO_ID = '" + personOid + "')";
                        List<Map> organList = publicExcuteSqlService.executeSql(seeOrgan);


                        personManage2.setDepartment(org.getOrgCname());//评审添加人员时使用


                        if (organList.size() > 0) {
                            personManage2.setPersonUnitname(organList.get(0).get("ORG_CNAME").toString());//前台展示所属单位
                        }
                        PersonManage per = personManageManager.queryPersonById(personManage2.getRptPerson());
                        if (per != null && !per.equals("")) {
                            personManage2.setCreatePersonShow(per.getUserCname());//前台展示创建人
                        }
                    }
                    res.put("rows", personManages);
                    personManage.setInitPage(1);
                    res.put("total", personManageManager.queryForOrgan(personManage).size());
                }


            } else {
                personManage.setInitPage(0);
                res.put("rows", personManageManager.queryForOrgan(personManage));
                personManage.setInitPage(1);
                res.put("total", personManageManager.queryForOrgan(personManage).size());
            }
        } else {
            personManage.setInitPage(0);
            List<PersonManage> pList = personManageManager.queryForOrgan(personManage);
            OrganizationInfo oi = new OrganizationInfo();
            for (PersonManage personManage2 : pList) {
                OrganPersonRelation opr = organPersonRelationManager.queryByPersonId(personManage2.getId());
                oi = organizationInfoManager.queryOrgById(opr.getOrganizationInfoId());
                //前台展示所属单位
                personManage2.setPersonUnitname(oi.getOrgCname());
                PersonManage pm = personManageManager.queryPersonById(personManage2.getRptPerson());
                //前台展示创建人
                personManage2.setCreatePersonShow(pm.getUserCname());
            }
            res.put("rows", pList);
            personManage.setInitPage(1);
            res.put("total", personManageManager.queryForOrgan(personManage).size());
        }
        List<PersonManage> l = (List) res.get("rows");

        if (l.size() > 0) {
            for (PersonManage pManage : l) {
                String sql1 = "select * from  AUTHORITY_ROLE a where a.OID in(select  t.ROLE_OID from  AUTHORITY_USER_ROLE  t where t.USER_OID='" + pManage.getId() + "')";
                List<Map<String, String>> list1 = excuteSqlService.executeSql(sql1);
                String rolename = "";
                if (!list1.isEmpty() && list1.size() > 0) {
                    for (Map map1 : list1) {
                        rolename += map1.get("ROLE_NAME").toString() + ",";
                    }
                    rolename = rolename.substring(0, rolename.length() - 1);
                }
                pManage.setPower(rolename);
            }
        }
	/*	personManage.setInitPage(0);
		res.put("rows", personManageManager.query(personManage));
		personManage.setInitPage(1);
		res.put("total", personManageManager.query(personManage).size());*/
        return res;
    }


    /**
     * 查询单条人员信息
     * 2014-10-16     sunyongxing修改
     *
     * @param model
     * @param personManage
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public PersonManage find(Model model, PersonManage personManage) {
        PersonManage person = new PersonManage();
        person = personManageManager.find(personManage);
        OrganPersonRelation organRe = new OrganPersonRelation();
        organRe = organPersonRelationManager.queryByPersonId(person.getId());
        person.setPersonUintId(organRe.getOrganizationInfoId());
        String funitCname = organizationInfoManager.queryOrgById(organRe.getOrganizationInfoId()).getOrgCname();
        person.setPersonUnitname(funitCname);//组织结构显示
        PersonManage p = personManageManager.queryPersonById(person.getRptPerson());
        person.setCreatePersonShow(p.getUserCname());//创建人显示


        return person;


    }


    @RequestMapping(value = "/findByOrg", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findByOrg(PersonManage pm, String organId, String appsysId) {
        Map<String, Object> res = new HashMap<String, Object>();
	/*	if(!"".equals(CommonUtils.convertNull(organId))) {
			List<OrganPersonRelation> organrelist = organPersonRelationManager.findByOrgId(organId);
			List<PersonManage> polist = new ArrayList<PersonManage>();
			PersonManage person;
			for(OrganPersonRelation organre : organrelist){
				person = new PersonManage();
				person = personManageManager.queryPersonById(organre.getPersonInfoId());
				if(person!=null){
					polist.add(person);
				  }
			}*/
        pm.setId(organId);
        pm.setInitPage(0);
        List<PersonManage> list = personManageManager.queryRelation(pm);
		/*	List<PersonManage> list=personManageManager.query(pm);*/

        if (list.size() > 0) {
            for (PersonManage personManage : list) {
                String sql = "select * from  AUTHORITY_ROLE a where a.OID in(select  t.ROLE_OID from  AUTHORITY_USER_ROLE  t where t.USER_OID='" + personManage.getId() + "')";
                List<Map<String, String>> list1 = excuteSqlService.executeSql(sql);
                String rolename = "";
                if (!list1.isEmpty() && list1.size() > 0) {
                    for (Map map : list1) {
                        rolename += map.get("ROLE_NAME").toString() + ",";
                    }
                    rolename = rolename.substring(0, rolename.length() - 1);
                }
                personManage.setPower(rolename);
                personManage.setDepartment(pm.getDepartment());
            }
        }

        res.put("rows", list);
        pm.setInitPage(1);
        res.put("total", personManageManager.queryRelation(pm).size());
        return res;
    }


    /**
     * 保存
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(PersonManage personManage, HttpServletRequest request, HttpSession session) {

        // 当前登录用户
        PersonManage currentpersonManage = (PersonManage) session.getAttribute("loginUser");
        Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        String reportUser = currentpersonManage.getId();
        String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
        String sorting = request.getParameter("sorting");
        //加密
        Blowfish bf = new Blowfish(passwordKey);
        String encrypt = bf.encrypt(personManage.getMd5Pwd().trim());
        personManage.setMd5Pwd(encrypt);
        personManage.setRptPerson(reportUser);
        //System.out.println(reportUser);
        //若果传过来的这个排序值在数据库已存在，就更新已存在且在已存在后面的数据，把这个排序字段加1  把前台的这条数据的排序变为所写序号
        //String sqlSort = "SELECT * FROM PERSON_INFO WHERE SORT = '"+Integer.parseInt(sorting)+"'";
        String sqlSort = "SELECT * FROM PERSON_INFO WHERE SORT = '" + Integer.parseInt(sorting) + "' AND OID IN (SELECT o.PERSON_INFO_ID FROM ORGAN_PERSON_RELATION_INFO o WHERE o.ORGANIZATION_INFO_ID='" + personManage.getPersonUintId() + "')";
        List<Map> sortList = publicExcuteSqlService.executeSql(sqlSort);
        if (sortList.size() == 0) {
            personManage.setSorting(Integer.parseInt(sorting));
        } else {
            //String updateSort ="UPDATE PERSON_INFO SET SORT=(SORT+1) WHERE SORT >= '"+Integer.parseInt(sorting)+"'";
            String updateSort = "UPDATE PERSON_INFO SET SORT=(SORT+1) WHERE SORT >= '" + Integer.parseInt(sorting) + "' AND OID IN (SELECT o.PERSON_INFO_ID FROM ORGAN_PERSON_RELATION_INFO o WHERE o.ORGANIZATION_INFO_ID='" + personManage.getPersonUintId() + "')";
            publicExcuteSqlService.updateBySql(updateSort);
            personManage.setSorting(Integer.parseInt(sorting));
        }

        // 人员所属公司ID的添加
        String companyId = personManageManager.queryCompanyIdByPersonId(reportUser);
        personManage.setCompanyId(companyId);

        personManageManager.save(personManage, reportUser);
        OfUser param = new OfUser();
        param.setUsername(personManage.getUserEname());
        param.setName(personManage.getUserCname());
        param.setEmail(personManage.getUserMail());
        param.setEncryptedPassword(encrypt);

        ofUserManager.insert(param);


        //自动分配去权限，在新增人员过后再权限表中加条数据
        String authOid = UUIDGenerator.getUUID();
        String insterOnePerson = "INSERT INTO AUTHORITY_USER_ROLE VALUES('" + authOid + "','000004','" + personManage.getId() + "','" + currentpersonManage.getId() + "','" + now + "');";
        publicExcuteSqlService.updateBySql(insterOnePerson);
        //添加环信
        EasemobUtils.insert(personManage.getUserEname(), encrypt);

        return "success";
    }

    private int valueOf(String sorting) {
        // TODO Auto-generated method stub
        return 0;
    }


    /**
     * 修改保存
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(PersonManage personManage, HttpServletRequest request, HttpSession session) {
        String sorting = request.getParameter("sorting");
        //获得人的id
        String id = personManage.getId();
        //获取修改后的组织机构
        String unitId = personManage.getPersonUintId();
        String unitName = personManage.getPersonUnitname();
        PersonManage person = personManageManager.queryPersonById(id);
        // 当前登录用户
        PersonManage currentpersonManage = (PersonManage) session.getAttribute("loginUser");
        String reportUser = currentpersonManage.getId();
        personManage.setRptPerson(reportUser);
        //若果传过来的这个排序值在数据库已存在，就更新已存在且在已存在后面的数据，把这个排序字段加1  把前台的这条数据的排序变为所写序号
        //String sqlSort = "SELECT * FROM PERSON_INFO WHERE SORT = '"+Integer.parseInt(sorting)+"'";
        String sqlSort = "SELECT * FROM PERSON_INFO WHERE SORT = '" + Integer.parseInt(sorting) + "' AND OID IN (SELECT o.PERSON_INFO_ID FROM ORGAN_PERSON_RELATION_INFO o WHERE o.ORGANIZATION_INFO_ID='" + personManage.getPersonUintId() + "')";
        List<Map> sortList = publicExcuteSqlService.executeSql(sqlSort);
        if (sortList.size() == 0) {
            personManage.setSorting(Integer.parseInt(sorting));
        } else {
            //String updateSort ="UPDATE PERSON_INFO SET SORT=(SORT+1) WHERE SORT >= '"+Integer.parseInt(sorting)+"'";
            String updateSort = "UPDATE PERSON_INFO SET SORT=(SORT+1) WHERE SORT >= '" + Integer.parseInt(sorting) + "' AND OID IN (SELECT o.PERSON_INFO_ID FROM ORGAN_PERSON_RELATION_INFO o WHERE o.ORGANIZATION_INFO_ID='" + personManage.getPersonUintId() + "')";
            publicExcuteSqlService.updateBySql(updateSort);
            personManage.setSorting(Integer.parseInt(sorting));
        }
        personManageManager.update(personManage);
        OrganPersonRelation opg = organPersonRelationManager.queryByPersonId(id);
        opg.setOrganizationInfoId(unitId);
        opg.setOrganizationInfoCNName(unitName);
        opg.setRptPerson(reportUser);
        organPersonRelationManager.update(opg);

        OfUser param = new OfUser();
        param.setUsername(person.getUserEname());
        param.setName(personManage.getUserCname());
        param.setEmail(personManage.getUserMail());

//		param.setCreationDate(String.valueOf(person.getCreateDate()));
        Date dt = new Date();
        Long time = dt.getTime();
        param.setModificationDate(time.toString());
        ofUserManager.update(param);
		
		/*String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
		//加密
		Blowfish bf = new Blowfish(passwordKey);
		String encrypt = bf.encrypt(personManage.getMd5Pwd().trim());
		personManage.setMd5Pwd(encrypt);
		personManage.setRptPerson(reportUser);
		//System.out.println(reportUser);
		personManageManager.save(personManage, reportUser);
		OfUser param =new OfUser();
		param.setUsername(personManage.getUserEname());
		param.setName(personManage.getUserCname());
		param.setEmail(personManage.getUserMail());
		param.setEncryptedPassword(encrypt);
		
		ofUserManager.insert(param);*/
        //添加openFire
        return "success";
    }

    /**
     * 批量删除
     * 2014-10-13 sunyongxing修改
     *
     * @param personManage
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(PersonManage personManage, HttpServletRequest request) {
        int res = 0;
        String[] ids = request.getParameter("param").split(",");
        for (int i = 0; i < ids.length; i++) {
            //-----删除两表中的数据--刘骁
            String Sql = "SELECT * FROM ofUser o  WHERE o.username = (SELECT s.USER_ENAME FROM PERSON_INFO s WHERE s.OID='" + ids[i] + "')";
            List<Map<String, String>> list = publicExcuteSqlService.executeSql(Sql);
            if (list.size() > 0) {
                String sql = "DELETE FROM ofUser  WHERE username = (SELECT t.USER_ENAME FROM PERSON_INFO t WHERE t.OID = '" + ids[i] + "'); UPDATE PERSON_INFO  SET DEL_FLAG='1' WHERE OID= '" + ids[i] + "'";
                res += publicExcuteSqlService.updateBySql(sql);
            } else {
                String sql = "UPDATE PERSON_INFO  SET DEL_FLAG='1' WHERE OID= '" + ids[i] + "'";
                res += publicExcuteSqlService.updateBySql(sql);
            }
            //-----删除两表中的数据--刘骁
            personManage.setId(ids[i]);
            res += personManageManager.delete(personManage);
            //PersonManage pm = personManageManager.query(personManage).get(0);
		/*	String personId = personManage.getId();
			if (personId != "") {
				List<OrganPersonRelation> relationList = organPersonRelationManager.findByPersonId(personId);
				if (relationList.size()>0) {
					for (int j = 0; j < relationList.size(); j++) {
						OrganPersonRelation opr = relationList.get(j);
						organPersonRelationManager.delete(opr);
					}
				}
			}		*/
        }
        if (res == (ids.length - 1) * 2) {
            return "success";
        } else {
            return "fail";
        }
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 用户修改密码
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(PersonManage p, OfUser ofUser, HttpServletRequest request, HttpSession session) {
        PersonManage currentpersonManage = (PersonManage) session.getAttribute("loginUser");
        String userId = currentpersonManage.getId();
        String username = currentpersonManage.getUserEname();
        String pwd = p.getMd5Pwd();
        String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
        //加密
        Blowfish bf = new Blowfish(passwordKey);
        String encrypt = bf.encrypt(pwd);
        ofUser.setUsername(username);
        ofUser.setEncryptedPassword(encrypt);
        ofUserManager.update(ofUser);
        p.setId(userId);
        int res = 0;
        res += personManageManager.updatePassword(p);
        if (res == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    /**
     * 密码重置
     * 2014-10-13 sunyongxing
     *
     * @param personManage
     * @param request
     * @return
     */
    @RequestMapping(value = "/passwordReset", method = RequestMethod.POST)
    @ResponseBody
    public String passwordReset(PersonManage personManage, OfUser ofUser, HttpServletRequest request) {
        int res = 0;
        String[] ids = request.getParameter("param").split(",");
        String[] usernames = request.getParameter("ename").split(",");
        for (int i = 0; i < ids.length; i++) {
            personManage.setId(ids[i]);
            personManage.setMd5Pwd("123456");
            String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
            //加密
            Blowfish bf = new Blowfish(passwordKey);
            String encrypt = bf.encrypt("123456");
            ofUser.setUsername(usernames[i]);
            ofUser.setEncryptedPassword(encrypt);
            ofUserManager.update(ofUser);

            res += personManageManager.updatePassword(personManage);
        }

        return "success";

    }


    /**
     * 检验所填用户英文名是否已存在
     * <p/>
     * 2014-10-15 sunyongxing
     *
     * @param personManage
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserEname", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserEname(PersonManage personManage, HttpServletRequest request) {
        //Map<String, Object> res = new HashMap<String, Object>();
        List<PersonManage> dList = personManageManager.query(personManage);
        String inputUeserEname = request.getParameter("inputUserEname");//接收前台输入的用户英文名
        for (PersonManage p : dList) {
            String alreadyUserEname = p.getUserEname();
            if (inputUeserEname.equals(alreadyUserEname)) {
                return "fail";
            }
        }
        return "success";
    }


    /**
     * 根据用户登陆名称查询用户id
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
    @ResponseBody
    public String getUserEname(HttpServletRequest request) {
        String inputUeserEname = request.getParameter("userEname");//接收前台输入的用户英文名
        String sql = "select   * from   PERSON_INFO a where a.USER_ENAME ='" + inputUeserEname + "'  and a.DEL_FLAG=0";
        List<Map> list = excuteSqlService.executeSql(sql);
        if (list.isEmpty() && list.size() == 0) {
            return "fail";
        } else {
            return list.get(0).get("OID").toString();
        }
    }

    /**
     * 检验所填用户英文名是否已存在
     * <p/>
     * 2014-10-15 sunyongxing
     *
     * @param personManage
     * @param request
     * @return
     */
    @RequestMapping(value = "/vUserEname", method = RequestMethod.POST)
    @ResponseBody
    public String vUserEname(PersonManage personManage, HttpServletRequest request) {
        String inputUeserEname = request.getParameter("inputUserEname");//接收前台输入的用户英文名
        String sql = "select   * from   PERSON_INFO a where a.USER_ENAME ='" + inputUeserEname + "'  and a.DEL_FLAG=0";
        List<Map> list = excuteSqlService.executeSql(sql);
        if (!list.isEmpty() && list.size() > 0) {
            return "fail";
        }
        return "success";
    }


    /**
     * 根据中文名查询
     */
    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public
    @ResponseBody
    Object findByName(HttpServletRequest request, PersonManage personManage) {
        Map<String, Object> res = new HashMap<String, Object>();
        String cName = request.getParameter("cName");
        String sql = "SELECT * FROM PERSON_INFO p WHERE p.USER_CNAME like '%" + cName + "%'";
        List<Map<String, String>> list = publicExcuteSqlService.executeSql(sql);
        for (int i = 0; i < list.size(); i++) {
            personManage.setUserEname(list.get(i).get("USER_CNAME"));
        }

        personManage.setInitPage(0);
        res.put("rows", personManageManager.query(personManage));
        personManage.setInitPage(1);
        res.put("total", personManageManager.query(personManage).size());
        return res;
    }

}