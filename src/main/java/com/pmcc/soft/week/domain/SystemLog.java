package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import java.util.Date;

/**
 * 系统操作记录表
 * Created by LvXL on 2016/5/9.
 */
public class SystemLog extends BaseModel{

    private String id;//
    private String operateDesc;//操作描述
    private String ids;//IDS
    private String rptPerson;//操作人id
    private String rptPersonName;//操作人名称
    private Date rptDate;//操作时间
    private String orgName;//机构名称
    private String orgCode;//机构编码
    private String orgId;//机构ID
    private String companyId;//公司ID
    private String companyName;//公司名称
    private String ipAddress;//访问IP
    private String macAddress;//访问MAC
    private String imei;//手机串号
    private String source;//来源（0.pc；1.iOS；2.android）
    private String businessModel;//业务模块
    private String businessType;//业务类型
    private String result;//访问返回结果
    private String parameters;//访问参数
    private String folderId;//文件夹ID
    private String folderType;//文件夹类型
    private String folderCode;//文件夹编码

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFolderType() {
        return folderType;
    }

    public void setFolderType(String folderType) {
        this.folderType = folderType;
    }

    public String getFolderCode() {
        return folderCode;
    }

    public void setFolderCode(String folderCode) {
        this.folderCode = folderCode;
    }
}
