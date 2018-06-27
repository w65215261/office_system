package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.CustomDateSerializer;

import java.util.Date;

/**
 * Created by sunyake on 15/10/27.
 * 签到信息
 */
public class SignInfo extends DiscussBaseModel {
    private String id;
    private String signPersonId;
    private String signAddress;
    private Double x;
    private Double y;
    private Date signTime;
    private String delFlag;
    private Date startTime;//根据时间范围查询用
    private Date endTime;
    private String signPersonName;

    public String getSignPersonName() {
        return signPersonName;
    }

    public void setSignPersonName(String signPersonName) {
        this.signPersonName = signPersonName;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    private PersonManage personManage;

    public PersonManage getPersonManage() {
        return personManage;
    }

    public void setPersonManage(PersonManage personManage) {
        this.personManage = personManage;
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

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
