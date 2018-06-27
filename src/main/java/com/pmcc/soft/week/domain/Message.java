package com.pmcc.soft.week.domain;

/**
 * Created by sunyake on 15/8/11.
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;


public class Message extends DiscussBaseModel {
    private String id;
    private String messageTitle;
    private String messageContent;
    private String messageToName;
    private String messageFromName;
    private String messageFrom;
    private String messageTo;
    private Date createDate;
    private String delFlag;
    private String showFlag;
    private String messageFromFlag;
    private String messageToFlag;

    private String dateFrom;
    private String dateTo;

    public String getMessageFromFlag() {
        return messageFromFlag;
    }

    public void setMessageFromFlag(String messageFromFlag) {
        this.messageFromFlag = messageFromFlag;
    }

    public String getMessageToFlag() {
        return messageToFlag;
    }

    public void setMessageToFlag(String messageToFlag) {
        this.messageToFlag = messageToFlag;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }
    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageToName() {
        return messageToName;
    }

    public void setMessageToName(String messageToName) {
        this.messageToName = messageToName;
    }

    public String getMessageFromName() {
        return messageFromName;
    }

    public void setMessageFromName(String messageFromName) {
        this.messageFromName = messageFromName;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}

