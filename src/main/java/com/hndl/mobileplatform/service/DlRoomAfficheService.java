package com.hndl.mobileplatform.service;

import java.util.List;

import com.hndl.mobileplatform.model.Dlroomaffiche;
import com.hndl.mobileplatform.model.DlroomafficheExample;
import com.hndl.mobileplatform.model.DlroomnoticemessageWithBLOBs;
import com.pmcc.soft.core.common.BaseVO;

public interface DlRoomAfficheService {
	List<Dlroomaffiche> selectByExample(DlroomafficheExample example);
	int insert (Dlroomaffiche record);
	int deleteByPrimaryKey(String afficheid);
	int updateByPrimaryKeyWithBLOBs(Dlroomaffiche record);
	List<Dlroomaffiche>  selectByExampleWithBLOBs(DlroomafficheExample example);
	  int countByExample(DlroomafficheExample example);
	List<Dlroomaffiche> selectByExample(DlroomafficheExample example,
			BaseVO baseVo);
	List<Dlroomaffiche> selectByExampleWithBLOBs(DlroomafficheExample example,
			BaseVO vo);
}
