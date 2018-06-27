package com.pmcc.soft.core.common;





public class BaseExample {
	BaseVO vo;
	String sql;

	public BaseVO getVo() {
		return vo;
	}

	public void setVo(BaseVO vo) {
		this.vo = vo;
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public boolean isPageSelect(){
		boolean res = false;
		if(vo==null)
			return res;
		if(vo.getRows()>1)
			return true;
		return false;
	}
}
