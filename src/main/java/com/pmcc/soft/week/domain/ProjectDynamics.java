package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.DiscussBaseModel;

import java.util.Date;

/**
 * 动态的实体类
 * Created by asus on 2015/10/22.
 */
public class ProjectDynamics extends DiscussBaseModel {
    private String id;//ID
    private String projectOid;//项目ID
    private String content;//内容
    private String rptPerson;//发布人
    private Integer replyPersonNumber;//回复人数
    private Date rptTime;//发布时间
    private String delFlag;//删除标记

    public Integer getReplyPersonNumber() {
        return replyPersonNumber;
    }

    public void setReplyPersonNumber(Integer replyPersonNumber) {
        this.replyPersonNumber = replyPersonNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
