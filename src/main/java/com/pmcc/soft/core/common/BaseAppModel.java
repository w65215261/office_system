package com.pmcc.soft.core.common;

/**
 * 分页，
 */
public class BaseAppModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int page = 1; // 当前页,名字必须为page
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
			top= "top "+this.getRows();
		}

		return top;
	}

	public String getTopB() {
		String top="";
		if(this.getInitPage()==0){
			top= "top "+this.getPage()*this.getRows();
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

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
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
