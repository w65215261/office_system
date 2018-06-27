package com.hndl.mobileplatform.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pmcc.soft.core.common.BaseExample;




public interface PublicDao {
	Long nextid(@Param("SequenceName") String SequenceName);
	List executeSql(@Param("sql") String sql);
    Integer updateBySql(@Param("sql") String sql);
    List queryBySqlPage(BaseExample baseExample);
    Integer queryBySqlPageCount(@Param("sql") String sql);
}
