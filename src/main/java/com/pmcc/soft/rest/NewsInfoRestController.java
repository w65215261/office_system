package com.pmcc.soft.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.NewsInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.NewsInfoManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("newsInfo")
public class NewsInfoRestController {

    @Autowired
    NewsInfoManager newsInfoManager;
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
    @Autowired
    private PersonManageManager personManageManager;
    /**
     * 处理日期保存
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(WebRequest request,
                              WebDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show() {
        ModelAndView model =new ModelAndView("/newsInfo/newsInfo");
      /*  model.addObject("type",type);*/
        return model;
    }



    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id) {
        ModelAndView model =new ModelAndView("/newsInfo/detail");
        NewsInfo newsInfo = newsInfoManager.findById(id);
        newsInfo.setClicknum(String.valueOf(Integer.parseInt(newsInfo.getClicknum())+1));
        newsInfoManager.update(newsInfo);
        model.addObject("newsInfo", newsInfo);
        return model;
    }

    @RequestMapping(value = "/see", method = RequestMethod.GET)
    public ModelAndView see(String id) {
        ModelAndView model =new ModelAndView("/login/see");
        NewsInfo newsInfo = newsInfoManager.findById(id);
        model.addObject("newsInfo", newsInfo);
        return model;
    }
    @RequestMapping(value = "/sysNotice", method = RequestMethod.GET)
    public ModelAndView sysNotice(String id) {
        ModelAndView model =new ModelAndView("/login/sysNotice");
        return model;
    }
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> query(NewsInfo ni, HttpServletRequest request) throws UnsupportedEncodingException{

        String startTime = request.getParameter("stime");
        String endTime = request.getParameter("etime");
        if(startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")){
            Timestamp timeStart = DateUtil.StringToDate(startTime, "yyyy-MM-dd");
            Timestamp timeEnd = DateUtil.StringToDate(endTime, "yyyy-MM-dd");
            ni.setStartTime(timeStart);
            ni.setEndTime(timeEnd);
        }

        String title=ni.getTitle();
        String content=ni.getContent();
        if(title != null){
//            title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
            ni.setTitle(title);
        }
        if(content != null){
//            content = new String(content.getBytes("ISO-8859-1"),"UTF-8");
            ni.setContent(content);
        }




        Map<String, Object> res = new HashMap<String, Object>();
        ni.setInitPage(0);
        String personId=request.getParameter("personId");
        String orgId=personManageManager.queryCompanyIdByPersonId(personId);
        ni.setOrgId(orgId);
        List<NewsInfo> nili=newsInfoManager.query(ni);
        	if(!nili.isEmpty()&&nili.size()>0){
        		for(NewsInfo newsInfo:nili){
        			String sql="select  e.ORG_CNAME from   ORGANIZATION e where e.OID=(select s.ORGANIZATION_INFO_ID from ORGAN_PERSON_RELATION_INFO s where s.PERSON_INFO_ID=(select  w.OID from  PERSON_INFO w where w.USER_ENAME='"+newsInfo.getRptPerson()+"'))";
        	List<Map>	list=	publicExcuteSqlService.executeSql(sql);
        	String department=null;
        	String sql1="select s.USER_CNAME  from PERSON_INFO s where s.USER_ENAME='"+newsInfo.getRptPerson()+"'";
        	List<Map>	list1=	publicExcuteSqlService.executeSql(sql1);
        	if(!list.isEmpty()&&list.size()>0){
        	department=list.get(0).get("ORG_CNAME").toString();
        	}
        	newsInfo.setDepartment(department);
        	if(!list1.isEmpty()&&list1.size()>0){
        		newsInfo.setRptPerson(list1.get(0).get("USER_CNAME").toString());
        	}
        		}
        	}
        res.put("rows", nili);
        ni.setInitPage(1);
        res.put("total",newsInfoManager.query(ni).size());

        return res;
    }

    /**
     * 跳转到新增界面
     * @return
     */
    @RequestMapping(value = "/toSaveUI", method = RequestMethod.GET)
    public ModelAndView toSaveUI() {
        ModelAndView model =new ModelAndView("newsInfo/saveNews");
      /*  model.addObject("type",type);*/
        return model;
    }

    @RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
    public ModelAndView toUpdateUI() {
        ModelAndView model =new ModelAndView("newsInfo/updateNews");
       /* model.addObject("type",type);*/
        return model;
    }

    @RequestMapping(value = "/stick", method = RequestMethod.GET)
    @ResponseBody
    public String stick(String id) {
    	NewsInfo newsInfo=newsInfoManager.findById(id);
    	newsInfo.setTopmark("1");
    	newsInfo.setToptime(new Date());
    	newsInfoManager.update(newsInfo);
      return "success";
    }
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    @ResponseBody
    public  String insert(NewsInfo  ni,HttpServletRequest request, HttpServletResponse response,HttpSession session)throws IOException{
    	PersonManage personManage = (PersonManage)session.getAttribute("loginUser");
        ni.setRptPerson(personManage.getUserEname());
        ni.setId(UUIDGenerator.getUUID());
        ni.setRptDate(new Date());
        ni.setClicknum("0");
        ni.setTopmark("0");
        ni.setToptime(ni.getRptDate());
        newsInfoManager.insert(ni);
        return "success";
       /* response.getWriter().write("success");
        response.getWriter().flush();*/
    }
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public  String  update(NewsInfo  ni,HttpServletRequest request, HttpServletResponse response,HttpSession session)throws IOException{
    	PersonManage personManage = (PersonManage)session.getAttribute("loginUser");
        ni.setRptPerson(personManage.getUserEname());     
        NewsInfo    newsInfo=newsInfoManager.findById(ni.getId());     
        ni.setClicknum(newsInfo.getClicknum());
        ni.setTopmark(newsInfo.getTopmark());
        ni.setToptime(newsInfo.getToptime());
        newsInfoManager.update(ni);
        return "success";
       /* response.getWriter().write("success");
        response.getWriter().flush();*/
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public NewsInfo find(Model model, NewsInfo ni) {
        String id=	ni.getId();
        return newsInfoManager.findById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(String ids ,HttpServletResponse response) throws IOException {
        String idArray[]=ids.split(",");
        for(int i=0;i<idArray.length;i++){
            NewsInfo ni=new NewsInfo();
            ni.setId(idArray[i]);
            newsInfoManager.delete(ni);
        }
        response.getWriter().write("success");
        response.getWriter().flush();
    }
    @RequestMapping(value = "/findNews", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findNews(Model model, NewsInfo ni) {
        ModelAndView mav=new ModelAndView("login/newDetail");
        String id=	ni.getId();
        ni= newsInfoManager.findById(id);
        mav.addObject("NewsInfo",ni);
        return mav;
    }


}
