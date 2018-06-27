package com.pmcc.soft.core.organization.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.AuthorityRoleInfo;
import com.pmcc.soft.core.organization.domain.AuthorityUserInfo;
import com.pmcc.soft.core.organization.domain.NewsInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.AuthorityRoleInfoManager;
import com.pmcc.soft.core.organization.manager.AuthorityUserInfoManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.CommonUtils;

@Controller
@RequestMapping("authorityUser")
public class AuthorityUserInfoController {
	@Autowired
	private AuthorityUserInfoManager authorityUserInfoManager;
	@Resource
    PublicExcuteSqlService excuteSqlService;
	@Autowired
	private AuthorityRoleInfoManager authorityRoleInfoManager;
	private static final String NAME_SPACE = "authorityUser/";

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String type) {
		ModelAndView model = new ModelAndView(NAME_SPACE + "show");
		model.addObject("type", CommonUtils.convertNull(type));
		return model;
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> query(String person) {
		List<Map<String, String>> res =new ArrayList<Map<String, String>>();
		List<AuthorityUserInfo> list = authorityUserInfoManager.query(person,null);
		Map<String, String> info = null;
		if(list!=null && list.size()>0){
			for (AuthorityUserInfo authorityUserInfo : list) {
				info =new HashMap<String,String>();
				info.put("id", authorityUserInfo.getId());
				info.put("roleId", authorityUserInfo.getRole().getId());
				info.put("roleCode", authorityUserInfo.getRole().getRoleCode());
				info.put("roleName", authorityUserInfo.getRole().getRoleName());
				info.put("appSystem", authorityUserInfo.getRole().getAppSystem());
				info.put("remark", authorityUserInfo.getRole().getRemark());
				
				res.add(info);
			}
		
		}
		
		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request,
			String roleItems, String user,HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		model.put("success", true);
		AuthorityUserInfo userInfo = new AuthorityUserInfo();
		PersonManage p = new PersonManage();
		userInfo.setCreateTime(new Date());
		userInfo.setRptPerson(loginUser.getId());
		p.setId(user);
		userInfo.setPerson(p);
		authorityUserInfoManager.save(roleItems, userInfo);
		return model;

	}
	
	
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id) {
        ModelAndView model =new ModelAndView("/authorityUser/detail");
        AuthorityUserInfo userInfo=new AuthorityUserInfo();
        userInfo.setId(id);
        AuthorityRoleInfo authorityRoleInfo=new AuthorityRoleInfo();
        AuthorityUserInfo authorityUserInfo= authorityUserInfoManager.findByid(userInfo);
        if(authorityUserInfo !=null&&!authorityUserInfo.equals("")){
             AuthorityRoleInfo role=authorityUserInfo.getRole();
             String sql ="select * from  AUTHORITY_ROLE a where a.OID =(select  t.ROLE_OID from  AUTHORITY_USER_ROLE  t where t.USER_OID='"+id+"')";
             		        List<Map<String,String>> list=excuteSqlService.executeSql(sql);
             		        if(!list.isEmpty()&&list !=null){
             		        	authorityRoleInfo.setId(list.get(0).get("OID"));
             		        	authorityRoleInfo.setRoleCode(list.get(0).get("ROLE_CODE"));
             		        	authorityRoleInfo.setRoleName(list.get(0).get("ROLE_NAME"));
             		        	authorityRoleInfo.setAppSystem(list.get(0).get("APP_SYSTEM"));
             		        	authorityRoleInfo.setDesc(list.get(0).get("DESCRIPTION"));
             		        	authorityRoleInfo.setRemark(list.get(0).get("REMARK"));
             		        	authorityRoleInfo.setRptPerson(list.get(0).get("RPT_PERSON"));	
             		        }  	
        } 
        model.addObject("authorityRoleInfo", authorityRoleInfo);	
        return model;
    }
	
	@RequestMapping(value = "/systemEdit", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> systemEdit(String id,String person) {
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		Map<String, Object> res=null;
			//根据应用获取应用角色
			AuthorityRoleInfo params= new   AuthorityRoleInfo();
			params.setInitPage(1);	//取消分页
			List<AuthorityRoleInfo> roleItems =authorityRoleInfoManager.query(params);
			for (AuthorityRoleInfo role : roleItems) {
				res = new HashMap<String, Object>();
				res.put("id", role.getId());
				res.put("text", role.getRoleName());
				res.put("system",role.getAppSystem());
				res.put("checked", authorityUserInfoManager.isChecked(person,role.getId()));
				res.put("state", "open");
				res.put("iconCls", "icon-add");
				result.add(res);
			}
	
		return result;

	}

}
