package com.hndl.mobileplatform.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.hndl.mobileplatform.model.Dlfilesmanager;
import com.hndl.mobileplatform.model.DlfilesmanagerExample;
import com.hndl.mobileplatform.model.Dlspacemanager;
import com.hndl.mobileplatform.model.DlspacemanagerExample;
import com.hndl.mobileplatform.service.DlFilesManagerService;
import com.hndl.mobileplatform.service.DlSpaceManagerService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.BaseVO;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;

/**
 * test
 * 
 * @author 郑冰冰
 * @since 2015.07.01
 **/
@Controller
@RequestMapping(value = "dlFilesManager")
public class DlFilesManagerController {
	@Resource
	private DlFilesManagerService dlFilesManagerService;
	@Autowired
	PersonManageManager personManageManager;
	@Resource
	private DlSpaceManagerService dlSpaceManagerService;
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String id) {
		ModelAndView model = new ModelAndView("/myspace/roomSpace");
		model.addObject("sadas",id);
		return model;
	}


	


	@RequestMapping(value = "/getFilesList", method = RequestMethod.GET)
	public @ResponseBody Object softupdate(HttpServletRequest request, BaseVO baseVo) {
		Map map = new HashMap();
		String userOrRoom = request.getParameter("userOrRoom");
		String userOrRoomid = request.getParameter("userOrRoomid");
		DlfilesmanagerExample example = new DlfilesmanagerExample();
		DlfilesmanagerExample.Criteria criteria = example.createCriteria();
		if (userOrRoom != null && !userOrRoom.equals("")) {
			criteria.andUserorroomEqualTo(userOrRoom);
		}
		if (userOrRoomid != null && !userOrRoomid.equals("")) {
			criteria.andUserorroomidEqualTo(userOrRoomid);
		}
		example.setOrderByClause("createtime desc");
		List<Dlfilesmanager> rows = dlFilesManagerService.selectByExample(example, baseVo);
		for (Dlfilesmanager df : rows) {
			df.setFilepath(SystemParamsUtil.getSysConfig().get("androidFileAddress").toString()	+ df.getFilepath());
		}
		int total=dlFilesManagerService.countByExample(example);
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}
	@RequestMapping(value = "/getFilesListForAndroid", method = RequestMethod.GET)
	public @ResponseBody Object getFilesListForAndroid(HttpServletRequest request) {
		Map map = new HashMap();
		String userOrRoom = request.getParameter("userOrRoom");
		String userOrRoomid = request.getParameter("userOrRoomid");
		DlfilesmanagerExample example = new DlfilesmanagerExample();
		DlfilesmanagerExample.Criteria criteria = example.createCriteria();
		if (userOrRoom != null && !userOrRoom.equals("")) {
			criteria.andUserorroomEqualTo(userOrRoom);
		}
		if (userOrRoomid != null && !userOrRoomid.equals("")) {
			criteria.andUserorroomidEqualTo(userOrRoomid);
		}
		example.setOrderByClause("createtime desc");
		List<Dlfilesmanager> rows = dlFilesManagerService.selectByExample(example);
		for (Dlfilesmanager df : rows) {
			df.setFilepath(SystemParamsUtil.getSysConfig().get("androidFileAddress").toString()	+ df.getFilepath());
		}
		return rows;
	}

	// 上传
	@RequestMapping(value = "/upload")
	public @ResponseBody
	Object addGoods(HttpServletRequest request, HttpSession session,
			@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			// String name = request.getParameter("fileid");
			String fileName = file.getOriginalFilename();

			try {
				// String tomcatPath =
				// request.getServletContext().getRealPath("/files//");
				FileCopyUtils.copy(file.getBytes(), new File(fileName));// FileCopyUtils来自org.springframework.util.FileCopyUtils

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "ddd";
	}

	// 下载
	@RequestMapping(value = "/downFile")
	public void downFile(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("APPLICATION/OCTET-STREAM; charset=utf-8");
		String filepath = "";
		//公告路径
		//SELECT * FROM dlRoomAffiche d WHERE d.imagename='1326356vgvzyfqzfx6gqfy.jpg'
		String affFileName = request.getParameter("fileName");
		if(affFileName != null && !"".equals(affFileName)){
		String selectAffFileName = "SELECT * FROM dlRoomAffiche d WHERE d.imagename='"+affFileName+"'";
		List<Map> affPath = publicExcuteSqlService.executeSql(selectAffFileName);
			filepath = (String) affPath.get(0).get("imagepath");
		}else{
			//群资料路径
			String filename = request.getParameter("filename");
			String sql = "SELECT d.filepath from dlFilesManager d WHERE d.filename='"+ filename +"'";
			List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			filepath = list.get(0).get("filepath");
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filepath + "\"");
		try {
			java.io.OutputStream os = response.getOutputStream();
//			ServletContext context = request.getSession().getServletContext().getContext("/openfireFiles");
			String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
			// SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
			//java.io.FileInputStream fis = new java.io.FileInputStream(context.getRealPath("/") + filename);
			java.io.FileInputStream fis = new java.io.FileInputStream(address + "/" + filepath);
			//java.io.FileInputStream fis = new java.io.FileInputStream(filepath);
			byte[] b = new byte[1024];
			int i = 0;

			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}

			fis.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 查询所有
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public @ResponseBody Object query(
			HttpServletRequest request, BaseVO baseVo) {
		Map map = new HashMap();
		DlfilesmanagerExample example = new DlfilesmanagerExample();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String roomid = request.getParameter("roomid");
		DlfilesmanagerExample.Criteria criteria = example.createCriteria();

		if (roomid != null && !"".equals(roomid)) {
			criteria.andUserorroomidEqualTo(roomid);
		}
		example.setOrderByClause("Createtime desc");
		List<Dlfilesmanager> rows = dlFilesManagerService.selectByExample(example, baseVo);
		int total=dlFilesManagerService.countByExample(example);
		for (Dlfilesmanager dfm : rows) {
			try {
				String sql="select  r.ORG_CNAME from    ORGANIZATION  r where r.OID=(select e.ORGANIZATION_INFO_ID from ORGAN_PERSON_RELATION_INFO  e where e.PERSON_INFO_ID='"+dfm.getCreateid()+"')";
				List <Map> list=publicExcuteSqlService.executeSql(sql);
				String orgCname=null;
				if(!list.isEmpty()&&list.size()>0){
				orgCname=	list.get(0).get("ORG_CNAME").toString();
				}
				dfm.setDepartment(orgCname);	
				PersonManage personManage = new PersonManage();
				personManage.setId(dfm.getCreateid());
				PersonManage person = personManageManager.find(personManage);
				dfm.setPerson(person.getUserCname());
				Date date = new Date(Long.valueOf(dfm.getCreatetime()));
				dfm.setCreatetime(formatter.format(date));
			} catch (Exception e) {
				dfm.setCreatetime("");
			}

			dfm.getFilesize();

			Double d = Double.valueOf(dfm.getFilesize());
			d = d / 1024.00;
			if (d < 1024.0) {
				BigDecimal big = new BigDecimal(d);
				double f1 = big.setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				dfm.setFilesize(f1 + "K");
			} else {
				d = d / 1000;
				BigDecimal big = new BigDecimal(d);
				double f1 = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				dfm.setFilesize(f1 + "M");
			}
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	// 群资料上传文件
	@RequestMapping(value = "/uploadFile")
	public @ResponseBody
	String addGoodsFile(MultipartHttpServletRequest request, HttpSession session) {
		MultipartFile file = request.getFile("file");
		Dlfilesmanager df = new Dlfilesmanager();
		

		// 页面中的描述
		String bz1 = request.getParameter("bz1");
		// 文件名称
		String filename = request.getParameter("filename");
		// 当前登录人
		PersonManage loginUser = (PersonManage) session.getAttribute("loginUser");
		// 群id
		String roomid = request.getParameter("roomid");

		if (roomid != null && !"".equals(roomid)) {
			if (file !=null&&!file.isEmpty()) {

				String fileName = file.getOriginalFilename();

				String fileSize = String.valueOf(file.getSize());
				String path = String.valueOf(new Date().getTime()) + fileName;
				String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
				try {
					// String tomcatPath =
					// request.getServletContext().getRealPath("/files//");
					FileCopyUtils.copy(file.getBytes(), new File(address + "/" + path));// FileCopyUtils来自org.springframework.util.FileCopyUtils

				} catch (IOException e) {
					e.printStackTrace();
				}

				if (filename != null && !filename.equals("")) {
					//如果自己设置文件名称，则前台显示设置设置名称，下载时用文件路径来下载
					df.setFilename(filename);
					df.setFilepath(path);
				} else {
					df.setFilename(fileName);
					df.setFilepath(path);
				}

				df.setCreatetime(String.valueOf(new Date().getTime()));
				df.setFilesize(fileSize);

				df.setBz1(bz1);
				df.setUserorroom("2");
				df.setFileid(UUIDGenerator.getUUID());
				df.setCreateid(loginUser.getId());
				df.setUserorroomid(roomid);

				if (roomid != null && !"".equals(roomid)) {

					Dlspacemanager dsm = new Dlspacemanager();
					DlspacemanagerExample example = new DlspacemanagerExample();
					DlspacemanagerExample.Criteria criteria = example.createCriteria();

					criteria.andRoomoruseridEqualTo(roomid);
					List<Dlspacemanager> list = dlSpaceManagerService.selectByExample(example);
					for (Dlspacemanager ds : list) {
						Integer spaceusesize = Integer.valueOf(ds.getSpaceusesize());
						Integer filesize = Integer.valueOf(df.getFilesize());
						Integer spacesize = Integer.valueOf(ds.getSpacesize());
						dsm.setSpaceid(ds.getSpaceid());
						dsm.setBz1(ds.getBz1());
						dsm.setCreateid(ds.getCreateid());
						dsm.setPacename(ds.getPacename());
						dsm.setRoomoruser(ds.getRoomoruser());
						dsm.setRoomoruserid(ds.getRoomoruserid());
						dsm.setSpacesize(ds.getSpacesize());
						dsm.setType(ds.getType());
						dsm.setCreatetime(ds.getCreatetime());
						if (spacesize - spaceusesize < filesize) {
							return "false";
						} else {
							dsm.setSpaceusesize(spaceusesize + filesize + "");
							dlSpaceManagerService.updateByPrimaryKey(dsm);
							dlFilesManagerService.insert(df);
							return "true";
						}

					}

				}

			}

		}
		return "";
	}


	@RequestMapping(value = "/delete")
	public @ResponseBody
	String delete(String ids , String fileid ,HttpServletRequest request ,HttpSession session) {
		//定义文件大小和群id两个变量，方便下面取值
		Integer filesize = 0;
		String userroomid = "";
		int delid = 0;
		String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
		Dlspacemanager spaceman = new Dlspacemanager();
		DlspacemanagerExample example = new DlspacemanagerExample();
		DlspacemanagerExample.Criteria criteria = example.createCriteria();
		String[] name = ids.split(",");
		
		for(String n : name){
			String sql = "SELECT * from dlFilesManager d WHERE d.fileid='" + n + "'";
			List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
			
			for(int i=0 ; i<list.size();i++){
			    filesize += Integer.valueOf(list.get(i).get("filesize"));
			    userroomid = list.get(i).get("userorroomid");
			}		
		}
		
		

		criteria.andRoomoruseridEqualTo(userroomid);
		List<Dlspacemanager> list = dlSpaceManagerService.selectByExample(example);
		
		
		for(Dlspacemanager dm : list){
			Integer sizes = Integer.valueOf(dm.getSpaceusesize());
			spaceman.setSpaceusesize(sizes - filesize + "");
			spaceman.setBz1(dm.getBz1());
			spaceman.setCreateid(dm.getCreateid());
			spaceman.setCreatetime(dm.getCreatetime());
			spaceman.setPacename(dm.getPacename());
			spaceman.setRoomoruser(dm.getRoomoruser());
			spaceman.setRoomoruserid(dm.getRoomoruserid());
			spaceman.setSpaceid(dm.getSpaceid());
			spaceman.setSpacesize(dm.getSpacesize());
			spaceman.setType(dm.getType());
		}
		/**
		 * 8-24  增加删除权限
		 */
		PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
		for(int i=0 ; i<name.length;i++){
			String n = name[i];
			String sql = "SELECT p.USER_ENAME FROM PERSON_INFO p WHERE p.OID=(SELECT d.createid FROM dlFilesManager d WHERE d.fileid='"+n+"');";
			List<Map<String,String>> List = publicExcuteSqlService.executeSql(sql);
			if(List.get(0).get("USER_ENAME").equals(loginUser.getUserEname()) || loginUser.getUserEname().equals("admin")){
				String Sql = "SELECT d.fileid,d.filepath FROM dlFilesManager d WHERE d.fileid='"+n+"'";
				List<Map<String,String>> l = publicExcuteSqlService.executeSql(Sql);
				if(l.size()>0){
					new File(address + "/" + l.get(0).get("filepath")).delete();
				}	
				delid += dlFilesManagerService.deleteByPrimaryKey(name[i]);
				}else{
					return "no";
				}
			}		
		
		


		if (delid == name.length) {
			dlSpaceManagerService.updateByPrimaryKey(spaceman);
			return "success";
		}
		return "abc";

	}

	@RequestMapping(value = "/uploadFileList")
	public @ResponseBody
	Object uploadFileList(MultipartHttpServletRequest request,
			HttpSession session) {

		int size = Integer.valueOf(request.getParameter("size"));
		String userOrRoomid = request.getParameter("userOrRoomid");
		String userOrRoom = request.getParameter("userOrRoom");
		String createid = request.getParameter("createid");
		Dlfilesmanager df = new Dlfilesmanager();
		String[] jid = createid.split("@");
		String sql = "SELECT p.OID,p.USER_ENAME,p.USER_CNAME from PERSON_INFO p WHERE p.USER_ENAME = '"+jid[0]+"'";
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				MultipartFile file = request.getFile("file" + i);
				String fileName = file.getOriginalFilename();
				String path=new Date().getTime()+fileName;
				String filesize = String.valueOf(file.getSize());
				String type = "0";
				if (fileName.toLowerCase().contains("bmp")
						|| fileName.toLowerCase().contains("jpeg")
						|| fileName.toLowerCase().contains("png")) {
					type = "1";
				}
				String address = SystemParamsUtil.getSysConfig()
						.get("uploadAddress").toString();
				try {
					FileCopyUtils.copy(file.getBytes(), new File(address + "/"
							+ path));// FileCopyUtils来自org.springframework.util.FileCopyUtils

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				df.setCreateid(list.get(0).get("OID"));
				df.setCreatetime(String.valueOf(new Date().getTime()));
				df.setFileid(UUIDGenerator.getUUID());
				df.setFilename(fileName);
				df.setFilepath(path);
				df.setFilesize(filesize);
				df.setType(type);
				df.setUserorroom(userOrRoom);
				df.setUserorroomid(userOrRoomid);
				dlFilesManagerService.insert(df);
			}
		}
		return "ddd";

	}
	@RequestMapping(value = "/addRoom")
	public ModelAndView addRoom(HttpServletRequest request, HttpSession session,String roomid) {
		ModelAndView model=new ModelAndView("../../myRoom/addRoom");
		model.addObject("roomid",roomid);
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/mobileDelete")
	public @ResponseBody
	Object mobileDelete(HttpServletRequest request, HttpSession session) {

		String fileId = request.getParameter("data");
		JSONArray fileIdArray = JSONArray.fromObject(fileId);
		for (int i = 0; i < fileIdArray.size(); i++) {
			JSONObject jsonObject = fileIdArray.getJSONObject(i);
			String fileid = jsonObject.getString("fileid");
			dlFilesManagerService.deleteByPrimaryKey(fileid);
		}
		return "ddd";
	}

	// 根据名字查询
	@RequestMapping(value = "/findByName")
	public @ResponseBody Object findByName(String filename, BaseVO baseVo,String roomid) {
		Map map = new HashMap();
		DlfilesmanagerExample df = new DlfilesmanagerExample();
		DlfilesmanagerExample.Criteria criteria = df.createCriteria();
		criteria.andUserorroomidEqualTo(roomid);
		if (filename != null && !filename.equals("")) {
			criteria.andFilenameLike("%" + filename + "%");
		}
		df.setOrderByClause("Createtime desc");
		List<Dlfilesmanager> rows = dlFilesManagerService.selectByExample(df,baseVo);
		int total=dlFilesManagerService.countByExample(df);
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}
}
