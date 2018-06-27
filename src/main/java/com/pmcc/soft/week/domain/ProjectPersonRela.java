package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.DiscussBaseModel;

import java.util.Date;

/**
 * 人员表的实体类
 * Created by asus on 2015/11/25.
 */
public class ProjectPersonRela extends DiscussBaseModel {
    private String oid;//ID
    private String projectOid;//项目ID
    private String personOid;//人员ID
    private String delFlag;//删除标记
    private String rptPerson;//填报人
    private Date rptTime;//填报时间
    private String personName;//人员名称

    private String projectName;//项目名称


    private Project project;//项目实体类用于关联查询

    private String approvalPerson;

    public String getApprovalPerson() {
        return approvalPerson;
    }

    public void setApprovalPerson(String approvalPerson) {
        this.approvalPerson = approvalPerson;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public String getPersonOid() {
        return personOid;
    }

    public void setPersonOid(String personOid) {
        this.personOid = personOid;
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
}
