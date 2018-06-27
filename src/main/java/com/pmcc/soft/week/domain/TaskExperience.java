package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 心得的实体类
 * Created by asus on 2015/11/13.
 */
public class TaskExperience extends DiscussBaseModel {
    private String oid;//心得ID
    private String taskOid;//任务Id
    private String experienceContent;//心得内容
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private String fillInPerson;//填写人

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTaskOid() {
        return taskOid;
    }

    public void setTaskOid(String taskOid) {
        this.taskOid = taskOid;
    }

    public String getExperienceContent() {
        return experienceContent;
    }

    public void setExperienceContent(String experienceContent) {
        this.experienceContent = experienceContent;
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

    public String getFillInPerson() {
        return fillInPerson;
    }

    public void setFillInPerson(String fillInPerson) {
        this.fillInPerson = fillInPerson;
    }
}
