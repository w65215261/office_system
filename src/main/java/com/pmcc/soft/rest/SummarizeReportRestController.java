package com.pmcc.soft.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonInfoManageManager;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.DateJsonValueProcessor;
import com.pmcc.soft.core.utils.JsonUtils;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SummarizeReportReceive;
import com.pmcc.soft.week.domain.SummarizeReportReplay;
import com.pmcc.soft.week.domain.SystemAttachment;
import com.pmcc.soft.week.manager.SummarizeReportManager;
import com.pmcc.soft.week.manager.SummarizeReportReceiveManager;
import com.pmcc.soft.week.manager.SummarizeReportReplayManager;
import com.pmcc.soft.week.manager.SystemAttachmentManager;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by wangbin on 2016/4/11.
 */
@Controller
@RequestMapping("/summarizeReport")
public class SummarizeReportRestController {

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

    /**
     * 插入一条日志
     * @param report
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String save(Report report,HttpServletRequest request) {
        String receivePersonId = request.getParameter("receivePersonId");
        String reportId = request.getParameter("uuid");
        String personId = request.getParameter("personId");
        Report rt = new Report();
        Date nowTime = new Date();

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

        if(reportList.size()>0){
            reportId = reportList.get(0).getId();
        }else{
            reportId = request.getParameter("uuid");
        }
        report.setId(reportId);
        report.setStatus("0");
        report.setBelongsDate(new Date());
        report.setType("0");
        report.setRptDate(new Date());
        report.setRtpPersonId(personId);
        report.setInitFlag("1");
        report.setReadCount("0");
        report.setReplayCount("0");

        int res = 0;
        int rows = 0;
        if(reportList.size()>0){
            //1.变更初始化数据
            rows = summarizeReportManager.update(report);

        }else{
            report.setInitFlag("1");
            report.setBelongsDate(new Date());
            rows = summarizeReportManager.insert(report);
        }
        if(res==rows){
            return "fail";
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
                return "fail";
            }
            //加入推送
            Map<String, String> extras = new HashMap<String, String>();
            extras.put("type", CommonVariables.BUSINESSMODEL_RZ);
            extras.put("report", new Gson().toJson(report));

            // 安卓
            JPush.sendPushToAndroidWithAlias(receivePersonIds[i], CommonVariables.SendPushToAndroid_Report_Alert, CommonVariables.SendPushToAndroid_Title, extras);

            // IOS
            JSONObject object = JSONObject.fromObject(report, JsonUtils.configJson("yyyy-MM-dd HH:mm:ss"));
            extras.put("report", object.toString());
            JPush.sendPushToIOSWithAlias(receivePersonIds[i],CommonVariables.SendPushToAndroid_Report_Alert,CommonVariables.SendPushToAndroid_Title, extras);
        }

        return "success";
    }

    @RequestMapping(value = "/createUuid", method = RequestMethod.POST)
    @ResponseBody
    public String createUuid(HttpServletRequest request) {
        String type = request.getParameter("type");
        String personId = request.getParameter("personId");
        if(type != null && type.equals("0")){
            Report rt = new Report();
            Date nowTime = new Date();
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
            List<Report> reportList = summarizeReportManager.queryByBelongsDate(rt);
            if (reportList.size()>0){
                return  reportList.get(0).getId();
            }else{
                return  UUIDGenerator.getUUID();
            }
        }
        return  UUIDGenerator.getUUID();
    }

    /**
     * 查看我接收的所有日志
     * @param request
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryReceive")
    @ResponseBody
    public List<Report> queryReceive(HttpServletRequest request) {
        String personId = request.getParameter("personId");
        SummarizeReportReceive summarizeReportReceive = new SummarizeReportReceive();
        summarizeReportReceive.setReceivePersonId(personId);
        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        summarizeReportReceive.setOrgId(pm.getCompanyId());
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
            reports.add(report);
        }

        return reports;
    }

    /**
     * 查询我发布的所有日志
     * @param request
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryRpt")
    @ResponseBody
    public List<Report> queryRpt(HttpServletRequest request) {
        String personId = request.getParameter("personId");

        PersonInfoManage personInfoManage = new PersonInfoManage();
        personInfoManage.setId(personId);
        PersonInfoManage pm = personInfoManageManager.queryByOid(personInfoManage);
        Report rt = new Report();
        if(pm != null){
            OrganizationInfo of = organizationInfoManager.queryOrgByOrgId(pm.getCompanyId());
            rt.setOrgId(of.getId());
        }
        rt.setRtpPersonId(personId);

        List<Report> rts =summarizeReportManager.queryByRtpPersonId(rt);
        for(Report report : rts){
            report.setReadStatus("-1");
            SystemAttachment systemAttachment = new SystemAttachment();
            systemAttachment.setBusinessData(report.getId());
            systemAttachment.setOrgId(report.getOrgId());
            int attachmentNum = systemAttachmentManager.queryByReportId(systemAttachment).size();
            report.setAttachmentNum(attachmentNum);
        }
        return rts;
    }

    /**
     * 查看日志详情
     * @param request
     * @return
     */
    @JsonView(View.RestView.class)
    @RequestMapping(value = "/queryDetails")
    @ResponseBody
    public Map<String,Object> queryDetails(HttpServletRequest request) {
        String reportId = request.getParameter("reportId");

        //区分是我发出OR我接受的FLAG
        String flag = request.getParameter("flag");

        //查找登陆人所对应的公司
        String personId = request.getParameter("personId");
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
        for (SystemAttachment systemAttachment : sas) {
            systemAttachment.setFileUrl("/appUploadPath"+systemAttachment.getFileUrl());
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("srres",srres);
        map.put("srresAll",srresAll);
        map.put("srrys",srrys);
        map.put("sas",sas);
        return map;
    }

    /**
     * 回复
     * @param replay
     * @return
     */
    @RequestMapping(value = "/replay", method = RequestMethod.POST)
    @ResponseBody
    public String save(SummarizeReportReplay replay) {
        String personId = replay.getReplyPersonId();
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
        return "success";
    }

}
