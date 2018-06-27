package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.organization.domain.PersonManage;

import java.util.Date;

/**
 * Created by xuxiaodong on 2015/10/20.
 * 项目实体类
 */
public class Project  extends DiscussBaseModel {
    private String id;
    private String projectName;//项目名称
    private String projectManager;//负责人
    private String approvePersonId; //审批人ID
    private Date startTime;//开始时间
    private Date endTime;//结束日期
    private String projectGoal;//项目目标
    private String visibility;//可见范围
    private String delFlag;//删除标记
    private String rptPerson;//创建人
    private Date rptTime;//创建日期
    private String projectStatus;//项目状态
    private Integer projectSchedule;//创建进度
    private String projectManagerId;//负责人ID

    public String getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(String projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    private PersonManage personManage;

    public PersonManage getPersonManage() {
        return personManage;
    }

    public void setPersonManage(PersonManage personManage) {
        this.personManage = personManage;
    }

    private String approvePersonName;//用于在前台展示审批人的姓名

    public String getApprovePersonName() {
        return approvePersonName;
    }

    public void setApprovePersonName(String approvePersonName) {
        this.approvePersonName = approvePersonName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getApprovePersonId() {
        return approvePersonId;
    }

    public void setApprovePersonId(String approvePersonId) {
        this.approvePersonId = approvePersonId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProjectGoal() {
        return projectGoal;
    }

    public void setProjectGoal(String projectGoal) {
        this.projectGoal = projectGoal;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Integer getProjectSchedule() {
        return projectSchedule;
    }

    public void setProjectSchedule(Integer projectSchedule) {
        this.projectSchedule = projectSchedule;
    }
}

