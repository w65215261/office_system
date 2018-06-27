package com.pmcc.soft.ydpt.domain;

import com.pmcc.soft.core.common.BaseModel;

/**
 * 群成员
 * Created by sunyongxing
 * on 2015/6/8 0008 9:06
 */
public class OfMucMember extends BaseModel{

    private int roomID;

    private String jid;

    private String nickname;

    private String firstName;

    private String lastName;

    private String url;

    private String email;

    private String faqentry;

    
    private String isOrAdmin;
  //-----------------
    //只做显示用的字段
    private String department;
    //只做显示用的字段
    private String telephone;
    //显示用
    private String age;
    //显示用
    private String officephone;
    //显示用
    private String qq;
    //显示用
    private String userMail;
    //显示用
    private String duty;
    //显示用
    private String remark;
	//显示用
    private String sorting;
	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
//---------------------------
	public String getIsOrAdmin() {
		return isOrAdmin;
	}

	public void setIsOrAdmin(String isOrAdmin) {
		this.isOrAdmin = isOrAdmin;
	}

	public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaqentry() {
        return faqentry;
    }

    public void setFaqentry(String faqentry) {
        this.faqentry = faqentry;
    }
}
