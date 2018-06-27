package com.hndl.mobileplatform.service;

import java.util.List;

import com.pmcc.soft.core.common.BaseExample;

public interface PublicExcuteSqlService {

	List executeSql(String sql);

	Integer updateBySql(String sql);

	List queryBySqlPage(BaseExample baseExample);

	Integer queryBySqlPageCount(String sql);
	
	List queryUserNameAndId();

}
