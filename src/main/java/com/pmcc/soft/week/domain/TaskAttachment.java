package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 附件的实体类
 * Created by sunyake on 15/6/26.
 */
public class TaskAttachment extends BaseModel {
    private String id;//ID
    private String taskOid;//任务ID
    private String fileName;//文件名称
    private String fileUrl;//文件路径
    private String fileMathName;//文件加密名称
    private Date createDate;//创建日期
    private String creatorId;
    private Date modifyDate;//修改日期
    private String modifyId;//修改ID
    private String delFlag;//删除标记
    private String workHourOid;//工时ID
    private String experienceOid;//心得ID
    private String attachmentReadFlag;//读取附件的标识符
    private String projectOid;//项目ID
    private String rptPerson;//发布人

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }

    public String getAttachmentReadFlag() {
        return attachmentReadFlag;
    }

    public void setAttachmentReadFlag(String attachmentReadFlag) {
        this.attachmentReadFlag = attachmentReadFlag;
    }

    public String getWorkHourOid() {
        return workHourOid;
    }

    public void setWorkHourOid(String workHourOid) {
        this.workHourOid = workHourOid;
    }

    public String getExperienceOid() {
        return experienceOid;
    }

    public void setExperienceOid(String experienceOid) {
        this.experienceOid = experienceOid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskOid() {
        return taskOid;
    }

    public void setTaskOid(String taskOid) {
        this.taskOid = taskOid;
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
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
