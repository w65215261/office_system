package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务树的实体类
 * Created by asus on 2015/10/28.
 */
public class TreeTask {
    private String oid;//ID
    private String taskName;//任务名称
    private String responsiblePersonName;//负责人
    private Date endTime;//结束时间
    private String taskStatus;//任务状态
    private String hasStar;//星标

    public String getHasStar() {
        return hasStar;
    }

    public void setHasStar(String hasStar) {
        this.hasStar = hasStar;
    }

    private List<TreeTask> tasks;

    public TreeTask(){
        this.tasks=new ArrayList<TreeTask>();
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<TreeTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<TreeTask> tasks) {
        this.tasks = tasks;
    }
}
