package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 云笔记的实体类
 * Created by asus on 2016/01/06.
 */
public class Note extends DiscussBaseModel {
    private String oid;//笔记ID
    private String notebookOid;//笔记本ID
    private String userOid;//用户ID
    private String delFlag;//删除标记
    private String noteTitle;//笔记名称
    private String noteBody;//笔记内容
    private Date noteCreateTime;//创建时间
    private Date noteLastModifyTime;//修改时间
    private  String noteBookName;//笔记本名称

    public String getNoteBookName() {
        return noteBookName;
    }

    public void setNoteBookName(String noteBookName) {
        this.noteBookName = noteBookName;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getNotebookOid() {
        return notebookOid;
    }

    public void setNotebookOid(String notebookOid) {
        this.notebookOid = notebookOid;
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

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getNoteCreateTime() {
        return noteCreateTime;
    }

    public void setNoteCreateTime(Date noteCreateTime) {
        this.noteCreateTime = noteCreateTime;
    }
    @JsonSerialize(using = CommonDateFormat.class)
    public Date getNoteLastModifyTime() {
        return noteLastModifyTime;
    }

    public void setNoteLastModifyTime(Date noteLastModifyTime) {
        this.noteLastModifyTime = noteLastModifyTime;
    }
}
