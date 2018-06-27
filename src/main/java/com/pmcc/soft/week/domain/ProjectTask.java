package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 任务的实体类
 * Created by asus on 2015/10/21.
 */
public class ProjectTask extends DiscussBaseModel {
    private String taskOidFlag;//任务ID标识符
    private String workHourOidFlag;//工时ID标识符
    private String experienceOidFlag;//心得ID标识符
    private String oid;//ID
    private String projectOid;//所属项目
    private String taskName;//任务名称
    private String responsiblePersonOid;//负责人ID
    private String responsiblePersonName;//负责人名字
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private String approvalPerson;//审批人
    private String taskContent;//任务内容
    private String rptPerson;//发布人
    private Date rptTime;//发布时间
    private String taskStatus;//任务状态
    private String taskLevel;//任务层级
    private String parentTaskOid;//父任务ID
    private String hasStar;//是否为星标任务
    private String planTypeOid;//计划类别
    private String planTimeType;//计划时间类别
    private String year;//年
    private String month;//月
    private String week;//周
    private String quarter;//季度
    private String taskCount;//完成数

    private  Date firstDate;
    private Date lastDate;
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getFirstDate() {
        return firstDate;
    }
    @JsonSerialize(using = CommonDateFormat.class)
    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(String taskCount) {
        this.taskCount = taskCount;
    }

    public String getWorkHourOidFlag() {
         return workHourOidFlag;
    }

    public void setWorkHourOidFlag(String workHourOidFlag) {
        this.workHourOidFlag = workHourOidFlag;
    }

    public String getExperienceOidFlag() {
        return experienceOidFlag;
    }

    public void setExperienceOidFlag(String experienceOidFlag) {
        this.experienceOidFlag = experienceOidFlag;
    }

    public String getTaskOidFlag() {
        return taskOidFlag;
    }

    public void setTaskOidFlag(String taskOidFlag) {
        this.taskOidFlag = taskOidFlag;
    }

    public String getHasStar() {
        return hasStar;
    }

    public void setHasStar(String hasStar) {
        this.hasStar = hasStar;
    }

    public String getPlanTypeOid() {
        return planTypeOid;
    }

    public void setPlanTypeOid(String planTypeOid) {
        this.planTypeOid = planTypeOid;
    }

    public String getPlanTimeType() {
        return planTimeType;
    }

    public void setPlanTimeType(String planTimeType) {
        this.planTimeType = planTimeType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getResponsiblePersonOid() {
        return responsiblePersonOid;
    }

    public void setResponsiblePersonOid(String responsiblePersonOid) {
        this.responsiblePersonOid = responsiblePersonOid;
    }
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getApprovalPerson() {
        return approvalPerson;
    }

    public void setApprovalPerson(String approvalPerson) {
        this.approvalPerson = approvalPerson;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(String taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getParentTaskOid() {
        return parentTaskOid;
    }

    public void setParentTaskOid(String parentTaskOid) {
        this.parentTaskOid = parentTaskOid;
    }
}
