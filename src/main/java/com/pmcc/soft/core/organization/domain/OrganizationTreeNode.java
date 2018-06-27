package com.pmcc.soft.core.organization.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyake on 15/7/15.
 */
public class OrganizationTreeNode {
    public String parentOrgID;
    public String orgCname;
    public String orgID;
    public String oid;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getParentOrgID() {
        return parentOrgID;
    }

    public void setParentOrgID(String parentOrgID) {
        this.parentOrgID = parentOrgID;
    }

    public String getOrgCname() {
        return orgCname;
    }

    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }
}
