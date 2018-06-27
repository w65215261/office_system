package com.hndl.mobileplatform.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.view.RedirectView;

import com.hndl.mobileplatform.model.Dlsoftversion;
import com.hndl.mobileplatform.model.DlsoftversionExample;
import com.hndl.mobileplatform.service.DlsoftversionService;
import com.pmcc.soft.core.common.BaseVO;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.sun.security.ntlm.Server;


/**
 * test
 * 
 * @author 郑冰冰
 * @since 2015.07.01
 **/
@RequestMapping(value = "mobilesoft")
@Controller
public class MobileSoftVersionController {
	@Resource
    private DlsoftversionService testService;

	@RequestMapping(value = "/softupdate", method = RequestMethod.GET)
    public @ResponseBody  Dlsoftversion softupdate(HttpServletRequest request,BaseVO baseVo) {
		DlsoftversionExample example = new DlsoftversionExample();
		DlsoftversionExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("createtime desc");
		List<Dlsoftversion> list = testService.selectByExample(example,baseVo);
		if(list.size()>0){
			
			Dlsoftversion softVersion = list.get(0);
			String path = SystemParamsUtil.getSysConfig().get("androidFileAddress").toString()+softVersion.getDownloadurl();
			softVersion.setDownloadurl(path);
			 return softVersion;
		}else{
			return null;
		}
       
        
    }
	@RequestMapping(value="/show",method=RequestMethod.GET)
	public String show(){
		
		return "mobilesoft/show";
		
	}
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public Object query(BaseVO baseVo){
		Map map = new HashMap();
		DlsoftversionExample example = new DlsoftversionExample();
		DlsoftversionExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("createtime desc");
		List<Dlsoftversion> rows = testService.selectByExample(example,baseVo);
		int total= testService.countByExample(example);
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}
	
	@RequestMapping(value="/queryByid")
	public ModelAndView queryByid(HttpServletRequest request,String id){
		ModelAndView model = new ModelAndView("/mobilesoft/edit");
		Dlsoftversion dlsoftversion = testService.selectByPrimaryKey(id);
		model.addObject("dlsoftversion",dlsoftversion);
		return model;
	}

	@RequestMapping(value="/download",method=RequestMethod.GET)
	@ResponseBody
	public void download(HttpServletRequest request,String id,HttpServletResponse response){
		Dlsoftversion dlsoftversion = null;
		Map<String, Object> model = new HashMap<String, Object>();
		dlsoftversion = testService.selectByPrimaryKey(id);
		String filename = dlsoftversion.getDownloadurl();
		response.setContentType("APPLICATION/OCTET-STREAM");  
		  response.setHeader("Content-Disposition","attachment;filename=\"" + filename + "\"");    
		  try 
		  { 
		  java.io.OutputStream os = response.getOutputStream(); 
		  String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
		  java.io.FileInputStream fis = new java.io.FileInputStream(address + "/" + filename);
		  /*java.io.FileInputStream fis = new java.io.FileInputStream("/openfireFiles/"+ filename); */
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
		  }  
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(Dlsoftversion dlsoftversion,MultipartHttpServletRequest request, HttpSession session) {
		MultipartFile file = request.getFile("file");
		String address = null;
		String fileName = null;
		/*String fileId = request.getParameter("dbtime");*/
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id=dlsoftversion.getVersionid();		
		if(file !=null&&!file.isEmpty()){
		         fileName = file.getOriginalFilename();  
		        String filepath = new Date().getTime()+ fileName;
		        try {  
		        	address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
		        	FileCopyUtils.copy(file.getBytes(), new File(address + "/" +filepath));
		        	  if(id !=null&&!id.equals("")){
		           			try {
		           				String path=testService.selectByPrimaryKey(id).getDownloadurl();
		           				new File(address + "/" + path).delete();
				        	 dlsoftversion.setDownloadurl(filepath);
		           				dlsoftversion.setCreatetime(sdf.parse(sdf.format(new Date())));
		           			} catch (ParseException e) {
		           				e.printStackTrace();
		           			}
		           			testService.updateByPrimaryKey(dlsoftversion);
		           		}else{
		           			try {
		           			 dlsoftversion.setDownloadurl(filepath);
		           				dlsoftversion.setCreatetime(sdf.parse(sdf.format(new Date())));
		           			} catch (ParseException e) {
		           				e.printStackTrace();
		           			}
		           				dlsoftversion.setVersionid(UUIDGenerator.getUUID());
		           				testService.insert(dlsoftversion);
		           		}
		        			        } catch (IOException e) {  
		        			        	e.printStackTrace();  
		        }  
		               } else{
		            	   if(!id.equals("")&&id !=null){
		           			try {
		           				dlsoftversion.setDownloadurl(testService.selectByPrimaryKey(id).getDownloadurl());
		           				dlsoftversion.setCreatetime(sdf.parse(sdf.format(new Date())));
		           			} catch (ParseException e) {
		           				e.printStackTrace();
		           			}
		           			testService.updateByPrimaryKey(dlsoftversion);
		           		}else{
		           			/*try {
		           				dlsoftversion.setCreatetime(sdf.parse(sdf.format(new Date())));
		           			} catch (ParseException e) {
		           				e.printStackTrace();
		           			}
		           				dlsoftversion.setVersionid(UUIDGenerator.getUUID());
		           				testService.insert(dlsoftversion);*/
		           		}   
		               }
		 return "success";
	}
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public Map<String, Object>  remove(String id){
		Map<String, Object> model = new HashMap<String, Object>();
		if(!id.equals("")){
			testService.deleteByPrimaryKey(id);
			model.put("success", true);
		}
		return model;
	}
	 @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    @ResponseBody
	    public String delete(String ids){
		String address = SystemParamsUtil.getSysConfig().get("uploadAddress").toString();
	        String[] roomIDarr = ids.split(",");
	        int operateNum = 0;
	        for (int i=0;i<roomIDarr.length;i++){
	        	Dlsoftversion dlsoftversion=testService.selectByPrimaryKey(roomIDarr[i]);
	        	new File(address + "/" + dlsoftversion.getDownloadurl()).delete();
	            operateNum += testService.deleteByPrimaryKey(roomIDarr[i]);
	        }
	        if (operateNum == roomIDarr.length){
	            return "success";
	        }
	        return "fail";
	    }
	
	 @RequestMapping(value="/find",method=RequestMethod.GET)
		@ResponseBody
		public Object find(String value,BaseVO baseVo){
			Map map = new HashMap();
			DlsoftversionExample example = new DlsoftversionExample();
			DlsoftversionExample.Criteria criteria = example.createCriteria();
			if(!value.equals("")&&value !=null){
				criteria.andVersioncodeLike("%"+value+"%");
			}
			example.setOrderByClause("createtime desc");
			List<Dlsoftversion> rows = testService.selectByExample(example,baseVo);
			int total= testService.countByExample(example);
			map.put("total", total);
			map.put("rows", rows);
			return map;
		} 
	 
	 
	 
	 
	 
	 
}
