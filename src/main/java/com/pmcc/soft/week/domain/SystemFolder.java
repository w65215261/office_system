package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;
import com.pmcc.soft.core.utils.CommonUtils;

import java.util.Date;

/**
 * 文件夹表
 * Created by LvXL on 2016/5/9.
 */
public class SystemFolder {

    private String id;//
    private String folderName;//文件夹名称
    private String folderCode;//文件夹编码
    private String folderType;//文件夹类型，0：公共(仅管理员可操作)；1：个人(仅自己操作)
    private Long folderTotalSize;//文件夹总容量(B)
    private Long folderUsedSize;//文件夹已用容量(B)
    private String rptPerson;//创建人id
    private String rptPersonName;//创建人名称
    private Date rptDate;//创建时间
    private String orgName;//机构名称
    private String orgCode;//机构编码
    private String orgId;//机构ID
    private String companyId;//公司ID
    private String companyName;//公司名称
    private String delFlag;//删除标记

    /* 用于手机端显示容量(带单位) */
    private String totalSizeShow;//文件夹总容量
    private String usedSizeShow;//文件夹已用容量

    public String getTotalSizeShow() {
        if(folderTotalSize != null && folderTotalSize > 0){
            return CommonUtils.getPrintSize(folderTotalSize);
        }
        return totalSizeShow;
    }

    public void setTotalSizeShow(String totalSizeShow) {
        this.totalSizeShow = totalSizeShow;
    }

    public String getUsedSizeShow() {
        if(folderUsedSize != null && folderUsedSize > 0){
            return CommonUtils.getPrintSize(folderUsedSize);
        }
        return folderUsedSize + "";
    }

    public void setUsedSizeShow(String usedSizeShow) {
        this.usedSizeShow = usedSizeShow;
    }

    public SystemFolder() {
    }

    public SystemFolder(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderCode() {
        return folderCode;
    }

    public void setFolderCode(String folderCode) {
        this.folderCode = folderCode;
    }

    public String getFolderType() {
        return folderType;
    }

    public void setFolderType(String folderType) {
        this.folderType = folderType;
    }

    public Long getFolderTotalSize() {
        return folderTotalSize;
    }

    public void setFolderTotalSize(Long folderTotalSize) {
        this.folderTotalSize = folderTotalSize;
    }

    public Long getFolderUsedSize() {
        return folderUsedSize;
    }

    public void setFolderUsedSize(Long folderUsedSize) {
        this.folderUsedSize = folderUsedSize;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
