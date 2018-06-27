package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 群基本信息
 * Created by sunyongxing
 * on 2015/7/16 0016 16:00
 */
public class GroupInfo extends BaseModel {

    private String id;

    private String groupCode;//群号码

    private String groupProfile;//群简介

    private String groupName;//群名称

    private String groupValidation;//加群验证(0：公开，1：验证身份，2：不公开)

    private String rptPerson;//创建人

    private Date rptDate;//创建时间

    private String modifiedPerson;//修改人

    private Date modifiedDate;//修改时间

    private String delFlag;//删除标志

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupProfile() {
        return groupProfile;
    }

    public void setGroupProfile(String groupProfile) {
        this.groupProfile = groupProfile;
    }

    public String getGroupValidation() {
        return groupValidation;
    }

    public void setGroupValidation(String groupValidation) {
        this.groupValidation = groupValidation;
    }

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getModifiedPerson() {
        return modifiedPerson;
    }

    public void setModifiedPerson(String modifiedPerson) {
        this.modifiedPerson = modifiedPerson;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
