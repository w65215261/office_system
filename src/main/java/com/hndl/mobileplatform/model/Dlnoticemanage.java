package com.hndl.mobileplatform.model;

public class Dlnoticemanage {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.id
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.title
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.createtime
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.type
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.bz1
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String bz1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.bz2
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String bz2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.bz3
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String bz3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.bz4
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String bz4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.bz5
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String bz5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.createid
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String createid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlNoticeManage.content
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.id
     *
     * @return the value of dlNoticeManage.id
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    
    private String name;
    
    //发送人数（只做显示用）
    private String sendNumber ;
    public String getSendNumber() {
		return sendNumber;
	}

	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
	}

	public String getFindNumber() {
		return findNumber;
	}

	public void setFindNumber(String findNumber) {
		this.findNumber = findNumber;
	}

	public String getNoNumber() {
		return noNumber;
	}

	public void setNoNumber(String noNumber) {
		this.noNumber = noNumber;
	}

	//查看人数（只做显示用）
    private String findNumber ;
    //未查看人数（只做显示用）
    private String noNumber ;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.id
     *
     * @param id the value for dlNoticeManage.id
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.title
     *
     * @return the value of dlNoticeManage.title
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.title
     *
     * @param title the value for dlNoticeManage.title
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.createtime
     *
     * @return the value of dlNoticeManage.createtime
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.createtime
     *
     * @param createtime the value for dlNoticeManage.createtime
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.type
     *
     * @return the value of dlNoticeManage.type
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.type
     *
     * @param type the value for dlNoticeManage.type
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.bz1
     *
     * @return the value of dlNoticeManage.bz1
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getBz1() {
        return bz1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.bz1
     *
     * @param bz1 the value for dlNoticeManage.bz1
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.bz2
     *
     * @return the value of dlNoticeManage.bz2
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getBz2() {
        return bz2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.bz2
     *
     * @param bz2 the value for dlNoticeManage.bz2
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.bz3
     *
     * @return the value of dlNoticeManage.bz3
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getBz3() {
        return bz3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.bz3
     *
     * @param bz3 the value for dlNoticeManage.bz3
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.bz4
     *
     * @return the value of dlNoticeManage.bz4
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getBz4() {
        return bz4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.bz4
     *
     * @param bz4 the value for dlNoticeManage.bz4
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setBz4(String bz4) {
        this.bz4 = bz4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.bz5
     *
     * @return the value of dlNoticeManage.bz5
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getBz5() {
        return bz5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.bz5
     *
     * @param bz5 the value for dlNoticeManage.bz5
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setBz5(String bz5) {
        this.bz5 = bz5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.createid
     *
     * @return the value of dlNoticeManage.createid
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getCreateid() {
        return createid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.createid
     *
     * @param createid the value for dlNoticeManage.createid
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setCreateid(String createid) {
        this.createid = createid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlNoticeManage.content
     *
     * @return the value of dlNoticeManage.content
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlNoticeManage.content
     *
     * @param content the value for dlNoticeManage.content
     *
     * @mbggenerated Thu Jul 16 17:55:50 CST 2015
     */
    public void setContent(String content) {
        this.content = content;
    }
}