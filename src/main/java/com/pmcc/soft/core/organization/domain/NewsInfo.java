package com.pmcc.soft.core.organization.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.utils.CommonDateFormat;

import java.util.Date;

/**
 * Created by sunyake on 15/3/19.
 */
public class NewsInfo extends BaseModel {

    private static final long serialVersionUID = 1L;
    @JsonView(View.RestView.class)
    private String id;
    // 标题
    @JsonView(View.RestView.class)
    private String title;
    // 内容
    @JsonView(View.RestView.class)
    private String content;
    // 文本内容
    @JsonView(View.RestView.class)
    private String contentTxt;
    // 发布时间
    @JsonView(View.RestView.class)
    private Date rptDate;
    // 发布人
    @JsonView(View.RestView.class)
    private String rptPerson;
    // 排序
    private String orderNo;
    // 类型0新闻1公告
    private String type;
    private Date startTime;
    private Date endTime;
    //置顶时间
    private Date toptime;
    //点击数
    private String clicknum;
    //置顶标识
    private String topmark;
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getClicknum() {
		return clicknum;
	}

	public void setClicknum(String clicknum) {
		this.clicknum = clicknum;
	}

	public String getTopmark() {
		return topmark;
	}

	public void setTopmark(String topmark) {
		this.topmark = topmark;
	}

	public Date getToptime() {
		return toptime;
	}

	public void setToptime(Date toptime) {
		this.toptime = toptime;
	}

	//发布人(所属部门)前台显示
    @JsonView(View.RestView.class)
	private String department;

    public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@JsonSerialize(using = CommonDateFormat.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonSerialize(using = CommonDateFormat.class)
    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @JsonIgnore
    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }


}
