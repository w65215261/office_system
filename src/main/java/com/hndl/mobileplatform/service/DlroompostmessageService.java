package com.hndl.mobileplatform.service;

import java.util.List;






import com.hndl.mobileplatform.model.DlroompostmessageExample;
import com.hndl.mobileplatform.model.DlroompostmessageWithBLOBs;
import com.pmcc.soft.core.common.BaseVO;

public interface DlroompostmessageService {

	int deleteByPrimaryKey(String id);

	int insert(DlroompostmessageWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(DlroompostmessageWithBLOBs record);

	List<DlroompostmessageWithBLOBs> selectByExampleWithBLOBs(
			DlroompostmessageExample example);
	List<DlroompostmessageWithBLOBs> selectByExampleWithBLOBs(
			DlroompostmessageExample example,BaseVO vo);
	
	DlroompostmessageWithBLOBs selectByPrimaryKey(String id);
	  int countByExample(DlroompostmessageExample example);

}
