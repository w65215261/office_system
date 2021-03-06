package com.hndl.mobileplatform.model;

public class Dlroompost {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.id
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.title
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.createid
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String createid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.createtime
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.roomid
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String roomid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.bz1
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String bz1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.bz2
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String bz2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.bz3
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String bz3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.bz4
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String bz4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlRoomPost.bz5
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    private String bz5;
    //发帖人只做显示使用
    private String  person;
    
    
	//前台展示(所属部门)
	private String department;
    
    public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.id
     *
     * @return the value of dlRoomPost.id
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.id
     *
     * @param id the value for dlRoomPost.id
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.title
     *
     * @return the value of dlRoomPost.title
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.title
     *
     * @param title the value for dlRoomPost.title
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.createid
     *
     * @return the value of dlRoomPost.createid
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getCreateid() {
        return createid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.createid
     *
     * @param createid the value for dlRoomPost.createid
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setCreateid(String createid) {
        this.createid = createid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.createtime
     *
     * @return the value of dlRoomPost.createtime
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.createtime
     *
     * @param createtime the value for dlRoomPost.createtime
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.roomid
     *
     * @return the value of dlRoomPost.roomid
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getRoomid() {
        return roomid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.roomid
     *
     * @param roomid the value for dlRoomPost.roomid
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.bz1
     *
     * @return the value of dlRoomPost.bz1
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getBz1() {
        return bz1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.bz1
     *
     * @param bz1 the value for dlRoomPost.bz1
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.bz2
     *
     * @return the value of dlRoomPost.bz2
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getBz2() {
        return bz2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.bz2
     *
     * @param bz2 the value for dlRoomPost.bz2
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.bz3
     *
     * @return the value of dlRoomPost.bz3
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getBz3() {
        return bz3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.bz3
     *
     * @param bz3 the value for dlRoomPost.bz3
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.bz4
     *
     * @return the value of dlRoomPost.bz4
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getBz4() {
        return bz4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.bz4
     *
     * @param bz4 the value for dlRoomPost.bz4
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setBz4(String bz4) {
        this.bz4 = bz4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlRoomPost.bz5
     *
     * @return the value of dlRoomPost.bz5
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public String getBz5() {
        return bz5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlRoomPost.bz5
     *
     * @param bz5 the value for dlRoomPost.bz5
     *
     * @mbggenerated Thu Jul 16 17:57:16 CST 2015
     */
    public void setBz5(String bz5) {
        this.bz5 = bz5;
    }
}