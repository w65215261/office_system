package com.pmcc.soft.week.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * 审批页面传值用
 * Created by wangBin on 2016/5/25.
 */
public class ApprovalHtmlData {
    private  String approvalHeadId;//主表ID
    private  String taskId;//主表ID
    //审批模板不同传的数据不同，统一一下起名字
    private String detailsIndexOne;//详情一
    private Date detailsIndexTwo;//详情二
    private String detailsIndexThree ;//详情三
    private String detailsIndexFour;//详情四
    private String detailsIndexFive ;//详情五
    private String detailsIndexSix ;//详情六
    private Date detailsIndexSeven ;//详情七
    private Date detailsIndexEight ;//详情八
    private String detailsIndexNine;//详情九
    private String detailsIndexTen;//详情十
    private String detailsIndexEleven;//详情十一

    public String getDetailsIndexEleven() {
        return detailsIndexEleven;
    }

    public void setDetailsIndexEleven(String detailsIndexEleven) {
        this.detailsIndexEleven = detailsIndexEleven;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDetailsIndexTen() {
        return detailsIndexTen;
    }

    public void setDetailsIndexTen(String detailsIndexTen) {
        this.detailsIndexTen = detailsIndexTen;
    }

    public String getApprovalHeadId() {
        return approvalHeadId;
    }

    public void setApprovalHeadId(String approvalHeadId) {
        this.approvalHeadId = approvalHeadId;
    }

    public String getDetailsIndexOne() {
        return detailsIndexOne;
    }

    public void setDetailsIndexOne(String detailsIndexOne) {
        this.detailsIndexOne = detailsIndexOne;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getDetailsIndexTwo() {
        return detailsIndexTwo;
    }

    public void setDetailsIndexTwo(Date detailsIndexTwo) {
        this.detailsIndexTwo = detailsIndexTwo;
    }

    public String getDetailsIndexThree() {
        return detailsIndexThree;
    }

    public void setDetailsIndexThree(String detailsIndexThree) {
        this.detailsIndexThree = detailsIndexThree;
    }

    public String getDetailsIndexFour() {
        return detailsIndexFour;
    }

    public void setDetailsIndexFour(String detailsIndexFour) {
        this.detailsIndexFour = detailsIndexFour;
    }

    public String getDetailsIndexFive() {
        return detailsIndexFive;
    }

    public void setDetailsIndexFive(String detailsIndexFive) {
        this.detailsIndexFive = detailsIndexFive;
    }

    public String getDetailsIndexSix() {
        return detailsIndexSix;
    }

    public void setDetailsIndexSix(String detailsIndexSix) {
        this.detailsIndexSix = detailsIndexSix;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getDetailsIndexSeven() {
        return detailsIndexSeven;
    }

    public void setDetailsIndexSeven(Date detailsIndexSeven) {
        this.detailsIndexSeven = detailsIndexSeven;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getDetailsIndexEight() {
        return detailsIndexEight;
    }

    public void setDetailsIndexEight(Date detailsIndexEight) {
        this.detailsIndexEight = detailsIndexEight;
    }

    public String getDetailsIndexNine() {
        return detailsIndexNine;
    }

    public void setDetailsIndexNine(String detailsIndexNine) {
        this.detailsIndexNine = detailsIndexNine;
    }
}
