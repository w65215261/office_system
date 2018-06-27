package com.pmcc.soft.core.organization.domain;

import com.pmcc.soft.core.common.BaseModel;

import java.util.Date;

/**
 * Created by sc on 2015/2/13.
 */
public class OutOfStorageDetail extends BaseModel {
    private String id;
    //��
    private int yy;
    //��
    private int mm;
    //��������OID
    private String outOfOid;
    //��������OID
    private String materialNOid;
    //�ƻ�����
    private int planNum;
    //ʵ������
    private int actualNum;
    //���
    private double amount;
    //��ע
    private String remark;
    //������
    private String createPerson;
    //����ʱ��
    private Date createTime;
    //ɾ����־
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