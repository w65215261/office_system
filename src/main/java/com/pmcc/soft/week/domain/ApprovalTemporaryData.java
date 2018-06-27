package com.pmcc.soft.week.domain;

/**
 * 转json时用到的实体类
 * Created by wangBin on 2016/5/24.
 */
public class ApprovalTemporaryData {
    private String controlDisplay;
    private String controlKey;
    private String controlTitle;
    private Integer controlType;
    private String controlValue;
    private Integer index;
    private String itemIndex;
    private String rptPersonId;

    public String getControlDisplay() {
        return controlDisplay;
    }

    public void setControlDisplay(String controlDisplay) {
        this.controlDisplay = controlDisplay;
    }

    public String getControlKey() {
        return controlKey;
    }

    public void setControlKey(String controlKey) {
        this.controlKey = controlKey;
    }

    public String getControlTitle() {
        return controlTitle;
    }

    public void setControlTitle(String controlTitle) {
        this.controlTitle = controlTitle;
    }

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public String getControlValue() {
        return controlValue;
    }

    public void setControlValue(String controlValue) {
        this.controlValue = controlValue;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(String itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getRptPersonId() {
        return rptPersonId;
    }

    public void setRptPersonId(String rptPersonId) {
        this.rptPersonId = rptPersonId;
    }
}
