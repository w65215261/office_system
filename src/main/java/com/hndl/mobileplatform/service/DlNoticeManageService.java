package com.hndl.mobileplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.model.Dlnoticemanage;
import com.hndl.mobileplatform.model.DlnoticemanageExample;
import com.hndl.mobileplatform.model.DlroomnoticemessageWithBLOBs;
import com.pmcc.soft.core.common.BaseVO;

@Service
public interface DlNoticeManageService {
	List<Dlnoticemanage> selectByExample(DlnoticemanageExample example);
	
	int insert(Dlnoticemanage record);
	
	int deleteByPrimaryKey(String fileid);
	
	List<Dlnoticemanage> selectByExampleWithBLOBs(DlnoticemanageExample example,BaseVO baseVo);
	
	int updateByPrimaryKeySelective(Dlnoticemanage record);
//	int updateByPrimaryKeyWithBLOBs(Dlnoticemanage record);
	
	Dlnoticemanage selectByPrimaryKey(String id);
	
	int countByExample(DlnoticemanageExample example);
	
//	int updateByPrimaryKeyWithBLOBs(DlroomnoticemessageWithBLOBs record);
	
//	int updateByPrimaryKey(Dlnoticemanage record);
	
//	int updateByPrimaryKeyWithBLOBs(Dlnoticemanage dlNoticeManage);
}
