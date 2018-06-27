package com.pmcc.soft.unit;

/**
 * 手机端保存传递参数用
 * Created by LvXL on 2016/4/27.
 */
public class ParameterBean {

    private String uuid;// 主键
    private String type;// 类型
    private String dataBlock; // 业务数据（JSON字符串数组）
    private String source;// 来源（0.pc/1.iOS/2.android）
    private String rptPersonId;// 创建人
    private Integer businessModel;//业务模块（0.日志1.审核）
    private String groupAndPerson;// 分组审核人员ID，一组code|人员一id,二组code|人员二id,三组code|人员三id

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroupAndPerson() {
        return groupAndPerson;
    }

    public void setGroupAndPerson(String groupAndPerson) {
        this.groupAndPerson = groupAndPerson;
    }

    public Integer getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(Integer businessModel) {
        this.businessModel = businessModel;
    }

    public String getRptPersonId() {
        return rptPersonId;
    }

    public void setRptPersonId(String rptPersonId) {
        this.rptPersonId = rptPersonId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataBlock() {
        return dataBlock;
    }

    public void setDataBlock(String dataBlock) {
        this.dataBlock = dataBlock;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
