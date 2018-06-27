package com.pmcc.soft.core.organization.domain;

import com.pmcc.soft.core.common.BaseModel;

import java.util.Date;

/**
 * Created by sc on 2015/2/13.
 */
public class OutOfStorageDetail extends BaseModel {
    private String id;
    //年
    private int yy;
    //月
    private int mm;
    //出库主表OID
    private String outOfOid;
    //货物名称OID
    private String materialNOid;
    //计划用量
    private int planNum;
    //实际用量
    private int actualNum;
    //金额
    private double amount;
    //备注
    private String remark;
    //创建人
    private String createPerson;
    //创建时间
    private Date createTime;
    //删除标志
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public String getOutOfOid() {
        return outOfOid;
    }

    public void setOutOfOid(String outOfOid) {
        this.outOfOid = outOfOid;
    }

    public String getMaterialNOid() {
        return materialNOid;
    }

    public void setMaterialNOid(String materialNOid) {
        this.materialNOid = materialNOid;
    }

    public int getPlanNum() {
        return planNum;
    }

    public void setPlanNum(int planNum) {
        this.planNum = planNum;
    }

    public int getActualNum() {
        return actualNum;
    }

    public void setActualNum(int actualNum) {
        this.actualNum = actualNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}