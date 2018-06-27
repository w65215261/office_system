package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 任务工时的实体类
 * Created by asus on 2015/11/11.
 */
public class ProjectTaskWorkHour extends DiscussBaseModel {
    private String oid;//ID
    private String taskOid;//任务ID
    private String workHourName;//工时名称
    private Date workHourDate;//工时日期
    private String workHourContent;//工时内容
    private float workHour;//工时

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

    public String getWorkHourName() {
        return workHourName;
    }

    public void setWorkHourName(String workHourName) {
        this.workHourName = workHourName;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getWorkHourDate() {
        return workHourDate;
    }

    public void setWorkHourDate(Date workHourDate) {
        this.workHourDate = workHourDate;
    }

    public String getWorkHourContent() {
        return workHourContent;
    }

    public void setWorkHourContent(String workHourContent) {
        this.workHourContent = workHourContent;
    }

    public float getWorkHour() {
        return workHour;
    }

    public void setWorkHour(float workHour) {
        this.workHour = workHour;
    }
}
