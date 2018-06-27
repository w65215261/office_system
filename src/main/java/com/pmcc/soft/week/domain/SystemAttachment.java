package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;
import com.pmcc.soft.core.utils.CommonUtils;

import java.util.Date;
import java.util.List;

/**
 * 系统附件
 * Created by wangbin on 2016/4/11.
 */
public class SystemAttachment extends BaseModel {
    @JsonView(View.RestView.class)
    private String id;
    private String businessModel;
    private String businessType;
    @JsonView(View.RestView.class)
    private String fileName;
    @JsonView(View.RestView.class)
    private String fileUrl;
    private String fileMathName;
    @JsonView(View.RestView.class)
    private String fileType;
    @JsonView(View.RestView.class)
    private String businessData;
    private Integer downloadCount;
    private String orgName;
    private String orgCode;
    private String orgId;
    @JsonView(View.RestView.class)
    private String rptPerson;
    private String rptPersonName;
    private Integer delFlag;
    @JsonView(View.RestView.class)
    private Date rptDate;
    private String tempBusinessData;
    @JsonView(View.RestView.class)
    private Long fileSize;// 文件大小(B)

    /* 手机云盘查询文件用 */
    private String personId;
    private String folderId;
    private String ordFlag = "0";
    private List<String> ids;
    private List<String> typeList;
    private String type;

    /* 用于手机端显示容量(带单位) */
    @JsonView(View.RestView.class)
    private String sizeShow;// 文件大小
    @JsonView(View.RestView.class)
    private int total;// 数据总数

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSizeShow() {
        if(fileSize != null && fileSize > 0){
            return CommonUtils.getPrintSize(fileSize);
        }
        return sizeShow;
    }

    public void setSizeShow(String sizeShow) {
        this.sizeShow = sizeShow;
    }

    public String getOrdFlag() {
        return ordFlag;
    }

    public void setOrdFlag(String ordFlag) {
        this.ordFlag = ordFlag;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileMathName() {
        return fileMathName;
    }

    public void setFileMathName(String fileMathName) {
        this.fileMathName = fileMathName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
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

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPersion) {
        this.rptPerson = rptPersion;
    }

    public String getRptPersonName() {
        return rptPersonName;
    }

    public void setRptPersonName(String rptPersionName) {
        this.rptPersonName = rptPersionName;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getTempBusinessData() {
        return tempBusinessData;
    }

    public void setTempBusinessData(String tempBusinessData) {
        this.tempBusinessData = tempBusinessData;
    }
}
