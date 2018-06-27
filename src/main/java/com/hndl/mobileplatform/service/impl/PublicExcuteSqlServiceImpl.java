package com.hndl.mobileplatform.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.PublicDao;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
import com.pmcc.soft.core.common.BaseExample;
@Service
public class PublicExcuteSqlServiceImpl implements PublicExcuteSqlService {
	@Resource
	private PublicDao publicDao; 
	@Override
	public List executeSql(@Param("sql") String sql){
		return publicDao.executeSql(sql);
	}
	@Override
    public Integer updateBySql(@Param("sql") String sql){
    	return publicDao.updateBySql(sql);
    }
	@Override
    public List queryBySqlPage(BaseExample baseExample){
    	return publicDao.queryBySqlPage(baseExample);
    }
	@Override
    public Integer queryBySqlPageCount(@Param("sql") String sql){
    	return publicDao.queryBySqlPageCount(sql);
    }
	@Override
	public List queryUserNameAndId() {
		return publicDao.executeSql("select oid,user_ename as username from PERSON_INFO");
	}
}
