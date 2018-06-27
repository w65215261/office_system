package com.hndl.mobileplatform.service;

import java.util.List;

import com.hndl.mobileplatform.model.Dlsoftversion;
import com.hndl.mobileplatform.model.DlsoftversionExample;
import com.pmcc.soft.core.common.BaseVO;

public interface DlsoftversionService{
	Dlsoftversion selectByPrimaryKey(String id);

	List<Dlsoftversion> selectByExample(DlsoftversionExample example, BaseVO vo);
	
	int insert(Dlsoftversion record);
	
	 int updateByPrimaryKey(Dlsoftversion record);
	 int deleteByPrimaryKey(String versionid);

	int countByExample(DlsoftversionExample example);
}
