package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.CommonDateFormat;
import com.pmcc.soft.core.utils.CommonDateTimeFormatHengHorizontal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by H on 2015/11/26.
 */
public class Report extends BaseModel {

    @JsonView(View.RestView.class)
    private String id;
    @JsonView(View.RestView.class)
    private String content;//备注

    private String visiblity;//可见性
    private String status;//状态
    @JsonView(View.RestView.class)
    private Date belongsDate;//所属时间
    @JsonView(View.RestView.class)
    private Date rptDate;//填报时间
    @JsonView(View.RestView.class)
    private String rtpPersonId;//填报人ID
    @JsonView(View.RestView.class)
    private String rptPersonName;//填报人名字
    private int week;//星期几
    private int weekNum;//第几周
    @JsonView(View.RestView.class)
    private String type;//类型,0日报,1周报,2月报
    private String weekDate;//保存周报时存入的月标识
    @JsonView(View.RestView.class)
    private String orgId;
    @JsonView(View.RestView.class)
    private Date startTime;//根据时间范围查询用
    @JsonView(View.RestView.class)
    private Date endTime;
    @JsonView(View.RestView.class)
    private String orgCode;
    @JsonView(View.RestView.class)
    private String x;//x轴坐标
    @JsonView(View.RestView.class)
    private String y;//坐标Y
    @JsonView(View.RestView.class)
    private String xyAddress;//地址详情
    @JsonView(View.RestView.class)
    private String doneWork;//完成工作
    @JsonView(View.RestView.class)
    private String undoneWork;//未完成工作
    @JsonView(View.RestView.class)
    private String teamWork;//需协调工作
    @JsonView(View.RestView.class)
    private String source;//来源（0.pc/1.iOS/2.android）
    @JsonView(View.RestView.class)
    private String replayCount;//回复数量
    @JsonView(View.RestView.class)
    private String readCount;//已读人数
    @JsonView(View.RestView.class)
    private String initFlag;//是否初始(0.是/1.否)
    @JsonView(View.RestView.class)
    private String readStatus;//日志状态
    @JsonView(View.RestView.class)
    private Integer attachmentNum;//附件数量



    public Integer getAttachmentNum() {
        return attachmentNum;
    }

    public void setAttachmentNum(Integer attachmentNum) {
        this.attachmentNum = attachmentNum;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getXyAddress() {
        return xyAddress;
    }

    public void setXyAddress(String xyAddress) {
        this.xyAddress = xyAddress;
    }

    public String getDoneWork() {
        return doneWork;
    }

    public void setDoneWork(String doneWork) {
        this.doneWork = doneWork;
    }

    public String getUndoneWork() {
        return undoneWork;
    }

    public void setUndoneWork(String undoneWork) {
        this.undoneWork = undoneWork;
    }

    public String getTeamWork() {
        return teamWork;
    }

    public void setTeamWork(String teamWork) {
        this.teamWork = teamWork;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(String replayCount) {
        this.replayCount = replayCount;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getInitFlag() {
        return initFlag;
    }

    public void setInitFlag(String initFlag) {
        this.initFlag = initFlag;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    private List<String> list = new ArrayList<String>();

    private List<String> personList = new ArrayList<String>();

    public List<String> getPersonList() {
        return personList;
    }

    public void setPersonList(List<String> personList) {
        this.personList = personList;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private long start;
    private long end;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    private PersonManage personManage;

    public PersonManage getPersonManage() {
        return personManage;
    }

    public void setPersonManage(PersonManage personManage) {
        this.personManage = personManage;
    }

    private Date firstDate;
    private Date lastDate;

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVisiblity() {
        return visiblity;
    }

    public void setVisiblity(String visiblity) {
        this.visiblity = visiblity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getBelongsDate() {
        return belongsDate;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public void setBelongsDate(Date belongsDate) {
        this.belongsDate = belongsDate;
    }

    @JsonSerialize(using = CommonDateTimeFormatHengHorizontal.class)
    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getRtpPersonId() {
        return rtpPersonId;
    }

    public void setRtpPersonId(String rtpPersonId) {
        this.rtpPersonId = rtpPersonId;
    }

    public String getRptPersonName() {
        return rptPersonName;
    }

    public void setRptPersonName(String rptPersonName) {
        this.rptPersonName = rptPersonName;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public String getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }
}
