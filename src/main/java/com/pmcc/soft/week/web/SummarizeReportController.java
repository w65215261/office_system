package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.*;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.OrganizationRelationManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.*;
import com.pmcc.soft.week.manager.*;
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
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangbin on 16/4/5.
 */
@Controller
@RequestMapping(value = "summarizeReport")
public class SummarizeReportController {
    @Autowired
    private PersonInfoManageManager personInfoManageManager;
    @Autowired
    private OrganizationInfoManager organizationInfoManager;
    @Autowired
    private SummarizeReportManager summarizeReportManager;
    @Autowired
    private SummarizeReportReceiveManager summarizeReportReceiveManager;
    @Autowired
    private SummarizeReportReplayManager summarizeReportReplayManager;
    @Autowired
    private SystemAttachmentManager systemAttachmentManager;
    @Autowired
    private OrganizationRelationManager organizationRelationManager;
    @Autowired
    private OrganPersonRelationManager organPersonRelationManager;


    @RequestMapping(value = "show",method = RequestMethod.GET)
    public ModelAndView show(){
        ModelAndView mav = new ModelAndView("summarizeReport/summarizeReport");
        return mav;
    }

    /**
     * 跳转到发日志界面
     * @return
     */
    @RequestMapping(value = "sendReportShow",method = RequestMethod.GET)
    public ModelAndView sendReportShow(){
        ModelAndView mav = new ModelAndView("summarizeReport/sendReport");
        String reportId = UUIDGenerator.getUUID();
        mav.addObject("reportId",reportId);
        return mav;
    }

    /**
     * 跳转到我发出的界面
     * @param session
     * @param rt
     * @return
     */
    @RequestMapping(value = "showSendWork",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showSendWork(HttpSession session,Report rt,Date startTime,Date endTime){
        ModelAndView mav = new ModelAndView("summarizeReport/showSendWork");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        Calendar calendar = Calendar.getInstance();
        Date date=new Date();
        if(startTime!=null){
            calendar.setTime(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            rt.setStartTime(start);
            mav.addObject("sdate",startTime);
        }else{
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            rt.setStartTime(start);
            mav.addObject("sdate",date);
        }
        if(endTime!=null){
            calendar.setTime(endTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.SECOND, -1);
            Date end = calendar.getTime();
            rt.setEndTime(end);
            mav.addObject("edate",endTime);
        }else{
            rt.setEndTime(date);
            mav.addObject("edate",date);
        }
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        if(pm != null){
            OrganizationInfo of = organizationInfoManager.queryOrgByOrgId(pm.getCompanyId());
            rt.setOrgId(of.getId());
        }
        rt.setRtpPersonId(personId);
        List<Report> rts =summarizeReportManager.queryByRtpPersonId(rt);
        for(Report report : rts){
            report.setReadStatus("-1");
            String doneWork = report.getDoneWork();
            if(doneWork.length()>12){
                report.setDoneWork(doneWork.substring(0, 12)+"...");
            }
            if( report.getUndoneWork().length()>12){
                report.setUndoneWork(report.getUndoneWork().substring(0, 12) + "...");
            }
            if( report.getTeamWork().length()>12){
                report.setTeamWork(report.getTeamWork().substring(0, 12)+"...");
            }

//
            SystemAttachment systemAttachment = new SystemAttachment();
            systemAttachment.setBusinessData(report.getId());
            systemAttachment.setOrgId(report.getOrgId());
            int attachmentNum = systemAttachmentManager.queryByReportId(systemAttachment).size();
            report.setAttachmentNum(attachmentNum);
        }
        mav.addObject("rts",rts);
        return mav;
    }

    /**
     * 跳转到我收到的界面
     * @param session
     * @param receive
     * @return
     */
    @RequestMapping(value = "showReceivedWork",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showReceivedWork(HttpSession session,SummarizeReportReceive receive,Date startTime,Date endTime){
        ModelAndView mav = new ModelAndView("summarizeReport/showReceivedWork");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        SummarizeReportReceive summarizeReportReceive = new SummarizeReportReceive();
        summarizeReportReceive.setReceivePersonId(personId);
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);

        summarizeReportReceive.setOrgId(pm.getCompanyId());
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        if(startTime!=null){
            calendar.setTime(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            summarizeReportReceive.setStartTime(start);
            mav.addObject("sdate",startTime);
        }else{
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            summarizeReportReceive.setStartTime(start);
            mav.addObject("sdate",date);
        }
        if(endTime!=null){
            calendar.setTime(endTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.SECOND, -1);
            Date end = calendar.getTime();
            summarizeReportReceive.setEndTime(end);
            mav.addObject("edate",endTime);
        }else{
            summarizeReportReceive.setEndTime(date);
            mav.addObject("edate",date);
        }
        List<SummarizeReportReceive> list = summarizeReportReceiveManager.queryByReceivePersonId(summarizeReportReceive);

        List<Report> reports = new ArrayList<Report>();

        for(SummarizeReportReceive srre : list){
            Report report = summarizeReportManager.queryByReportId(srre.getReportId());
            SystemAttachment systemAttachment = new SystemAttachment();
            systemAttachment.setBusinessData(srre.getReportId());
            systemAttachment.setOrgId(report.getOrgId());
            int attachmentNum = systemAttachmentManager.queryByReportId(systemAttachment).size();
            report.setReadStatus(String.valueOf(srre.getReadFlag()));
            report.setAttachmentNum(attachmentNum);
            String doneWork = report.getDoneWork();
            if(doneWork.length()>12){
                report.setDoneWork(doneWork.substring( 0,12)+"...");
            }
            if( report.getUndoneWork().length()>12){
                report.setUndoneWork(report.getUndoneWork().substring(0, 12) + "...");
            }
            if( report.getTeamWork().length()>12){
                report.setTeamWork(report.getTeamWork().substring(0, 12)+"...");
            }
            reports.add(report);
        }

        mav.addObject("reports", reports);
        return mav;
    }
    /**
     * 跳转到日志信息界面
     * @return
     */
    @RequestMapping(value = "showLogWork",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showLogWork(){
        ModelAndView mav = new ModelAndView("summarizeReport/showLogWork");
        return mav;
    }


    /**
     * 查看报表的信息
     * @param report
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryShowLogWork",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryShow(Report report,HttpSession session,HttpServletRequest request){
        String sEcho = request.getParameter("sEcho");
        Map<String,Object> map = new HashMap<String, Object>();
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        report.setRtpPersonId(personId);
        List<Report> list = summarizeReportManager.queryReport(report);
        map.put("sEcho", sEcho);
        report.setInitPage(1);
        map.put("iTotalRecords",summarizeReportManager.queryReport(report).size());
        map.put("iTotalDisplayRecords",summarizeReportManager.queryReport(report).size());
        map.put("aaData", list);
        return  map;
    }

    /**
     * 查看日志详情
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryDetails")
    @ResponseBody
    public Map<String,Object> queryDetails(HttpServletRequest request,HttpSession session) {
        String reportId = request.getParameter("reportId");

        //获取日志对象
        Report report = summarizeReportManager.queryByReportId(reportId);
        //区分是我发出OR我接受的FLAG
        String flag = request.getParameter("flag");

        //查找登陆人所对应的公司
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
            PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);

        SummarizeReportReceive srre = new SummarizeReportReceive();
        SummarizeReportReplay srry = new SummarizeReportReplay();
        SystemAttachment sa = new SystemAttachment();

        List<SummarizeReportReceive> srres = new ArrayList<SummarizeReportReceive>();
        List<SummarizeReportReceive> srresAll = new ArrayList<SummarizeReportReceive>();
        List<SummarizeReportReplay> srrys = new ArrayList<SummarizeReportReplay>();
        List<SystemAttachment> sas = new ArrayList<SystemAttachment>();

        //查看接受日志详情时会更新已读状态
        if("receive".equals(flag)){
            srre = new SummarizeReportReceive();
            srre.setReportId(reportId);
            srre.setReceivePersonId(personId);
            srre.setReadFlag(0);
            srres = summarizeReportReceiveManager.query(srre);
            if(srres.size() !=0){
                srre.setReadFlag(1);
                summarizeReportReceiveManager.update(srre);
            }
        }

        //已读日志的人员
        srre = new SummarizeReportReceive();
        srre.setReportId(reportId);
        srre.setReadFlag(1);
        srres = summarizeReportReceiveManager.query(srre);

        //所有接受日志的人员
        srre = new SummarizeReportReceive();
        srre.setReportId(reportId);
        srre.setReadFlag(-1);
        srresAll = summarizeReportReceiveManager.query(srre);

        //本日志的所有回复信息
        srry.setReportId(reportId);
        srrys = summarizeReportReplayManager.query(srry);

        //本日志的所有附件
        sa.setBusinessData(reportId);
        sa.setOrgId(pm.getCompanyId());
        sas = systemAttachmentManager.queryByReportId(sa);
        String reportIds = UUIDGenerator.getUUID();
        Map<String,Object> map = new HashMap<String, Object>();
        Date rptDate = report.getRptDate();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rptDate);
        map.put("report",report);
        map.put("srres",srres);
        map.put("srresAll",srresAll);
        map.put("srrys",srrys);
        map.put("sas",sas);
        map.put("reportId",reportIds);
        map.put("date",dateStr);
        return map;
    }

    /**
     * 插入一条日志
     * @param report
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(Report report,HttpServletRequest request,HttpSession session) {

        String receivePersonId = request.getParameter("receivePersonId");
        Report rt = new Report();
        Date nowTime = new Date();
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        String nowTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(nowTime);
        String nowTimeStrStart = nowTimeStr+" 00:00:00";
        String nowTimeStrEnd = nowTimeStr+" 23:59:59";
        try {
            rt.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTimeStrStart));
            rt.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTimeStrEnd));
            rt.setRtpPersonId(personId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        rt.setRtpPersonId(personId);
        List<Report> reportList = summarizeReportManager.queryByBelongsDate(rt);


        OrganizationInfo of = new OrganizationInfo();
        if(!"".equals(personId) && personId != null){
            PersonInfoManage personInfoManage = new PersonInfoManage();
            personInfoManage.setId(personId);
            PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
            if(pm != null){
                report.setRptPersonName(pm.getUserCname());
                of = organizationInfoManager.queryOrgByOrgId(pm.getCompanyId());
                report.setOrgCode(of.getOrgCode());
                report.setOrgId(of.getId());
            }
        }
        String reportId;
        if(reportList.size()>0){
            reportId = reportList.get(0).getId();
        }else{
            reportId = request.getParameter("uuid");
        }
        report.setId(reportId);
        report.setStatus("0");
        report.setType("0");
        report.setRptDate(new Date());
        report.setRtpPersonId(personId);
        report.setSource("0");
        report.setReadCount("0");
        report.setReplayCount("0");
        int res = 0;
        int rows = 0;
        if(reportList.size()>0){
            //1.变更齿数化数据
            rows = summarizeReportManager.update(report);
            //2.变更附件数据外键
            SystemAttachment systemAttachment = new SystemAttachment();
            systemAttachment.setTempBusinessData(request.getParameter("uuid"));
            systemAttachment.setBusinessData(reportId);
            systemAttachmentManager.updateByTempBusinessData(systemAttachment);
        }else{
            report.setInitFlag("1");
            report.setBelongsDate(new Date());
            rows = summarizeReportManager.insert(report);
        }

        if(res==rows){

        }

        String[] receivePersonIds = receivePersonId.split(",");
        SummarizeReportReceive srre = new SummarizeReportReceive();
        for (int i = 0; i < receivePersonIds.length; i++) {
            srre = new SummarizeReportReceive();
            PersonInfoManage personInfoManage = new PersonInfoManage();
            personInfoManage.setId(receivePersonIds[i]);
            PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
            srre.setReportId(reportId);
            srre.setReportType(0);
            srre.setReadFlag(0);
            srre.setReceivePersonName(pm.getUserCname());
            srre.setReceivePersonId(receivePersonIds[i]);
            srre.setOrgId(of.getId());
            srre.setOrgCode(of.getOrgCode());
            srre.setRptPersonId(personId);
            srre.setRptDate(new Date());
            rows = summarizeReportReceiveManager.insert(srre);
            if(res==rows){
            }

        }
        Map<String,Object> map = new HashMap<String, Object>();
        Date rptDate = report.getRptDate();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rptDate);
        map.put("date",dateStr);
        map.put("uuid",reportId);
        return map;
    }

    /**
     * 生成日志ID
     * @return
     */
    @RequestMapping(value = "/createUuid")
    @ResponseBody
    public String createUuid() {
        String reportId = UUIDGenerator.getUUID();
        if(!"".equals(reportId) && reportId != null){
            return reportId;
        }
        return "fail";
    }

    /**
     * 回复
     * @param replay
     * @param session
     * @return
     */
    @RequestMapping(value = "/replay", method = RequestMethod.POST)
    @ResponseBody
    public String save(SummarizeReportReplay replay,HttpSession session) {
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        String personName = pm.getUserCname();
        replay.setReplyPersonName(personName);
        replay.setReplyDate(new Date());
        int res = 0;
        int rows = summarizeReportReplayManager.insert(replay);
        if(res == rows){
            return "fail";
        }
        Date rptDate = replay.getReplyDate();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rptDate);
        return dateStr;
    }
    /**
     * 查看日志附件
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryDetail")
    @ResponseBody
    public Map<String,Object> queryDetail(HttpServletRequest request,HttpSession session) {
        String reportId = request.getParameter("reportId");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);

        SystemAttachment sa = new SystemAttachment();

        List<SystemAttachment> sas = new ArrayList<SystemAttachment>();

        //本日志的所有附件
        sa.setBusinessData(reportId);
        sa.setOrgId(pm.getCompanyId());
        sas = systemAttachmentManager.queryByReportId(sa);
        String reportIds = UUIDGenerator.getUUID();
        Map<String,Object> map = new HashMap<String, Object>();

        map.put("sas",sas);
        map.put("reportId",reportIds);
        return map;
    }
    /**
     * 日报查询
     * @param report
     * @return
     */
    @RequestMapping(value = "/queryAllReport",method = RequestMethod.GET)
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
        if(orgId!=null&&!"".equals(orgId)){
            if (type == null) {
                report.setType("0");
            } else {
                report.setType(type);
            }
            List<String> orgIds = new ArrayList<String>();
            List<String>lists=queryOrgId(orgId);
            for(int i = 0;i<lists.size();i++){
                orgIds.add(organizationRelationManager.queryOrgRById(lists.get(i)).getOrganizationInfoId());
            }
            String startTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
            String endTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(endTime);
            startTimeStr = startTimeStr+" 00:00:00";
            endTimeStr = endTimeStr+" 23:59:59";
            try {
                report.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimeStr));
                report.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            report.setList(orgIds);

            report.setInitPage(0);

            PersonInfoManage personInfoManage = new PersonInfoManage();
            personInfoManage.setInitPage(1);
            personInfoManage.setCompanyId(orgId);
            List<String> ids = new ArrayList<String>();
            List<PersonInfoManage> personInfoManageList = personInfoManageManager.query(personInfoManage);
            for(PersonInfoManage pm : personInfoManageList){
                ids.add(pm.getId());
            }
            report.setPersonList(ids);
            List<Report> list = summarizeReportManager.searchByOrgIdMap(report);
            ma.put("sEcho", sEcho);
            report.setInitPage(1);
            int s = summarizeReportManager.searchByOrgIdMap(report).size();
            ma.put("iTotalRecords", s);
            ma.put("iTotalDisplayRecords", s);
            ma.put("aaData", list);
        }else {
            if (personId != null &&!"".equals(personId)) {
                report.setRtpPersonId(personId);

                if (type == null) {
                    report.setType("0");
                } else {
                    report.setType(type);
                }
                List<Report> list1 =new ArrayList<Report>();
                List<Report> list = summarizeReportManager.searchReport(report);
                ma.put("sEcho", sEcho);
                report.setInitPage(1);
                int s = summarizeReportManager.searchReport(report).size();
                ma.put("iTotalRecords", s);
                ma.put("iTotalDisplayRecords", s);
                ma.put("aaData", list);
            }
        }

        return  ma;
    }

    @RequestMapping(value = "/searchReport", method = RequestMethod.GET)
    public ModelAndView searchReports(Report report,HttpSession session) {
        ModelAndView ma = new ModelAndView("/summarizeReport/seachDayReport");
        Date date=new Date();
        String sDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String personId=user.getId();
        List<OrganPersonRelation>organPersonRelationList=organPersonRelationManager.findByPersonId(personId);
        OrganizationInfo organizationInfo= organizationInfoManager.queryOrgCname(organPersonRelationList.get(0).getOrganizationInfoId());
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        String orgId=pm.getCompanyId();
        ma.addObject("sDate",sDate);
        ma.addObject("orgId",orgId);
        ma.addObject("orgCode",organizationInfoManager.queryOrgByOrgId(organPersonRelationList.get(0).getOrganizationInfoId()).getOrgCode());
        ma.addObject("organizationInfo",organizationInfo);
        ma.addObject("list",organPersonRelationList);
        return ma;
    }
    @RequestMapping(value = "/searchReports", method = RequestMethod.GET)
    public ModelAndView searchReport(Report report,HttpSession session) {
        ModelAndView ma = new ModelAndView("/summarizeReport/seachDayReports");
        Date date=new Date();
        String sDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        String personId=user.getId();
        List<OrganPersonRelation>organPersonRelationList=organPersonRelationManager.findByPersonId(personId);
        OrganizationInfo organizationInfo= organizationInfoManager.queryOrgCname(organPersonRelationList.get(0).getOrganizationInfoId());
        String orgId=organPersonRelationList.get(0).getOrganizationInfoId();
        ma.addObject("sDate",sDate);
        ma.addObject("orgId",orgId);
        ma.addObject("orgCode",organizationInfoManager.queryOrgByOrgId(organPersonRelationList.get(0).getOrganizationInfoId()).getOrgCode());
        ma.addObject("organizationInfo",organizationInfo);
        ma.addObject("list",organPersonRelationList);
        return ma;
    }

    /**
     * 用于时间转换
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
     * 首次登入日报查询界面查询的数据
     * @param report
     * @return
     */
    @RequestMapping(value = "/queryReport",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryReport(Report report,HttpServletRequest request,Date startTime,Date endTime,HttpSession session){
        Map<String,Object> ma= new HashMap<String, Object>();
        String sEcho=request.getParameter("sEcho");

        String type=request.getParameter("type");
        Date date=new Date();
        report.setEndTime(date);
        //得到一天的开始时间和结束时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        report.setStartTime(start);
                if (type == null) {
                    report.setType("0");
                } else {
                    report.setType(type);
                }
                List<Report> list1 =new ArrayList<Report>();
                List<Report> list = summarizeReportManager.searchReport(report);
                ma.put("sEcho", sEcho);
                report.setInitPage(1);
                int s = summarizeReportManager.searchReport(report).size();
                ma.put("iTotalRecords", s);
                ma.put("iTotalDisplayRecords", s);
                ma.put("aaData", list);
        return ma;
    }
    /**
     * 我收到的界面按照人员,开始时间,结束时间查询
     * @param session
     * @param receive
     * @return
     */
    @RequestMapping(value = "seachReceivedWork",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView seachReceivedWork(HttpSession session,SummarizeReportReceive receive,Date startTime,Date endTime,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("summarizeReport/showReceivedWork");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        SummarizeReportReceive summarizeReportReceive = new SummarizeReportReceive();
        summarizeReportReceive.setReceivePersonId(personId);
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);

        summarizeReportReceive.setOrgId(pm.getCompanyId());
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        if(startTime!=null){
            calendar.setTime(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            summarizeReportReceive.setStartTime(start);
            mav.addObject("sdate",startTime);
        }else{
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            summarizeReportReceive.setStartTime(start);
            mav.addObject("sdate",date);
        }
        if(endTime!=null){
            calendar.setTime(endTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.SECOND, -1);
            Date end = calendar.getTime();
            summarizeReportReceive.setEndTime(end);
            mav.addObject("edate",endTime);
        }else{
            summarizeReportReceive.setEndTime(date);
            mav.addObject("edate",date);
        }
        String rptPerosnId=request.getParameter("rptPersonId");

        PersonInfoManage personInfoManage1 = new PersonInfoManage();
        if(rptPerosnId!=null&&rptPerosnId.length()>0) {
            personInfoManage1.setId(rptPerosnId);
            PersonInfoManage pm1 = personInfoManageManager.queryByOid(personInfoManage1);
            String rptPersonName = pm1.getUserCname();

            mav.addObject("rptPersonName", rptPersonName);
            mav.addObject("rptPersonId", rptPerosnId);
            summarizeReportReceive.setRptPersonId(rptPerosnId);
        }

        List<SummarizeReportReceive> list = summarizeReportReceiveManager.queryByReceivePersonId(summarizeReportReceive);

        List<Report> reports = new ArrayList<Report>();

        for(SummarizeReportReceive srre : list) {
            Report report = summarizeReportManager.queryByReportId(srre.getReportId());
            SystemAttachment systemAttachment = new SystemAttachment();
            systemAttachment.setBusinessData(srre.getReportId());
            systemAttachment.setOrgId(report.getOrgId());
            int attachmentNum = systemAttachmentManager.queryByReportId(systemAttachment).size();
            report.setReadStatus(String.valueOf(srre.getReadFlag()));
            report.setAttachmentNum(attachmentNum);
            String doneWork = report.getDoneWork();
            if (doneWork.length() > 12) {
                report.setDoneWork(doneWork.substring(0, 12) + "...");
            }
            if (report.getUndoneWork().length() > 12) {
                report.setUndoneWork(report.getUndoneWork().substring(0, 12) + "...");
            }
            if (report.getTeamWork().length() > 12) {
                report.setTeamWork(report.getTeamWork().substring(0, 12) + "...");
            }
            reports.add(report);
        }

        mav.addObject("reports", reports);

        return mav;
    }

    /**
     * 日报查询只查询本人收到的
     * @param report
     * @return
     */
    @RequestMapping(value = "/queryAllReportByPerson",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryAllReportByPerson(Report report,HttpServletRequest request,Date startTime,Date endTime,HttpSession session){
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
        if(orgId!=null&&!"".equals(orgId)){
            if (type == null) {
                report.setType("0");
            } else {
                report.setType(type);
            }
            List<String> orgIds = new ArrayList<String>();
            List<String>lists=queryOrgId(orgId);
            for(int i = 0;i<lists.size();i++){
                orgIds.add(organizationRelationManager.queryOrgRById(lists.get(i)).getOrganizationInfoId());
            }
            String startTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
            String endTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(endTime);
            startTimeStr = startTimeStr+" 00:00:00";
            endTimeStr = endTimeStr+" 23:59:59";
            try {
                report.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimeStr));
                report.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            report.setList(orgIds);

            report.setInitPage(0);
            List<Report> list = summarizeReportManager.searchByOrgIdMap(report);
            ma.put("sEcho", sEcho);
            report.setInitPage(1);
            int s = summarizeReportManager.searchByOrgIdMap(report).size();
            ma.put("iTotalRecords", s);
            ma.put("iTotalDisplayRecords", s);
            ma.put("aaData", list);
        }else {
            if (personId != null &&!"".equals(personId)) {
                report.setRtpPersonId(personId);

                if (type == null) {
                    report.setType("0");
                } else {
                    report.setType(type);
                }
                List<Report> list1 =new ArrayList<Report>();
                List<Report> list = summarizeReportManager.searchReport(report);
                ma.put("sEcho", sEcho);
                report.setInitPage(1);
                int s = summarizeReportManager.searchReport(report).size();
                ma.put("iTotalRecords", s);
                ma.put("iTotalDisplayRecords", s);
                ma.put("aaData", list);
            }
        }

        return  ma;
    }
    /**
     * 首次登入日报查询界面查询本人的数据
     * @param report
     * @return
     */
    @RequestMapping(value = "/queryReportByPerson",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryReportByPerson(Report report,HttpServletRequest request,Date startTime,Date endTime,HttpSession session){
        String  receivePersonId=request.getParameter("personId");
        Map<String,Object> ma= new HashMap<String, Object>();
        String sEcho=request.getParameter("sEcho");
        PersonManage user = (PersonManage) session.getAttribute("loginUser");
        if(receivePersonId!=null) {
            SummarizeReportReceive summarizeReportReceive = new SummarizeReportReceive();

            String personId = user.getId();
            summarizeReportReceive.setRptPersonId(personId);
            summarizeReportReceive.setReceivePersonId(receivePersonId);
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            if (startTime != null) {
                calendar.setTime(startTime);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date start = calendar.getTime();
                summarizeReportReceive.setStartTime(start);
                ma.put("sdate", startTime);
            } else {
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date start = calendar.getTime();
                summarizeReportReceive.setStartTime(start);
                ma.put("sdate", date);
            }
            if (endTime != null) {
                calendar.setTime(endTime);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.SECOND, -1);
                Date end = calendar.getTime();
                summarizeReportReceive.setEndTime(end);
                ma.put("edate", endTime);
            } else {
                summarizeReportReceive.setEndTime(endTime);
                ma.put("edate", endTime);
            }
            List<SummarizeReportReceive> list = summarizeReportReceiveManager.queryReportByReceivePersonId(summarizeReportReceive);


            for (SummarizeReportReceive srre : list) {
                Report report1 = summarizeReportManager.queryByReportId(srre.getReportId());

                srre.setTeamWork(report1.getTeamWork());
                srre.setRptPersonName(report1.getRptPersonName());
                srre.setDoneWork(report1.getDoneWork());
                srre.setUndoneWork(report1.getUndoneWork());
                if(report1.getTeamWork().length()>20){
                    srre.setTeamWork(report1.getTeamWork().substring(0, 20)+"...");
                }
                if(report1.getDoneWork().length()>20){
                    srre.setDoneWork(report1.getDoneWork().substring(0, 20) + "...");
                }
                if(report1.getUndoneWork().length()>20){
                    srre.setUndoneWork(report1.getUndoneWork().substring(0, 20)+"...");
                }
            }
            ma.put("sEcho", sEcho);
            int s = list.size();
            ma.put("iTotalRecords", s);
            ma.put("iTotalDisplayRecords", s);
            ma.put("aaData", list);
        }else{


        String  personId =user.getId();
        String type=request.getParameter("type");
        Date date=new Date();
        //得到一天的开始时间和结束时间
        Calendar calendar = Calendar.getInstance();
        if(startTime!=null){
            calendar.setTime(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            report.setStartTime(start);
        }else {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date start = calendar.getTime();
            report.setStartTime(start);
        }
            if (endTime != null) {
                calendar.setTime(endTime);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.SECOND, -1);
                Date end = calendar.getTime();
                report.setEndTime(end);
                ma.put("edate", end);
            } else {
                report.setEndTime(date);
                ma.put("edate", date);
            }
        if (type == null) {
            report.setType("0");
        } else {
            report.setType(type);
        }
        report.setRtpPersonId(personId);
        List<Report> list1 =new ArrayList<Report>();
        List<Report> list = summarizeReportManager.searchReport(report);
            for(Report report2:list){
                if( report2.getDoneWork().length()>20){
                    report2.setDoneWork(report2.getDoneWork().substring(0, 20) + "...");
                }
                if( report2.getUndoneWork().length()>20){
                    report2.setUndoneWork(report2.getUndoneWork().substring(0, 20) + "...");
                }
                if( report2.getTeamWork().length()>20){
                    report2.setTeamWork(report2.getTeamWork().substring(0, 20)+"...");
                }
            }
        ma.put("sEcho", sEcho);
        report.setInitPage(1);
        int s = summarizeReportManager.searchReport(report).size();
        ma.put("iTotalRecords", s);
        ma.put("iTotalDisplayRecords", s);
        ma.put("aaData", list);
        }
        return ma;
    }
    /**
     * 登入日报查询界面根据时间查询本人的数据
     * @param report
     * @return
     */
    @RequestMapping(value = "/queryReportByDate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryReportByDate(Report report,HttpServletRequest request,Date startTime,Date endTime,HttpSession session){
        Map<String,Object> ma= new HashMap<String, Object>();
        String sEcho=request.getParameter("sEcho");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String  personId =user.getId();
        String type=request.getParameter("type");
        Date date=new Date();
        report.setEndTime(date);
        //得到一天的开始时间和结束时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        report.setStartTime(start);
        if (type == null) {
            report.setType("0");
        } else {
            report.setType(type);
        }
        report.setRtpPersonId(personId);
        List<Report> list1 =new ArrayList<Report>();
        List<Report> list = summarizeReportManager.searchReport(report);
        ma.put("sEcho", sEcho);
        report.setInitPage(1);
        int s = summarizeReportManager.searchReport(report).size();
        ma.put("iTotalRecords", s);
        ma.put("iTotalDisplayRecords", s);
        ma.put("aaData", list);
        return ma;
    }
//    /**
//     * 登入日报查询界面根据接收人查询本人的数据
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "queryReportByPersons",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> queryReportByPersons(HttpSession session,SummarizeReportReceive receive,Date startTime,Date endTime,HttpServletRequest request){
//        Map<String,Object> ma= new HashMap<String, Object>();
//        String sEcho=request.getParameter("sEcho");
//        SummarizeReportReceive summarizeReportReceive = new SummarizeReportReceive();
//        PersonManage user = (PersonManage)session.getAttribute("loginUser");
//        String  personId =user.getId();
//        summarizeReportReceive.setRptPersonId(personId);
//        String  receivePersonId=request.getParameter("personId");
//        summarizeReportReceive.setReceivePersonId(receivePersonId);
//        Date date=new Date();
//        Calendar calendar = Calendar.getInstance();
//        if(startTime!=null){
//            calendar.setTime(startTime);
//            calendar.set(Calendar.HOUR_OF_DAY, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            Date start = calendar.getTime();
//            summarizeReportReceive.setStartTime(start);
//            ma.put("sdate", startTime);
//        }else{
//            calendar.setTime(date);
//            calendar.set(Calendar.HOUR_OF_DAY, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            Date start = calendar.getTime();
//            summarizeReportReceive.setStartTime(start);
//            ma.put("sdate", date);
//        }
//        if(endTime!=null){
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//            calendar.add(Calendar.SECOND, -1);
//            Date end = calendar.getTime();
//            summarizeReportReceive.setEndTime(end);
//            ma.put("edate", endTime);
//        }else{
//            summarizeReportReceive.setEndTime(date);
//            ma.put("edate", date);
//        }
//        List<SummarizeReportReceive> list = summarizeReportReceiveManager.queryReportByReceivePersonId(summarizeReportReceive);
//
//
//        for(SummarizeReportReceive srre : list){
//            Report report = summarizeReportManager.queryByReportId(srre.getReportId());
//            srre.setTeamWork(report.getTeamWork());
//            srre.setRptPersonName(report.getRptPersonName());
//            srre.setDoneWork(report.getDoneWork());
//            srre.setUndoneWork(report.getUndoneWork());
//        }
//        ma.put("sEcho", sEcho);
//        int s = list.size();
//        ma.put("iTotalRecords", s);
//        ma.put("iTotalDisplayRecords", s);
//        ma.put("aaData", list);
//        return ma;
//    }

}
