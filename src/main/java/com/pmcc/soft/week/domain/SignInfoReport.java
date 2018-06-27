package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.CustomDateSerializer;

import java.util.Date;

/**
 * Created by sunyake on 15/10/27.
 * 签到信息
 */
public class SignInfoReport extends DiscussBaseModel {
    private String id;
    private String signPersonId;
    private String signPersonName;
    private String personDuty;
    private Date belongsDate;
    private String signFlag;
    private Date lastSignTime;
    private String lastSignAddress;
    private String orgId;
    private String orgCode;
    private String orgCname;

    public String getOrgCname() {
        return orgCname;
    }

    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname;
    }

    //一天的开始时间
    private Date startTime;
    //一天的结束时间
    private Date endTime;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSignPersonId() {
        return signPersonId;
    }

    public void setSignPersonId(String signPersonId) {
        this.signPersonId = signPersonId;
    }

    public String getSignPersonName() {
        return signPersonName;
    }

    public void setSignPersonName(String signPersonName) {
        this.signPersonName = signPersonName;
    }

    public String getPersonDuty() {
        return personDuty;
    }

    public void setPersonDuty(String personDuty) {
        this.personDuty = personDuty;
    }

    public Date getBelongsDate() {
        return belongsDate;
    }

    public void setBelongsDate(Date belongsDate) {
        this.belongsDate = belongsDate;
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }

    public Date getLastSignTime() {
        return lastSignTime;
    }

    public void setLastSignTime(Date lastSignTime) {
        this.lastSignTime = lastSignTime;
    }

    public String getLastSignAddress() {
        return lastSignAddress;
    }

    public void setLastSignAddress(String lastSignAddress) {
        this.lastSignAddress = lastSignAddress;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
