package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.CommonTimeFormat;

import java.util.Date;

/**
 * Created by sunyake on 15/7/8.
 */
public class WorkHour extends BaseModel {
    private String id;
    private String personId;//用户ID
    private Date belongsDate;//工时从属日期
    private String belongsProject;//工时从属项目
    private String workType;//工作性质
    private String workContent;//工作内容
    private String remark;//备注
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private String delFlag;
    private Date createTime;//创建时间

    private long start;
    private long end;

    private Date dateStart;
    private Date dateEnd;

    private PersonManage personManage;

    public PersonManage getPersonManage() {
        return personManage;
    }

    public void setPersonManage(PersonManage personManage) {
        this.personManage = personManage;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Date getBelongsDate() {
        return belongsDate;
    }

    public void setBelongsDate(Date belongsDate) {
        this.belongsDate = belongsDate;
    }

    public String getBelongsProject() {
        return belongsProject;
    }

    public void setBelongsProject(String belongsProject) {
        this.belongsProject = belongsProject;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @JsonSerialize(using = CommonTimeFormat.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @JsonSerialize(using = CommonTimeFormat.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
