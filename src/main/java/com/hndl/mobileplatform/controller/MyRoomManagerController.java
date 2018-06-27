package com.hndl.mobileplatform.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hndl.mobileplatform.model.Dlroompost;
import com.hndl.mobileplatform.model.DlroompostExample.Criteria;
import com.hndl.mobileplatform.model.Dlnoticeperson;
import com.hndl.mobileplatform.model.DlnoticepersonExample;
import com.hndl.mobileplatform.model.DlroompostExample;
import com.hndl.mobileplatform.model.DlroompostWithBLOBs;
import com.hndl.mobileplatform.model.DlroompostmessageExample;
import com.hndl.mobileplatform.model.DlroompostmessageWithBLOBs;
import com.hndl.mobileplatform.service.DlNoticPersonService;
import com.hndl.mobileplatform.service.DlroompostService;
import com.hndl.mobileplatform.service.DlroompostmessageService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.BaseVO;
import com.pmcc.soft.core.organization.domain.PersonInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;

/**
 * test
 * 
 * @author 郑冰冰
 * @since 2015.07.01
 **/
@Controller
@RequestMapping(value = "myRoomManager")
public class MyRoomManagerController {
	@Autowired
	PersonManageManager personManageManager;
	@Resource
	DlroompostService roompostService;
	@Resource
	PublicExcuteSqlService excuteSqlService;
	@Resource
	DlroompostmessageService dlroompostmessageService;
	@Resource
	private DlNoticPersonService dlNoticePersonService;
	@Resource
	private DlNoticPersonService dnps;
	
	@RequestMapping(value = "/selectNoticesByRoomId")
	public @ResponseBody
	Object mobileDelete(String roomid, BaseVO baseVo) {
		Map map = new HashMap();
		DlroompostExample example = new DlroompostExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoomidEqualTo(roomid);
		example.setOrderByClause("createtime desc");
		int total = roompostService.countByExample(example);
		List<Dlroompost> rows = roompostService
				.selectByExample(example, baseVo);
		for (Dlroompost dlroompost : rows) {
			String sql="select  r.ORG_CNAME from    ORGANIZATION  r where r.OID=(select e.ORGANIZATION_INFO_ID from ORGAN_PERSON_RELATION_INFO  e where e.PERSON_INFO_ID='"+dlroompost.getCreateid()+"')";
			List <Map> list=excuteSqlService.executeSql(sql);
			String orgCname=null;
			if(!list.isEmpty()&&list.size()>0){
			orgCname=	list.get(0).get("ORG_CNAME").toString();
			}
			dlroompost.setDepartment(orgCname);	
			PersonManage personManage = new PersonManage();
			personManage.setId(dlroompost.getCreateid());
			PersonManage person = personManageManager.find(personManage);
			dlroompost.setPerson(person.getUserCname());
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@RequestMapping(value = "/addNotice")
	public ModelAndView addNotice(String roomid, String id) {
		ModelAndView view = new ModelAndView("../../myRoom/addNotice");
		if (roomid != null && id == null) {
			String sql = "SELECT * from ofMucRoom where roomID='" + roomid
					+ "'";
			List roomObject = excuteSqlService.executeSql(sql);
			if (roomObject.size() > 0) {
				view.addObject("room", roomObject.get(0));
			}
		}
		if (roomid != null && id != null) {
			String sql = "SELECT * from ofMucRoom where roomID='" + roomid
					+ "'";
			List roomObject = excuteSqlService.executeSql(sql);
			if (roomObject.size() > 0) {
				view.addObject("room", roomObject.get(0));
			}
			DlroompostWithBLOBs dlroompostWithBLOBs = roompostService
					.selectByPrimaryKey(id);
			view.addObject(dlroompostWithBLOBs);
		}
		return view;
	}

	@RequestMapping(value = "/editNotice", method = RequestMethod.POST)
	@ResponseBody
	public String editNotice(String roomid, String id,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		PersonManage personManage = (PersonManage) session
				.getAttribute("loginUser");
		String sql = "select jid from ofMucAffiliation  where roomID='"
				+ roomid + "'";
		List<Map<String, String>> map = excuteSqlService.executeSql(sql);
		if (!map.isEmpty() && map != null) {
			String pname = map.get(0).get("jid");
			String[] sss = pname.split("@");
			if (sss[0].equals(personManage.getUserEname())) {
				return "success";
			}
		}
		if(id !=""&&id !=null){
			DlroompostWithBLOBs dlroompostWithBLOBs = roompostService
					.selectByPrimaryKey(id);
			PersonManage personManage1 = new PersonManage();
			personManage1.setId(dlroompostWithBLOBs.getCreateid());
			PersonManage person = personManageManager.find(personManage1);
			if (person.getUserEname().equals(personManage.getUserEname())) {
				return "success";
			}
		}else{
		    String sql1 = "SELECT count(1) as SL FROM ofMucMember where roomID="+roomid+" and jid='"+personManage.getUserEname() + "@rfya9p57h7wzd8s"+"'";
		    List<Map> list =  excuteSqlService.executeSql(sql1);
			   if(Integer.valueOf(list.get(0).get("SL").toString())>=1){
					return "success";
			   }
		}
		return "fail";
	}

	public ModelAndView addNoticeSucces(String roomid) {
		ModelAndView view = new ModelAndView("../../myRoom/addNotice");
		if (roomid != null) {
			String sql = "SELECT * from ofMucRoom where roomID='" + roomid
					+ "'";
			List roomObject = excuteSqlService.executeSql(sql);
			if (roomObject.size() > 0) {
				view.addObject("room", roomObject.get(0));
			}

		}

		return view;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public void insert(DlroompostWithBLOBs ni, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		PersonManage personManage = (PersonManage) session
				.getAttribute("loginUser");
		String id = ni.getId();
		if (!id.isEmpty() && id != "") {
			DlroompostWithBLOBs dlroompostWithBLOBs = roompostService
					.selectByPrimaryKey(id);
			String Roomid = dlroompostWithBLOBs.getRoomid();
			/*
			 * String
			 * sql="select jid from ofMucAffiliation  where roomID='"+Roomid
			 * +"'"; List<Map<String,String>> map=
			 * excuteSqlService.executeSql(sql); if(!map.isEmpty()&&map !=null){
			 * String pname=map.get(0).get("jid"); String[] sss=
			 * pname.split("@"); if(sss[0].equals(personManage.getUserEname())){
			 */
			dlroompostWithBLOBs.setContent(ni.getContent());
			if (ni.getTitle() != null && !ni.getTitle().equals("")) {
				dlroompostWithBLOBs.setTitle(ni.getTitle());
				roompostService
						.updateByPrimaryKeyWithBLOBs(dlroompostWithBLOBs);
				response.getWriter().write("success");
				response.getWriter().flush();
			} else {
				response.getWriter().write("fail");
				response.getWriter().flush();
			}

			/*
			 * }else{ response.getWriter().write("fail");
			 * response.getWriter().flush(); }
			 */
			/* } */
		} else {
			
			if (ni.getTitle() != null && !ni.getTitle().equals("")) {
				ni.setId(UUIDGenerator.getUUID());
				ni.setCreateid(personManage.getId());
				Date date = new Date();
				/*
				 * SimpleDateFormat ss = new
				 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				 */
				Long time = date.getTime();// Long类型变量
				ni.setCreatetime(time.toString());
				roompostService.insert(ni);
				response.getWriter().write("success");
				response.getWriter().flush();
			} else {
				response.getWriter().write("fail");
				response.getWriter().flush();
			}
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		PersonManage personManage = (PersonManage) session
				.getAttribute("loginUser");
		String[] data = id.split(",");
		for (int i = 0; i < data.length; i++) {
			DlroompostWithBLOBs dlroompostWithBLOBs = roompostService
					.selectByPrimaryKey(data[i]);
			String Roomid = dlroompostWithBLOBs.getRoomid();
			PersonManage personManage1 = new PersonManage();
			personManage1.setId(dlroompostWithBLOBs.getCreateid());
			PersonManage person = personManageManager.find(personManage1);
			String sql = "select jid from ofMucAffiliation  where roomID='"
					+ Roomid + "'";
			List<Map<String, String>> map = excuteSqlService.executeSql(sql);
			String pname = map.get(0).get("jid");
			String[] sss = pname.split("@");
			if (sss[0].equals(personManage.getUserEname())||person.getUserEname().equals(personManage.getUserEname())) {
				DlroompostmessageExample example = new DlroompostmessageExample();
				DlroompostmessageExample.Criteria Criteria = example
						.createCriteria();
				Criteria.andNoticeidEqualTo(data[i]);
				List<DlroompostmessageWithBLOBs> list = dlroompostmessageService
						.selectByExampleWithBLOBs(example);
				if (list != null && !list.isEmpty()) {
					for (int j = 0; j < list.size(); j++) {
						dlroompostmessageService.deleteByPrimaryKey(list.get(j)
								.getId());
					}
				}
				roompostService.deleteByPrimaryKey(data[i]);

			} else {
				return "fail";
			}
		}
		return "success";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String type) {
		ModelAndView model = new ModelAndView("../../myRoom/myRoomList");
		model.addObject("type", type);
		return model;
	}

	@RequestMapping(value = "/getShow", method = RequestMethod.GET)
	public ModelAndView getShow(String roomid, String title) {
		ModelAndView model = new ModelAndView("../../myRoom/myRoomList");
		model.addObject("roomid", roomid);
		model.addObject("title", title);
		return model;
	}
	@RequestMapping(value = "/selectByRoomId", method = RequestMethod.GET)
	public ModelAndView selectByRoomId(String id, HttpSession session) {
		PersonManage personManage = (PersonManage) session
				.getAttribute("loginUser");
		String personUsersex=String.valueOf(personManage.getUserSex());
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		DlroompostWithBLOBs dlroompostWithBLOBs = roompostService
				.selectByPrimaryKey(id);
		DlroompostmessageExample example = new DlroompostmessageExample();
		DlroompostmessageExample.Criteria Criteria = example.createCriteria();
		Criteria.andNoticeidEqualTo(dlroompostWithBLOBs.getId());
		example.setOrderByClause("Createtime");
		List<DlroompostmessageWithBLOBs> BLOBss = dlroompostmessageService
				.selectByExampleWithBLOBs(example);
		DlnoticepersonExample     example1=new DlnoticepersonExample();
		DlnoticepersonExample.Criteria Criteria1=example1.createCriteria();
		Criteria1.andInfoidEqualTo(id);
		Criteria1.andPersonidEqualTo(personManage.getId());
		List<Dlnoticeperson> personlist=dlNoticePersonService.selectByExample(example1);
		if(!personlist.isEmpty()&&personlist.size()>0){
			Dlnoticeperson dnp=personlist.get(0);
			Date dt= new Date();
			Long time1= dt.getTime();
			dnp.setAccepttime("" + time1);
			dnps.updateByPrimaryKey(dnp);
		}else{
			Dlnoticeperson dnp=new Dlnoticeperson();
			dnp.setId(UUIDGenerator.getUUID());
			dnp.setInfoid(id);
			dnp.setName(personManage.getUserCname());
			dnp.setType("0");
			Date dt= new Date();
			Long time1= dt.getTime();
			dnp.setAccepttime("" + time1);
			dnp.setChecktime("" + time1);
			dnp.setPersonid(personManage.getId());
			dnp.setBz2("0");
			dnp.setUsername(personManage.getUserEname());
			dnps.insert(dnp);
		}	
		String sql = "SELECT top 9 * FROM dlNoticePerson a WHERE a.infoid='"+id+"' order by checktime desc";
		List<Map> personReadInfoList = excuteSqlService.executeSql(sql);
		if(!personReadInfoList.isEmpty()&&personReadInfoList.size()>0){
			for(Map map:personReadInfoList){
				PersonManage personManag=	personManageManager.queryPersonById(map.get("personid").toString());
			String checktime=map.get("checktime").toString();
			long lg = Long.parseLong(checktime);
			Date checkdate = new Date(lg);
		String checktim = formatter1.format(checkdate);
		map.put("usersex", personManag.getUserSex());
		 map.put("checktime", checktim);
			}
		}
		List<Map> list = excuteSqlService.queryUserNameAndId();
		for (DlroompostmessageWithBLOBs Dlroompostmessage : BLOBss) {
			for (Map personInfo : list) {
				if (personInfo.get("oid").equals(
						Dlroompostmessage.getCreateid())) {
					Dlroompostmessage.setName(personInfo.get("username") 
							.toString());
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					String s = Dlroompostmessage.getCreatetime();
					long l = Long.parseLong(s);
					Date date = new Date(l);
					String time = formatter.format(date);
					Dlroompostmessage.setCreatetime(time);
				}
			}
		}
		for (Map personInfo : list) {
			if (dlroompostWithBLOBs.getCreateid().equals(personInfo.get("oid"))) {
				dlroompostWithBLOBs.setName(personInfo.get("username")
						.toString());
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String s = dlroompostWithBLOBs.getCreatetime();
				long l = Long.parseLong(s);
				Date date = new Date(l);
				String time = formatter.format(date);
				dlroompostWithBLOBs.setCreatetime(time);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dlroompostWithBLOBs", dlroompostWithBLOBs);
		map.put("BLOBss", BLOBss);
		map.put("personUsersex", personUsersex);
		map.put("cur_ryxxid", personManage.getId());
		map.put("personReadInfoList", personReadInfoList);
		return new ModelAndView("../../myRoom/addComment", map);
	}
	
	
	@RequestMapping(value = "save1", method = RequestMethod.POST)
	@ResponseBody
	public void save1(HttpServletRequest request, String content,
			String noticeid, HttpSession session) {
		PersonManage personManage = (PersonManage) session
				.getAttribute("loginUser");
		DlroompostmessageWithBLOBs dlroompostmessageWithBLOBs = new DlroompostmessageWithBLOBs();
		dlroompostmessageWithBLOBs.setId(UUIDGenerator.getUUID());
		dlroompostmessageWithBLOBs.setNoticeid(noticeid);
		String ss = null;
		try {
			ss = java.net.URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		dlroompostmessageWithBLOBs.setContent(ss);
		dlroompostmessageWithBLOBs.setCreateid(personManage.getId());
		Date date = new Date();
		// SimpleDateFormat ss1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Long time = date.getTime();// Long类型变量
		dlroompostmessageWithBLOBs.setCreatetime(time.toString());
		dlroompostmessageService.insert(dlroompostmessageWithBLOBs);
/*		DlroompostWithBLOBs dlroompostWithBLOBs = roompostService
				.selectByPrimaryKey(noticeid);*/
		/*DlroompostmessageExample example = new DlroompostmessageExample();
		DlroompostmessageExample.Criteria Criteria = example.createCriteria();
		Criteria.andNoticeidEqualTo(noticeid);
		example.setOrderByClause("Createtime");
		List<DlroompostmessageWithBLOBs> BLOBss = dlroompostmessageService
				.selectByExampleWithBLOBs(example);*/
		/*List<Map> list = excuteSqlService.queryUserNameAndId();
		for (DlroompostmessageWithBLOBs Dlroompostmessage : BLOBss) {
			for (Map personInfo : list) {
				if (personInfo.get("oid").equals(
						Dlroompostmessage.getCreateid())) {
					Dlroompostmessage.setName(personInfo.get("username")
							.toString());
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					String s = Dlroompostmessage.getCreatetime();
					long l = Long.parseLong(s);
					Date date1 = new Date(l);
					String time1 = formatter.format(date1);
					Dlroompostmessage.setCreatetime(time1);
				}
			}
		}
		for (Map personInfo : list) {
			if (dlroompostWithBLOBs.getCreateid().equals(personInfo.get("oid"))) {
				dlroompostWithBLOBs.setName(personInfo.get("username")
						.toString());
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String s = dlroompostWithBLOBs.getCreatetime();
				long l = Long.parseLong(s);
				Date date2 = new Date(l);
				String time2 = formatter.format(date2);
				dlroompostWithBLOBs.setCreatetime(time2);
			}
		}*/
	
	}

	@RequestMapping(value = "save", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView save(HttpServletRequest request, String content,
			String noticeid, HttpSession session) {
		PersonManage personManage = (PersonManage) session
				.getAttribute("loginUser");
		DlroompostmessageWithBLOBs dlroompostmessageWithBLOBs = new DlroompostmessageWithBLOBs();
		dlroompostmessageWithBLOBs.setId(UUIDGenerator.getUUID());
		dlroompostmessageWithBLOBs.setNoticeid(noticeid);
		String ss = null;
		try {
			ss = java.net.URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		dlroompostmessageWithBLOBs.setContent(ss);
		dlroompostmessageWithBLOBs.setCreateid(personManage.getId());
		Date date = new Date();
		// SimpleDateFormat ss1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Long time = date.getTime();// Long类型变量
		dlroompostmessageWithBLOBs.setCreatetime(time.toString());
		dlroompostmessageService.insert(dlroompostmessageWithBLOBs);
		DlroompostWithBLOBs dlroompostWithBLOBs = roompostService
				.selectByPrimaryKey(noticeid);
		DlroompostmessageExample example = new DlroompostmessageExample();
		DlroompostmessageExample.Criteria Criteria = example.createCriteria();
		Criteria.andNoticeidEqualTo(noticeid);
		example.setOrderByClause("Createtime");
		List<DlroompostmessageWithBLOBs> BLOBss = dlroompostmessageService
				.selectByExampleWithBLOBs(example);
		List<Map> list = excuteSqlService.queryUserNameAndId();
		for (DlroompostmessageWithBLOBs Dlroompostmessage : BLOBss) {
			for (Map personInfo : list) {
				if (personInfo.get("oid").equals(
						Dlroompostmessage.getCreateid())) {
					Dlroompostmessage.setName(personInfo.get("username")
							.toString());
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					String s = Dlroompostmessage.getCreatetime();
					long l = Long.parseLong(s);
					Date date1 = new Date(l);
					String time1 = formatter.format(date1);
					Dlroompostmessage.setCreatetime(time1);
				}
			}
		}
		for (Map personInfo : list) {
			if (dlroompostWithBLOBs.getCreateid().equals(personInfo.get("oid"))) {
				dlroompostWithBLOBs.setName(personInfo.get("username")
						.toString());
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String s = dlroompostWithBLOBs.getCreatetime();
				long l = Long.parseLong(s);
				Date date2 = new Date(l);
				String time2 = formatter.format(date2);
				dlroompostWithBLOBs.setCreatetime(time2);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dlroompostWithBLOBs", dlroompostWithBLOBs);
		map.put("BLOBss", BLOBss);
		return new ModelAndView("../../myRoom/addComment", map);
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView edit(HttpServletRequest request,
			DlroompostWithBLOBs dlroompostWithBLOBs, HttpSession session,
			HttpServletResponse response) {
		String id = dlroompostWithBLOBs.getId();
		DlroompostWithBLOBs BLOBs = roompostService.selectByPrimaryKey(id);
		dlroompostWithBLOBs.setContent(BLOBs.getContent());
		dlroompostWithBLOBs.setContentType(BLOBs.getContentType());
		dlroompostWithBLOBs.setCreateid(BLOBs.getCreateid());
		dlroompostWithBLOBs.setCreatetime(BLOBs.getCreatetime());
		dlroompostWithBLOBs.setRoomid(BLOBs.getRoomid());
		roompostService.updateByPrimaryKeyWithBLOBs(dlroompostWithBLOBs);
		return new ModelAndView(new RedirectView("show.do"));
	}
	//根据infoid查询评论
	@RequestMapping(value = "/findMessageByInfoId", method = RequestMethod.GET)
    public @ResponseBody Object  findMessageByInfoId(HttpServletRequest request,String id,BaseVO vo) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DlroompostmessageExample example = new DlroompostmessageExample();
		DlroompostmessageExample.Criteria Criteria = example.createCriteria();
		Criteria.andNoticeidEqualTo(id);
		example.setOrderByClause("Createtime");
		List<DlroompostmessageWithBLOBs> BLOBss = dlroompostmessageService
				.selectByExampleWithBLOBs(example,vo);
		if(!BLOBss.isEmpty()&&BLOBss.size()>0){
			for(DlroompostmessageWithBLOBs dlroompostmessageWithBLOBs:BLOBss){
				PersonManage personManag=	personManageManager.queryPersonById(dlroompostmessageWithBLOBs.getCreateid());
				long lg = Long.parseLong(dlroompostmessageWithBLOBs.getCreatetime());
				Date crecatedate = new Date(lg);
				String createtime = formatter.format(crecatedate);
				dlroompostmessageWithBLOBs.setCreatetime(createtime);
				dlroompostmessageWithBLOBs.setUsersex(String.valueOf(personManag.getUserSex()));
				dlroompostmessageWithBLOBs.setName(personManag.getUserCname());
			}
			}
		return BLOBss;
    }
	//根据infoid查询评论页数
	@RequestMapping(value = "/findMessageNumByInfoId", method = RequestMethod.GET)
    public @ResponseBody Object  findMessageNumByInfoId(HttpServletRequest request,String id) {
		DlroompostmessageExample example = new DlroompostmessageExample();
		DlroompostmessageExample.Criteria Criteria = example.createCriteria();
		Criteria.andNoticeidEqualTo(id);
		example.setOrderByClause("Createtime");
		List<DlroompostmessageWithBLOBs> BLOBss = dlroompostmessageService
				.selectByExampleWithBLOBs(example);
		int size = BLOBss.size();
		int total=0;
		if(size!=0){
			if(size%Integer.parseInt("10")!=0){
				total = size/Integer.parseInt("10")+1;
			}else{
				total = size/Integer.parseInt("10");
			}
		}
		return total;
    }
	 //获取信息浏览数,评论数,回执数
	@RequestMapping(value = "/findNumByInfoId", method = RequestMethod.GET)
    public @ResponseBody Object  findNumByInfoId(HttpServletRequest request,String id,String numb) {
		Map map = new HashMap();
		if(numb.equals("1")){
			  String numLiulan = "0";
			   String numPinglun ="0";
			   DlnoticepersonExample example=new DlnoticepersonExample();
	    	   DlnoticepersonExample.Criteria criteria=example.createCriteria();
	    	   criteria.andInfoidEqualTo(id);
	    	   int  rows =dlNoticePersonService.countByExample(example);
				numLiulan=String.valueOf(rows);
				DlroompostmessageExample example1=new DlroompostmessageExample();
				DlroompostmessageExample.Criteria criteria1=example1.createCriteria();
				criteria1.andNoticeidEqualTo(id);
				int dd=dlroompostmessageService.countByExample(example1);
				numPinglun=String.valueOf(dd);
			String numHuizhi = "9";
			map.put("numLiulan", numLiulan);
			map.put("numPinglun", numPinglun);
			map.put("numHuizhi", numHuizhi);
			return map;
		}else if(numb.equals("2")){
			  String numLiulan = "0";
			   String numPinglun ="0";
			   String sql="SELECT  * FROM dlNoticePerson a WHERE a.infoid = '"+id+"' AND (a.type = 1 OR a.bz2 = 1) order by checktime desc";	
			   List<Map> list=excuteSqlService.executeSql(sql);
			   if(list.isEmpty()&&list.size()>0){
				   int  rows =list.size();
					numLiulan=String.valueOf(rows);
			   }
				DlroompostmessageExample example1=new DlroompostmessageExample();
				DlroompostmessageExample.Criteria criteria1=example1.createCriteria();
				criteria1.andNoticeidEqualTo(id);
				int dd=dlroompostmessageService.countByExample(example1);
				numPinglun=String.valueOf(dd);
			String numHuizhi = "9";
			map.put("numLiulan", numLiulan);
			map.put("numPinglun", numPinglun);
			map.put("numHuizhi", numHuizhi);
			return map;
		}
		 
		return map;
	}
	 //加载评论回复
	@RequestMapping(value = "/findPinlunHuifuByid", method = RequestMethod.GET)
    public @ResponseBody Object  findPinlunHuifuByid(HttpServletRequest request,String id) {
		DlroompostmessageExample example = new DlroompostmessageExample();
		DlroompostmessageExample.Criteria Criteria = example.createCriteria();
		Criteria.andNoticeidEqualTo(id);
		example.setOrderByClause("Createtime");
		List<DlroompostmessageWithBLOBs> BLOBss = dlroompostmessageService
				.selectByExampleWithBLOBs(example);
		for(DlroompostmessageWithBLOBs dl:BLOBss){
			PersonManage personManage=	personManageManager.queryPersonById(dl.getCreateid());
			dl.setUsersex(String.valueOf(personManage.getUserSex()));
			dl.setName(personManage.getUserCname());
		}
		return BLOBss;
    }
	//删除回复或者评论
	@RequestMapping(value = "/delPinlunHuifuByid", method = RequestMethod.GET)
    public @ResponseBody Object  delPinlunHuifuByid(HttpServletRequest request,String id) {
		
		return dlroompostmessageService.deleteByPrimaryKey(id);
    }
	
	
	
	
	//评论显示人员信息
		@RequestMapping(value = "/findPersonId", method = RequestMethod.GET)
	    public @ResponseBody Object  findPersonId(HttpServletRequest request,String id) {
			Map map = new HashMap();
			PersonManage personManage=	personManageManager.queryPersonById(id);
			 String department=null;
 	    	String sql="select e.ORG_CNAME from ORGANIZATION e where e.OID=(select  r.ORGANIZATION_INFO_ID from  ORGAN_PERSON_RELATION_INFO r where r.PERSON_INFO_ID='"+personManage.getId()+"')";
 	    	List<Map> list=excuteSqlService.executeSql(sql);
 	    	if(!list.isEmpty()&&list.size()>0){
 	    		department=list.get(0).get("ORG_CNAME").toString();
 	    		personManage.setDepartment(department);
 	    	}
			map.put("usercname", personManage.getUserCname());
 	    	map.put("department", personManage.getDepartment());
 	    	
 	    	return map;
 	    	
	    }
	
		//评论回复
				@RequestMapping(value = "/AddOneHf", method = RequestMethod.POST)
			    public @ResponseBody Object  AddOneHf(HttpServletRequest request, HttpSession session) {
					PersonManage personManage = (PersonManage) session
							.getAttribute("loginUser");
					Map map = new HashMap();
				String content	=request.getParameter("hfContent");
				String noticeid=request.getParameter("plid");
				DlroompostmessageWithBLOBs dlroompostmessageWithBLOBs=new DlroompostmessageWithBLOBs();
				dlroompostmessageWithBLOBs.setId(UUIDGenerator.getUUID());
				dlroompostmessageWithBLOBs.setNoticeid(noticeid);
				String ss = null;
				try {
					ss = java.net.URLDecoder.decode(content, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				dlroompostmessageWithBLOBs.setContent(ss);
				dlroompostmessageWithBLOBs.setCreateid(personManage.getId());
				Date date = new Date();
				// SimpleDateFormat ss1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Long time = date.getTime();// Long类型变量
				dlroompostmessageWithBLOBs.setCreatetime(time.toString());
				dlroompostmessageWithBLOBs.setName(personManage.getUserCname());
				dlroompostmessageService.insert(dlroompostmessageWithBLOBs);
				map.put("d", 1);
		 	    	return map; 	
			    }
	
	
	
	
	
	
	
	
	
	
}
