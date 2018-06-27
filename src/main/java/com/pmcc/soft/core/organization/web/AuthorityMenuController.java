package com.pmcc.soft.core.organization.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.organization.manager.*;
import com.pmcc.soft.week.domain.Message;
import com.pmcc.soft.week.domain.ProgrammeManagement;
import com.pmcc.soft.week.domain.TreeNode;
import com.pmcc.soft.week.manager.MessageManager;
import com.pmcc.soft.week.manager.ProgrammeManagementManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.EncryptMD5;
import com.pmcc.soft.core.utils.SystemParamsUtil;

@Controller
@RequestMapping("authority")
public class AuthorityMenuController {

	@Autowired
	private PersonManageManager personManageManager;

	@Autowired
	private AuthorityUserInfoManager authorityUserInfoManager;

	@Autowired
	private OrganPersonRelationManager organPersonRelationManager;
	@Autowired
	private AuthorityMenuInfoManager authorityMenuInfoManager;
	@Autowired
	private MessageManager messageManager;
	@Autowired
	private ProgrammeManagementManager programmeManagementManager;
	@Autowired
	private NewsInfoManager newsInfoManager;

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "findPassword")
	public ModelAndView findPassword(String username) {
		ModelAndView model = new ModelAndView("../jsp/findPassword/findPassword");
		return model.addObject("username", username);
	}
	
	// 系统用户登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request,
									 String userName, String passWord,HttpSession session,HttpServletResponse response) {
		logger.info(userName+"正在登陆系统...");
		Map<String, Object> model = new HashMap<String, Object>();
		
		PersonManage loginUser = null;
		model.put("success", false);
		if ( "".equals(CommonUtils.convertNull(userName))) {
			model.put("loginMsg", "登录失败:用户名或密码不能为空!");
			return model;
		}
		if ("".equals(CommonUtils.convertNull(passWord))) {
			model.put("loginMsg", "登录失败:用户名或密码不能为空!");
			return model;
		}
		PersonManage person = new PersonManage();
		person.setUserEname(userName);
		loginUser = personManageManager.find(person);
		if (loginUser == null) {
			model.put("loginMsg", "登录失败:用户名或密码错误!");
			return model;
		}
		String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
		//加密
		Blowfish bf = new Blowfish(passwordKey);
		String decrypt = bf.decrypt(loginUser.getMd5Pwd());
		String md5pwd = EncryptMD5.getMD5(passWord.trim().getBytes());
		if (decrypt.equals(passWord.trim())) {
			//登录权限判断
			if(!authorityUserInfoManager.hasMenu(loginUser.getId())){
				model.put("loginMsg", "登录失败:无系统权限!");
				return model;
			}
			model.put("loginUrl", "authority/show.do");

			model.put("success", true);
			model.put("loginMsg", "登陆成功!");
			session.setAttribute("loginUser", loginUser);
			return model;
		} else {
			model.put("loginMsg", "登录失败:用户名或密码错误!");
			return model;
		}

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("login/index");
		String sessionId=WebUtils.getSessionId(request);
		PersonManage user = (PersonManage)session.getAttribute("loginUser");
			List<String> roles = new ArrayList<String>();
		if (user != null) {
			// 获取用户所持有的角色
			List<AuthorityUserInfo> aUsers = authorityUserInfoManager.query(user.getId(),null);
			for (int i = 0; i < aUsers.size(); i++) {
				if(aUsers.get(i).getRole()!=null) {
					roles.add(aUsers.get(i).getRole().getId());
				}

			}
		}
		String id="-1";
		List<MenuNode> aList = new ArrayList<MenuNode>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parentMenu", id);
		param.put("roles", roles);
		TreeNode node=new TreeNode();
		TreeNode treeNode = authorityMenuInfoManager.queryAllMenuMap(param,node);

		modelAndView.addObject("treeNodes",treeNode.getNodes());//忽略根目录，直接展现数据
		return modelAndView;
	}


	@RequestMapping(value = "/showQuestion", method = RequestMethod.GET)
	public String showQuestion() {
		return "login/Question";
	}

	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> logOut(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		model.put("loginOutMsg", "注销成功!");
		return model;
	}
	@RequestMapping(value = "/backIndexShow", method = RequestMethod.GET)
	public String backIndexShow(HttpServletRequest request) {

		return "login/backIndex";

	}

	@RequestMapping(value = "/dashboard.do", method = RequestMethod.GET)
	public ModelAndView dashboardShow(HttpServletRequest request,Message   message,HttpSession session) {
		ModelAndView mav = new ModelAndView("login/dashboard");
		PersonManage user = (PersonManage)session.getAttribute("loginUser");
		message.setMessageTo(user.getId());
		message.setMessageToFlag("0");
		//查询收件箱的条数
		message.setInitPage(1);
		int inBoxCount=messageManager.query(message).size();
		//查询未读邮件的条数
		message.setShowFlag("1");
		int noReadBoxCount=messageManager.query(message).size();
		//查询日程的前4条记录
		List<ProgrammeManagement> programmeList=programmeManagementManager.query(user.getId());
		//查询公告的前4条记录
		NewsInfo newsInfo=new NewsInfo();
		newsInfo.setOrgId(user.getCompanyId());
		List<NewsInfo>newsList=newsInfoManager.queryNewsInfo(newsInfo);
		mav.addObject("newsList",newsList);
		mav.addObject("programmeList",programmeList);
		mav.addObject("user",user.getUserCname());
		mav.addObject("inBoxCount",inBoxCount);
		mav.addObject("noReadBoxCount",noReadBoxCount);
		return mav;

	}


	private void convertOnlieUser(String sessionId,PersonManage loginUser) {
		OnlineUser online = new OnlineUser();
		online.setUserId(loginUser.getId());
		online.setName(loginUser.getUserCname());
		OrganPersonRelation personRelation = organPersonRelationManager.queryByPersonId(loginUser.getId());
		online.setUnit(personRelation.getOrganizationInfoCNName());
		online.setUnitId(personRelation.getOrganizationInfoId());
		AppUtils.addOnlineUser(sessionId, online);
	}
}
