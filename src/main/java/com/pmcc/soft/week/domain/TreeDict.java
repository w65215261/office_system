package com.pmcc.soft.week.domain;

import java.util.List;

/**
 * Created by asus on 2015/10/28.
 */
public class TreeDict {
    private String oid;//ID
    private String dictName;//任务名称
    private String parentId;//负责人
    private List<TreeDict> children;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeDict> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDict> children) {
        this.children = children;
    }
}
