package com.pmcc.soft.week.domain;

import java.util.Date;

/**
 * 模板控件
 * Created by ZhangYanChang on 2016/4/21.
 */
public class TempleateControl {

    private String id;            // 主键
    private int controlType; // 类型(0.文本1.多行文本2.日期区间3.日期4.下拉框)
    private String controlTitle;// 标题
    private String placeHolder; // 提示
    private String validateType;// 验证类型(0.仅数字1.仅字母2.混合)
    private String controlKey;  // 键名
    private int    index;       // 排序
    private int    itemIndex;  // 组合控件
    private String rptPersonId;// 创建人
    private Date   rptDate;    // 创建时间
    private String templateId;//模板ID
    private String templateCode;// 控件模板编码

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public TempleateControl() {
    }

    public TempleateControl(int controlType, String controlTitle, String placeHolder, String controlKey, int itemIndex) {
        this.controlType = controlType;
        this.controlTitle = controlTitle;
        this.placeHolder = placeHolder;
        this.controlKey = controlKey;
        this.itemIndex = itemIndex;
    }
    public TempleateControl(int controlType, String templateCode,String controlTitle, String placeHolder, String controlKey,String templateId,int index) {
        this.controlType = controlType;
        this.controlTitle = controlTitle;
        this.placeHolder = placeHolder;
        this.controlKey = controlKey;
        this.templateId = templateId;
        this.index = index;
        this.templateCode = templateCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getControlType() {
        return controlType;
    }

    public void setControlType(int controlType) {
        this.controlType = controlType;
    }

    public String getControlTitle() {
        return controlTitle;
    }

    public void setControlTitle(String controlTitle) {
        this.controlTitle = controlTitle;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType;
    }

    public String getControlKey() {
        return controlKey;
    }

    public void setControlKey(String controlKey) {
        this.controlKey = controlKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getRptPersonId() {
        return rptPersonId;
    }

    public void setRptPersonId(String rptPersonId) {
        this.rptPersonId = rptPersonId;
    }

    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }
}
