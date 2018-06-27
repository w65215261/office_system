package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.BaseModel;

/**
 * 群成员
 * Created by sunyongxing
 * on 2015/7/16 0016 16:24
 */
public class GroupPerson extends BaseModel {

    private String id;

    private String groupInfoOid;//群oid

    private String friendsOid;//群成员oid

    private String friendsName;//群成员名称

    private String isNotAdmin;//是否管理员(0：否，1：是)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupInfoOid() {
        return groupInfoOid;
    }

    public void setGroupInfoOid(String groupInfoOid) {
        this.groupInfoOid = groupInfoOid;
    }

    public String getFriendsOid() {
        return friendsOid;
    }

    public void setFriendsOid(String friendsOid) {
        this.friendsOid = friendsOid;
    }

    public String getFriendsName() {
        return friendsName;
    }

    public void setFriendsName(String friendsName) {
        this.friendsName = friendsName;
    }

    public String getIsNotAdmin() {
        return isNotAdmin;
    }

    public void setIsNotAdmin(String isNotAdmin) {
        this.isNotAdmin = isNotAdmin;
    }
}
