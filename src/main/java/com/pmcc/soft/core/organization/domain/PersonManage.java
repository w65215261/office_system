package com.pmcc.soft.core.organization.domain;


import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pmcc.soft.core.common.BaseModel;
import com.pmcc.soft.core.common.DiscussBaseModel;
import com.pmcc.soft.core.common.View;
import com.pmcc.soft.core.utils.CommonDateFormat;

/**
 * 
 * @author syx
 * @date 2014-10-10修改
 * 2014-11-19 再次修改
 */
public class PersonManage extends DiscussBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonView(View.RestView.class)
	private String id;
	@JsonView(View.RestView.class)
	private String userEname;
	@JsonView(View.RestView.class)
	private String userCname;
	
	private String md5Pwd;
	
	private String userCode;
	
	private String userDescription;
	
	private String telephone;
	
	private String telephoneMac;
	
	private int userSex;
	
	private Date userBrothday;
	
	private String userMail;
	
	private String userQQ;
	
	private String userPhotoUrl;
	
	private String remark;
	
	private String personUintId;
	
	private String personUnitname;
	
	private String rptPerson;
	
	private Date createDate;
	
	private String delFlag;
	
	private String duty;//职务
	private String openId;
	private String wechatId;
	
	private String power;//显示权限
	
	//前台展示(所属部门)
	private String department;
	private String sexShow;//性别从0.1转换为男.女
	private String createPersonShow;//创建人
	//新增两个字段（刘骁）
    private String pacename;
	private String spacesize;
	
	private String organList;
	
	private String roomid;
	private String companyId;//所属公司ID
	private String companyName;//所属公司名称

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getOrganList() {
		return organList;
	}

	public void setOrganList(String organList) {
		this.organList = organList;
	}

	//------新增字段
	private String age;
	private String officephone;
	private int sorting;


	

	public int getSorting() {
		return sorting;
	}

	public void setSorting(int sorting) {
		this.sorting = sorting;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}



	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	
    public String getPacename() {
		return pacename;
	}

	public void setPacename(String pacename) {
		this.pacename = pacename;
	}

	public String getSpacesize() {
		return spacesize;
	}

	public void setSpacesize(String spacesize) {
		this.spacesize = spacesize;
	}

	

	public String getCreatePersonShow() {
		return createPersonShow;
	}

	public void setCreatePersonShow(String createPersonShow) {
		this.createPersonShow = createPersonShow;
	}

	public String getSexShow() {
		if (0 == userSex) {
			sexShow = "男";
		} else {
			sexShow = "女";
		}
		return sexShow;
	}

	public void setSexShow(String sexShow) {
		this.sexShow = sexShow;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserEname(String userEname) {
		this.userEname = userEname;
	}

	public void setUserCname(String userCname) {
		this.userCname = userCname;
	}

	public void setMd5Pwd(String md5Pwd) {
		this.md5Pwd = md5Pwd;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setTelephoneMac(String telephoneMac) {
		this.telephoneMac = telephoneMac;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}


	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}

	public void setUserPhotoUrl(String userPhotoUrl) {
		this.userPhotoUrl = userPhotoUrl;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRptPerson(String rptPerson) {
		this.rptPerson = rptPerson;
	}



	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getId() {
		return id;
	}

	public String getUserEname() {
		return userEname;
	}

	public String getUserCname() {
		return userCname;
	}

	public String getMd5Pwd() {
		return md5Pwd;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getTelephoneMac() {
		return telephoneMac;
	}

	public int getUserSex() {
		return userSex;
	}
	

	public String getUserMail() {
		return userMail;
	}

	public String getUserQQ() {
		return userQQ;
	}

	public String getUserPhotoUrl() {
		return userPhotoUrl;
	}

	public String getRemark() {
		return remark;
	}

	public String getRptPerson() {
		return rptPerson;
	}

	@JsonSerialize(using =CommonDateFormat.class)
	public Date getUserBrothday() {
		return userBrothday;
	}

	public void setUserBrothday(Date userBrothday) {
		this.userBrothday = userBrothday;
	}
	@JsonSerialize(using = CommonDateFormat.class)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPersonUintId() {
		return personUintId;
	}

	public void setPersonUintId(String personUintId) {
		this.personUintId = personUintId;
	}

	public String getPersonUnitname() {
		return personUnitname;
	}

	public void setPersonUnitname(String personUnitname) {
		this.personUnitname = personUnitname;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	
	
}
