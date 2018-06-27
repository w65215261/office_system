package com.hndl.mobileplatform.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hndl.mobileplatform.model.Dlroomaffiche;
import com.hndl.mobileplatform.model.DlroomafficheExample;
import com.hndl.mobileplatform.model.DlroomafficheExample.Criteria;
import com.hndl.mobileplatform.model.Ofmucaffiliation;
import com.hndl.mobileplatform.model.OfmucaffiliationExample;
import com.hndl.mobileplatform.service.DlRoomAfficheService;
import com.hndl.mobileplatform.service.OfmucaffiliationService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.BaseVO;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Controller
@RequestMapping(value = "dlRoomAffiche")
public class DlRoomAfficheController {
	
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
	@Resource
	private DlRoomAfficheService dlRoomAfficheService;
	@Resource
	private OfmucaffiliationService ofmucaffiliationService;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView model = new ModelAndView("/roomaffiche/roomAffiche");
		return model;
	}

	@RequestMapping(value = "/getShow", method = RequestMethod.GET)
	public ModelAndView getShow(String roomid,String title) {
		ModelAndView model = new ModelAndView("../../myRoom/myRoomList");
		  model.addObject("roomid",roomid);
		  model.addObject("title",title);
		return model;
	}
	
	//删除
	@RequestMapping(value = "/delete")
	public @ResponseBody Object delete(String ids,HttpSession session){
		PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		String[] name = ids.split(",");
		
		String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
   	 	String sql = "SELECT o.jid FROM  ofMucAffiliation o WHERE o.roomID = (SELECT t.roomid FROM dlRoomAffiche t WHERE t.afficheid='" + name[0] + "'"+')';
        List<Map<String,String>> List = publicExcuteSqlService.executeSql(sql);
   	String jid[] = List.get(0).get("jid").split("@");
   	if(!jid[0].equals(loginUser.getUserEname())){
   		return "false";
   	}else{
		
		int delid = 0;
		for(int i = 0; i< name.length; i++){
			String Sql = "SELECT d.afficheid,d.imagepath FROM dlRoomAffiche d WHERE d.afficheid='"+name[i]+"'";
			List<Map<String,String>> l = publicExcuteSqlService.executeSql(Sql);
			if(l.size()>0){
				new File(address + "/" + l.get(0).get("imagepath")).delete();
			}
			
			delid += dlRoomAfficheService.deleteByPrimaryKey(name[i]);
		}
		
			
		
		
		
		if(delid == name.length){
			return "success";
		}
		return "fali";
   	}
	}
    
	//查询
    @RequestMapping(value = "/seletAfficheByRoomid")
    public  @ResponseBody Object afficheByRoomid (String roomid , HttpServletRequest request, BaseVO baseVo){
    	Map map = new HashMap();
    	DlroomafficheExample example = new DlroomafficheExample();
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Criteria criteria = example.createCriteria();
    	
    	criteria.andRoomidEqualTo(roomid);
    	example.setOrderByClause("createtime desc");
    	int total = dlRoomAfficheService.countByExample(example);
    	List<Dlroomaffiche> rows = dlRoomAfficheService.selectByExample(example, baseVo);
    	
    	List<Map> listMap = publicExcuteSqlService.queryUserNameAndId();
    	for(Dlroomaffiche df : rows){ 	
    		for(Map map1 : listMap){
    			if(map1.get("oid").equals(df.getCreateid())){
    				try {
    					String sql="select e.ORG_CNAME from   ORGANIZATION e where e.OID = (select   o.ORGANIZATION_INFO_ID from   ORGAN_PERSON_RELATION_INFO  o where o.PERSON_INFO_ID='"+map1.get("oid")+"')";
    					List<Map> list=publicExcuteSqlService.executeSql(sql);
    					String orgCname = null;
    					if(!list.isEmpty()&&list.size()>0){
    				orgCname=list.get(0).get("ORG_CNAME").toString();
    					}
    					df.setDepartment(orgCname);
    					Date date = new Date(Long.valueOf(df.getCreatetime()));
    					df.setCreatetime(formatter.format(date));
    					df.setUserName(map1.get("username").toString());
    				} catch (Exception e) {
    					df.setCreatetime("");
    				}
    				
    			}
    		}
    		
    	}
    	map.put("total", total);
		map.put("rows", rows);
		return map;
    }
    
    @RequestMapping(value = "/see", method = RequestMethod.GET)
    public @ResponseBody Object see(String afficheid){
    	ModelAndView model =new ModelAndView("/roomaffiche/detail");
    	String sql = "SELECT * FROM dlRoomAffiche d WHERE d.afficheid='"+afficheid+"'";
    	List<Map<String , String>> list = publicExcuteSqlService.executeSql(sql);
    	if(list.size()>0){
    		if(list.get(0).get("imagepath") != null && !"".equals(list.get(0).get("imagepath"))){
    			list.get(0).put("imagepath", SystemParamsUtil.getSysConfig().get("hostFileAddress").toString()+ list.get(0).get("imagepath"));
    		}
    	
    	model.addObject("da", list.get(0));
    	}
		return  model;
    	
    }
    
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String afficheid , HttpServletRequest request) {
    	Dlroomaffiche da = new Dlroomaffiche();
        ModelAndView model =new ModelAndView("/roomaffiche/detail");
        DlroomafficheExample example = new DlroomafficheExample();
    	Criteria criteria = example.createCriteria();
    	criteria.andAfficheidEqualTo(afficheid);
        List<Dlroomaffiche> list = dlRoomAfficheService.selectByExampleWithBLOBs(example);
        for(Dlroomaffiche df : list){
        	da.setAffichename(df.getAffichename());
    		da.setContent(df.getContent());
        }
        request.setAttribute("da" , da);
        return model;
    }
    
    
    /**
     * 双击人员查询详情信息
     */
    @RequestMapping(value = "/selectUser",method = RequestMethod.GET)
    
    public @ResponseBody Object selectUser(HttpServletRequest request , String afficheid){	
    	String Sql = "SELECT o.naturalName,t.roomid,t.affichename,t.afficheid,t.content,t.imagename,t.imagepath FROM ofMucRoom o,dlRoomAffiche t" +
				" WHERE o.roomID = (SELECT t.roomid FROM dlRoomAffiche t WHERE t.afficheid = '"+afficheid+"')AND t.afficheid = '"+afficheid+"'";
    	List<Map> list = publicExcuteSqlService.executeSql(Sql);
    	if(list.size()>0){
    		if(list.get(0).get("imagepath") != null && !"".equals(list.get(0).get("imagepath"))){
    			list.get(0).get("imagepath");
        		list.get(0).put("imagepath", SystemParamsUtil.getSysConfig().get("hostFileAddress").toString()+ list.get(0).get("imagepath"));
    		}
    		
    		return list.get(0);
    	}else{
    		return "false";
    	}
    	
    	//System.out.println(list);
		
    	
    }
    
    
    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
    public @ResponseBody Object toUpdate(HttpServletRequest request , String afficheid,HttpSession session){
    	PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
    	 String sql = "SELECT o.jid FROM  ofMucAffiliation o WHERE o.roomID = (SELECT t.roomid FROM dlRoomAffiche t WHERE t.afficheid='" + afficheid + "'"+')';
         List<Map<String,String>> List = publicExcuteSqlService.executeSql(sql);
    	String jid[] = List.get(0).get("jid").split("@");
    	if(!jid[0].equals(loginUser.getUserEname())){
    		return "false";
    	}else{
    	Dlroomaffiche da = new Dlroomaffiche();
    	ModelAndView model = new ModelAndView("/roomaffiche/updateAffiche");
    	DlroomafficheExample example = new DlroomafficheExample();
    	Criteria criteria = example.createCriteria();
    	criteria.andAfficheidEqualTo(afficheid);
    	List<Dlroomaffiche> list = dlRoomAfficheService.selectByExampleWithBLOBs(example);
    	for(Dlroomaffiche df : list){
    		da.setAffichename(df.getAffichename());
    		da.setImagename(df.getImagename());
    		da.setImagepath(df.getImagepath());
    		da.setImagesize(df.getImagesize());
    		da.setContent(df.getContent());
    		da.setRoomid(df.getRoomid());
        	da.setAfficheid(afficheid);
        	
    	}
    	//为了查naturalName  群名称 所加的 代码
    	String Sql = "SELECT o.naturalName,t.roomid,t.afficheid,t.content,t.imagename FROM ofMucRoom o,dlRoomAffiche t" +
    					" WHERE o.roomID = (SELECT t.roomid FROM dlRoomAffiche t WHERE t.afficheid = '"+afficheid+"')AND t.afficheid = '"+afficheid+"'";
    	 List<Map<String,String>> l = publicExcuteSqlService.executeSql(Sql);
    	
    	 if(l.size()>0){
    		 da.setBz1(l.get(0).get("naturalName"));
    		request.setAttribute("da" , da);
    	    return model;
     	}else{
     		return "false";
     	}
    	
    	/* request.setAttribute("da" , da);
         return model;*/
    	}
    }
	
    /**
     * 跳转到新增界面
     * @return
     */
    @RequestMapping(value = "/toSaveUI", method = RequestMethod.GET)
    
    public ModelAndView toSaveUI(HttpServletRequest request , String roomid ,HttpSession session) {

    	
        Dlroomaffiche da = new Dlroomaffiche();
    	ModelAndView model =new ModelAndView("/roomaffiche/newAffiche");
        da.setRoomid(roomid);
    	request.setAttribute("da", da);
        return model;
    }

	@RequestMapping(value = "/checkSaveUI", method = RequestMethod.GET)

	public @ResponseBody Object checkSaveUI(HttpServletRequest request , String roomid ,HttpSession session) {
		//PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		OfmucaffiliationExample example = new OfmucaffiliationExample();

		com.hndl.mobileplatform.model.OfmucaffiliationExample.Criteria criteria = example.createCriteria();
		criteria.andRoomidEqualTo(Integer.valueOf(roomid));
		List<Ofmucaffiliation> list = ofmucaffiliationService.selectByExample(example);
		String [] jid = {"",""};
		if(list.size()>0){
			jid = list.get(0).getJid().split("@");
		}else{
			return "false";
		}

		if(!jid[0].equals(loginUser.getUserEname())){
			return "false";
		}else{

		return "success";
		}
	}
    
    //上传修改方法
    @RequestMapping(value = "/uploadFile")
	public @ResponseBody
	Object addGoodsFile(MultipartHttpServletRequest request, HttpSession session) {
    	MultipartFile file = request.getFile("file");
    	Dlroomaffiche da = new Dlroomaffiche();
    	
    	PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
    	//时间转换
//		Date date = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String createtime =  formatter.format(date);
		
		//获取前台数据
        String affichename = request.getParameter("affichename");
		String content = request.getParameter("content");
		String imagenameweb = request.getParameter("imagename");
		String imagepathweb = request.getParameter("imagepath");
		String imagesizeweb = request.getParameter("imagesize");
		String roomid = request.getParameter("roomid");
		String afficheid = request.getParameter("afficheid");
		
		//由于修改后要把之前的图片删除所以在这里做一次查询，然后在更新成功之后做删除磁盘原图片操作
    	String sql = "SELECT * FROM dlRoomAffiche d WHERE d.afficheid='"+afficheid+"'";
    	List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
        
		//如果有上传文件
		if (file !=null&&!file.isEmpty()) {
			
			//获取文件名字大小
			String imagename = file.getOriginalFilename();
			String imagesize = String.valueOf(file.getSize()/1024.0);
			String path = String.valueOf(new Date().getTime()) + imagename;
			//拿到设置过的地址
			String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
			try {
				//设置上传路径
				FileCopyUtils.copy(file.getBytes(), new File(address + "/" +path));
			} catch (IOException e) {
				e.printStackTrace();
			}
			da.setRoomid(roomid);
			da.setImagename(imagename);
			da.setImagepath(path);
			//da.setCreatetime(createtime);
			da.setCreatetime(String.valueOf(new Date().getTime()));
			da.setAffichename(affichename);
			da.setImagesize(imagesize);
			da.setContent(content);
			da.setCreateid(loginUser.getId());
			if(afficheid != null && !afficheid.equals("")){
				da.setAfficheid(afficheid);
				dlRoomAfficheService.updateByPrimaryKeyWithBLOBs(da);
				new File(address + "/" + list.get(0).get("imagepath")).delete();
				return "success";
			}else{
				da.setAfficheid(UUIDGenerator.getUUID());
				dlRoomAfficheService.insert(da);
				return "success";
			}
		}else{
			//没有上传文件 所以不需要文件名字大小等
			da.setRoomid(roomid);
			//da.setCreatetime(createtime);
			Long time= new Date().getTime();
			da.setCreatetime(String.valueOf(time));
			da.setAffichename(affichename);
			da.setContent(content);
			da.setImagename(imagenameweb);
			da.setImagepath(imagepathweb);
			da.setImagesize(imagesizeweb);
			da.setCreateid(loginUser.getId());
			
			if(afficheid != null && !afficheid.equals("")){
				da.setAfficheid(afficheid);
				dlRoomAfficheService.updateByPrimaryKeyWithBLOBs(da);
				return "success";
			}else{
				da.setAfficheid(UUIDGenerator.getUUID());
				dlRoomAfficheService.insert(da);
				return "success";
			}
		}
		
		//return new ModelAndView(new RedirectView("getShow.do?roomid="+roomid+"&&title="+"112"));
	}
	
	
	//手机端调用接口
	@RequestMapping(value = "/getAfficheList",method = RequestMethod.GET)
	public @ResponseBody Object afficheList(HttpServletRequest request, BaseVO baseVo){
		baseVo.setRows(5);
		DlroomafficheExample example = new DlroomafficheExample();
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//转换时间格式
		//formatter.format(date);
		DlroomafficheExample.Criteria criteria = example.createCriteria();
		
		//根据群id查询
		String roomid = request.getParameter("roomid");
		if(roomid != null && !roomid.equals("")){
			criteria.andRoomidEqualTo(roomid);
		}
		
		List<Dlroomaffiche> list = dlRoomAfficheService.selectByExampleWithBLOBs(example,baseVo);
		//转换路径
		for (Dlroomaffiche d : list) {
			
			try {
				Date date = new Date(Long.valueOf(d.getCreatetime()));
				d.setCreatetime(formatter.format(date));
			} catch (Exception e) {
				d.setCreatetime("");
			}
			
			d.setImagepath(SystemParamsUtil.getSysConfig().get("androidFileAddress").toString()+ d.getImagepath());
		}
		
		
		
		return list;
	}
	
	
	
}
