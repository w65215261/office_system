package com.pmcc.soft.ydpt.web;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hndl.mobileplatform.service.MemberSplicingService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.ydpt.domain.OfMucMember;
import com.pmcc.soft.ydpt.domain.OfMucRoom;
import com.pmcc.soft.ydpt.manager.OfMucMemberManager;
import com.pmcc.soft.ydpt.manager.OfMucRoomManager;

/**
 * Created by sunyongxing
 * on 2015/6/8 0008 9:22
 */
@RequestMapping("ofMucMember")
@Controller
public class OfMucMemberController {

    @Autowired
    OfMucMemberManager ofMucMemberManager;
    
    @Autowired
    PersonManageManager personManageManager;
    @Autowired
    OfMucRoomManager ofMucRoomManager;
    @Autowired
    MemberSplicingService memberSplicingService;
    @Autowired
    PublicExcuteSqlService publicExcuteSqlService;

    /**
     * 查询所有
     * @param omm
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<OfMucMember> query(OfMucMember omm){
        return ofMucMemberManager.query(omm);
    }

    //设置为管理员 ---刘骁
    @RequestMapping(value = "/setUp" , method = RequestMethod.GET)
    public @ResponseBody Object setUp(HttpSession session , HttpServletRequest request){
    	PersonManage currentpersonManage = (PersonManage)session.getAttribute("loginUser");
		Timestamp now = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
    	String jid = request.getParameter("jid");
    	String roomID = request.getParameter("roomID");
    	String selectSetUp = "SELECT * FROM ofMucAffiliation WHERE roomID='"+roomID+"'";
    	List<Map> personList = publicExcuteSqlService.executeSql(selectSetUp);
    	String sql = "";
    	if(personList.size()>0){
    		sql = "UPDATE ofMucAffiliation SET jid ='"+ jid +"' WHERE roomID='"+roomID+"'";
    	}else{
    		sql = "INSERT INTO ofMucAffiliation VALUES('"+roomID+"','"+jid+"','10')";
    	}
    	
    	String[] userEname = jid.split("@");
    	//自动分配去权限，在新增人员过后再权限表中加条数据
    	
    	//根据群组查询群主
    	String groupMaster = "SELECT * FROM ofMucAffiliation WHERE roomID='"+roomID+"'";
    	List<Map> master = publicExcuteSqlService.executeSql(groupMaster);
    	String masterJid ="";
    	if(master.size()>0){
    		masterJid = (String) master.get(0).get("jid");
    	}
    	
    	String[] masterEname = masterJid.split("@");
    	//根据jid查询人员id
    	String userOid = "SELECT p.OID FROM PERSON_INFO p WHERE p.USER_ENAME='"+userEname[0]+"'";
    	List<Map> Oid = publicExcuteSqlService.executeSql(userOid);
    	//
    	String sqlRole = "SELECT * FROM AUTHORITY_USER_ROLE a WHERE a.USER_OID=(SELECT p.OID FROM PERSON_INFO p WHERE p.USER_ENAME='"+masterEname[0]+"') AND a.ROLE_OID='000003'";
    	List<Map> roleList = publicExcuteSqlService.executeSql(sqlRole);
    	if(roleList.size()>0){
    		String updatePerson = "UPDATE AUTHORITY_USER_ROLE SET USER_OID = '"+Oid.get(0).get("OID")+"' WHERE OID = '"+roleList.get(0).get("OID")+"'";
    		publicExcuteSqlService.updateBySql(updatePerson);
    	}else{
    		String authOid = UUIDGenerator.getUUID();
        	String insterOnePerson ="INSERT INTO AUTHORITY_USER_ROLE VALUES('"+authOid+"','000003','"+Oid.get(0).get("OID")+"','"+currentpersonManage.getId()+"','"+now+"');";
        	publicExcuteSqlService.updateBySql(insterOnePerson);
    	}
    	/*String authOid = UUIDGenerator.getUUID();
    	String insterOnePerson ="INSERT INTO AUTHORITY_USER_ROLE VALUES('"+authOid+"','000003','"+personManage.getId()+"','"+currentpersonManage.getId()+"','"+now+"');";
    	publicExcuteSqlService.updateBySql(insterOnePerson);*/
    	publicExcuteSqlService.updateBySql(sql);
    	
    	return "true";
    }
    /**
     * 添加群成员
     * @param request
     * @return
     */
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    @ResponseBody
    public String addMember(HttpServletRequest request){
        String personIds = request.getParameter("personIds");
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String[] personIdArr = null;
        if(personIds.contains(",")){
        	personIdArr = personIds.split(",");
        }else{
        	personIdArr[0]=personIds;
        }
        
        int operateNum = 0;
        for (int i=0;i<personIdArr.length;i++){
            PersonManage p = personManageManager.queryPersonById(personIdArr[i]);
            if (p != null){
                OfMucMember omm = new OfMucMember();
                omm.setRoomID(roomID);
                omm.setJid(p.getUserEname() + memberSplicingService.UserId());
                omm.setNickname(p.getUserEname());
                //因为页面需要中文名，但是表中并没有这个字段，而我也不知道FirstName这个字段是干啥的，而且暂时没用到，所以我借用一下----刘骁
                String sql = "SELECT p.USER_CNAME FROM PERSON_INFO p WHERE p.USER_ENAME='"+p.getUserEname()+"'";
                List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
                omm.setFirstName(list.get(0).get("USER_CNAME"));
                
               
                
                operateNum += ofMucMemberManager.insert(omm);
            }
        }
        if (operateNum == personIdArr.length){
            return "success";
        }
        return "fail";
    }

    /**
     * 新增或修改
     * @param omm
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(OfMucMember omm){
        int operateNum = 0;
        if (omm.getRoomID()!=0 && !"".equals(omm.getRoomID())){
            operateNum = ofMucMemberManager.update(omm);
        }else {
            operateNum = ofMucMemberManager.insert(omm);
        }
        if (operateNum == 0){
            return "fail";
        }else {
            return "success";
        }
    }
    @RequestMapping(value = "/addMemberForAndroid", method = RequestMethod.GET)
    @ResponseBody
    public String addMemberForAndroid(HttpServletRequest request){
        String userEname = request.getParameter("userEname");
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String password = request.getParameter("password");
        String Sqlselect = "SELECT *  from PERSON_INFO t where t.USER_ENAME='"+userEname+"'";
        String sql = "SELECT * FROM ofMucRoom where roomID="+roomID;
        List<Map> list = publicExcuteSqlService.executeSql(sql);
        List<Map> listUsername = publicExcuteSqlService.executeSql(Sqlselect);
        String username = String.valueOf(listUsername.get(0).get("USER_CNAME"));
        if(!"".equals(list.get(0).get("roomPassword")) && list.get(0).get("roomPassword")!=null){
        	if(list.get(0).get("roomPassword").equals(password)){
        		return insertOf(roomID,userEname,username);
        	}else{
        		return "passwordWrong";
        	}
        }else {
        	if("".equals(password) || password==null){
        		return insertOf(roomID,userEname,username);
        	}else{
        		return "passwordWrong";
        	}
        }
    }
    public  String insertOf(int roomID,String userEname,String username){
    	OfMucMember omm = new OfMucMember();
        omm.setRoomID(roomID);
        omm.setJid(userEname + memberSplicingService.UserId());
        omm.setNickname(userEname);
        omm.setFirstName(username);
//                omm.setNickname(p.getUserCname());
        String sql = "SELECT count(1) as SL FROM ofMucMember where roomID="+roomID+" and jid='"+userEname + "@rfya9p57h7wzd8s"+"'";
        List<Map> list = publicExcuteSqlService.executeSql(sql);
        if(Integer.valueOf(list.get(0).get("SL").toString())==0){
        	int i =ofMucMemberManager.insert(omm);
        	if (i == 1){
                return "true";
            }else{
            	 return "false";
            }
        }else{
        	return "true";
        }
        
        
    }
    /**
     * 批量删除群成员
     * @param request
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(HttpServletRequest request){
        String roomIDs = request.getParameter("roomID");
        String jids = request.getParameter("jid");
        String[] roomIDarr = roomIDs.split(",");
        String[] jidArr = jids.split(",");
        int operateNum = 0;
        for (int i=0;i<roomIDarr.length;i++){
            OfMucMember omm = new OfMucMember();
            omm.setRoomID(Integer.parseInt(roomIDarr[i]));
            omm.setJid(jidArr[i]);
            operateNum += ofMucMemberManager.delete(omm);
        }
        if (operateNum == roomIDarr.length){
            return "success";
        }
        return "fail";
    }
    @RequestMapping(value = "/mobileDelete", method = RequestMethod.POST)
    @ResponseBody
    public void mobileDelete(HttpServletRequest request){
        String data = request.getParameter("data");
        JSONArray dataArray = JSONArray.fromObject(data);
		for(int i=0 ; i < dataArray.size() ;i++){
			JSONObject jsonObject = dataArray.getJSONObject(i);
			String roomid = jsonObject.getString("roomid");
			String jid = jsonObject.getString("jid");
			OfMucMember omm = new OfMucMember();
            omm.setRoomID(Integer.parseInt(roomid));
            omm.setJid(jid);
           ofMucMemberManager.delete(omm);
		}
        
    }


    /**
     * 根据roomID查找
     * @param omm
     * @return
     */
    @RequestMapping(value = "/findByRoomId", method = RequestMethod.GET)
    @ResponseBody
    public Object findByRoomId(PersonManage omm , String roomID){
        Map<String,Object> res = new HashMap<String, Object>();
        List<OfMucMember> list = new ArrayList<OfMucMember>();
        omm.setInitPage(0);
        omm.setRoomid(roomID);
        List<PersonManage> perList = personManageManager.queryByRoomId(omm);
        String count = "SELECT COUNT(*) as num FROM PERSON_INFO where USER_ENAME IN(SELECT nickname FROM ofMucMember WHERE roomID='"+roomID+"')";
        List<Map> countList = publicExcuteSqlService.executeSql(count);
        for(PersonManage p  : perList){
        	String sql = "SELECT * FROM ofMucMember WHERE roomID='"+roomID+"' AND nickname='"+p.getUserEname()+"'";
        	List<Map> jidList = publicExcuteSqlService.executeSql(sql);
        	OfMucMember ofmuc = new OfMucMember();
        	//jid
        	ofmuc.setJid(String.valueOf(jidList.get(0).get("jid")));
        	//年龄
        	ofmuc.setAge(p.getAge());
        	//所属部门
        	//根据上面查到的人员的OID 循环查询 人员所在的部门 显示在前台
			String personOid = p.getId();
			String seeOrgan ="SELECT * FROM ORGANIZATION c WHERE c.OID=(SELECT o.ORGANIZATION_INFO_ID FROM ORGAN_PERSON_RELATION_INFO o WHERE o.PERSON_INFO_ID = '"+personOid+"')";
			List<Map> organList = publicExcuteSqlService.executeSql(seeOrgan);
			//群id
			ofmuc.setRoomID(Integer.valueOf(roomID));

            if(organList!=null&&organList.size()>0){
			//所属部门
        	ofmuc.setDepartment(organList.get(0).get("ORG_CNAME").toString());

            }
        	//职务
        	ofmuc.setDuty(p.getDuty());
        	//邮箱
        	ofmuc.setEmail(p.getUserMail());
        	//电话
        	ofmuc.setTelephone(p.getTelephone());
        	//职责
        	String sqls = "SELECT * FROM ofMucAffiliation WHERE roomID ='"+roomID+"'";
            List<Map<String,String>> li = publicExcuteSqlService.executeSql(sqls);
            String jidlist="";
            if(li.size()>0){
            	 jidlist = li.get(0).get("jid");
            }
            String[] jid = jidlist.split("@");
            
            if(p.getUserEname().equals(jid[0])){
            	ofmuc.setIsOrAdmin("群主");
            }else{
            	ofmuc.setIsOrAdmin("群成员");
            }
        	//中文名
        	ofmuc.setFirstName(p.getUserCname());
        	//用户名
        	ofmuc.setNickname(p.getUserEname());
        	//QQ
        	ofmuc.setQq(p.getUserQQ());
        	//办公电话
        	ofmuc.setOfficephone(p.getOfficephone());
        	//排序
        	ofmuc.setSort(String.valueOf(p.getSorting()));
        	//备注
        	ofmuc.setRemark(p.getRemark());
        	list.add(ofmuc);
        }
        res.put("rows", list);      
        omm.setInitPage(1);
        res.put("total", countList.get(0).get("num"));
        return res;
    }
    @RequestMapping(value = "/findByRoom", method = RequestMethod.GET)
    @ResponseBody
    public Object findByRoom(OfMucMember omm , String roomID,String infoid){
    	String sql="select * from dlNoticePerson t where t.infoid='"+infoid+"'";
    	List<Map> dlNoticePersonlist=publicExcuteSqlService.executeSql(sql); 	
        Map<String,Object> res = new HashMap<String, Object>();
        omm.setInitPage(0);
        List<OfMucMember> list=ofMucMemberManager.query(omm);
        List<OfMucMember> listOfMucMember= new ArrayList<OfMucMember>();
        for(OfMucMember member : list){
        	listOfMucMember.add(member);
        }
        for(OfMucMember ofMucMember:list){
        	int i=0;
        	for(i =0;i<dlNoticePersonlist.size();i++){
        		if(ofMucMember.getNickname().equals(dlNoticePersonlist.get(i).get("username"))){
        			listOfMucMember.remove(ofMucMember);
        		}
        	}
        }
        res.put("rows", listOfMucMember);      
        omm.setInitPage(1);
        res.put("total", listOfMucMember.size());
        return res;
    }

    
    
    @RequestMapping(value = "/findyRoom", method = RequestMethod.GET)
    @ResponseBody
    public Object findyRoom(OfMucMember omm , String roomID,String infoid,String name){
    	String sql="select * from dlNoticePerson t where t.infoid='"+infoid+"'";
    	List<Map> dlNoticePersonlist=publicExcuteSqlService.executeSql(sql); 	
        Map<String,Object> res = new HashMap<String, Object>();
        omm.setInitPage(0);
        List<OfMucMember> list=ofMucMemberManager.query(omm);
        List<OfMucMember> listOfMucMember= new ArrayList<OfMucMember>();
    
        	if(name.equals("")){
        	    for(OfMucMember member : list){
        	 	listOfMucMember.add(member);
        		}
        	}else{
        		
        	    for(OfMucMember member : list){
        	    	if(member.getFirstName().equals(name)){
            	 	listOfMucMember.add(member);
        	    	}
            		}
        
        }
        for(OfMucMember ofMucMember:list){
        	int i=0;
        	for(i =0;i<dlNoticePersonlist.size();i++){
        		if(ofMucMember.getNickname().equals(dlNoticePersonlist.get(i).get("username"))){
        			listOfMucMember.remove(ofMucMember);
        		}
        	}
        }
      /*  for(OfMucMember Member:listOfMucMember){
        	if(Member.getFirstName().equals(name)){
        		listOfMucMember.remove(Member);
        	}
        }*/
        
        res.put("rows", listOfMucMember);      
        omm.setInitPage(1);
        res.put("total", listOfMucMember.size());
        return res;
    }
    /**
     * 通过roomID和群成员姓名查询(判断要添加的成员是否已经在该群中存在)
     * @param request
     * @return
     */
    @RequestMapping(value = "/findByRoomAndPersonName", method = RequestMethod.GET)
    @ResponseBody
    public String findByRoomAndPersonName(HttpServletRequest request){
        String personIds = request.getParameter("personIds");
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String[] personIdArr = personIds.split(",");
        String memberName = "";
        for (int i=0;i<personIdArr.length;i++){
            PersonManage p = personManageManager.queryPersonById(personIdArr[i]);
            if (p != null){
                OfMucMember omm = new OfMucMember();
                omm.setRoomID(roomID);
                omm.setJid(p.getUserEname()+memberSplicingService.UserId());
                List<OfMucMember> ofMucMembers = ofMucMemberManager.query(omm);
                if (ofMucMembers!=null && ofMucMembers.size()>0){
                    memberName += ofMucMembers.get(0).getNickname() + ",";
                }
            }
        }
        if (memberName!=null && !"".equals(memberName)){
            return memberName;
        }
        return "success";
    }

    /**
     * 根据jid查找
     * @param omm
     * @return
     */
    @RequestMapping(value = "/findByJid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findByJid(OfMucMember omm){
        Map<String,Object> res = new HashMap<String, Object>();
        if (omm.getJid()==null || "".equals(omm.getJid())){
            omm.setJid("-99999999999999999999999999999");
        }
        List<OfMucMember> ofMucMembers = ofMucMemberManager.query(omm);
        if (ofMucMembers != null && ofMucMembers.size() > 0){
            List<OfMucRoom> oList = new ArrayList<OfMucRoom>();
            for (OfMucMember o : ofMucMembers) {
                int roomID = o.getRoomID();
                OfMucRoom ofMucRoom = new OfMucRoom();
                ofMucRoom.setRoomID(roomID);
                List<OfMucRoom> list = ofMucRoomManager.query(ofMucRoom);
                if (list!=null && list.size()>0){
                    OfMucRoom omr = list.get(0);
                    oList.add(omr);
                }
            }
            res.put("rows",oList);
            res.put("total",oList.size());
            //return res;
        }else{
            //res.put("rows","[]");
            res.put("total",0);
        }
        return res;
    }

    public static void main(String[] args) {
        String a = "abc";
        String[] b = a.split(",");
        System.out.println(b[0]);
    }
}
