package com.pmcc.soft.core.organization.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.AuthorityRoleInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.AuthorityRoleInfoManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.CommonUtils;

@Controller
@RequestMapping("authorityRole")
public class AuthorityRoleInfoController {
	@Autowired
	private AuthorityRoleInfoManager authorityRoleInfoManager;
	private static final String NAME_SPACE="authorityRole/";

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show() {
		return NAME_SPACE+"show";
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public List<AuthorityRoleInfo> query(AuthorityRoleInfo info) {
		List<AuthorityRoleInfo> res = authorityRoleInfoManager.query(info);
		return res;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> remove(String id) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		authorityRoleInfoManager.delete(id);
		return model;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request,
			AuthorityRoleInfo info,HttpSession session) {
	/*	Map<String, Object> model = new HashMap<String, Object>();*/
		PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
	/*	model.put("success", true);*/
		info.setCreateTime(new Date());
		info.setRptPerson(loginUser.getId());
		info.setRptUnit(loginUser.getDepartment());
		info.setDelFlag(0);
		authorityRoleInfoManager.save(info);
		return "success";
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public AuthorityRoleInfo find(String id) {
		return authorityRoleInfoManager.find(id);
	}
	

	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public ModelAndView toEdit(String id) {
		ModelAndView model = new ModelAndView(NAME_SPACE+"edit");
		model.addObject("id", CommonUtils.convertNull(id));
		return  model;
	}


	@RequestMapping(value = "/checkData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  checkData(AuthorityRoleInfo info) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<AuthorityRoleInfo> res = authorityRoleInfoManager.query(info);
		if(res != null && res.size() > 0){
			String id = info.getId();
			if(id != null && !"".equals(id)){
				if(	res.size() == 1 && id.equals(res.get(0).getId())){
					model.put("num", 0);
				}else{
					model.put("num", res.size());
				}
			}else{
				model.put("num", res.size());
			}
		}else{
			model.put("num", 0);
		}
		return model;
	}


}
