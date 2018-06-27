package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateTimeFormat;

import java.util.Date;

/**
 * Created by asus on 2015/11/26.
 */
public class OperationRecord extends DiscussBaseModel {
    private String oid;
    private String operationType;
    private String operationContent;
    private String taskOid;
    private String projectOid;
    private Date operationTime;
    private String operationPersonOid;
    private String workHourOid;
    private String operationPersonName;

    private String modifyDate;
    private String modifyTime;
    private String oldContent;
    private String newContent;
    private String distinguish;//区分

    public String getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
    }

    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getTaskOid() {
        return taskOid;
    }

    public void setTaskOid(String taskOid) {
        this.taskOid = taskOid;
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }
    @JsonSerialize(using = CommonDateTimeFormat.class)
    public Date getOperationTime() {
        return operationTime;
    }
    @JsonSerialize(using = CommonDateTimeFormat.class)
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationPersonOid() {
        return operationPersonOid;
    }

    public void setOperationPersonOid(String operationPersonOid) {
        this.operationPersonOid = operationPersonOid;
    }

    public String getWorkHourOid() {
        return workHourOid;
    }

    public void setWorkHourOid(String workHourOid) {
        this.workHourOid = workHourOid;
    }

    public String getOperationPersonName() {
        return operationPersonName;
    }

    public void setOperationPersonName(String operationPersonName) {
        this.operationPersonName = operationPersonName;
    }
}
