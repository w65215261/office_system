package com.pmcc.soft.ydpt.web;

import java.io.UnsupportedEncodingException;
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

import com.hndl.mobileplatform.dao.OfmucaffiliationMapper;
import com.hndl.mobileplatform.model.Dlspacemanager;
import com.hndl.mobileplatform.model.Ofmucaffiliation;
import com.hndl.mobileplatform.service.DlRoomAfficheService;
import com.hndl.mobileplatform.service.DlSpaceManagerService;
import com.hndl.mobileplatform.service.MemberSplicingService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.ydpt.domain.OfMucRoom;
import com.pmcc.soft.ydpt.manager.OfMucRoomManager;

/**
 * Created by sunyongxing
 * on 2015/6/4 0004 12:09
 */
@RequestMapping("ofMucRoom")
@Controller
public class OfMucRoomController {

    @Autowired
    OfMucRoomManager ofMucRoomManager;
    @Autowired
    OfmucaffiliationMapper ofmucaffiliationMapper;
    @Autowired
    MemberSplicingService memberSplicingService;
    @Resource
    PublicExcuteSqlService excuteSqlService;
    @Resource
	private DlSpaceManagerService dlSpaceManagerService;
    
    
    @Resource
    private PublicExcuteSqlService publicExcuteSqlService;

    /**
     * 查询所有
     * @param omr
     * @return
     */
    @RequestMapping(value="/query",method = RequestMethod.GET)
    public @ResponseBody Object query(HttpSession session){
    	PersonManage  user = (PersonManage) session.getAttribute("loginUser");
    	String eName = user.getUserEname();
    	String sql = "SELECT *  FROM ofMucRoom WHERE ROOMID IN (SELECT d.ROOMID FROM dlRoomOfUnit d WHERE d.ORGANIZATION_ID=(SELECT o.OID FROM ORGANIZATION o WHERE o.RPT_UNIT='"+eName+"'))";
    	List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
    	return list;
    }
    
   /* @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<OfMucRoom> query(OfMucRoom omr){   	
    	List<OfMucRoom> list=ofMucRoomManager.query(omr);
        return list;
    }*/
    
    /**
     * 根据当前用户获得当前用户的群组
     */
    @RequestMapping(value = "/queryGroup", method = RequestMethod.GET)
    @ResponseBody
    public Object queryGroup(OfMucRoom omr,HttpSession session){
    	Map map = new HashMap();
    	PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		String logEname = loginUser.getUserEname() + memberSplicingService.UserId();
		String sql = "SELECT * FROM ofMucRoom t WHERE t.roomID in (SELECT t.roomID FROM ofMucAffiliation t WHERE t.jid = '" + logEname +" ')";
		List<Map<String,String>> rows = publicExcuteSqlService.executeSql(sql);
		int total = ofMucRoomManager.query(omr).size();
    	List<OfMucRoom> list=ofMucRoomManager.query(omr);
    	map.put("total", total);
		map.put("rows", rows);
        return map;
    }
    
    
    /**
     * 双击人员查询详情信息
     */
    @RequestMapping(value = "/selectUser",method = RequestMethod.GET)
    @ResponseBody
    public Object selectUser(HttpServletRequest request , String jid){	
    	String [] id = jid.split("@");
    	String sql = "SELECT p.USER_ENAME,p.USER_CNAME,p.USER_DESCRIPTION,p.USER_SEX,p.RPT_PERSON,p.DUTY,t.ORG_CNAME "+
    				 "FROM PERSON_INFO p,ORGAN_PERSON_RELATION_INFO a,ORGANIZATION t WHERE " +
    				 "p.USER_ENAME = '"+ id[0] + "'" + " AND p.RPT_PERSON = a.PERSON_INFO_ID AND a.ORGANIZATION_INFO_ID= t.OID";
    	List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
    	if(list.size()>0){
    		return list.get(0);
    	}else{
    		return "false";
    	}
    	
    	//System.out.println(list);
		
    	
    }

    /**
     * 新增或修改
     * @param omr
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(OfMucRoom omr , HttpSession session ,String spaceid ,String spacesize,String pacename){
        int operateNum = 0;
        if (omr.getRoomID()!=0 && !"".equals(omr.getRoomID())){
        	
        	Dlspacemanager mana = new Dlspacemanager();
        	String sql = "SELECT * FROM dlSpaceManager d WHERE d.spaceid='"+spaceid+"'";
        	List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
        	String id = list.get(0).get("roomoruserid");
        	Integer orid = Integer.valueOf(id);
        	Long d = Long.parseLong(spacesize); 
            //数据库存储以B 为单位，而前台输入框内是一M 为单位 所以是 输入框的值 X 1024的三次方
    		Long	w = (long) (1024 * 1024);
    		Long c = d*w;
    		  //空间大小
        	mana.setBz1(list.get(0).get("bz1"));
        	mana.setBz2(list.get(0).get("bz2"));
        	mana.setBz3(list.get(0).get("bz3"));
        	mana.setBz4(list.get(0).get("bz4"));
        	mana.setBz5(list.get(0).get("bz5"));
        	mana.setCreateid(list.get(0).get("createid"));
        	mana.setCreatetime(list.get(0).get("createtime"));
        	mana.setPacename(pacename);
        	//当时在这里直接转换类型不成功所以写在了外面
        	mana.setRoomoruser(orid);
        	mana.setRoomoruserid(list.get(0).get("roomoruserid"));
        	mana.setSpaceid(spaceid);
        	mana.setSpacesize(c+"");
        	mana.setSpaceusesize(list.get(0).get("spaceusesize"));
        	mana.setType(1);
        	dlSpaceManagerService.updateByPrimaryKey(mana);
        	operateNum = ofMucRoomManager.update(omr);
        	
            
        }else {
        	String sql = "select * FROM ofMucRoom o WHERE o.naturalName='" + omr.getNaturalName() + "'" + "or  o.name = '" + omr.getName() + "'";
        	List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			
			if(list.size() == 0){
				String Sql = "select name, CAST(name AS INT)  from ofMucRoom order by name desc";
				List<Map<String,String>> List = publicExcuteSqlService.executeSql(Sql);
				if(List.size() == 0){
					
					omr.setName("11110");
				}else{
					Integer name =Integer.valueOf(List.get(0).get("name"));
					name++;
					
					omr.setName(""+name);
				}
				
				
				ofMucRoomManager.insert(omr);
				//在过滤表中增加数据，需要根据当前人得所在组织机构过滤
				String oid = UUIDGenerator.getUUID();
				PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
				String sqls = "SELECT * FROM ORGANIZATION o WHERE o.RPT_UNIT='"+loginUser.getUserEname()+"'";
				List<Map<String,String>> l =publicExcuteSqlService.executeSql(sqls); 
				String Sqls = "INSERT INTO dlRoomOfUnit VALUES('"+oid+"','"+omr.getRoomID()+"','"+l.get(0).get("OID")+"')";
				publicExcuteSqlService.updateBySql(Sqls);
				
				
				/*Ofmucaffiliation aff = new Ofmucaffiliation();
				aff.setJid(loginUser.getUserEname()+memberSplicingService.UserId());
				aff.setAffiliation(10);
				aff.setRoomid(omr.getRoomID());
				ofmucaffiliationMapper.insert(aff);*/
				operateNum = 1;
			}else{
				operateNum = 0;
			}
        	
        }
        if (operateNum == 0){
            return "fail";
        }else {
            return "success";
        }
    }


    /**
     * 删除
     * @param omr
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(OfMucRoom omr){
        int operateNum = 0;
        operateNum = ofMucRoomManager.delete(omr);
        if (operateNum == 1){
            return "success";
        }
        return "fail";
    }


    /**
     * 根据roomID查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/findByRoomID", method = RequestMethod.GET)
    @ResponseBody
    public Object findByRoomID(HttpServletRequest request){
    	//OfMucRoom
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String sql = "SELECT o.naturalName,o.description,o.roomID,d.spacesize,d.pacename,d.spaceid FROM ofMucRoom o, dlSpaceManager d WHERE o.roomID ='"+roomID+"' AND d.roomoruserid = '"+roomID+"'";
        List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
        //ofMucRoomManager.findByRoomID(roomID)
        if(list.size()>0){
    		return list.get(0);
    	}else{
    		return "false";
    	}
    }

    /**
     * 通过群名称查找
     * @param omr
     * @return
     */
    @RequestMapping(value = "/findByRoomName", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findByRoomName(OfMucRoom omr) throws UnsupportedEncodingException {
//        OfUser param = new OfUser();
//        param.setUserName(userName);
        String name = omr.getNaturalName();
        //处理中文参数乱码
        if (name != null){
            name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
            omr.setNaturalName(name);
        }
        Map<String, Object> res = new HashMap<String, Object>();
        omr.setInitPage(0);
        res.put("rows", ofMucRoomManager.query(omr));
        omr.setInitPage(1);
        res.put("total", ofMucRoomManager.query(omr).size());
        return res;
    }

    /**
     * 显示菜单
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show() {
        return "ofMucRoomAndMember/list";
    }
    
    @RequestMapping(value = "/selectMyRoomListByCurrentUser", method = RequestMethod.GET)
    public @ResponseBody Object selectMyRoomListByJid(HttpServletRequest request,HttpSession session) {
    	PersonManage personManage = (PersonManage)session.getAttribute("loginUser");
    	String userid = personManage.getId();
    	String userEname=personManage.getUserEname();
    	/*String  sql = "select roomID,naturalName from ofMucRoom ";*/
    	String sql = "select roomID,naturalName from ofMucRoom where roomID in (SELECT roomID FROM ofMucMember where SUBSTRING(jid,0,charindex('@',jid)) ='"+userEname+"')";
    	if(personManage.getUserEname().equals("admin")){
    		sql = "select roomID,naturalName from ofMucRoom ";
    	}
        return excuteSqlService.executeSql(sql);
    }
}
