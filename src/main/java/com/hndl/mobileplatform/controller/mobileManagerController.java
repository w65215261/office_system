package com.hndl.mobileplatform.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.hndl.mobileplatform.model.Dlfilesmanager;
import com.hndl.mobileplatform.model.DlfilesmanagerExample;
import com.hndl.mobileplatform.model.Dlnoticeperson;
import com.hndl.mobileplatform.model.DlnoticepersonExample;
import com.hndl.mobileplatform.model.DlroompostmessageWithBLOBs;
import com.hndl.mobileplatform.service.DlFilesManagerService;
import com.hndl.mobileplatform.service.DlroompostmessageService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.Blowfish;
import com.pmcc.soft.core.common.HttpClientPost;
import com.pmcc.soft.core.common.SendMailUtil;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.Encript;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.ydpt.domain.OfUser;
import com.pmcc.soft.ydpt.manager.OfUserManager;


/**
 * test
 * 
 * @author 郑冰冰
 * @since 2015.07.01
 **/
@Controller
@RequestMapping(value = "mobileManager")
public class mobileManagerController {
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
	@Resource
    private SendMailUtil sendMailUtil;
	@Autowired
	PersonManageManager personManageManager;
	@Autowired
	OfUserManager ofUserManager;
	@Autowired
	DlroompostmessageService dlroompostmessageService;

	/**
	 * 查询创建的房间
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectCreatedRoomsByJid", method = RequestMethod.GET)
    public @ResponseBody List  selectCreatedRoomsByJid(HttpServletRequest request) {
		String jid = request.getParameter("jid");
		String sql = "SELECT * FROM ofMucRoom t WHERE t.roomID IN (SELECT a.roomID FROM ofMucAffiliation a WHERE a.jid ='"+jid+"')";
		
		return publicExcuteSqlService.executeSql(sql);
    }
	@RequestMapping(value = "/deleteRoomByRoomId", method = RequestMethod.GET)
    public @ResponseBody int  deleteRoomByRoomId(HttpServletRequest request) {
		String roomid = request.getParameter("roomid");
		String sql = "delete from ofMucRoom  WHERE roomID ='"+roomid+"'";
		return publicExcuteSqlService.updateBySql(sql);
    }
	@RequestMapping(value = "/exitRoomByRoomIdAndJid", method = RequestMethod.GET)
    public @ResponseBody int  exitRoom(HttpServletRequest request) {
		String roomid = request.getParameter("roomid");
		String jid = request.getParameter("jid");
		String sql = "delete from ofMucMember  WHERE roomID ='"+roomid+"' and jid='"+jid+"'";
		return publicExcuteSqlService.updateBySql(sql);
    }
	@RequestMapping(value = "/selectRosterByUsernameAndJid", method = RequestMethod.GET)
    public @ResponseBody List  selectRosterByUsernameAndJid(HttpServletRequest request) {
		String username = request.getParameter("username");
		String jid = request.getParameter("jid");
		String sql = "select * ofRoster  WHERE ask='0' and username ='"+username+"' and jid='"+jid+"'";
		return publicExcuteSqlService.executeSql(sql);
    }
	//根据openid查询用户
	@RequestMapping(value = "/selectUserByOpenid", method = RequestMethod.GET)
    public @ResponseBody Map  selectUserByJid(HttpServletRequest request) {
		Map map = new HashMap();
		String openid = request.getParameter("openid");
		String sql = "SELECT * from PERSON_INFO where OPEN_ID='"+openid+"'";
		List<Map> list = publicExcuteSqlService.executeSql(sql);
		if(list.size()==0){
			map.put("result", "false");
		}else{
			String passwordFish = list.get(0).get("MD5PWD").toString();
			String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
			//解密
			Blowfish bf = new Blowfish(passwordKey);
			String password = bf.decrypt(passwordFish);
			list.get(0).put("MD5PWD", password);
			map.put("result", "true");
			map.put("user", list.get(0));
		}
		return map;
    }
	//创建QQ用户
	@RequestMapping(value = "/createQQuser", method = RequestMethod.GET)
    public @ResponseBody Map  createQQuser(HttpServletRequest request) {
		Map map = new HashMap();
		String openid = request.getParameter("openid");
		String username = request.getParameter("username");
		String userCname = request.getParameter("usercname");
		String password = request.getParameter("password");
		String sql = "SELECT * from PERSON_INFO where USER_ENAME='"+username+"'";
		List list = publicExcuteSqlService.executeSql(sql);
		if(list.size()>0){
			map.put("result", "用户名重复");
			return map;
		}else{
			PersonManage personManage = new PersonManage();
			personManage.setUserEname(username);
			personManage.setUserCname(userCname);
			personManage.setOpenId(openid);
			//加密
			String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
			//解密
			Blowfish bf = new Blowfish(passwordKey);
			String encrypt = bf.encrypt(password.trim());
			personManage.setMd5Pwd(encrypt);
			personManageManager.save(personManage, "QQ第三方登录");
			OfUser param =new OfUser();
			param.setUsername(personManage.getUserEname());
			param.setEncryptedPassword(encrypt);
			param.setName(userCname);
			ofUserManager.insert(param);
			map.put("result", "true");
			return map;
		}
		
    }
	//根据用户查询通知
	@RequestMapping(value = "/selectNoticeByUsername", method = RequestMethod.GET)
    public @ResponseBody List  selectNoticeByUsername(HttpServletRequest request) {
		String username = request.getParameter("username");
		String page = request.getParameter("page");
		String sql = "SELECT TOP 10 * FROM (SELECT row_number () OVER (ORDER BY b.CREATETIME DESC) AS rownumber ,* FROM "+
		"(SELECT    w.USER_CNAME as cname,d.ORG_CNAME AS department,  i.id AS id,i.title as title,i.content as content,i.createtime as createtime,j.type as type FROM dlNoticeManage i,dlNoticePerson j ,ORGAN_PERSON_RELATION_INFO t ,PERSON_INFO w,ORGANIZATION d  WHERE    t.ORGANIZATION_INFO_ID= d.OID  and   w.DEL_FLAG=0 and  t.PERSON_INFO_ID=w.OID  AND w.USER_ENAME= j.username  AND   i.id = j.infoid  AND j.username = '"+username+"') b ) a WHERE rownumber > "+(Integer.valueOf(page)-1)*10;
		String sqlfile = "SELECT TOP 10 * FROM (SELECT row_number () OVER (ORDER BY b.CREATETIME) AS rownumber ,* FROM "+
				"(SELECT i.id AS id,i.title as title,i.content as content,i.createtime as createtime,j.type as type,k.filepath AS filepath FROM dlNoticeManage i,dlNoticePerson j,dlNoticFile k WHERE	i.id = j.infoid and i.id=k.infoid AND j.username = '"+username+"') b ) a WHERE rownumber > "+(Integer.valueOf(page)-1)*10;
		List<Map> listMap =  publicExcuteSqlService.executeSql(sql);
		List<Map> listMapFile =  publicExcuteSqlService.executeSql(sqlfile);
		for(Map map : listMap){
			map.put("content", SystemParamsUtil.getSysConfig().get("webAddress").toString()+"mobileManager/selectInfoBynoticeid.do?noticeid="+map.get("id"));
			for(Map mapfile :listMapFile){
				
				if(map.get("id").toString().equals(mapfile.get("id"))){
					if(mapfile.get("filepath")!=null && !"".equals(mapfile.get("filepath"))){
						String path = mapfile.get("filepath").toString();
						map.put("filepath", SystemParamsUtil.getSysConfig().get("androidFileAddress").toString()+path); 
					}
				}
				
			}
			
		}
		return listMap;
    }
	//手机app根据notieceid查询通知详情的html5模版
			@RequestMapping(value = "/selectInfoBynoticeid", method = RequestMethod.GET)
			public ModelAndView selectInfoBynoticeid(HttpServletRequest request) {
				ModelAndView model = new ModelAndView("/weixin/weixinNoticeContent");
				String noticeid = request.getParameter("noticeid");
				String sql1 = "select * from dlNoticeManage where id='" + noticeid
						+ "'";
				List<Map> list = publicExcuteSqlService.executeSql(sql1);
				if (list.size() == 0) {
					return model;
				} else {
					model.addObject("result",list.get(0));
					return model;
				}

			}
	//根据用户查询未读总数
		@RequestMapping(value = "/selectNoticeCountByUsername", method = RequestMethod.GET)
	    public @ResponseBody String  selectNoticeCountByUsername(HttpServletRequest request) {
			String username = request.getParameter("username");
			String sql = "SELECT count(1) as sl from dlNoticePerson t , dlNoticeManage w where  t.username='"+username+"' and t.type=0 and t.infoid=w.id";
			List<Map> list = publicExcuteSqlService.executeSql(sql);
			return list.get(0).get("sl").toString();
	    }
	//更新通知表的状态为已读
	@RequestMapping(value = "/updateNoticeType", method = RequestMethod.GET)
    public @ResponseBody int  updateNoticeType(HttpServletRequest request) {
		String noticeid = request.getParameter("noticeid");
		String username = request.getParameter("username");
		String sql = "update dlNoticePerson set type='1',checktime='"+new Date().getTime()+"' where infoid='"+noticeid+"' and username='"+username+"'";
		return publicExcuteSqlService.updateBySql(sql);
    }
	//绑定QQ与系统帐号
	@RequestMapping(value = "/qqBindUser", method = RequestMethod.GET)
	 public @ResponseBody Map  qqBindUser(HttpServletRequest request) {
		Map map = new HashMap();
		String openid = request.getParameter("openid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<Map> listmap = publicExcuteSqlService.executeSql("SELECT * from PERSON_INFO where USER_ENAME='"+username+"'");
		if(listmap.size()>0){
			String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
			//解密
			Blowfish bf = new Blowfish(passwordKey);
			String pwd = listmap.get(0).get("MD5PWD").toString();
			if(!password.trim().equals(bf.decrypt(pwd))){
				map.put("result", "密码错误");
				return map;
			}else{
				if(!"".equals(openid)){
					String sql = "UPDATE PERSON_INFO set OPEN_ID='"+openid+"'  where USER_ENAME='"+username+"'";
					publicExcuteSqlService.updateBySql(sql);
					map.put("result", "true");
				}else{
					map.put("result", "无法获取QQ信息");
				}
				
			}
		}else{
			map.put("result", "系统无此用户");
		}
		return map;
	   }
	//根据jid查询加入的群组
		@RequestMapping(value = "/selectJoinRoomByJid", method = RequestMethod.GET)
	    public @ResponseBody List  selectJoinRoomByJid(HttpServletRequest request) {
			String jid = request.getParameter("jid");
			String sql = "SELECT a.jid as jid,b.* from ofMucMember a right JOIN ofMucRoom b on a.roomID = b.roomID where a.jid ='"+jid+"' ";
			return publicExcuteSqlService.executeSql(sql);
	    }

	// 根据房间名称查询群组
	@RequestMapping(value = "/selectRoomByNaturalName", method = RequestMethod.GET)
	public @ResponseBody
	List selectRoomByNaturalName(HttpServletRequest request) {
		String roomname = request.getParameter("roomname");
		String sql = "select * FROM ofMucRoom where naturalName like '%"+roomname+"%'";
		return publicExcuteSqlService.executeSql(sql);
	}

	// 根据jid查询群组
	@RequestMapping(value = "/selectRoomByName", method = RequestMethod.GET)
	public @ResponseBody
	List selectRoomByName(HttpServletRequest request) {
		String roomname = request.getParameter("roomname");
		String sql = "select * FROM ofMucRoom where name like '%"+roomname+"%'";
		return publicExcuteSqlService.executeSql(sql);
	}
	//根据微信id查询用户，如果没有，直接跳到绑定账户页面
	@RequestMapping(value = "/selectUserByWechatId", method = RequestMethod.GET)
	public ModelAndView selectUserByWechatId(HttpServletRequest request) {
		Map map = new HashMap();

		String openid = request.getParameter("openid");
		if(openid==null){
			ModelAndView model = new ModelAndView("/weixin/sendWeixinInforesult");
			
			model.addObject("result", "没有微信信息！");
			
			return model;
		}
		String sql = "SELECT * from PERSON_INFO where WECHAT_ID='" + openid
				+ "'";
		List<Map> list = publicExcuteSqlService.executeSql(sql);
		if (list.size() == 0) {
			ModelAndView model = new ModelAndView("../../index");
			model.addObject("result", "请先绑定用户！");
			model.addObject("openid",openid);
			return model;
		} else {
			ModelAndView model = new ModelAndView("/weixin/sendWeixinInforesult");
			
			model.addObject("result", "用户已绑定！");
			
			return model;
		}

	}

	// 绑定微信与系统帐号
	@RequestMapping(value = "/weiXinBindUser", method = RequestMethod.POST)
	public @ResponseBody
	Map weiXinBindUser(HttpServletRequest request,HttpServletResponse response) {
		Map map = new HashMap();
		String openid = request.getParameter("openid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<Map> listmap = publicExcuteSqlService
				.executeSql("SELECT * from PERSON_INFO where USER_ENAME='"
						+ username + "'");
		if (listmap.size() > 0) {
			String passwordKey = SystemParamsUtil.getSysConfig()
					.get("passwordKey").toString();
			// 解密
			Blowfish bf = new Blowfish(passwordKey);
			String pwd = listmap.get(0).get("MD5PWD").toString();
			if (!password.trim().equals(bf.decrypt(pwd))) {
				map.put("result", "密码错误");
				return map;
			} else {
				if("".equals(listmap.get(0).get("WECHAT_ID")) || listmap.get(0).get("WECHAT_ID")==null){
					if (!"".equals(openid) && openid!=null) {
						String sql = "UPDATE PERSON_INFO set WECHAT_ID='" + openid
								+ "'  where USER_ENAME='" + username + "'";
						publicExcuteSqlService.updateBySql(sql);
						map.put("result", "true");
					} else {
						map.put("result", "无法获取微信信息");
					}
				}else{
					map.put("result", "用户已经被绑定！");
				}
				

			}
		} else {
			map.put("result", "系统无此用户");
		}
		return map;
	}
	//解除微信绑定
	@RequestMapping(value = "/releaseBound", method = RequestMethod.GET)
	public ModelAndView releaseBound(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("/weixin/sendWeixinInforesult");
		String openid = request.getParameter("openid");
		String sql = "UPDATE PERSON_INFO set WECHAT_ID=''  where WECHAT_ID='" + openid + "'";
		publicExcuteSqlService.updateBySql(sql);
		model.addObject("result","已解除绑定！");
		return model;
	}
	//根据微信id查询通知详情
		@RequestMapping(value = "/selectNoticeByid", method = RequestMethod.GET)
		public ModelAndView selectNoticeByid(HttpServletRequest request) {
			ModelAndView model = new ModelAndView("/weixin/weixinNoticeInfo");
			String personid = request.getParameter("personid");
			String noticeid = request.getParameter("noticeid");
			String wechatId = request.getParameter("openid");
			String sql = "update dlNoticePerson set bz2='1',checktime='"+new Date().getTime()+"'  where infoid='"+noticeid+"' and personid='"+personid+"'";
			publicExcuteSqlService.updateBySql(sql);
			String sql1 = "select * from dlNoticeManage where id='" + noticeid
					+ "'";
			List<Map> list = publicExcuteSqlService.executeSql(sql1);
			if (list.size() == 0) {
				return model;
			} else {
				list.get(0).put("wechatid", wechatId);
				model.addObject("result",list.get(0));
				return model;
			}

		}
		//解除微信绑定
		@RequestMapping(value = "/weiXinBindSucces", method = RequestMethod.GET)
		public ModelAndView weiXinBindSucces(HttpServletRequest request) {
			ModelAndView model = new ModelAndView("/weixin/sendWeixinInforesult");
			
			model.addObject("result","绑定成功！");
			return model;
		}
		/**
		 * 根据微信ID查询通知列表
		 */
		@RequestMapping(value = "queryNoticeByWechatId", method = RequestMethod.GET)
		public ModelAndView queryNoticeByWechatId(
				HttpServletRequest request) {
			ModelAndView model = new ModelAndView("/weixin/weixinNoticeList");
			String pagesize = SystemParamsUtil.getSysConfig().get("pagesize").toString();
			if(pagesize==null || "".equals(pagesize)){
				pagesize="10";
			}
			String page = "1";
			String wechatId = request.getParameter("openid");
			String sql = "select top "+pagesize+"* from (select row_number() over(order by b.checkflag asc, convert(BIGINT,b.createtime) desc) as rownumber,* from ("
					+ "SELECT c.*,b.bz2 as checkflag FROM dlNoticeManage c, PERSON_INFO a,dlNoticePerson b WHERE a.OID = b.personid AND c.id = b.infoid AND a.WECHAT_ID ='"+wechatId+"'"
                    +  ")b)a where rownumber > "+(Integer.valueOf(page)-1)*Integer.parseInt(pagesize);
			List<Map> list = publicExcuteSqlService.executeSql(sql);
			String sqlsize = "SELECT count(1) as size FROM dlNoticeManage c WHERE c.id IN (SELECT b.infoid FROM PERSON_INFO a,dlNoticePerson b WHERE a.OID = b.personid AND a.WECHAT_ID = '"+wechatId+"')";
			List<Map> listsize = publicExcuteSqlService.executeSql(sqlsize);
			int size = Integer.parseInt(listsize.get(0).get("size").toString());
			int total=0;
			if(size!=0){
				if(size%Integer.parseInt(pagesize)!=0){
					total = size/Integer.parseInt(pagesize)+1;
				}else{
					total = size/Integer.parseInt(pagesize);
				}
			}
			model.addObject("noticeList",list);
			model.addObject("wechatId",wechatId);
			model.addObject("page",page);
			model.addObject("total",total);
			return model;
		}
		//ajax获取分页数据
		@RequestMapping(value = "queryNoticeForajax", method = RequestMethod.GET)
		@ResponseBody
		public Object queryNoticeForajax(
				HttpServletRequest request) {
			String pagesize = SystemParamsUtil.getSysConfig().get("pagesize").toString();
			if(pagesize==null || "".equals(pagesize)){
				pagesize="10";
			}
			String page = request.getParameter("page");
			String wechatId = request.getParameter("wechatId");
			String sql = "select top "+pagesize+"* from (select row_number() over(order by b.tp asc, convert(BIGINT,b.createtime) desc) as rownumber,* from ("
					+ "SELECT c.*,b.type as tp FROM dlNoticeManage c, PERSON_INFO a,dlNoticePerson b WHERE a.OID = b.personid AND c.id = b.infoid AND a.WECHAT_ID ='"+wechatId+"')"
                    +  "b)a where rownumber > "+(Integer.valueOf(page)-1)*Integer.parseInt(pagesize);
			List<Map> list = publicExcuteSqlService.executeSql(sql);
			return list;
		}
		//查看通知详情
		@RequestMapping(value = "/noticeInfo", method = RequestMethod.GET)
		public ModelAndView noticeInfo(HttpServletRequest request) {
			ModelAndView model = new ModelAndView("/weixin/weixinNoticeInfo");
			String wechatId = request.getParameter("openid");
			String noticeid = request.getParameter("noticeid");
			String sql = "update dlNoticePerson set bz2='1',checktime='"+new Date().getTime()+"'  where infoid='"+noticeid+"' and personid=(SELECT a.OID from PERSON_INFO a  where a.wechat_id='"+wechatId+"')";
			publicExcuteSqlService.updateBySql(sql);
//			String sql1 = "select * from dlNoticeManage where id='" + noticeid
//					+ "'";
			String sql1 = String.format(
					"SELECT dn.*,pi.USER_CNAME,org.ORG_CNAME " +
					"FROM dlNoticeManage dn " +
					"LEFT JOIN PERSON_INFO pi on dn.createid = pi.USER_ENAME " +
					"LEFT JOIN ORGAN_PERSON_RELATION_INFO op on pi.OID = op.PERSON_INFO_ID " +
					"LEFT JOIN ORGANIZATION org on op.ORGANIZATION_INFO_ID = org.OID " +
					"WHERE dn.id = '%s'", noticeid);
			List<Map> list = publicExcuteSqlService.executeSql(sql1);
			if (list.size() == 0)
				return model;
			
			list.get(0).put("wechatid", wechatId);
			Map<String, Object> map = list.get(0);
			Long createTimeLong = Long.parseLong(map.get("createtime").toString());
			Date date = new Date(createTimeLong);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			String createTime = sdf.format(date);
			map.put("createtime", createTime);
			model.addObject("result",list.get(0));
			model.addObject("flag",0);
			return model;
		}
		@RequestMapping(value = "/toResetPassword", method = RequestMethod.GET)
		public ModelAndView toResetPassword(String username) {
			ModelAndView model = new ModelAndView("../../update/resetPassword");
			return model.addObject("username", username);
//			return "../../update/resetPassword";
		}
		
		//找回密码
		@RequestMapping(value = "/sendMail")
		public @ResponseBody Object sendMail(HttpServletRequest request) {
			Map<String, String> map = new HashMap<String, String>();
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String sql = "select USER_MAIL, MD5PWD from PERSON_INFO where USER_ENAME='" + username + "'";
			List<Map> list = publicExcuteSqlService.executeSql(sql);
			String email1 = "";
			String encryptedPassword = "";
			if(list.size() == 0) {
				map.put("state", "1");
				map.put("content", "没有该用户");
				return map;
			}
			for(Map m : list) {
				email1 = m.get("USER_MAIL").toString();
				encryptedPassword = m.get("MD5PWD").toString();
			}
			System.out.println(email1);
			if(!email.equalsIgnoreCase(email1)) {
				map.put("state", "1");
				map.put("content", "邮箱输入不正确！");
			} else {
				System.out.println(encryptedPassword);
				String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
				Blowfish bf = new Blowfish(passwordKey);
				sendMailUtil.send(email1,"你的密码是：" + bf.decrypt(encryptedPassword));
				map.put("state", "0");
				map.put("content", "密码已发送至邮箱！");
			}
			return map;
		}	
		@RequestMapping(value = "noticeFile")
		@ResponseBody
		public Object noticeFile(String noticeId) {
			if(noticeId == null || "".equals(noticeId)) return null;
			String sql = String.format(
					"select a.filename, a.filepath from dlNoticFile a, dlNoticeManage b where a.infoid = b.id and b.id = '%s'", noticeId);
			List<Object> list = publicExcuteSqlService.executeSql(sql);
			Map<String, Object> map = new HashMap<String, Object>();
			if(list.size() == 0) {
				map.put("state", 1);
			} else {
				Object obj = list.get(0);
				Map<String, Object> m = (Map<String, Object>)obj;
				String filePath = m.get("filepath").toString();
				String androidFileAddress = SystemParamsUtil.getSysConfig().get("androidFileAddress").toString();
				m.put("filepath", androidFileAddress + filePath);
				map.put("state", 0);
				map.put("result", list.get(0));
			}
			return map;
		}
		
		@RequestMapping(value = "showCommontList")
		@ResponseBody
		public Map<String, Object> showCommontList(String noticeid, String page) {
			if(noticeid == null || "".equals(noticeid)) {
				return null;
			}
			Map<String, Object> result = new HashMap<String, Object>();
			String queryListByNoticeidSql = String.format(
					"select top 5* from (select row_number() over(order by b.createtime desc, convert(BIGINT,b.createtime) desc) as rownumber,* from (" +
					"SELECT t5.org_cname, t5.user_cname, t5.user_sex, t6.* FROM(" +
					"SELECT t3.*, t4.user_cname, t4.user_sex, t4.oid from " +
					"(select t1.org_cname, t2.PERSON_INFO_ID from ORGANIZATION t1 JOIN ORGAN_PERSON_RELATION_INFO t2 ON t1.OID=t2.ORGANIZATION_INFO_ID ) t3 " +
					"JOIN PERSON_INFO t4 ON t3.PERSON_INFO_ID=t4.OID) t5 " +
					"JOIN " +
					"(SELECT * FROM dlRoomPostMessage t WHERE t.noticeid = '%s') t6 " +
					"ON t5.oid=t6.createid " +
					")b)a where rownumber > %d", noticeid, (Integer.parseInt(page) - 1)*5);
			System.out.println(queryListByNoticeidSql);
			List list = publicExcuteSqlService.executeSql(queryListByNoticeidSql);
			if(list.size() == 0) {
				return null;
			}
			String sqlSize = String.format("select count(1) size from dlRoomPostMessage where noticeid = '%s'", noticeid);
			List<Map> listsize = publicExcuteSqlService.executeSql(sqlSize);
			int size = Integer.parseInt(listsize.get(0).get("size").toString());
			int total=0;
			if(size!=0){
				if(size%5!=0){
					total = size/5+1;
				}else{
					total = size/5;
				}
			}
			Map<String, String> allId = new HashMap<String, String>();
			for(Object obj : list) {
				Map<String, Object> map = (Map<String, Object>)obj;
				allId.put(map.get("id").toString(), map.get("user_cname").toString());
			}
			String allIdString = "";
			for(Entry entry : allId.entrySet()) {	
				allIdString += "'" + entry.getKey() + "',";
			}
			allIdString = allIdString.substring(0, allIdString.length() - 1);
			String replySql = String.format(
					"SELECT t2.user_cname, t2.user_sex, t1.* FROM dlRoomPostMessage t1 " +
					"JOIN PERSON_INFO t2 ON t1.createid = t2.oid " +
					"WHERE t1.noticeid in (%s)", allIdString);
			System.out.println(replySql);
			List list1 = publicExcuteSqlService.executeSql(replySql);
			for(Object obj : list1) {
				Map<String, Object> map = (Map<String, Object>)obj;
				for(Entry entry : allId.entrySet()) {
					if(entry.getKey().equals(map.get("noticeid"))){
						map.put("toObserver", entry.getValue());
					}
				}
			}
			list.addAll(list1);
			for(Object obj : list) {
				Map<String, Object> map = (Map<String, Object>)obj;
				long createTimeMills = Long.parseLong(map.get("createtime").toString());
				Date date = new Date(createTimeMills);
				SimpleDateFormat  sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				String createTime = sdf.format(date);
				map.put("createtime", createTime);
			}
			result.put("state", "0");
			result.put("list", list);
			result.put("total", total);
			return result;
		}
		
		@RequestMapping(value = "insertCommont", method = RequestMethod.GET)
		@ResponseBody
		public Object insertCommont(DlroompostmessageWithBLOBs dbs, String wechatId) {
			dbs.setId(UUIDGenerator.getUUID());
			String getCreateIdSql = String.format(
					"select OID from PERSON_INFO where WECHAT_ID = '%S'", wechatId);
			List l = publicExcuteSqlService.executeSql(getCreateIdSql);
			String createId = "";
			for(Object obj : l) {
				Map map = (Map)obj;
				createId = map.get("OID").toString();
			}
			dbs.setCreateid(createId);
			dbs.setCreatetime(String.valueOf(System.currentTimeMillis()));
			int i = dlroompostmessageService.insert(dbs);
			if(i == 0) {
				return null;
			}
			List list = (List)(showCommontList(dbs.getNoticeid(), "1").get("list"));
			if(list.size() == 0) {
				return null;
			}
			return list.get(0);
		}
		
		@RequestMapping(value = "/bindEmail", method = RequestMethod.GET)
		@ResponseBody
		public Map bindEmail(String username, String password, String email) {
			Map result = new HashMap();
			String sql = String.format("select MD5PWD from PERSON_INFO where USER_ENAME = '%s'", username);
			List list = publicExcuteSqlService.executeSql(sql);
			if(list.size() == 0) {
				result.put("state", "1");
				result.put("content", "用户不存在!");
				return result;
			}
			//验证密码
			Map map = (Map)list.get(0);
			String md5Pwd = map.get("MD5PWD").toString();
			String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
			Blowfish bf = new Blowfish(passwordKey);
			String pwd = bf.encrypt(password);
			if(!bf.decrypt(md5Pwd).equals(password)){
				result.put("state", "1");
				result.put("content", "密码输入错误！");
				return result;
			}
			String searchChars="@";
			if(email.contains(searchChars) ==false){
				result.put("state", "1");
				result.put("content", "邮箱格式输入错误！");
				return result;	
			}
			
			String sql1 = String.format("update PERSON_INFO set USER_MAIL = '%s' where USER_ENAME = '%s'", email, username);
			publicExcuteSqlService.executeSql(sql1); 
			result.put("state", "0");
			result.put("content", "修改成功");
			return result;
		}
		/**
		 * 手机端用户修改密码
		 */
		@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
		@ResponseBody
		public Map updatePassword(String username, String password, String newPassword){
			Map result = new HashMap();
			//验证老密码
			String sql = String.format("select MD5PWD from PERSON_INFO where USER_ENAME = '%s'", username);
			List list = publicExcuteSqlService.executeSql(sql);
			if(list.size() == 0) {
				result.put("state", "1");
				result.put("content", "用户不存在!");
				return result;
			}
			Map map = (Map)list.get(0);
			String md5Pwd = map.get("MD5PWD").toString();
			String passwordKey = SystemParamsUtil.getSysConfig().get("passwordKey").toString();
			Blowfish bf = new Blowfish(passwordKey);
			if(!bf.decrypt(md5Pwd).equals(password)){
				result.put("state", "1");
				result.put("content", "密码错误！");
				return result;
			}
			String encrypt = bf.encrypt(newPassword);
			String sql1 = String.format("update PERSON_INFO set MD5PWD = '%s' where USER_ENAME = '%s'", encrypt, username);
			publicExcuteSqlService.executeSql(sql1); 
			result.put("state", "0");
			result.put("content", "修改成功");
			return result;
		}
		
}
