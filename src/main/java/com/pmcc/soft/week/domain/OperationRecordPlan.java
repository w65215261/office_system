package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.DiscussBaseModel;

import java.util.Date;

/**
 * Created by xuxiaodong on 2015/12/07.
 * 计划操作记录的实体类
 */
public class OperationRecordPlan extends DiscussBaseModel {
    private String oid;
    private String operationType;
    private String operationContent;//操作详情
    private String taskOid;//计划OID
    private String projectOid;//项目OID
    private Date operationTime;//创建世界
    private String operationPersonOid;//创建人id
    private String workHourOid;//工时OID
    private String operationPersonName;//创建人
//下面用于前台展示的字段
    private String modifyDate;//修改日期 年月日
    private String modifyTime;//修改时间 时分
    private String oldContent;//修改内容的老值
    private String newContent;//修改内容的新值
    private String distinguish;//区分

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

    public String getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
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

    public Date getOperationTime() {
        return operationTime;
    }

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
