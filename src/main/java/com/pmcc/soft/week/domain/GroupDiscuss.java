package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 群讨论
 * Created by sunyongxing
 * on 2015/7/17 0017 9:25
 */
public class GroupDiscuss extends DiscussBaseModel {

    private String id;

    private String groupDiscussOid;//主贴Oid(如果为主贴，此字段为空)

    private String groupInfoOid;//群oid

    private String discussType;//讨论类型(0：主贴，1：跟帖)

    private String discussTitle;//讨论标题

    private String discussion;//讨论内容

    private Integer browseNumber;//浏览人数

    private Integer replyPersonNumber;//回复人数

    private String rptPerson;//发布人

    private Date rptDate;//发布时间

    private String modifiedPerson;//修改人

    private Date modifiedDate;//修改时间

    private String lockFlag;//是否锁定(0：未锁定，1：已锁定)

    private String delFlag;//删除标志(0：未删除，1：已删除)

    private  String groupName;

    private String rptPersonName;

    public String getRptPersonName() {
        return rptPersonName;
    }

    public void setRptPersonName(String rptPersonName) {
        this.rptPersonName = rptPersonName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupDiscussOid() {
        return groupDiscussOid;
    }

    public void setGroupDiscussOid(String groupDiscussOid) {
        this.groupDiscussOid = groupDiscussOid;
    }

    public String getGroupInfoOid() {
        return groupInfoOid;
    }

    public void setGroupInfoOid(String groupInfoOid) {
        this.groupInfoOid = groupInfoOid;
    }

    public String getDiscussType() {
        return discussType;
    }

    public void setDiscussType(String discussType) {
        this.discussType = discussType;
    }

    public String getDiscussTitle() {
        return discussTitle;
    }

    public void setDiscussTitle(String discussTitle) {
        this.discussTitle = discussTitle;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public Integer getBrowseNumber() {
        return browseNumber;
    }

    public void setBrowseNumber(Integer browseNumber) {
        this.browseNumber = browseNumber;
    }

    public Integer getReplyPersonNumber() {
        return replyPersonNumber;
    }

    public void setReplyPersonNumber(Integer replyPersonNumber) {
        this.replyPersonNumber = replyPersonNumber;
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

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
