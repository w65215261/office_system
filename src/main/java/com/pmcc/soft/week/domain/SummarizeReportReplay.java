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
public class SummarizeReportReplay extends BaseModel {
    @JsonView(View.RestView.class)
    private String id;
    @JsonView(View.RestView.class)
    private String reportId;
    @JsonView(View.RestView.class)
    private Integer reportType;
    @JsonView(View.RestView.class)
    private String replyContent;
    @JsonView(View.RestView.class)
    private String replyPersonId;
    @JsonView(View.RestView.class)
    private String replyPersonName;
    @JsonView(View.RestView.class)
    private Date replyDate;

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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyPersonId() {
        return replyPersonId;
    }

    public void setReplyPersonId(String replyPersonId) {
        this.replyPersonId = replyPersonId;
    }

    public String getReplyPersonName() {
        return replyPersonName;
    }

    public void setReplyPersonName(String replyPersonName) {
        this.replyPersonName = replyPersonName;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }
}
