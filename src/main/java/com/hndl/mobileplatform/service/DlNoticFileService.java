package com.hndl.mobileplatform.service;

import java.util.List;

import com.hndl.mobileplatform.model.Dlnoticfile;
import com.hndl.mobileplatform.model.DlnoticfileExample;

public interface DlNoticFileService {
	int insert(Dlnoticfile record);
	
	Dlnoticfile selectByPrimaryKey(String id);
	
	List<Dlnoticfile> selectByExample(DlnoticfileExample example);
	
	int updateByPrimaryKeySelective(Dlnoticfile record);
}
