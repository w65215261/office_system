package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.OrganizationInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganPersonRelationManager;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SignInfoReport;
import com.pmcc.soft.week.manager.SignInfoReportManager;
import com.pmcc.soft.week.manager.SummarizeReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sunyake on 15/10/27.
 * <p/>
 * modifed by zhangyanchang on 2016/4/19.
 * 1.修正类名 SignInfoReportController -> ScheduleTaskMoudle.
 * 2.修正方法名 test -> initSignInfoReportData.
 */
@Component
public class ScheduleTaskMoudle {

    @Autowired
    private PersonManageManager        personManageManager;
    @Autowired
    private OrganPersonRelationManager organPersonRelationManager;
    @Autowired
    private OrganizationInfoManager    organizationInfoManager;
    @Autowired
    private SignInfoReportManager      signInfoReportManager;
    @Autowired
    private SummarizeReportManager     summarizeReportManager;

    /**
     * 初始化签到数据
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void initSignInfoReportData() {
        System.out.println("init initSignInfoReportData(cron=0 30 0 * * ?) start...");
        PersonManage personManage = new PersonManage();
        SignInfoReport signInfoReport = new SignInfoReport();
        personManage.setInitPage(1);
        List<PersonManage> personList = personManageManager.query(personManage);
        for (PersonManage personManage1 : personList) {
            String orgId = organPersonRelationManager.findOrgByPersonId(personManage1.getId()).getOrganizationInfoId();
            OrganizationInfo organizationInfo = organizationInfoManager.queryOrgByOrgId(orgId);
            signInfoReport.setId(UUIDGenerator.getUUID());
            signInfoReport.setSignPersonId(personManage1.getId());
            signInfoReport.setSignPersonName(personManage1.getUserCname());
            signInfoReport.setPersonDuty(personManage1.getDuty());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            signInfoReport.setBelongsDate(calendar.getTime());
            signInfoReport.setSignFlag("0");
            signInfoReport.setOrgId(orgId);
            signInfoReport.setOrgCode(organizationInfo.getOrgCode());
            signInfoReport.setOrgCname(organizationInfo.getOrgCname());
            signInfoReportManager.insertSignInfoReport(signInfoReport);
        }
        System.out.println("init initSignInfoReportData(cron=0 30 0 * * ?) end...");
    }


    /**
     * 初始化日志->日报数据
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void initDayWorkData() {
        System.out.println("init initDayWorkData(cron=0 30 0 * * ?) start...");
        PersonManage personManage = new PersonManage();
        SignInfoReport signInfoReport = new SignInfoReport();
        personManage.setInitPage(1);
        List<PersonManage> personList = personManageManager.query(personManage);
        for (PersonManage personManage1 : personList) {
            String orgId = organPersonRelationManager.findOrgByPersonId(personManage1.getId()).getOrganizationInfoId();
            OrganizationInfo organizationInfo = organizationInfoManager.queryOrgByOrgId(orgId);
            summarizeReportManager.insert(setReportData(personManage1, organizationInfo));
        }
        System.out.println("init initDayWorkData(cron=0 30 0 * * ?) end...");
    }


    // 获取当天初始日期
    private Date getInitDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    // 获取封装Report对象
    private Report setReportData(PersonManage personManage1, OrganizationInfo organizationInfo) {
        Report report = new Report();
        report.setId(UUIDGenerator.getUUID());
        report.setStatus("0");
        report.setBelongsDate(getInitDate());
        report.setType("0");
        report.setRptDate(getInitDate());
        report.setRtpPersonId(personManage1.getId());
        report.setSource("0");
        report.setInitFlag("0");
        report.setReadCount("0");
        report.setReplayCount("0");
        report.setOrgId(organizationInfo.getId());
        report.setOrgCode(organizationInfo.getOrgCode());
        report.setRptPersonName(personManage1.getUserCname());
        return report;
    }
}
