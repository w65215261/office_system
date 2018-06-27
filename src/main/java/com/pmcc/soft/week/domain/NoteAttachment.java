package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 笔记附件的实体类
 * Created by wangbin on 16/3/25.
 */
public class NoteAttachment extends BaseModel {
    private String id;//ID
    private String noteOid;//笔记ID
    private String fileName;//文件名称
    private String fileUrl;//文件路径
    private String fileMathName;//文件加密名称
    private Date createDate;//创建日期
    private String creatorId;
    private Date modifyDate;//修改日期
    private String modifyId;//修改ID
    private String delFlag;//删除标记
    private String rptPerson;//发布人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteOid() {
        return noteOid;
    }

    public void setNoteOid(String noteOid) {
        this.noteOid = noteOid;
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

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
    }
}
