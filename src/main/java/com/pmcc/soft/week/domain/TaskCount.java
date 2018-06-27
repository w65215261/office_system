package com.pmcc.soft.week.domain;

import com.pmcc.soft.core.common.DiscussBaseModel;

/**
 * 任务统计的实体类
 * Created by asus on 2015/10/21.
 */
public class TaskCount extends DiscussBaseModel {
    private String responsiblePersonName;//负责人名字
    private String responsiblePersonOid;//负责人ID
    private String completeCount;//完成数
    private String responsibleCount;//发布数

    public String getResponsiblePersonOid() {
        return responsiblePersonOid;
    }

    public void setResponsiblePersonOid(String responsiblePersonOid) {
        this.responsiblePersonOid = responsiblePersonOid;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(String completeCount) {
        this.completeCount = completeCount;
    }

    public String getResponsibleCount() {
        return responsibleCount;
    }

    public void setResponsibleCount(String responsibleCount) {
        this.responsibleCount = responsibleCount;
    }
}
