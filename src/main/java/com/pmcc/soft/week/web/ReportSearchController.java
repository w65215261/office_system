package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.OrganPersonRelation;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.OrganizationRelation;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.OrganizationRelationManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManager;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.manager.ReportManager;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by H on 2016/1/18.
 */

@Controller
@RequestMapping("search")
public class ReportSearchController {
    @Autowired
    ReportManager reportManager;
    @Autowired
    PersonInfoManager personInfoManager;
    @Autowired
    OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    private  OrganizationInfoManager organizationInfoManager;
    @Autowired
    private OrganizationRelationManager organizationRelationManager;
    /**
     * 侧边年月
     * @param report
     * @return
     */
    @RequestMapping(value = "/searchReports", method = RequestMethod.GET)
    public ModelAndView report(Report report) {
        ModelAndView ma = new ModelAndView("/report/searchReport");
        String[] s = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};//前台页面日报侧边月份显示
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        ma.addObject("month", s);
        ma.addObject("year", year);
        return ma;
    }
    @RequestMapping(value = "/searchReport", method = RequestMethod.GET)
    public ModelAndView searchReport(Report report,HttpSession session) {
        ModelAndView ma = new ModelAndView("/report/seachDayReport");
        Date date=new Date();
        String sDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String personId=user.getId();
        List<OrganPersonRelation>organPersonRelationList=organPersonRelationManager.findByPersonId(personId);
       OrganizationInfo organizationInfo= organizationInfoManager.queryOrgCname(organPersonRelationList.get(0).getOrganizationInfoId());
//        String[] s = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};//前台页面日报侧边月份显示
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        ma.addObject("month", s);
//        ma.addObject("year", year);
       String orgId=organPersonRelationList.get(0).getOrganizationInfoId();
        ma.addObject("sDate",sDate);
        ma.addObject("orgId",orgId);
        ma.addObject("orgCode",organizationInfoManager.queryOrgByOrgId(organPersonRelationList.get(0).getOrganizationInfoId()).getOrgCode());
        ma.addObject("organizationInfo",organizationInfo);
        ma.addObject("list",organPersonRelationList);
        return ma;
    }
    /**
     * load jsp页面
     * @return
     */
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ModelAndView load(HttpSession session,HttpServletRequest request) {
        ModelAndView ma = new ModelAndView("/report/detailsForReport");
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String userName=user.getUserCname();
        String sDate=request.getParameter("date");
        if (sDate!=null){
            Date date1=null;
            try {
                date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String d1=new SimpleDateFormat("yyyy年MM月dd日").format(date1);
            ma.addObject("nowTime",d1);
        }else{
            Date date=new Date();
            String d=new SimpleDateFormat("yyyy年MM月dd日").format(date);
            ma.addObject("nowTime",d);
        }
        ma.addObject("cDate",sDate);
        ma.addObject("userName", userName);
        return ma;
    }
    public List queryOrgId(String rootId){
        List<String>list=new ArrayList<>();
        String rootOrgId=rootId;
        if(rootOrgId!=null){
            String paranId=organizationRelationManager.queryORbyOrgId(rootOrgId).getId();
            list.add(paranId);
            list = queryList(paranId,list);
        }

        return list;
    }

    public List<String> queryList(String oid,List<String> list){
        List<OrganizationRelation>organizationRelationList=organizationRelationManager.queryOrgRbyOrgFoid(oid);
        for(OrganizationRelation organizationRelation:organizationRelationList){
            list.add(organizationRelation.getId());
            queryList(organizationRelation.getId(),list);


        }
        return list;
    }

    /**
     * 日报查询
     * @param report
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> query(Report report,HttpServletRequest request,Date startTime,Date endTime,HttpSession session){
        Map<String,Object> ma= new HashMap<String, Object>();
        String sEcho=request.getParameter("sEcho");

        String type=request.getParameter("type");
        String personId=request.getParameter("personId");
        report.setStartTime(startTime);
        if(endTime==null){
            Date date=new Date();
            report.setEndTime(date);
        }else {
            report.setEndTime(endTime);
        }
        String orgId=request.getParameter("orgId");
        if(orgId!=null&&orgId.length()>0){
            if (type == null) {
                report.setType("0");
            } else {
                report.setType(type);
            }
            List<String> orgIds = new ArrayList<String>();
//            PersonManage user = (PersonManage) session.getAttribute("loginUser");
//            String personIds=user.getId();
//            List<OrganPersonRelation>organPersonRelationList=organPersonRelationManager.findByPersonId(personIds);
            List<String>lists=queryOrgId(orgId);
            for(int i = 0;i<lists.size();i++){
                orgIds.add(organizationRelationManager.queryOrgRById(lists.get(i)).getOrganizationInfoId());
            }
            report.setList(orgIds);
            report.setStartTime(startTime);
            report.setEndTime(endTime);
            report.setType("0");

            report.setInitPage(0);
            List<Report> list = reportManager.searchByOrgIdMap(report);
            ma.put("sEcho", sEcho);
            report.setInitPage(1);
            int s = reportManager.searchByOrgIdMap(report).size();
            ma.put("iTotalRecords", s);
            ma.put("iTotalDisplayRecords", s);
            ma.put("aaData", list);
        }else {
            if (personId != null && personId.length() > 0) {
                report.setRtpPersonId(personId);

                if (type == null) {
                    report.setType("0");
                } else {
                    report.setType(type);
                }
                List<Report> list1 =new ArrayList<Report>();
                List<Report> list = reportManager.searchReport(report);
                ma.put("sEcho", sEcho);
                report.setInitPage(1);
                int s = reportManager.searchReport(report).size();
                ma.put("iTotalRecords", s);
                ma.put("iTotalDisplayRecords", s);
                ma.put("aaData", list);
            }
        }

        return  ma;
    }

    /**
     * 点击侧边年月查询
     * @param report
     * @return
     */
    @RequestMapping(value = "/searchDetails",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,Object> searchDetails(Report report,HttpServletRequest request){
        Map<String,Object> ma= new HashMap<String, Object>();
        String sEcho=request.getParameter("sEcho");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String dates = year + "-" + month + "-1";
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd").parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
        String nowTime = new SimpleDateFormat("yyyy年MM月dd日").format(d);
        report.setInitPage(0);
        report.setType("0");
        report.setBelongsDate(d);
        List<Report> list=reportManager.searchReport(report);//分页查询

        report.setInitPage(1);
        int totalRecord= reportManager.searchReport(report).size();//总条数
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  report.getRows());//总页数
        int currentPage= report.getPage();//当前页


        return ma;
    }

    /**
     * 颜色
     * @param response
     */
    public static final int DAY=60;
    @RequestMapping(value = "/color",method = RequestMethod.POST)
    @ResponseBody
    public void color( HttpServletResponse response,HttpServletRequest request){
        String rptPersonId=request.getParameter("rptPersonId");
        Date date = new Date();
        String color = "[";
        for (int i = 0; i <= DAY; i++) {
            Date newDate = DateUtil.addDays(date, -i);
            //通过id,时间查询数据判断m的值
            int m = reportManager.searchByDate(newDate,rptPersonId);
            int year = DateUtil.getYear(new Timestamp(newDate.getTime()));
            int month = DateUtil.getMonth(new Timestamp(newDate.getTime())) - 1;
            int day = DateUtil.getDay(new Timestamp(newDate.getTime()));

            //m等于0，日报按时提交
            if (m == 0) {
                if (i == DAY) {
                    color += "{allDay:true, start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-greenLight\"]}";
                } else {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-greenLight\"]},";
                }
            }//m等于2，为填报
            else if (m == 1) {
                if (i == DAY) {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-red\"]}";
                } else {
                    color += "{ allDay:true,start:new Date(" + year + "," + month + "," + day + "),className:[\"event\", \"bg-color-red\"]},";
                }
            }
        }
        color += "]";
        try {
            response.getWriter().write(color);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 进入日报查询
     * @param
     * @return
     */
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryAll(HttpServletRequest request,HttpSession session,Date startTime,Date endTime) throws ParseException {
        Report report= new Report();
        Map<String,Object> ma= new HashMap<String, Object>();
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String personId=user.getId();
         String sEcho=request.getParameter("sEcho");
        List<OrganPersonRelation>organPersonRelationList=organPersonRelationManager.findByPersonId(personId);
          report.setType("0");
          report.setOrgId(organPersonRelationList.get(0).getOrganizationInfoId());
        Calendar calendar = Calendar.getInstance();

        Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2016-3-08");
        report.setStartTime(startTime);
        report.setEndTime(endTime);
        report.setInitPage(0);
        List<Report> list = reportManager.searchReport(report);
            ma.put("sEcho", sEcho);
            report.setInitPage(1);
            int s = reportManager.searchReport(report).size();
            ma.put("iTotalRecords", s);
            ma.put("iTotalDisplayRecords", s);
            ma.put("aaData", list);

        return  ma;
    }
    /**
     * 进入日报查询
     * @param
     * @return
     */
    @RequestMapping(value = "/queryAllReport",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryAllReport(HttpServletRequest request,HttpSession session,Date startTime,Date endTime) throws ParseException {
        Report report= new Report();
        Map<String,Object> ma= new HashMap<String, Object>();
        String sEcho=request.getParameter("sEcho");
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String personId=user.getId();
        List<OrganPersonRelation>organPersonRelationList=organPersonRelationManager.findByPersonId(personId);
        String rootOrgId=organPersonRelationList.get(0).getOrganizationInfoId();
        String paranId=organizationRelationManager.queryORbyOrgId(rootOrgId).getId();

        List<String> orgIds = new ArrayList<String>();
        orgIds.add(rootOrgId);
        orgIds.add("f70bf61f543f428d92a27907b579f6de");
//        orgIds.add("1bd62521b11a4e21b5425ed404ca4e78");
//        orgIds.add("22ef3b907a6d4cd89b09023db65a392c");
//        orgIds.add("4ab3204ce3e2490582ab3de640fef662");
        report.setList(orgIds);
        report.setStartTime(startTime);
        report.setEndTime(endTime);
        report.setType("0");

        report.setInitPage(0);
//        List<Report> list = reportManager.searchByOrgIdMap(report);
        List<Report> list = reportManager.searchReport(report);
        report.setInitPage(1);
        int s = reportManager.searchByOrgIdMap(report).size();
        ma.put("sEcho", sEcho);
        ma.put("iTotalRecords", s);
        ma.put("iTotalDisplayRecords", s);
        ma.put("aaData", list);
        return  ma;
    }

    /**
     * 格式化时间
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
