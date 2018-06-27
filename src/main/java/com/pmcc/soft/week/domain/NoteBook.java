package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 云笔记本的实体类
 * Created by asus on 2016/01/06.
 */
public class NoteBook extends DiscussBaseModel {
    private String oid;//笔记本ID
    private String userOid;//用户ID
    private String delFlag;//删除标记
    private String notebookName;//笔记本名称
    private Date notebookCreateTime;//创建时间
    private List<NoteBook> noteBooks;


    public NoteBook(){
        this.noteBooks=new ArrayList<NoteBook>();
    }

    public List<NoteBook> getNoteBooks() {
        return noteBooks;
    }

    public void setNoteBooks(List<NoteBook> noteBooks) {
        this.noteBooks = noteBooks;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUserOid() {
        return userOid;
    }

    public void setUserOid(String userOid) {
        this.userOid = userOid;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getNotebookCreateTime() {
        return notebookCreateTime;
    }

    public void setNotebookCreateTime(Date notebookCreateTime) {
        this.notebookCreateTime = notebookCreateTime;
    }
}
