package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.DiscussBaseModel;

import java.util.Date;

/**
 * 群讨论回复
 * Created by sunyongxing
 * on 2015/7/20 0020 16:26
 */
public class ReplyDiscuss extends DiscussBaseModel {

    private String id;

    private String groupDiscussOid;//主贴oid/跟帖oid

    private String replyType;//回复类型(0：主贴；1：跟帖)

    private String reply;//回复内容

    private String peplyOid;//回复人

    private Date replyDate;//回复时间

    private String modifiedPerson;//修改人

    private String modifiedDate;//修改时间

    private String delFlag;//删除标志

    private String peplyName;//回复人名称

    public String getPeplyName() {
        return peplyName;
    }

    public void setPeplyName(String peplyName) {
        this.peplyName = peplyName;
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

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getPeplyOid() {
        return peplyOid;
    }

    public void setPeplyOid(String peplyOid) {
        this.peplyOid = peplyOid;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getModifiedPerson() {
        return modifiedPerson;
    }

    public void setModifiedPerson(String modifiedPerson) {
        this.modifiedPerson = modifiedPerson;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
