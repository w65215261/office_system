package com.pmcc.soft.unit;

import java.util.Date;

/**
 * 手机端返回审批信息用
 * Created by LvXL on 2016/4/29.
 */
public class AuditData {

    private String groupCode;//审核人员所在组
    private String auditPerson;//审核人员ID
    private String auditPersonName;//审核人员名称
    private Date auditDate;//审核时间
    private String auditStatus;//审核状态(0通过1拒绝)
    private String auditRemark;//审核备注

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    public String getAuditPersonName() {
        return auditPersonName;
    }

    public void setAuditPersonName(String auditPersonName) {
        this.auditPersonName = auditPersonName;
    }

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
