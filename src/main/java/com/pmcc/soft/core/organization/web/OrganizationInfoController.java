package com.pmcc.soft.core.organization.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.organization.dao.AuthorityRoleInfoDao;
import com.pmcc.soft.core.organization.dao.AuthorityUserInfoDao;
import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationRelationManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.week.domain.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.organization.dao.OrganizationInfoDao;
import com.pmcc.soft.core.organization.dao.OrganizationRelationDao;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.utils.UUIDGenerator;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("organization")
public class OrganizationInfoController {
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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, PersonInfo person) {
		this.show(model);
		return "login/index";
	}

	/**
	 * 排序，在前台自动赋值，反序，拿到第一条数据的值加1返回页面（根据所选数据id查询）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/selectSorting" , method = RequestMethod.GET)
	public @ResponseBody Object selectSorting(HttpServletRequest request){
		String organId = request.getParameter("organId");
		String sql = "SELECT * from ORGANIZATION_RELATION WHERE PARENT_ORG_ID = (SELECT o.OID FROM ORGANIZATION_RELATION o WHERE o.ORGANIZATION_ID='"+organId+"')";
		List<Map> list = publicExcuteSqlService.executeSql(sql);
		if(list.size()>0){
			OrganizationInfo organizationInfo=new OrganizationInfo();
			int sorting = list.size()+1;
			
			organizationInfo.setManageUnitId(sorting+"");
			request.setAttribute("organizationInfo", organizationInfo);
			return organizationInfo;
		}else{
			return "";
		}
		
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> show(Model model) {
		List<OrganizationInfo> array = organizationInfoManager.query(null);
		// StringBuffer res = new StringBuffer();
		// for (OrganizationInfo object : array) {
		//
		// res.append(object.getId() + "---");
		// res.append(object.getOrgEname() + "---");
		// res.append(object.getOrgCname() + "");
		// res.append( "<a href='delete.do?id="+object.getId()+"' >删除</a><br>");
		//
		// }

		Map<String, Object> res = new HashMap<String, Object>();

		res.put("total", array.size());
		res.put("rows", array);

		return res;
	}
	
	
	/**
	 * 跳转到增加组织机构页面
	 */
	 @RequestMapping(value = "/toAddOrgan", method = RequestMethod.GET)
	    
	    public @ResponseBody Object toAddOrgan(HttpServletRequest request,HttpSession session) {
		 PersonManage  user = (PersonManage) session.getAttribute("loginUser");
		/* if(user.getUserEname().equals("admin")){
			 return "isAdmin";
		 }else{
			 return "noAdmin"; 
		 }
		 */
		 
		 //之前为只有admin才 可以增加组织机构，现在把他放开，因为没有这个权限的就不显示当前列表
		 return "isAdmin";
		 
	 }
	 
	
	/**
	 * 保存
	 * @param organizationInfo
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	 public String  save(OrganizationInfo organizationInfo, HttpServletRequest request,
	            HttpServletResponse response) throws IOException {
		//获取父机构id
			//String organId = "SELECT * FROM ORGANIZATION_RELATION r WHERE r.OID IN (SELECT p.PARENT_ORG_ID FROM ORGANIZATION_RELATION p WHERE p.PARENT_ORG_ID=(SELECT o.PARENT_ORG_ID FROM ORGANIZATION_RELATION o WHERE o.ORGANIZATION_ID='"+organizationInfo.getId()+"'))";
			String organId = organizationInfo.getFunitOid();
//			List<Map> organIdList = publicExcuteSqlService.executeSql(organId);
			String sort = request.getParameter("manageUnitId");
	        Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
	        String orgType = organizationInfo.getOrgType();
	        organizationInfo.setCreateDate(now);
	        organizationInfo.setOrgType(orgType);
	        //SELECT * FROM ORGANIZATION WHERE OID IN (SELECT c.ORGANIZATION_ID FROM ORGANIZATION_RELATION c WHERE c.PARENT_ORG_ID=(SELECT o.OID FROM ORGANIZATION_RELATION o WHERE o.ORGANIZATION_ID='d8a3f8c055f94d7584292e62c103a765'));
	        String sqlSort = "SELECT * FROM ORGANIZATION WHERE MANAGE_UNIT_ID ='"+sort+"' AND OID IN (SELECT c.ORGANIZATION_ID FROM ORGANIZATION_RELATION c WHERE c.PARENT_ORG_ID=(SELECT o.OID FROM ORGANIZATION_RELATION o WHERE o.ORGANIZATION_ID='"+organId+"'))";
	        List<Map> sortList = publicExcuteSqlService.executeSql(sqlSort);
	        if(sortList.size()==0){
	        	organizationInfo.setManageUnitId(sort);
				//personManage.setSorting(Integer.parseInt(sorting));
			}else{
				String updateSort = "UPDATE ORGANIZATION SET MANAGE_UNIT_ID = (MANAGE_UNIT_ID+1) WHERE MANAGE_UNIT_ID >= '"+sort+"' AND OID IN (SELECT c.ORGANIZATION_ID FROM ORGANIZATION_RELATION c WHERE c.PARENT_ORG_ID=(SELECT o.OID FROM ORGANIZATION_RELATION o WHERE o.ORGANIZATION_ID='"+organId+"'))";
				publicExcuteSqlService.updateBySql(updateSort);
				organizationInfo.setManageUnitId(sort);
			}
	        organizationInfoManager.save(organizationInfo);
	        
	        return "success";
	    }
	
	@RequestMapping(value = "/saveOrgan", method = RequestMethod.POST)
	public @ResponseBody Object saveOrgan(HttpServletRequest request){
		 int turn = 0 ;
		 String orgEname = request.getParameter("orgEname");//机构英文名
		 String orgCname = request.getParameter("orgCname");//机构中文文名
		 String orgShortCname = request.getParameter("orgShortCname");//机构中文简称
		 String orgShortEname = request.getParameter("orgShortEname");//机构英文简称
		 String description = request.getParameter("description");//描述
		 String orgCode = request.getParameter("orgCode");//机构编码
		 String remark = request.getParameter("remark");//备注
		 
		 if(remark.equals("undefined")){
			 remark="";
		 }
		 
		 String oid = UUIDGenerator.getUUID();
		 String ooid = UUIDGenerator.getUUID();
		 Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
		 String sql = "INSERT INTO ORGANIZATION (OID,ORG_ENAME,ORG_CNAME,ORG_SHORT_ENAME,ORG_SHORT_CNAME,DESCRIPTION,ORG_CODE,REMARK,CREATE_DATE,ORGAN_TYPE) VALUES (" + "'" + oid +"'" + "," + "'" + orgEname + "'" + ","  + "'" + orgCname + "'" + "," + "'" + orgShortEname + "'" + "," + "'" + orgShortCname + "'" + "," + "'" + description + "'" + "," + "'" + orgCode + "'" + "," + "'" + remark + "'" + ", " + "'" + now + "','1'" + ")";
		 //oid,orgEname,orgCname,orgShortEname,orgShortCname,description,orgCode,remark,now
		 String Sql = "INSERT INTO ORGANIZATION_RELATION (OID , ORGANIZATION_ID , PARENT_ORG_ID, RELATION , ORGAN_ORDER,CREATE_DATE) VALUES ("+ "'" + ooid +"'"+","+"'" + oid +"'"+","+"'"+"78d356c910514089a61b36e7bb9a88f6"+"'"+","+"1"+","+1+","+"'" + now + "'"+")";
		 publicExcuteSqlService.updateBySql(Sql);
		 publicExcuteSqlService.updateBySql(sql);
		 turn++;
		 if(turn != 0){
			 return "success";
		 }else{
			 return "false";
		 }
		 
		
	}
	

	/**
	 *删除
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, OrganizationInfo organizationInfo) {
		
		organizationInfoManager.delete(organizationInfo);
		return "../../organization/test";
	}
	
	/**
	 * 组织机构是不可删除的
	 * @param
	 * @return
	 */
	@RequestMapping(value="/deleteNoOrgan",method = RequestMethod.GET)
	public @ResponseBody Object deleteNoOrgan(HttpServletRequest request){
		String id = request.getParameter("id");
		OrganizationInfo organizationInfo  = organizationInfoDao.queryOrgById(id);//部门表id
		String sql ="SELECT * FROM ORGANIZATION_RELATION WHERE ORGANIZATION_ID='"+id+"'";
		List<Map<String,String>> seeOid = publicExcuteSqlService.executeSql(sql);
		List<OrganizationRelation> organReList = organizationRelationDao.queryOrgRbyOrgFoid(seeOid.get(0).get("OID")); //关系表id
		if(organReList.size()>0){
			return "false";
		}else{
//		String sql = "SELECT * FROM ORGANIZATION_RELATION o WHERE o.ORGANIZATION_ID='"+id+"'";
		/*String orgType = "SELECT * FROM ORGANIZATION o WHERE o.OID='"+id+"'";
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(orgType);
		if(!list.get(0).get("ORGAN_TYPE").equals("1") && !list.get(0).get("ORGAN_TYPE").equals("-1")){*/
			String Sql = "DELETE FROM ORGANIZATION WHERE OID ='"+id+"'";
			String sqls = "DELETE FROM ORGANIZATION_RELATION WHERE ORGANIZATION_ID='"+id+"'";
			publicExcuteSqlService.updateBySql(sqls);
			publicExcuteSqlService.updateBySql(Sql);
			return "success";
		}
	}
	
	/**
	 * 设置组织机构管理员
	 */
	@RequestMapping(value="/saveOrganPerson", method = RequestMethod.GET)
	public @ResponseBody Object saveOrganPerson(HttpServletRequest request,HttpSession session){
			PersonManage  user = (PersonManage) session.getAttribute("loginUser");
			Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
			String lastName = "";
			int res=0;
			String id = request.getParameter("id");
			String oid = request.getParameter("oid");
			//根据当前选择组织机构id查询RPT_UNIT   为获得当前组织机构管理员
			String Sqls = "SELECT * FROM ORGANIZATION WHERE OID='"+oid+"'";
			List<Map<String,String>> selectEname = publicExcuteSqlService.executeSql(Sqls);
			if(!"".equals(selectEname.get(0).get("RPT_UNIT")) && selectEname.get(0).get("RPT_UNIT") != null){
				lastName = selectEname.get(0).get("RPT_UNIT").trim();
			}
			
			
			//根据当前选择人员id查询人员信息
			String sql = "SELECT * FROM PERSON_INFO o WHERE o.OID='"+id+"'";
			List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			String nowOid = list.get(0).get("OID");
			String eName = list.get(0).get("USER_ENAME").trim();
			//更新RPT_UNIT字段为当前选择人员
			String Sql = "UPDATE ORGANIZATION SET RPT_UNIT ='"+eName+"' WHERE OID='"+oid+"'";
		 	res +=publicExcuteSqlService.updateBySql(Sql);
		 	if(res == list.size()){
		 		//查到之前的组织机构管理员所在的权限表的数据
		 		String lastPerson = "SELECT * FROM AUTHORITY_USER_ROLE b WHERE b.USER_OID IN(SELECT a.OID FROM PERSON_INFO a WHERE a.USER_ENAME = '"+lastName+"') AND b.ROLE_OID = '000002'";
		 		List<Map<String,String>> selectLastEname = publicExcuteSqlService.executeSql(lastPerson);
		 		if(selectLastEname.size()>0){
		 			//如果通过之前的条件查询出来数据就更新
		 			String lastOid = selectLastEname.get(0).get("OID");
			 		//把这条数据中的user_id改为现在设置的人员id
			 		String updateUserOid = "UPDATE AUTHORITY_USER_ROLE SET USER_OID ='"+nowOid+"' WHERE OID='"+lastOid+"'";
			 		publicExcuteSqlService.updateBySql(updateUserOid);
		 		}else{
		 			//否则就新增一条数据
		 			String authOid = UUIDGenerator.getUUID();
		 			String insterOnePerson ="INSERT INTO AUTHORITY_USER_ROLE VALUES('"+authOid+"','000002','"+nowOid+"','"+user.getId()+"','"+now+"');";
		 			publicExcuteSqlService.updateBySql(insterOnePerson);
		 		}
		 		
		 		return "true";
		 	}else{
		 		return "false";
		 	}
		
	}
	
	/**
	 * 根据id查询是否为组织机构    并且本组织机构管理员不可更换本组织机构管理员
	 */
	
	@RequestMapping(value="/queryOrganById",method = RequestMethod.GET)
	public @ResponseBody Object queryOrganById(String id,HttpSession session){
		PersonManage  user = (PersonManage) session.getAttribute("loginUser");
		String userEname = user.getUserEname();
		String rptName = "";
		
		String sql = "SELECT * FROM ORGANIZATION o WHERE o.OID='"+id+"'";
		
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			if(list.get(0).get("ORGAN_TYPE").equals("1")){
				
				String selectOrgan = "SELECT * FROM ORGANIZATION a WHERE a.OID='"+id+"'";
				List<Map> unitList = publicExcuteSqlService.executeSql(selectOrgan);
				if(unitList.get(0).get("RPT_UNIT") != null && !"".equals(unitList.get(0).get("RPT_UNIT"))){
					rptName = unitList.get(0).get("RPT_UNIT").toString();
					if(userEname.trim() != rptName.trim() && !userEname.trim().equals(rptName.trim())){
						return "true";
					}else{
						return "isMe";
					}
				}else{
					return "true";
				}
				
			}
			
		return "false";
			
	}
	
	/*
	 * 根据id查询是否为组织机构
	 */
	@RequestMapping(value="/queryOrganByIdSetWeChat",method = RequestMethod.GET)
	public @ResponseBody Object queryOrganByIdSetWeChat(String id,HttpSession session,HttpServletRequest request){
		/*PersonManage  user = (PersonManage) session.getAttribute("loginUser");
		String userEname = user.getUserEname();
		String rptName = "";*/
		
		String sql = "SELECT * FROM ORGANIZATION o WHERE o.OID='"+id+"'";
		Map map = new HashMap<String,String>();
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			if(list.get(0).get("ORGAN_TYPE").equals("1")){
				String selectOrg = "SELECT * FROM dlWeChatManager WHERE ORGANIZATION_ID = '"+id+"'";
				List<Map> orgList = publicExcuteSqlService.executeSql(selectOrg);
				if(orgList.size()>0){
					map.put("name", orgList.get(0).get("NAME"));
					map.put("uri", orgList.get(0).get("URI"));
					map.put("token", orgList.get(0).get("TOKEN"));
					map.put("appId", orgList.get(0).get("APP_ID"));
					map.put("id", id);
					map.put("result", "true");
					return map;
				}else{
					map.put("name", "");
					map.put("uri", "");
					map.put("token", "");
					map.put("appId", "");
					map.put("id", id);
					map.put("result", "true");
					return map;
				}
				
			}else{
				map.put("result", "false");
				return map;
			}
	}
	
	/*
	 * 公众号保存方法
	 */
	@RequestMapping (value="/saveWeChat",method = RequestMethod.POST)
	public @ResponseBody Object saveWeChat (HttpServletRequest request,HttpSession session){
		String oid = UUIDGenerator.getUUID();
		String name = request.getParameter("name");
		String uri = request.getParameter("uri");
		String token = request.getParameter("token");
		String appId = request.getParameter("appId");
		String orgId = request.getParameter("orgId");
		String selectOrg = "SELECT * FROM dlWeChatManager WHERE ORGANIZATION_ID = '"+orgId+"'";
		List<Map> orgList = publicExcuteSqlService.executeSql(selectOrg);
		if(orgList.size()>0){
			String sql = "UPDATE dlWeChatManager SET NAME = '"+name+"',TOKEN='"+token+"',URI='"+uri+"',APP_ID='"+appId+"' WHERE ORGANIZATION_ID='"+orgId+"'";
			publicExcuteSqlService.updateBySql(sql);
			return "updateTrue";
		}else{
			String sql = "INSERT INTO dlWeChatManager VALUES('"+oid+"','"+name+"','"+uri+"','"+token+"','"+appId+"','"+orgId+"')";
			publicExcuteSqlService.updateBySql(sql);
			return "insertTrue";
		}
		
		
		
	}
	
	
	/**
	 * 根据id查询组织机构
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryOrgan",method = RequestMethod.POST)
	public @ResponseBody List<OrganizationInfo> queryOrgan(String id ,String sjid){
		List<OrganizationInfo> oList=new ArrayList<OrganizationInfo>();
		if(id==null){
			id=sjid;
		}
		List<OrganizationInfo> organlist=organizationInfoManager.queryOrgtree(id,"UNIT");
		for(OrganizationInfo it:organlist){
			OrganizationInfo organizationInfo=new OrganizationInfo();
			organizationInfo.setId(it.getId());
			organizationInfo.setText(it.getOrgCname());
			organizationInfo.setState(it.getState());
			organizationInfo.setOrgType(it.getOrgType());
			oList.add(organizationInfo);
		}
		return oList;
	}
	/**
	 * 增加组织机构
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addOrgan",method = RequestMethod.POST)
	public @ResponseBody List<OrganizationInfo> queryOrgan(String id,HttpSession session){
		List<OrganizationInfo> oList=new ArrayList<OrganizationInfo>();
		if(id == null || "".equals(id)){
			PersonManage  user = (PersonManage) session.getAttribute("loginUser");
			String sql = "SELECT * FROM ORGANIZATION a WHERE a.RPT_UNIT='"+user.getUserEname()+"'";
			List<Map> list = publicExcuteSqlService.executeSql(sql);
			if(list.size()>0){
				String	jgid = list.get(0).get("OID").toString();
				OrganizationInfo organizationInfoRpt  = organizationInfoManager.queryOrgById(jgid);
				String sql1 ="SELECT * FROM PERSON_INFO WHERE USER_ENAME ='"+organizationInfoRpt.getRptUnit()+"'";
				List<Map<String,String>> list1 = publicExcuteSqlService.executeSql(sql1);
				OrganizationInfo organizationInfo=new OrganizationInfo();
				organizationInfo.setId(organizationInfoRpt.getId());
				organizationInfo.setText(organizationInfoRpt.getOrgCname());
				organizationInfo.setOrgEname(organizationInfoRpt.getOrgEname());
				organizationInfo.setDescription(organizationInfoRpt.getDescription());
				organizationInfo.setOrgCode(organizationInfoRpt.getOrgCode());
				organizationInfo.setRemark(organizationInfoRpt.getRemark());
				organizationInfo.setState("closed");
				if(list1.size()>0){
					organizationInfo.setRptUnit(list1.get(0).get("USER_CNAME"));
				}
				if(organizationInfoRpt.getOrgType().equals("-1")){
					organizationInfo.setOrgType("移动平台");
				}else if(organizationInfoRpt.getOrgType().equals("1")){
					organizationInfo.setOrgType("组织机构");
				}else{
					organizationInfo.setOrgType("部门");
				}
				
				oList.add(organizationInfo);
				return oList;
			}else{
				id="-1";
				List<OrganizationInfo> organlist=organizationInfoManager.queryOrgtree(id,"ORG");
				for(OrganizationInfo it:organlist){
					OrganizationInfo organizationInfo=new OrganizationInfo();
					organizationInfo.setId(it.getId());
					organizationInfo.setText(it.getOrgCname());
					organizationInfo.setState(it.getState());
					organizationInfo.setOrgType(it.getOrgType());
					oList.add(organizationInfo);
				}
				return oList;
			}
		}else{
			List<OrganizationInfo> organlist=organizationInfoManager.queryOrgtree(id,"ORG");
			for(OrganizationInfo it:organlist){
				OrganizationInfo organizationInfo=new OrganizationInfo();
				organizationInfo.setId(it.getId());
				organizationInfo.setText(it.getOrgCname());
				organizationInfo.setState(it.getState());
				organizationInfo.setOrgType(it.getOrgType());
				oList.add(organizationInfo);
			}
			return oList;
		}
		
		
		
		/*if(id==null){
			id="-1";
		}*/
		
		
	}
	/**
	 * 根据当前登录人id查询组织机构
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/userQueryOrgan",method = RequestMethod.POST)
	public @ResponseBody Object userQueryOrgan(String id ,String sjid,HttpSession session){
		List<OrganizationInfo> oList=new ArrayList<OrganizationInfo>();
		PersonManage  user = (PersonManage) session.getAttribute("loginUser");
		if(sjid == null){
			String sql = "SELECT d.ORGANIZATION_ID FROM dlRoomOfUnit d WHERE d.ORGANIZATION_ID=(SELECT o.OID FROM ORGANIZATION o WHERE o.RPT_UNIT='"+user.getUserEname()+"')";
			List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			if(list.size()>0){
				sjid = list.get(0).get("ORGANIZATION_ID");
			}else{
				return "";
			}
			
		}
		if(id==null){
			id=sjid;
		}
		List<OrganizationInfo> organlist=organizationInfoManager.queryOrgtree(id,"UNIT");
		for(OrganizationInfo it:organlist){
			OrganizationInfo organizationInfo=new OrganizationInfo();
			organizationInfo.setId(it.getId());
			organizationInfo.setText(it.getOrgCname());
			organizationInfo.setState(it.getState());
			organizationInfo.setOrgType(it.getOrgType());
			oList.add(organizationInfo);
		}
		return oList;
	}

	/**
	 * id=null ｜｜当前登陆人为某个机构的管理员－－》》查询本人管理的组织机构的根节点
	 * id!=null -->>直接查询id代表的组织机构的下级组织机构
	 * 这里两个方法分开写最好，逻辑更加清晰一些。
 	 * @param id
	 * @param session
	 * @param flag
	 * @return
	 */
	@RequestMapping(value = "/queryOrgtree", method = RequestMethod.POST)
	@ResponseBody
	public  List<OrganizationInfo> queryOrgtree(String id,HttpSession session,String flag){
		//如果是admin就传-1，主要是根据当前组织机构管理员过滤树
		if("0".equals(flag)){
			id=null;
		}
		PersonManage user = (PersonManage) session.getAttribute("loginUser");
		List<OrganizationInfo> oList = new ArrayList<OrganizationInfo>();
		String jgid = user.getCompanyId();
		OrganizationInfo organizationInfoRpt = new OrganizationInfo();
		OrganizationInfo organization=new OrganizationInfo();
		organization.setId(jgid);
//		String sqlStr = "SELECT * FROM ORGANIZATION o WHERE o.RPT_UNIT='"+user.getUserEname() + "'";
//		List<Map<String, String>> rptUnitList = publicExcuteSqlService.executeSql(sqlStr);
		List<OrganizationInfo> rptUnitList=organizationInfoManager.queryOrgByOrgId(organization);
		if (rptUnitList.size() > 0 && (id==null || "".equals(id)) ) {
				organizationInfoRpt  = organizationInfoManager.queryOrgById(jgid);
				
//						String sql ="SELECT * FROM PERSON_INFO WHERE USER_ENAME ='"+organizationInfoRpt.getRptUnit()+"'";
//						List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
						OrganizationInfo organizationInfo=new OrganizationInfo();
						organizationInfo.setId(organizationInfoRpt.getId());
						organizationInfo.setOrgCname(organizationInfoRpt.getOrgCname());
						organizationInfo.setOrgEname(organizationInfoRpt.getOrgEname());
						organizationInfo.setDescription(organizationInfoRpt.getDescription());
						organizationInfo.setOrgCode(organizationInfoRpt.getOrgCode());
						organizationInfo.setRemark(organizationInfoRpt.getRemark());
						organizationInfo.setRptUnit(user.getUserCname());
						organizationInfo.setState("closed");
//						if(list.size()>0){
//							organizationInfo.setRptUnit(list.get(0).get("USER_CNAME"));
//						}
						if(organizationInfoRpt.getOrgType().equals("-1")){
							organizationInfo.setOrgType("移动平台");
						}else if(organizationInfoRpt.getOrgType().equals("1")){
							organizationInfo.setOrgType("组织机构");
						}else{
							organizationInfo.setOrgType("部门");
						}
						
						oList.add(organizationInfo);
				
		}else{
			//如果是admin登陆的话，就查机构当中所有的树的信息
			if ((id==null || "".equals(id)) && user.getUserEname().equals("admin")) {
				id = "-1";
			}
			if(id==null || "".equals(id)){
				return null;
			}
			List<OrganizationInfo> organlist=organizationInfoManager.queryOrgtree(id,"Unit");
			for(OrganizationInfo it:organlist){
//				String sql ="SELECT * FROM PERSON_INFO WHERE USER_ENAME ='"+it.getRptUnit()+"'";
//				List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
				OrganizationInfo organizationInfo=new OrganizationInfo();
				organizationInfo.setId(it.getId());
				organizationInfo.setOrgCname(it.getOrgCname());
				organizationInfo.setOrgEname(it.getOrgEname());
				organizationInfo.setDescription(it.getDescription());
				organizationInfo.setOrgCode(it.getOrgCode());
				organizationInfo.setRemark(it.getRemark());
				organizationInfo.setState(it.getState());
				organizationInfo.setManageUnitId(it.getManageUnitId());
//				if(list.size()>0){
//					organizationInfo.setRptUnit(list.get(0).get("USER_CNAME"));
//				}
				if(it.getOrgType().equals("-1")){
					organizationInfo.setOrgType("移动平台");
				}else if(it.getOrgType().equals("1")){
					organizationInfo.setOrgType("组织机构");
				}else{
					organizationInfo.setOrgType("部门");
				}
				
				oList.add(organizationInfo);
			}
		}
		
		Collections.sort(oList, new Comparator<OrganizationInfo>(){

			/*
			 * int compare(OrganizationInfo o1, OrganizationInfo o2) 返回一个基本类型的整型，
			 * 返回负数表示：o1 小于o2，
			 * 返回0 表示：o1和o2相等，
			 * 返回正数表示：o1大于o2。
			 */
			public int compare(OrganizationInfo o1, OrganizationInfo o2) {
			Integer.valueOf(o1.getManageUnitId());
				//按照学生的年龄进行升序排列
				if(Integer.valueOf(o1.getManageUnitId()) > Integer.valueOf(o2.getManageUnitId())){
					return 1;
				}
				if(Integer.valueOf(o1.getManageUnitId()) == Integer.valueOf(o2.getManageUnitId())){
					return 0;
				}
				return -1;
			}
		}); 

		return oList;
	}
	
	@RequestMapping(value = "/queryOrgCombtree", method = RequestMethod.POST)
	@ResponseBody
	public  List<OrganizationInfo> queryOrgCombtree(String id,String sjid,HttpSession session){
		//如果是admin就传-1，主要是根据当前组织机构管理员过滤树
		List<OrganizationInfo> oList=new ArrayList<OrganizationInfo>();
		PersonManage  user = (PersonManage) session.getAttribute("loginUser");
		OrganizationInfo organization=new OrganizationInfo();
		String jgid = user.getCompanyId();
		organization.setId(jgid);
		List<OrganizationInfo> list=organizationInfoManager.queryOrgByOrgId(organization);
		OrganizationInfo organizationInfoRpt = new OrganizationInfo();
		if(list.size()>0 && id==null){
				organizationInfoRpt  = organizationInfoManager.queryOrgById(jgid);
			
						OrganizationInfo organizationInfo=new OrganizationInfo();
						organizationInfo.setId(organizationInfoRpt.getId());
						organizationInfo.setText(organizationInfoRpt.getOrgCname());
						organizationInfo.setState("closed");
						organizationInfo.setOrgType(organizationInfoRpt.getOrgType());
						oList.add(organizationInfo);
					
//			}
		}else{
			if(id==null && user.getUserEname().equals("admin")){
				id="-1";
			}
			if(id==null){
				return null;
			}
			/*if(id==null){
				id="-1";
			}*/
			List<OrganizationInfo> organlist=organizationInfoManager.queryOrgtree(id,"UNIT");
			for(OrganizationInfo it:organlist){
				OrganizationInfo organizationInfo=new OrganizationInfo();
				organizationInfo.setId(it.getId());
				organizationInfo.setText(it.getOrgCname());
				organizationInfo.setState(it.getState());
				organizationInfo.setOrgType(it.getOrgType());
				oList.add(organizationInfo);
			}
		}
		
		return oList;
		
	}
	
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public OrganizationInfo find(Model model, OrganizationInfo organizationInfo) {
		OrganizationInfo organ = organizationInfoManager.find(organizationInfo);
		String foid = organizationInfoManager.qeruyFoidByorgId(organizationInfo.getId());
	
		if(foid!=null && !"00000000000000000000000000000001".equals(foid)){
			String fname = organizationInfoManager.queryOrgById(foid).getOrgCname();
			organ.setFunitName(fname);
			organ.setFunitOid(foid);
		}else{
			organ.setFunitName("组织机构");
			organ.setFunitOid("00000000000000000000000000000001");
		}
		
		
		return organ;
	}
	
	
	/**
	 * 机构编码唯一性验证
	 * @param orgCode
	 * @return
	 */
	@RequestMapping(value = "/checkOrgCode", method = RequestMethod.POST)
	@ResponseBody
	public  String checkOrgCode(String orgCode){
		
		String res = "no";
		List<OrganizationInfo> organ = organizationInfoManager.queryOrgCode(orgCode);
		if(organ != null && organ.size() > 0){
			res = "yes";
		}
		return res;
	}
	
	/**
	 * 根据中文名查询
	 */
	@RequestMapping(value = "/findByName", method = RequestMethod.POST)
	public @ResponseBody Object findByName(HttpServletRequest request){
		OrganizationInfo organ = new OrganizationInfo();
		List<OrganizationInfo> oList=new ArrayList<OrganizationInfo>();
		
		String cName = request.getParameter("cName");
		String sql =  "SELECT * FROM ORGANIZATION t WHERE t.ORG_CNAME like '%"+cName+"%'";
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
		//以上为获取输入框的值，然后根据值模糊查询
		
			for(int i=0;i<list.size();i++){
				organ = organizationInfoManager.queryOrgById(list.get(i).get("OID"));
				if(list.get(i).get("ORGAN_TYPE").equals("1")){
					organ.setOrgType("组织机构");
				}else if(list.get(i).get("ORGAN_TYPE").equals("2")){
					organ.setOrgType("部门");
				}else if(list.get(i).get("ORGAN_TYPE").equals("-1")){
					organ.setOrgType("移动平台");
				}
				oList.add(organ);
		}
		
		
		return oList;
		
	}



	@RequestMapping(value = "/queryAlltree", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> queryAlltree(HttpSession session){
		PersonManage  user = (PersonManage) session.getAttribute("loginUser");
		String userId = user.getId();
		TreeNode node=new TreeNode();
		node.setUserId(userId);
		node=organizationInfoManager.queryAllTree(node,"-1");

		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		treeNodes.add(node);
		return treeNodes;
//		treeNodes = node.getNodes();
//		return treeNodes;

	}

	@RequestMapping(value = "/queryAllOrganTree", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> queryAllOrganTree(HttpSession session){
		PersonManage  user = (PersonManage) session.getAttribute("loginUser");
		String userId = user.getId();
		TreeNode node=new TreeNode();
		node.setUserId(userId);
		node=organizationInfoManager.queryAllOrganTree(node, "-1");

		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		treeNodes.add(node);
		return treeNodes;

	}
	/**
	 * 企业注册
	 */
	@RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
	@ResponseBody
	public  int insertCompany(HttpServletRequest request,PersonManage personManage){
		int res=0;
		String orgCname=request.getParameter("company");
		OrganizationInfo organizationInfo=new OrganizationInfo();
		organizationInfo.setOrgCname(orgCname);
		organizationInfo.setDelFlag(2);
		Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
		organizationInfo.setCreateDate(now);
		organizationInfo.setOrgType("1");
		String oid=UUIDGenerator.getUUID();
		organizationInfo.setId(oid);
		res=organizationInfoManager.insertOrg(organizationInfo);
		AuthorityRoleInfo authorityRoleInfo1=new AuthorityRoleInfo();
		authorityRoleInfo1.setRoleCode("companyAdmin");
		String roleId=authorityRoleInfoDao.query(authorityRoleInfo1).get(0).getId();
		if(res>0){
			OrganizationRelation organizationRelation=new OrganizationRelation();
			organizationRelation.setId(UUIDGenerator.getUUID());
			organizationRelation.setOrganizationInfoId(oid);
			String rootOrgId="00000000000000000000000000000001";
			String paranID=organizationRelationManager.queryOrgRbyOrgFoid(rootOrgId).get(0).getId();
			organizationRelation.setOrganizationRelationId(paranID);
			organizationRelation.setRelation("1");
			organizationRelation.setOrganOrder("1");
			organizationRelation.setCreateDate(now);
			int re=0;
			re=organizationRelationDao.insertOrg(organizationRelation);
			if(re>0){
				String userCname=request.getParameter("usercname");
				String userEname=request.getParameter("userename");
				String password=request.getParameter("password");
				String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
				String telPhone=request.getParameter("telPhone");
				Blowfish bf = new Blowfish(passwordKey);
				String encrypt = bf.encrypt(password.trim());
				personManage.setUserCname(userCname);
				personManage.setUserEname(userEname);
				personManage.setMd5Pwd(encrypt);
				personManage.setTelephone(telPhone);
				personManage.setDelFlag("2");
				personManage.setCompanyId(oid);
				String userId=UUIDGenerator.getUUID();
				personManage.setId(userId);
				personManage.setCreateDate(now);
				personManageManager.insertPerson(personManage);
				OrganPersonRelation organPersonRelation=new OrganPersonRelation();
//				organPersonRelation.setId(UUIDGenerator.getUUID());
				organPersonRelation.setPersonInfoId(userId);
				organPersonRelation.setOrganizationInfoId(oid);
				organPersonRelation.setCreateDate(now);
				organPersonRelationManager.save(organPersonRelation);
				AuthorityUserInfo authorityUserInfo=new AuthorityUserInfo();
				authorityUserInfo.setId(UUIDGenerator.getUUID());
				AuthorityRoleInfo authorityRoleInfo=authorityRoleInfoDao.find(roleId);
				PersonManage personManage1=personManageManager.findPersonCnameByOid(userId);
				authorityUserInfo.setRole(authorityRoleInfo);
				authorityUserInfo.setPerson(personManage1);
				authorityUserInfo.setCreateTime(now);
				authorityUserInfoDao.insert(authorityUserInfo);
			}
		}
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
	public Map<String, Object>  queryBussiness(HttpServletRequest request,OrganizationInfo organizationInfo,PersonManage personManage) {
		String sEcho = request.getParameter("sEcho");
		Map<String, Object> map = new HashMap<String, Object>();
		organizationInfo.setInitPage(0);
		//		用于存放一级根目录的ID
		List<String>list=new ArrayList<>();
		//查询一级根目录
		String rootOrgId="00000000000000000000000000000001";
		List<OrganizationRelation>organizationRelationList=organizationRelationManager.queryOrgRbyOrgFoid(rootOrgId);
		List<OrganizationRelation>organizationRelationList1=organizationRelationManager.queryOrgRbyOrgFoid(organizationRelationList.get(0).getId());
		for(OrganizationRelation organizationRelation:organizationRelationList1){
			list.add(organizationRelation.getOrganizationInfoId());
		}
		organizationInfo.setList(list);
		//查询一级根目录的信息
		List<OrganizationInfo> companyList=organizationInfoDao.queryBussiness(organizationInfo);

	for(OrganizationInfo organizationInfo1:companyList){

		personManage.setCompanyId(organizationInfo1.getId());

				List<PersonManage> lists=personManageManager.queryBussiness(personManage);
					PersonManage personManage1=lists.get(0);
					organizationInfo1.setUserCname(personManage1.getUserCname());
					organizationInfo1.setUserEname(personManage1.getUserEname());
	}
		map.put("sEcho",sEcho);
		map.put("aaData", companyList);
		organizationInfo.setInitPage(1);
		int size= organizationInfoDao.queryBussiness(organizationInfo).size();
		map.put("iTotalDisplayRecords",size);
		map.put("iTotalRecords", size);
		return map;
	}
	@RequestMapping(value = "/updateCompany", method = RequestMethod.GET)
	@ResponseBody
	public String  updateCompany(HttpServletRequest request,PersonManage personManage,OrganizationInfo organizationInfo) {
		String ids=request.getParameter("ids");
		String [] str=ids.split(",");
		Date date=new Date();
		String flag="success";
		for (int i = 0; i < str.length; i++) {
			organizationInfo.setId(str[i]);
			organizationInfo.setActivationTime(date);
			organizationInfoManager.updateCompany(organizationInfo);
			personManage.setCompanyId(str[i]);
			String personId=personManageManager.queryBussiness(personManage).get(0).getId();
			PersonManage personManage1=new PersonManage();
			personManage1.setId(personId);
			personManageManager.updateCompany(personManage1);
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
