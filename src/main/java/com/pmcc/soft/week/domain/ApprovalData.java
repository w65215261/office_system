package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import java.util.Date;

/**
 * 审批业务数据表
 * Created by wangbin on 2016/4/25.
 */
public class ApprovalData {
    private String id;//ID
    private Integer controlType;//类型(0.文本1.多行文本2.日期区间3.日期4.下拉框)
    private String controlKey;//键名
    private String controlDisplay;//键显示值(下拉框text)
    private String controlValue;//键值(下拉框value)
    private Integer index;//排序
    private Integer itemIndex;//组合控件
    private String rptPersonId;//创建人
    private Date rptDate;//创建时间
    private String approvalHeadId;//主表ID
    private String controlTitle;// 标题

    private String auditRemark;//审批意见

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getControlTitle() {
        return controlTitle;
    }

    public void setControlTitle(String controlTitle) {
        this.controlTitle = controlTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public String getControlKey() {
        return controlKey;
    }

    public void setControlKey(String controlKey) {
        this.controlKey = controlKey;
    }

    public String getControlDisplay() {
        return controlDisplay;
    }

    public void setControlDisplay(String controlDisplay) {
        this.controlDisplay = controlDisplay;
    }

    public String getControlValue() {
        return controlValue;
    }

    public void setControlValue(String controlValue) {
        this.controlValue = controlValue;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(Integer itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getRptPersonId() {
        return rptPersonId;
    }

    public void setRptPersonId(String rptPersonId) {
        this.rptPersonId = rptPersonId;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getApprovalHeadId() {
        return approvalHeadId;
    }

    public void setApprovalHeadId(String approvalHeadId) {
        this.approvalHeadId = approvalHeadId;
    }
}
