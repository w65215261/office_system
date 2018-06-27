package com.hndl.mobileplatform.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.view.RedirectView;

import com.hndl.mobileplatform.dao.DlnoticemanageMapper;
import com.hndl.mobileplatform.model.Dlnoticemanage;
import com.hndl.mobileplatform.model.DlnoticemanageExample;
import com.hndl.mobileplatform.model.Dlnoticeperson;
import com.hndl.mobileplatform.model.DlnoticepersonExample;
import com.hndl.mobileplatform.model.DlnoticepersonExample.Criteria;
import com.hndl.mobileplatform.model.Dlwechatmanager;
import com.hndl.mobileplatform.service.DlNoticPersonService;
import com.hndl.mobileplatform.service.DlNoticeManageService;
import com.hndl.mobileplatform.service.MemberSplicingService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.BaseVO;
import com.pmcc.soft.core.common.SendInfoToAndroid;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Controller
@RequestMapping(value = "noticePerson")
public class DlNoticePersonController {
	@Resource
	private DlNoticPersonService dlNoticePersonService;
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
	@Resource
	private DlNoticPersonService dnps;
    @Autowired
    MemberSplicingService memberSplicingService;
	@Resource
	private DlNoticeManageService dlNoticeManageService;
	 @Autowired
	PersonManageManager personManageManager;
	/**
	 * 查询
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	@ResponseBody
	public List<Dlnoticeperson> query(Dlnoticeperson dc,
			HttpServletRequest request) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		DlnoticepersonExample example = new DlnoticepersonExample();
		List<Dlnoticeperson> list = dlNoticePersonService.selectByExample(example);
		for(Dlnoticeperson dl : list){
			try {
				Date date = new Date(Long.valueOf(dl.getChecktime()));
				dl.setChecktime(formatter.format(date));
				dl.setAccepttime(formatter.format(date));
				
			} catch (Exception e) {
				dl.setChecktime("");
				dl.setAccepttime("");
			}
		}
		return list;
	}
	/**
	 * 发送
	 */
	@RequestMapping(value = "send", method = RequestMethod.POST)
	@ResponseBody
	public String send(String ids,String personIds,String usernames,HttpServletRequest request,HttpSession session){
		PersonManage currentpersonManage = (PersonManage)session.getAttribute("loginUser");
		String uri = "";
	 	String name = "";
	 	String token = "";
	 	String appId= "";
	 	String selectOrg = "SELECT * FROM dlWeChatManager WHERE ORGANIZATION_ID = (SELECT OID FROM ORGANIZATION WHERE RPT_UNIT ='"+currentpersonManage.getUserEname()+"');";
	 	List<Map> weChatList = publicExcuteSqlService.executeSql(selectOrg);
	 	if(weChatList.size()>0){
	 		uri = (String) weChatList.get(0).get("URI");
	 		name = (String) weChatList.get(0).get("NAME");
	 		token = (String) weChatList.get(0).get("TOKEN");
	 		appId = (String) weChatList.get(0).get("APP_ID");
	 	}
	 	
		Map<String ,Object> map = new HashMap<String ,Object>();
		String[] noticeid = ids.split(",");
		Dlnoticemanage manage = dlNoticeManageService.selectByPrimaryKey(noticeid[0]);
		manage.setType("1");
		dlNoticeManageService.updateByPrimaryKeySelective(manage);
		String[] noticePersonIds = personIds.split(","); 
		String[] userNames = usernames.split(",");
		String sqlpersion = "SELECT * FROM PERSON_INFO WHERE USER_ENAME in("+userNames+")";
		List<Map> listPersion = publicExcuteSqlService.executeSql(sqlpersion);
		String param = memberSplicingService.UserId();
//		SendInfoToAndroid.sendInfo(manage,listPersion,param);

		for(int i = 0 ; i < noticePersonIds.length ; i++){
			String personid = userNames[i];
			Dlnoticeperson dnp = new Dlnoticeperson();
			dnp.setId(UUIDGenerator.getUUID());
			dnp.setInfoid(noticeid[0]);
			dnp.setUsername(userNames[0]);
			String flag = dlNoticePersonService.sendWeiXin(noticeid[0], noticePersonIds[i],uri,token,appId);
			if(flag.equals("发送成功")){
				dnp.setBz1("1");
			}else {
				dnp.setBz1("0");
			}
			String sql = "SELECT t.USER_CNAME,t.USER_ENAME FROM PERSON_INFO t WHERE t.OID = '" + personid + "'" ;
			List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			dnp.setName(list.get(0).get("USER_CNAME"));
			dnp.setUsername(list.get(0).get("USER_ENAME"));
			dnp.setType("0");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str = sdf.format(date);
			dnp.setAccepttime(str);
			dnp.setPersonid(noticePersonIds[i]);
			dlNoticePersonService.insert(dnp);
		}
		return "success";
	}
	/**
	 * 查看详情
	 */
	 @RequestMapping(value = "/showView", method = RequestMethod.GET)
	 @ResponseBody
    public ModelAndView showView(String id,HttpServletRequest request){
		 	Dlnoticeperson dnp = new Dlnoticeperson();
	    	ModelAndView model = new ModelAndView("/noticeManage/showView");
	    	List<Dlnoticeperson>  alist =new ArrayList<Dlnoticeperson>();
	    	String sql = "SELECT t.name,t.accepttime,t.checktime FROM dlNoticePerson t WHERE t.infoid = '" + id + "'";
	    	List<Map<String,Object>> list = publicExcuteSqlService.executeSql(sql);
	    	 if(list != null && list.size() > 0){
	    	     for(Map map:list){
	    	    	 dnp.setName(map.get("name").toString());
	    	    	 dnp.setAccepttime(map.get("accepttime").toString());
	    	    	 System.out.println(map.get("checktime"));
	    	    	 if (map.get("checktime") == null) {
						dnp.setState("未查看");
					}else{
	    	    		 dnp.setState(map.get("checktime").toString());
	    	    	 }
	    	    	 alist.add(dnp);
	    	     }
	    	   }
	    	request.setAttribute("dnps", alist); 
	    	return model;
	    }
	 @RequestMapping(value = "/showMic", method = RequestMethod.GET)
	 @ResponseBody
	 public List<Dlnoticeperson> showMic(String id,HttpServletRequest request,BaseVO vo){
	    	DlnoticepersonExample example=new DlnoticepersonExample();
	    	DlnoticepersonExample.Criteria criteria=example.createCriteria();
	    	criteria.andInfoidEqualTo(id);
	    	List<Dlnoticeperson>  rows =dlNoticePersonService.selectByExample(example,vo);
	    	 if(rows != null && rows.size() > 0){
	    	     for(Dlnoticeperson dnp:rows){
	    	    	 if (dnp.getBz1().equals("1")) {
						dnp.setChecktime("微信已发送成功");
					}else{
						dnp.setChecktime("微信未发送成功");
					}
	    	     }
	    	   }
	    	return rows;
	    }

	 @RequestMapping(value = "/showView1", method = RequestMethod.GET)
	 @ResponseBody
	 public  List<Map> showView1(String id,HttpServletRequest request,String num,String page,String rows,String sort,String order){
		 	//checkInfo为1表示已查看
		 String  sql="";
		 	String checkInfo = request.getParameter("checkInfo");
		 	String sortStr ="";
		 	if(sort !=null&&order !=null){ 
		 		sortStr = sort+" "+order+", ";
		 	}
		 	sql="SELECT TOP 10 * FROM (SELECT row_number () OVER (ORDER BY "+sortStr+" b_ORG_CNAME DESC,d_sort) AS rownumber ,* FROM "
		 		  		+ "(SELECT a.name AS a_name,b.ORG_CNAME AS b_ORG_CNAME,d.DUTY AS d_duty,d.AGE as d_age,d.TELEPHONE as d_TELEPHONE,d.USER_QQ as d_USER_QQ,a.accepttime as a_accepttime,a.checktime as a_checktime,a.type as a_type,d.sort as d_sort,d.OFFICEPHONE as d_officephone,a.bz2 as a_bz2 FROM dlNoticePerson a,ORGANIZATION b,ORGAN_PERSON_RELATION_INFO c,PERSON_INFO d WHERE 	a.personid = d.OID AND a.personid = c.PERSON_INFO_ID AND C.ORGANIZATION_INFO_ID =b.OID AND a.infoid ='"+id+"'";
			if("1".equals(checkInfo)){
				sql = sql +" and a.checktime is not null";
			}else if("2".equals(checkInfo)){
				sql = sql +"  and a.checktime is  null";
			}
			
			sql= sql +") b ) a WHERE rownumber > "+(Integer.valueOf(page)-1)*10;
			 List<Map>  list=publicExcuteSqlService.executeSql(sql);
		 return list;
	    }

	 @RequestMapping(value = "/showView2", method = RequestMethod.GET)
	 @ResponseBody
    public ModelAndView showView2(String id,HttpServletRequest request){
		 ModelAndView model = new ModelAndView("/noticeManage/showView");
		 model.addObject("id", id);
	 return model;
	 }
	 
	 /**
	  * 群组发送
	  */
	 @RequestMapping(value = "sendGroup", method = RequestMethod.POST)
	 @ResponseBody
		public String sendGroup(String message,String groups,String usernames,String persons,String infoids, HttpServletRequest request,HttpSession session){
		 	PersonManage currentpersonManage = (PersonManage)session.getAttribute("loginUser");
		 	
		 	//根据当前人英文名查询 如果有数据 赋值给前台  没有就新增，所需要的值不能为空，否则返回公众微信号有误
		 	String uri = "";
		 	String name = "";
		 	String token = "";
		 	String appId= "";
		 	String selectOrg = "SELECT * FROM dlWeChatManager WHERE ORGANIZATION_ID = (SELECT OID FROM ORGANIZATION WHERE RPT_UNIT ='"+currentpersonManage.getUserEname()+"');";
		 	List<Map> weChatList = publicExcuteSqlService.executeSql(selectOrg);
		 	if(weChatList.size()>0){
		 		uri = (String) weChatList.get(0).get("URI");
		 		name = (String) weChatList.get(0).get("NAME");
		 		token = (String) weChatList.get(0).get("TOKEN");
		 		appId = (String) weChatList.get(0).get("APP_ID");
		 	}
		 	
		 	if(uri == null || uri.equals("") || token == null || token.equals("") || appId == null || appId.equals("")){
		 		return "weiChatNo";
		 	}
		 	
		 	Dlnoticeperson dnp = new Dlnoticeperson();		
			String[] personId = persons.split(",");
			String[] username = usernames.split(",");
			String newName ="'"+username[0]+"'";
			if(username.length>1){
				for(int i=1;i<username.length;i++){
					newName = newName+",'"+username[i]+"'";
				}
			}
			
			String sqlpersion = "SELECT * FROM PERSON_INFO WHERE USER_ENAME in("+newName+")";
			List<Map> listPersion = publicExcuteSqlService.executeSql(sqlpersion);
			String param = memberSplicingService.UserId();
		
			Dlnoticemanage manage = dlNoticeManageService.selectByPrimaryKey(message);
			
			manage.setType("1");
			dlNoticeManageService.updateByPrimaryKeySelective(manage);
			for(int i = 0 ; i < personId.length ; i++){
				String personName = personId[i].split("@")[0];
				String flag = dlNoticePersonService.sendWeiXin(message, personName,uri,token,appId);
				if(flag.equals("发送成功")){
					dnp.setBz1("1");
				}else {
					dnp.setBz1("0");
				}
				
				String sql = "SELECT t.OID,t.USER_ENAME,t.USER_CNAME FROM PERSON_INFO t WHERE t.USER_ENAME = '" + personName + "'";
				List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
				String sQl="select  t.username from  dlNoticePerson t where t.username = '"+personName+"' and t.infoid='"+message+"'";
				List<Map<String,String>>  qList=publicExcuteSqlService.executeSql(sQl);	
				if(qList.size() !=0){
					return "fail";
				}else{
					if(list.size() != 0 ){
						dnp.setId(UUIDGenerator.getUUID());
						dnp.setInfoid(message);
						dnp.setName(list.get(0).get("USER_CNAME"));
						dnp.setType("0");
						Date dt= new Date();
						Long time= dt.getTime();
						dnp.setAccepttime("" + time);
						dnp.setPersonid(list.get(0).get("OID"));
						dnp.setUsername(list.get(0).get("USER_ENAME"));
						dnps.insert(dnp);
				}		
				}	
			}
			SendInfoToAndroid.sendInfo(currentpersonManage,manage,listPersion,param);
			return "success";
		}
		
		
		
}
