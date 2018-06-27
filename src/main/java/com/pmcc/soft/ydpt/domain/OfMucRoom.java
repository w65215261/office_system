package com.pmcc.soft.ydpt.domain;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.pmcc.soft.core.common.BaseModel;

/**
 * 群实体
 * Created by sunyongxing
 * on 2015/6/4 0004 12:09
 */
public class OfMucRoom extends BaseModel{

    private int serviceID;

    private int roomID;//群成员id

    private String creationDate;

    private String modificationDate;

    private String name;//群英文名

    private String naturalName;//群名称

    private String description;//群描述

    private String emptyDate;//

    private String lockedDate;

    private int canChangeSubject;

    private int maxUsers;

    private int publicRoom;

    private int moderated;

    private int membersOnly;

    private int canInvite;

    private String roomPassword;

    private int canDiscoverJID;

    private int logEnabled;

    private String subject;

    private int rolesToBroadcast;

    private int useReservedNick;

    private int canChangeNick;

    private int canRegister;
    
    //新增两个字段（刘骁）
    private String pacename;
    private String spacesize;

    public String getPacename() {
		return pacename;
	}

	public void setPacename(String pacename) {
		this.pacename = pacename;
	}

	public String getSpacesize() {
		return spacesize;
	}

	public void setSpacesize(String spacesize) {
		this.spacesize = spacesize;
	}

	public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNaturalName() {
        return naturalName;
    }

    public void setNaturalName(String naturalName) {
        this.naturalName = naturalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmptyDate() {
        return emptyDate;
    }

    public void setEmptyDate(String emptyDate) {
        this.emptyDate = emptyDate;
    }

    public String getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(String lockedDate) {
        this.lockedDate = lockedDate;
    }

    public int getCanChangeSubject() {
        return canChangeSubject;
    }

    public void setCanChangeSubject(int canChangeSubject) {
        this.canChangeSubject = canChangeSubject;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public int getPublicRoom() {
        return publicRoom;
    }

    public void setPublicRoom(int publicRoom) {
        this.publicRoom = publicRoom;
    }

    public int getModerated() {
        return moderated;
    }

    public void setModerated(int moderated) {
        this.moderated = moderated;
    }

    public int getMembersOnly() {
        return membersOnly;
    }

    public void setMembersOnly(int membersOnly) {
        this.membersOnly = membersOnly;
    }

    public int getCanInvite() {
        return canInvite;
    }

    public void setCanInvite(int canInvite) {
        this.canInvite = canInvite;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public int getCanDiscoverJID() {
        return canDiscoverJID;
    }

    public void setCanDiscoverJID(int canDiscoverJID) {
        this.canDiscoverJID = canDiscoverJID;
    }

    public int getLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(int logEnabled) {
        this.logEnabled = logEnabled;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getRolesToBroadcast() {
        return rolesToBroadcast;
    }

    public void setRolesToBroadcast(int rolesToBroadcast) {
        this.rolesToBroadcast = rolesToBroadcast;
    }

    public int getUseReservedNick() {
        return useReservedNick;
    }

    public void setUseReservedNick(int useReservedNick) {
        this.useReservedNick = useReservedNick;
    }

    public int getCanChangeNick() {
        return canChangeNick;
    }

    public void setCanChangeNick(int canChangeNick) {
        this.canChangeNick = canChangeNick;
    }

    public int getCanRegister() {
        return canRegister;
    }

    public void setCanRegister(int canRegister) {
        this.canRegister = canRegister;
    }
}
