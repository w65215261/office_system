package com.pmcc.soft.core.common;

public class BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int page = 1; // 当前页,名字必须为page
	private int iDisplayLength = 10; // 每页大小,名字必须为rows
	private int iDisplayStart;//当前页从第几条开始
	private String sort; // 排序字段
	private String order; // 排序规则
	private int initPage;
	private int rows = 10; // 每页大小,名字必须为rows

	@SuppressWarnings("unused")
	private String topA;
	@SuppressWarnings("unused")
	private String topB;

	public String getTopA() {
		String top="";
		if(this.getInitPage()==0){
			top= "top "+this.getiDisplayLength();
		}

		return top;
	}

	public String getTopB() {
		String top="";
		if(this.getInitPage()==0){
			top= "top "+this.getPage()*this.getiDisplayLength();
		}
		return top;
	}

	public void setTopB(String topB) {
		this.topB = topB;
	}

	public void setTopA(String topA) {
		this.topA = topA;
	}

	public int getInitPage() {
		return initPage;
	}

	public void setInitPage(int initPage) {
		this.initPage = initPage;
	}

	//当前页为当前记录开始条数+每页条数然后除以每页条数
	public int getPage() {
		return (this.getiDisplayLength() + this.getiDisplayStart())/this.getiDisplayLength();
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
