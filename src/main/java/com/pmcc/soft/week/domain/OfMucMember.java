package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.BaseModel;

/**
 * 群成员
 * Created by sunyongxing
 * on 2015/6/8 0008 9:06
 */
public class OfMucMember extends BaseModel {

    private int roomID;

    private String jid;

    private String nickname;

    private String firstName;

    private String lastName;

    private String url;

    private String email;

    private String faqentry;

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaqentry() {
        return faqentry;
    }

    public void setFaqentry(String faqentry) {
        this.faqentry = faqentry;
    }
}
