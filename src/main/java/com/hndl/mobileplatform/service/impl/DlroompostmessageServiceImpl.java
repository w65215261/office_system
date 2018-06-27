package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlroompostmessageMapper;
import com.hndl.mobileplatform.model.DlroomnoticemessageExample;
import com.hndl.mobileplatform.model.DlroomnoticemessageWithBLOBs;
import com.hndl.mobileplatform.model.Dlroompost;
import com.hndl.mobileplatform.model.DlroompostExample;
import com.hndl.mobileplatform.model.DlroompostWithBLOBs;
import com.hndl.mobileplatform.model.DlroompostmessageExample;
import com.hndl.mobileplatform.model.DlroompostmessageWithBLOBs;
import com.hndl.mobileplatform.service.DlroompostService;
import com.hndl.mobileplatform.service.DlroompostmessageService;
import com.pmcc.soft.core.common.BaseVO;
@Service
public class DlroompostmessageServiceImpl implements DlroompostmessageService {

	@Resource
	DlroompostmessageMapper dlroompostmessageMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		
		return dlroompostmessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DlroompostmessageWithBLOBs record) {
		
		return dlroompostmessageMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(DlroompostmessageWithBLOBs record) {
		
		return dlroompostmessageMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public List<DlroompostmessageWithBLOBs> selectByExampleWithBLOBs(
			DlroompostmessageExample example) {
		
		return dlroompostmessageMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public DlroompostmessageWithBLOBs selectByPrimaryKey(String id) {
		
		return dlroompostmessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DlroompostmessageWithBLOBs> selectByExampleWithBLOBs(
			DlroompostmessageExample example, BaseVO vo) {
		// TODO Auto-generated method stub
		vo.setOrderField("createtime");
		example.setVo(vo);
		return dlroompostmessageMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int countByExample(DlroompostmessageExample example) {
	
		return dlroompostmessageMapper.countByExample(example);
	}



	

}
