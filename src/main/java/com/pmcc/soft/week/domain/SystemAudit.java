package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 系统审核
 * Created by wangbin on 2016/4/25.
 */
public class SystemAudit {
    private String id;//ID
    private Integer businessModel;//业务模块（0.日志1.审核）
    private String businessType;//业务类型（0.请假.....待补充）
    private String businessData;//业务数据ID
    private String auditPerson;//审核人
    private Date auditDate;//审核时间
    private String auditStatus;//审核状态(1通过2拒绝)
    private String auditRemark;//审核备注

    private String auditPersonCname;//审核人中文名

    /* 工作流 */
    private String taskId;

    public String getAuditPersonCname() {
        return auditPersonCname;
    }

    public void setAuditPersonCname(String auditPersonCname) {
        this.auditPersonCname = auditPersonCname;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(Integer businessModel) {
        this.businessModel = businessModel;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }
}
