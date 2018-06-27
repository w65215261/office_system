package com.hndl.mobileplatform.service;

import java.util.List;

import com.hndl.mobileplatform.model.Dlroompost;
import com.hndl.mobileplatform.model.DlroompostExample;
import com.hndl.mobileplatform.model.DlroompostWithBLOBs;
import com.pmcc.soft.core.common.BaseVO;

public interface DlroompostService {

	int deleteByPrimaryKey(String id);

	int insert(DlroompostWithBLOBs record);

	List<DlroompostWithBLOBs> selectByExampleWithBLOBs(
			DlroompostExample example);

	int updateByPrimaryKeyWithBLOBs(DlroompostWithBLOBs record);

	List<Dlroompost> selectByExample(DlroompostExample example, BaseVO vo);

	int countByExample(DlroompostExample example);
	 DlroompostWithBLOBs selectByPrimaryKey(String id);

}
