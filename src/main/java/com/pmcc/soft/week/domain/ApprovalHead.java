package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/4/25.
 */
public class ApprovalHead  extends BaseModel {

    private String id;//ID
    private String type;//审批类型编码
    private String workFlowId;//工作流主键
    private String auditStatus;//审核状态(0未1同意2拒绝3.进行中)
    private String dataBlock;//业务数据（JSON字符串）
    private String orgId;//机构ID
    private String orgCode;//机构代码
    private String orgName;//机构名称
    private String rptPersonId;//创建人
    private String rptPersonName;//创建人名称
    private String source;//来源（0.pc/1.iOS/2.android）
    private Date rptDate;//创建时间
    private List<String> idList;
    private List<String> personIdList;
    private String taskId;
    private String templateCode;
    private String startDate;//创建时间
    private String endDate;//创建时间

    public List<String> getPersonIdList() {
        return personIdList;
    }

    public void setPersonIdList(List<String> personIdList) {
        this.personIdList = personIdList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    /* 用于手机端展示图片用 */
    private List<String> urlList;

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public ApprovalHead(String id) {
        this.id = id;
    }

    public ApprovalHead() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDataBlock() {
        return dataBlock;
    }

    public void setDataBlock(String dataBlock) {
        this.dataBlock = dataBlock;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
