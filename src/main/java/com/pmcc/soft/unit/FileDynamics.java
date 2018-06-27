package com.pmcc.soft.unit;

import com.fasterxml.jackson.annotation.JsonView;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.week.domain.SystemAttachment;

import java.util.Date;
import java.util.List;

/**
 * 文件动态列表，手机端返回用
 * Created by LvXL on 2016/5/11.
 */
public class FileDynamics {

    @JsonView(View.RestView.class)
    private String operateDesc;//操作描述
    @JsonView(View.RestView.class)
    private Date rptDate;//操作时间
    @JsonView(View.RestView.class)
    private List<SystemAttachment> attachments;//文件路径
    @JsonView(View.RestView.class)
    private int total;// 数据总数
    @JsonView(View.RestView.class)
    private String rptPersonId;
    @JsonView(View.RestView.class)
    private String  rptPersonName;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public List<SystemAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<SystemAttachment> attachments) {
        this.attachments = attachments;
    }

    public String getRptPersonId() {
        return rptPersonId;
    }

    public void setRptPersonId(String rptPersonId) {
        this.rptPersonId = rptPersonId;
    }

    public String getRptPersonName() {
        return rptPersonName;
    }

    public void setRptPersonName(String rptPersonName) {
        this.rptPersonName = rptPersonName;
    }
}
