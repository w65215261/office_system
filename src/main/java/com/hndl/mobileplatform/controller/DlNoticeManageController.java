package com.hndl.mobileplatform.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.view.RedirectView;

import com.hndl.mobileplatform.model.Dlnoticemanage;
import com.hndl.mobileplatform.model.DlnoticemanageExample;
import com.hndl.mobileplatform.model.Dlnoticeperson;
import com.hndl.mobileplatform.model.DlnoticepersonExample;
import com.hndl.mobileplatform.model.Dlnoticfile;
import com.hndl.mobileplatform.model.DlnoticfileExample;
import com.hndl.mobileplatform.model.DlroompostExample;
import com.hndl.mobileplatform.model.DlroompostWithBLOBs;
import com.hndl.mobileplatform.model.DlroompostmessageExample;
import com.hndl.mobileplatform.model.DlroompostmessageWithBLOBs;
import com.hndl.mobileplatform.model.DlroompostExample.Criteria;
import com.hndl.mobileplatform.service.DlNoticFileService;
import com.hndl.mobileplatform.service.DlNoticPersonService;
import com.hndl.mobileplatform.service.DlNoticeManageService;
import com.hndl.mobileplatform.service.DlroompostmessageService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.BaseVO;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Controller
@RequestMapping(value = "noticeManage")
public class DlNoticeManageController {
	@Autowired
	PersonManageManager personManageManager;
	@Resource
	private DlNoticeManageService dlNoticeManageService;
	@Resource
	private DlNoticFileService dlNoticeFileService;
	@Resource
	private DlNoticPersonService  dlNoticPersonService;
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
	@Resource
	DlroompostmessageService dlroompostmessageService;
	/**
	 * 查询全部
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(Dlnoticemanage dc,HttpServletRequest request,BaseVO baseVo,HttpSession session) {
		PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		String logEname = loginUser.getUserEname();
		Map map = new HashMap();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DlnoticemanageExample example = new DlnoticemanageExample();
		DlnoticemanageExample.Criteria criteria=example.createCriteria();
		criteria.andCreateidEqualTo(logEname);
		example.setOrderByClause("createtime,type  desc");
		int total = dlNoticeManageService.countByExample(example);
	/*	String sql = "SELECT * FROM dlNoticeManage t WHERE t.createid = '" + logEname + "'";
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
		List<Dlnoticemanage> rows =new ArrayList<Dlnoticemanage>();
		for(Map<String,String> dnm : list){
			Dlnoticemanage dlnoticemanage = new Dlnoticemanage(); 
			dlnoticemanage.setId(dnm.get("id"));
			dlnoticemanage.setTitle(dnm.get("title"));
			dlnoticemanage.setCreatetime(formatter.format(new Date(Long.valueOf(dnm.get("createtime")))));
			dlnoticemanage.setContent(dnm.get("content"));
			dlnoticemanage.setType(dnm.get("type"));
			dlnoticemanage.setCreateid(dnm.get("createid"));
			rows.add(dlnoticemanage);
		}*/
		List<Dlnoticemanage> rows=dlNoticeManageService.selectByExampleWithBLOBs(example,baseVo);
		for(Dlnoticemanage dlnoticemanage:rows){
			dlnoticemanage.setCreatetime(formatter.format(new Date(Long.valueOf(dlnoticemanage.getCreatetime()))));
			DlnoticepersonExample sendexample=new DlnoticepersonExample();
	    	DlnoticepersonExample.Criteria sendcriteria=sendexample.createCriteria();
	    	sendcriteria.andInfoidEqualTo(dlnoticemanage.getId());
	    	List<Dlnoticeperson>  sendlist =dlNoticPersonService.selectByExample(sendexample);
	    	if(sendlist !=null&&!sendlist.isEmpty()){
	    		dlnoticemanage.setSendNumber(String.valueOf(sendlist.size()));
	    	}
	    	String namesql="select s.USER_CNAME  from PERSON_INFO s where s.USER_ENAME='"+dlnoticemanage.getCreateid()+"'";
	    	List<Map>  namelist=publicExcuteSqlService.executeSql(namesql);
	    	if(!namelist.isEmpty()&&namelist.size()>0){
	    		dlnoticemanage.setCreateid(namelist.get(0).get("USER_CNAME").toString());
	    	}
	    /*	DlnoticepersonExample findexample=new DlnoticepersonExample();
	    	DlnoticepersonExample.Criteria findcriteria=findexample.createCriteria();
	    	findcriteria.andInfoidEqualTo(dlnoticemanage.getId());
	    	findcriteria.andTypeEqualTo("1");*/
	    	
	    	String sql="select  COUNT(*) as num from  dlNoticePerson s where s.infoid='"+dlnoticemanage.getId()+"' AND s.checktime is not null ";
	    	List<Map>  list=publicExcuteSqlService.executeSql(sql);
	    	if(!list.isEmpty()&&list.size()>0){
	    		dlnoticemanage.setFindNumber(String.valueOf(list.get(0).get("num")));    	
	    	}
	    	
	    	String sql1="select  COUNT(*) as num1 from  dlNoticePerson s where s.infoid='"+dlnoticemanage.getId()+"' AND s.checktime is null ";
	    /*	DlnoticepersonExample noexample=new DlnoticepersonExample();
	    	DlnoticepersonExample.Criteria nocriteria=noexample.createCriteria();
	    	nocriteria.andInfoidEqualTo(dlnoticemanage.getId());
	    	nocriteria.andTypeEqualTo("0");
	    	List<Dlnoticeperson>  nolist =dlNoticPersonService.selectByExample(noexample);*/
	    	List<Map>  list1=publicExcuteSqlService.executeSql(sql1);
	    	if(list1 !=null&&!list1.isEmpty()){
	    		dlnoticemanage.setNoNumber(String.valueOf(list1.get(0).get("num1")));    	
	    	}	
		}
		map.put("total", total);
		map.put("rows", rows);
//		Map map = new HashMap();
//		List<Dlnoticemanage> rows  = dlNoticeManageService.selectByExampleWithBLOBs(example,baseVo);
//		for (Dlnoticemanage dlnoticemanage : rows) {
//			Date date = new Date(Long.valueOf(dlnoticemanage.getCreatetime()));
//			dlnoticemanage.setCreatetime(formatter.format(date));
//		}
//		map.put("total", total);
//		map.put("rows", rows);
//		return map;
		return map;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView model = new ModelAndView("/noticeManage/noticeManage");
		return model;
	}
	@RequestMapping(value = "/Xinsert", method = RequestMethod.GET)
	public ModelAndView Xinsert() {
		ModelAndView model = new ModelAndView("/noticeManage/add");
		return model;
	}
	
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public String insert(Dlnoticemanage dlNoticeManage, MultipartHttpServletRequest request, HttpSession session) {
		//保存内容
		MultipartFile file = request.getFile("file");
		PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		dlNoticeManage.setCreateid(loginUser.getUserEname());
		String filename = request.getParameter("filename");
		dlNoticeManage.setType("0");
		dlNoticeManage.setCreatetime(String.valueOf(new Date().getTime()));
		String message = UUIDGenerator.getUUID();
		dlNoticeManage.setId(message);
		//保存文件
		Dlnoticfile dnf = new Dlnoticfile();
		if(file !=null&&!file.isEmpty()){
			dnf.setId(UUIDGenerator.getUUID());
			dnf.setInfoid(message);
			String fileName = file.getOriginalFilename();
			String filepath = String.valueOf(new Date().getTime())+fileName;
			String fileSize = String.valueOf(file.getSize());
			dnf.setFilename(fileName);
			dnf.setFilepath(filepath);
			dnf.setTime(String.valueOf(new Date().getTime()));
			dnf.setSize(fileSize);
			String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
			try {
				FileCopyUtils.copy(file.getBytes(), new File(address + "/"+filepath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			dlNoticeManageService.insert(dlNoticeManage);
			dlNoticeFileService.insert(dnf);
		}else{
			dlNoticeManageService.insert(dlNoticeManage);
		}
		return /*new ModelAndView(new RedirectView("show.do"))*/"success";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String ids) {
		String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
		List<Dlnoticeperson>  list = null;
		String[] id = ids.split(",");
		int del = 0;
		for (int i = 0; i < id.length; i++) {
			DlnoticepersonExample  example=new DlnoticepersonExample();
			DlnoticepersonExample.Criteria criteria=example.createCriteria();
			criteria.andInfoidEqualTo(id[i]);
		 list= dlNoticPersonService.selectByExample(example);
		 DlnoticfileExample example1=new DlnoticfileExample();
		 DlnoticfileExample.Criteria criteria1=example1.createCriteria();
		 criteria1.andInfoidEqualTo(id[i]);
		List<Dlnoticfile> list1= dlNoticeFileService.selectByExample(example1);
		if(!list1.isEmpty()){
			new File(address + "/" + list1.get(0).getFilepath()).delete();
		}
			if(list.isEmpty()){
				del += dlNoticeManageService.deleteByPrimaryKey(id[i]);
			}/*else{
				for(int j=0; j<list.size();j++){
					dlNoticPersonService.deleteByPrimaryKey(list.get(j).getId());
				}
				del += dlNoticeManageService.deleteByPrimaryKey(id[i]);
			}*/
		}
		if (del == id.length) {
			return "success";
		}else if(!list.isEmpty()&&list !=null){
			return "person";
		}else {
			return "fail";
		}
	}
	@RequestMapping(value="/findByoId")
	public ModelAndView findByoId(HttpServletRequest request,BaseVO baseVo){
		ModelAndView model =new ModelAndView("/noticeManage/detail");
		String oid = request.getParameter("oid");
		Dlnoticfile dnf =null;
		Dlnoticemanage dnm=null;
//		String sql = "SELECT t.title,t.content,t1.filename FROM dlNoticeManage t,dlNoticFile t1 WHERE t.id = '" + oid + "'" + "AND t1.infoid = '" + oid + "'";
//		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
		Map<String,Object> map=new HashMap<String, Object>();
		
		DlnoticemanageExample dnce = new DlnoticemanageExample();
		DlnoticemanageExample.Criteria criteria = dnce.createCriteria();
		criteria.andIdEqualTo(oid);
		
		List<Dlnoticemanage> list = dlNoticeManageService.selectByExampleWithBLOBs(dnce,baseVo);
		
		if(list !=null&&!list.isEmpty()){
			
			 dnm=list.get(0);
		}
	/*	for (Dlnoticemanage dlnoticemanage : list) {
			dnm.setContent(dlnoticemanage.getContent());
			dnm.setCreateid(dlnoticemanage.getCreateid());
			dnm.setTitle(dlnoticemanage.getTitle());
//			dlNoticeManageService.updateByPrimaryKeySelective(dnm);
		}*/
		DlnoticfileExample dnfe = new DlnoticfileExample();
		DlnoticfileExample.Criteria criteriadnfe = dnfe.createCriteria();
		criteriadnfe.andInfoidEqualTo(oid);
		List<Dlnoticfile> olist = dlNoticeFileService.selectByExample(dnfe);	
			if(olist !=null&&!olist.isEmpty()){
					 dnf = olist.get(0);
					 dnm.setName(dnf.getFilename());
		}
			  model.addObject("dnm", dnm);	
		        return model;
	}
	@RequestMapping(value = "/fById", method = RequestMethod.GET) /*  内容换成通知的*/
	public ModelAndView fById(String id, HttpSession session,BaseVO baseVo) {
		PersonManage personManage = (PersonManage) session.getAttribute("loginUser");
		String personUsersex=String.valueOf(personManage.getUserSex());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Dlnoticemanage dlnoticemanage=null;
		String dlnoticfilename =null;
		DlnoticemanageExample example = new DlnoticemanageExample();
		DlnoticemanageExample.Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(id);
		example.setOrderByClause("Createtime");
		List<Dlnoticemanage> nlnoticemanagelist = dlNoticeManageService.selectByExampleWithBLOBs(example,baseVo);
		if(!nlnoticemanagelist.isEmpty()&&nlnoticemanagelist.size()>0){
			 dlnoticemanage=	nlnoticemanagelist.get(0);	
		}
		String  DlniceFilesql="select  s.filename from  dlNoticFile s where s.infoid='"+id+"'";
		List<Map> DlniceFileList=publicExcuteSqlService.executeSql(DlniceFilesql);
		if(!DlniceFileList.isEmpty()&&DlniceFileList.size()>0){
			dlnoticfilename =	DlniceFileList.get(0).get("filename").toString();	
		}
		DlroompostmessageExample example1 = new DlroompostmessageExample();
		DlroompostmessageExample.Criteria Criteria = example1.createCriteria();
		Criteria.andNoticeidEqualTo(id);
		example.setOrderByClause("Createtime");
		List<DlroompostmessageWithBLOBs> BLOBss = dlroompostmessageService.selectByExampleWithBLOBs(example1);
		String sql="SELECT top 9 * FROM dlNoticePerson a WHERE a.infoid = '"+id+"' AND (a.type = 1 OR a.bz2 = 1) order by checktime desc";
		/*String sql = "SELECT top 9 * FROM dlNoticePerson a WHERE a.infoid='"+id+"' order by checktime desc";*/
		List<Map> personReadInfoList = publicExcuteSqlService.executeSql(sql);
		if(!personReadInfoList.isEmpty()&&personReadInfoList.size()>0){
			for(Map map:personReadInfoList){
				PersonManage personManag=	personManageManager.queryPersonById(map.get("personid").toString());
			String checktime=map.get("checktime").toString();
			long lg = Long.parseLong(checktime);
			Date checkdate = new Date(lg);
			String checktim = formatter.format(checkdate);
			map.put("checktime", checktim);
			map.put("usersex", personManag.getUserSex());
			}
		}
		SimpleDateFormat formatter1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<Map> list = publicExcuteSqlService.queryUserNameAndId();
				String s = dlnoticemanage.getCreatetime();
				long l = Long.parseLong(s);
				Date date = new Date(l);
				String time = formatter1.format(date);
				dlnoticemanage.setCreatetime(time);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dlnoticemanage", dlnoticemanage);
		map.put("BLOBss", BLOBss);
		map.put("dlnoticfilename", dlnoticfilename);
		map.put("cur_ryxxid", personManage.getId());
		map.put("personUsersex", personUsersex);
		map.put("personReadInfoList", personReadInfoList);
		return new ModelAndView("/noticeManage/fById", map);
	}
	/**
	 * 查看详情
	 */
	/*@RequestMapping(value="/fById")
	public ModelAndView fById(HttpServletRequest request,String id,BaseVO baseVo){
		ModelAndView model = new ModelAndView("/noticeManage/fById");
	
		Dlnoticemanage dlnoticemanage=null;
		DlnoticemanageExample example = new DlnoticemanageExample();
		DlnoticemanageExample.Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(id);
		List<Dlnoticemanage> list = dlNoticeManageService.selectByExampleWithBLOBs(example,baseVo);
		if(!list.isEmpty()&&list.size()>0){
			dlnoticemanage=list.get(0);
		}
	model.addObject("dlnoticemanage",dlnoticemanage);
	return model;
	}*/
	
	
	
	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/findById")
	public ModelAndView findById(HttpServletRequest request,BaseVO baseVo){
		ModelAndView model = new ModelAndView("/noticeManage/update");
		String oid = request.getParameter("oid");
		Dlnoticfile dnf =null;
		Dlnoticemanage dlnoticemanage=null;
//		String sql = "SELECT t.title,t.content,t1.filename FROM dlNoticeManage t,dlNoticFile t1 WHERE t.id = '" + oid + "'" + "AND t1.infoid = '" + oid + "'";
//		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
		Map<String,Object> map=new HashMap<String, Object>();
		DlnoticemanageExample dnce = new DlnoticemanageExample();
		DlnoticemanageExample.Criteria criteria = dnce.createCriteria();
		criteria.andIdEqualTo(oid);
		
		List<Dlnoticemanage> list = dlNoticeManageService.selectByExampleWithBLOBs(dnce,baseVo);
		
		if(list !=null&&!list.isEmpty()){
			
			dlnoticemanage=list.get(0);
		}
	/*	for (Dlnoticemanage dlnoticemanage : list) {
			dnm.setContent(dlnoticemanage.getContent());
			dnm.setCreateid(dlnoticemanage.getCreateid());
			dnm.setTitle(dlnoticemanage.getTitle());
//			dlNoticeManageService.updateByPrimaryKeySelective(dnm);
		}*/
		DlnoticfileExample dnfe = new DlnoticfileExample();
		DlnoticfileExample.Criteria criteriadnfe = dnfe.createCriteria();
		criteriadnfe.andInfoidEqualTo(oid);
		List<Dlnoticfile> olist = dlNoticeFileService.selectByExample(dnfe);	
			if(olist !=null&&!olist.isEmpty()){
					 dnf = olist.get(0);
					 dlnoticemanage.setName(dnf.getFilename());
		}
			model.addObject("dlnoticemanage",dlnoticemanage);
		return model;
	}
	/**
	 * 修改验证
	 */
	@RequestMapping(value = "/Valupdate", method = RequestMethod.POST)
	@ResponseBody
	public String Valupdate(String ids) {
		List<Dlnoticeperson>  list = null;
			DlnoticepersonExample  example=new DlnoticepersonExample();
			DlnoticepersonExample.Criteria criteria=example.createCriteria();
			criteria.andInfoidEqualTo(ids);
		 list= dlNoticPersonService.selectByExample(example);
	 if(!list.isEmpty()&&list !=null){
			return "person";
		}else {
			return "success";
		}
	}
	
	
	
	
	
	
	
	/**
	 * 修改保存
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public /*ModelAndView*/ String update(MultipartHttpServletRequest request, HttpSession session,Dlnoticemanage dlnoticemanage/*,@RequestParam("file") MultipartFile file*/) {
		MultipartFile file = request.getFile("file");
		String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
		String inforId = dlnoticemanage.getId();
		String filename = "";
		String filepath="";
		if(file !=null&&!file.isEmpty()){
			filename =file.getOriginalFilename();
			filepath=String.valueOf(new Date().getTime())+filename;
		}
		if("".equals(filename)){
			filename=null;
		}
	/*	String filepath=String.valueOf(new Date().getTime())+filename;*/
		List<Dlnoticeperson>  list1 = null;
		DlnoticepersonExample  example1=new DlnoticepersonExample();
		DlnoticepersonExample.Criteria criteria1=example1.createCriteria();
		criteria1.andInfoidEqualTo(inforId);
	 list1= dlNoticPersonService.selectByExample(example1);
/*	 if(list1.isEmpty()){*/
		 if(inforId != null && filename != null){
				DlnoticfileExample example = new DlnoticfileExample();
				DlnoticfileExample.Criteria criteria = example.createCriteria();
				criteria.andInfoidEqualTo(inforId);
				List<Dlnoticfile> list = dlNoticeFileService.selectByExample(example);			
				if(!list.isEmpty()&&list !=null){
					String id = list.get(0).getId();
					String time = list.get(0).getTime();
					String url=list.get(0).getFilepath();
					new File(address + "/" + url).delete();
					Dlnoticfile dnf = new Dlnoticfile();
					dnf.setId(id);
					dnf.setInfoid(inforId);
					dnf.setFilename(filename);
					dnf.setTime(time);
					dnf.setSize(String.valueOf(file.getSize()));
					dnf.setFilepath(filepath);
					try {
						FileCopyUtils.copy(file.getBytes(), new File(address + "/" +filepath));
					} catch (IOException e) {
						e.printStackTrace();
					}
					dlNoticeFileService.updateByPrimaryKeySelective(dnf);
				}else if(filename != null){
					Dlnoticfile dnf = new Dlnoticfile();
					dnf.setId(UUIDGenerator.getUUID());
					dnf.setInfoid(inforId);
					String fileName = file.getOriginalFilename();
					String fileSize = String.valueOf(file.getSize());
					dnf.setFilename(fileName);
					dnf.setFilepath(filepath);
					dnf.setTime(String.valueOf(new Date().getTime()));
					dnf.setSize(fileSize);
					try {
						FileCopyUtils.copy(file.getBytes(), new File(address + "/" +filepath));
					} catch (IOException e) {
						e.printStackTrace();
					}
					dlNoticeFileService.insert(dnf);
				}
				
			}
		 dlNoticeManageService.updateByPrimaryKeySelective(dlnoticemanage);
	/* }*/
		return /*new ModelAndView(new RedirectView("show.do"))*/"success";	 	
	}
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downFile",method=RequestMethod.GET)
	public void  downFile( HttpServletRequest request, HttpServletResponse response ) {
			String id=request.getParameter("id");
	  response.setContentType("APPLICATION/OCTET-STREAM; charset=utf-8");
	  Dlnoticfile dnf = new Dlnoticfile();
	  DlnoticfileExample example = new DlnoticfileExample();
	  DlnoticfileExample.Criteria criteria = example.createCriteria();
	  criteria.andInfoidEqualTo(id);
	  //riteria.andRoomoruseridEqualTo(roomid);
	  List<Dlnoticfile> list = dlNoticeFileService.selectByExample(example);
	  String filepath = "";
	  for(Dlnoticfile dl : list){
		  filepath = dl.getFilepath();
		  
	  }
	  response.setHeader("Content-Disposition",	"attachment;filename=\""+ filepath + "\"");    
	  try 
	  { 
	  java.io.OutputStream os = response.getOutputStream();
	/*  ServletContext context = request.getSession().getServletContext().getContext("/openfireFiles");*/
	  String address = SystemParamsUtil.getSysConfig()
				.get("uploadAddress").toString();
	  java.io.FileInputStream fis = new java.io.FileInputStream(address + "/" + filepath);
	  byte[] b = new byte[1024]; 
	  int i = 0; 

	  while ( (i = fis.read(b)) > 0 ) 
	  { 
	  os.write(b, 0, i); 
	  } 

	  fis.close(); 
	  os.flush(); 
	  os.close(); 
	  } 
	  catch ( Exception e ) 
	  { 
		  e.printStackTrace();
	  }  
	}
	
	
	
	
	
	
	
	
	/**
	 * 查看文件是否存在
	 */
	@RequestMapping(value = "/findByFileId")
	@ResponseBody
	public String  findByFileId( HttpServletRequest request,String id, HttpServletResponse response) {
	  response.setContentType("APPLICATION/OCTET-STREAM; charset=utf-8");
	  Dlnoticfile dnf = new Dlnoticfile();
	  DlnoticfileExample example = new DlnoticfileExample();
	  DlnoticfileExample.Criteria criteria = example.createCriteria();
	  criteria.andInfoidEqualTo(id);
	  //riteria.andRoomoruseridEqualTo(roomid);
	  List<Dlnoticfile> list = dlNoticeFileService.selectByExample(example);
	  String filename = "";
	  for(Dlnoticfile dl : list){
		  filename = dl.getFilename();
	  }
	  if(filename != null && filename != ""){
		  return "success";
	  }else{
		  return "";
	  }
	}
}