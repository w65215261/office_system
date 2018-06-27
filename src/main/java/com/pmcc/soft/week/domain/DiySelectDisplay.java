package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.View;

/**
 * Created by ZhangYanChang on 2016/4/21.
 */
public class DiySelectDisplay {
    private String id;//ID
    private String controlId;//控件ID
    @JsonView(View.RestView.class)
    private String optionText;//显示文本
    @JsonView(View.RestView.class)
    private String optionValue;//值
    private int    optionIndex;//序号

    /* 临时字段，查询下拉框用 */
    private String controlType;
    private String templateCode;
    private String controlIndex;

    public String getControlIndex() {
        return controlIndex;
    }

    public void setControlIndex(String controlIndex) {
        this.controlIndex = controlIndex;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public DiySelectDisplay(String optionText, String optionValue) {
        this.optionText = optionText;
        this.optionValue = optionValue;
    }

    public DiySelectDisplay() {
        this.optionText = "";
        this.optionValue = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(int optionIndex) {
        this.optionIndex = optionIndex;
    }
}
