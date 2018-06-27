package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import java.util.Date;

/**
 * 日程管理的实体类
 * Created by asus on 2016/01/18.
 */
public class ProgrammeManagement extends DiscussBaseModel {
    private String oid;//日程ID
    private String title;//日程名称
    private Date startTime;//日程开始时间
    private Date endTime;//日程结束时间
    private String className;//日程背景色
    private String icon;//日程图标
    private String userOid;//日程图标
    private String description;//日程描述

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserOid() {
        return userOid;
    }

    public void setUserOid(String userOid) {
        this.userOid = userOid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
