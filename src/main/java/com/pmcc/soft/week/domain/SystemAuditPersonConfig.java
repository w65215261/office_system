package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import java.util.Date;

/**
 * 审核人员配置
 * Created by wangbin on 2016/4/25.
 */
public class SystemAuditPersonConfig {
    private String id;//ID
    private Integer businessModel;//业务模块（0.日志1.审核）
    private String businessType;//业务类型编码
    private String businessData;//业务数据
    private String groupCode;//分组
    private String auditPerson;//审核人员ID
    private String auditPersonName;//审核人员名称
    private String auditOrgId;//审核人员机构ID
    private String auditOrgName;//审核人员机构名称
    private String auditOrgCode;//审核人员机构代码
    private String rptPersonId;//创建人
    private String rptPersonName;//创建人名称
    private Date rptDate;//创建时间

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

    public String getAuditOrgId() {
        return auditOrgId;
    }

    public void setAuditOrgId(String auditOrgId) {
        this.auditOrgId = auditOrgId;
    }

    public String getAuditOrgName() {
        return auditOrgName;
    }

    public void setAuditOrgName(String auditOrgName) {
        this.auditOrgName = auditOrgName;
    }

    public String getAuditOrgCode() {
        return auditOrgCode;
    }

    public void setAuditOrgCode(String auditOrgCode) {
        this.auditOrgCode = auditOrgCode;
    }

    public String getRptPersonId() {
        return rptPersonId;
    }

    public void setRptPersonId(String rptPersonId) {
        this.rptPersonId = rptPersonId;
    }

    public String getRptPersonName() {
        return rptPersonName;
    }

    public void setRptPersonName(String rptPersonName) {
        this.rptPersonName = rptPersonName;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }
}
