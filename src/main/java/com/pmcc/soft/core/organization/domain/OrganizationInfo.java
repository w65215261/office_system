package com.pmcc.soft.core.organization.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.utils.CommonDateFormat;
import com.pmcc.soft.core.utils.CommonDateTimeFormat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizationInfo extends BaseModel {
	@JsonView(View.RestView.class)
	private String id;
	// 机构英文名称
	private String orgEname;

	// 机构中文名称
	@JsonView(View.RestView.class)
	private String orgCname;

	// 中文简称
	private String orgShortCname;

	// 英文简称
	private String orgShortEname;

	// 机构描述
	private String description;

	// 机构编码
	private String orgCode;

	// 机构类型
	private String orgType;

	// 管理单位
//	private OrganizationInfo manageUnit;
//在这里用作了排序字段用
	private String manageUnitId;

	// 统计单位
//	private OrganizationInfo countUnit;
	
	private String countUnitId;

	// 备注
	private String remark;

	// 填报人
	private PersonInfo rptPerson;
	// 创建时间
	private Timestamp createDate;

	// 填报单位
//	private OrganizationInfo rptUnit;
	//在这里用作了组织机构管理员
	private String rptUnit;
	
	//父机构oid
	private String funitOid;
	
	//父机构名称
	private String funitName;

	// 删除标识 0未删除 1已删除
	private int delFlag;
	
	private String state;
	
	private String text;
	
	private String iconCls;

	private String userCname;
	private String userEname;
	private Date activationTime;

	private List<String> list=new ArrayList<>();

	/* 通过人员id查询机构 用  */
	private String personId;
	private String companyId;

	public OrganizationInfo() {
	}

	public OrganizationInfo(String personId, String companyId) {
		this.personId = personId;
		this.companyId = companyId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@JsonSerialize(using = CommonDateTimeFormat.class)
	public Date getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getUserCname() {
		return userCname;
	}

	public void setUserCname(String userCname) {
		this.userCname = userCname;
	}

	public String getUserEname() {
		return userEname;
	}

	public void setUserEname(String userEname) {
		this.userEname = userEname;
	}

	public String getIconCls() {
		return iconCls;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgEname() {
		return orgEname;
	}

	public void setOrgEname(String orgEname) {
		this.orgEname = orgEname;
	}

	public String getOrgCname() {
		return orgCname;
	}

	public void setOrgCname(String orgCname) {
		this.orgCname = orgCname;
	}

	public String getOrgShortCname() {
		return orgShortCname;
	}

	public void setOrgShortCname(String orgShortCname) {
		this.orgShortCname = orgShortCname;
	}

	public String getOrgShortEname() {
		return orgShortEname;
	}

	public void setOrgShortEname(String orgShortEname) {
		this.orgShortEname = orgShortEname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		if(orgType!=null){
			if(orgType.equals("组织机构") || orgType.equals("1")){
				this.iconCls = "icon-danwei";
			}else if(orgType.equals("部门") || orgType.equals("2")){
				this.iconCls = "icon-bumen";
			}
		}
		
		this.orgType = orgType;
	}
//
//	public OrganizationInfo getManageUnit() {
//		return manageUnit;
//	}
//
//	public void setManageUnit(OrganizationInfo manageUnit) {
//		this.manageUnit = manageUnit;
//	}
//
//	public OrganizationInfo getCountUnit() {
//		return countUnit;
//	}
//
//	public void setCountUnit(OrganizationInfo countUnit) {
//		this.countUnit = countUnit;
//	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	public PersonInfo getRptPerson() {
		return rptPerson;
	}

	public void setRptPerson(PersonInfo rptPerson) {
		this.rptPerson = rptPerson;
	}

	@JsonSerialize(using = CommonDateTimeFormat.class)
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

//	public OrganizationInfo getRptUnit() {
//		return rptUnit;
//	}
//
//	public void setRptUnit(OrganizationInfo rptUnit) {
//		this.rptUnit = rptUnit;
//	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getManageUnitId() {
		return manageUnitId;
	}

	public void setManageUnitId(String manageUnitId) {
		this.manageUnitId = manageUnitId;
	}

	public String getCountUnitId() {
		return countUnitId;
	}

	public void setCountUnitId(String countUnitId) {
		this.countUnitId = countUnitId;
	}

	public String getFunitOid() {
		return funitOid;
	}

	public void setFunitOid(String funitOid) {
		this.funitOid = funitOid;
	}

	public String getRptUnit() {
		return rptUnit;
	}

	public void setRptUnit(String rptUnit) {
		this.rptUnit = rptUnit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFunitName() {
		return funitName;
	}

	public void setFunitName(String funitName) {
		this.funitName = funitName;
	}
	
	
	
}
