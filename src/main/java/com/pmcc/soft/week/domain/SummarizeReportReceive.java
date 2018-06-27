package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import java.util.Date;

/**
 * Created by wangbin on 2016/4/11.
 */
public class SummarizeReportReceive extends BaseModel {
    @JsonView(View.RestView.class)
    private String  id;
    @JsonView(View.RestView.class)
    private String  reportId;
    @JsonView(View.RestView.class)
    private Integer reportType;
    @JsonView(View.RestView.class)
    private String  receivePersonId;
    @JsonView(View.RestView.class)
    private String  receivePersonName;
    @JsonView(View.RestView.class)
    private Integer readFlag;
    @JsonView(View.RestView.class)
    private String  orgId;
    @JsonView(View.RestView.class)
    private String  orgCode;

    private Date startTime;

    private Date endTime;

    private Date rptDate;

    private String rptPersonId;
    private String doneWork;//完成工作
    private String undoneWork;//未完成工作
    private String teamWork;//需协调工作
    private String rptPersonName;//填报人名字

    public String getDoneWork() {
        return doneWork;
    }

    public void setDoneWork(String doneWork) {
        this.doneWork = doneWork;
    }

    public String getUndoneWork() {
        return undoneWork;
    }

    public void setUndoneWork(String undoneWork) {
        this.undoneWork = undoneWork;
    }

    public String getTeamWork() {
        return teamWork;
    }

    public void setTeamWork(String teamWork) {
        this.teamWork = teamWork;
    }

    public String getRptPersonName() {
        return rptPersonName;
    }

    public void setRptPersonName(String rptPersonName) {
        this.rptPersonName = rptPersonName;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReceivePersonId() {
        return receivePersonId;
    }

    public void setReceivePersonId(String receivePersonId) {
        this.receivePersonId = receivePersonId;
    }

    public String getReceivePersonName() {
        return receivePersonName;
    }

    public void setReceivePersonName(String receivePersonName) {
        this.receivePersonName = receivePersonName;
    }

    public Integer getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }
    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getRptPersonId() {
        return rptPersonId;
    }

    public void setRptPersonId(String rptPersonId) {
        this.rptPersonId = rptPersonId;
    }
}
