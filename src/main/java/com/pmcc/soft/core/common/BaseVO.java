package com.pmcc.soft.core.common;


import java.util.Date;

public class BaseVO {
	public final static int PAGE_SHOW_COUNT = 10;
	private int page = 1;
	private int rows = 0;
	private int total = 0;
	private String id;
	private String orderField;
	private String orderDirection;
	private String keywords;
	private String status;
	private String type;
	private Date startDate;
	private Date endDate;
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return "".equals(type) ? null : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return "".equals(status)? null : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderDirection() {
		return "desc".equals(orderDirection) ? "desc" : "asc";
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	

	
	public String getKeywords() {
		return "".equals(keywords)? null : keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public int getStartIndex() {
		int pageNum = this.getPage() > 0 ? this.getPage() - 1 : 0;
		return pageNum * this.getRows();
	}
	public int getEndIndex(){
		int star = this.getStartIndex();
		return star + this.getRows();
	}
}
