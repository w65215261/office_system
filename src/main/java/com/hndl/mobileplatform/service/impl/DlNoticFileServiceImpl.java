package com.hndl.mobileplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlnoticfileMapper;
import com.hndl.mobileplatform.model.Dlnoticfile;
import com.hndl.mobileplatform.model.DlnoticfileExample;
import com.hndl.mobileplatform.service.DlNoticFileService;
@Service
public class DlNoticFileServiceImpl implements DlNoticFileService{
	@Autowired
	DlnoticfileMapper dlNoticeFile;
	
	@Override
	public int insert(Dlnoticfile record) {
		// TODO Auto-generated method stub
		return dlNoticeFile.insert(record);
	}

	@Override
	public Dlnoticfile selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dlNoticeFile.selectByPrimaryKey(id);
	}

	@Override
	public List<Dlnoticfile> selectByExample(DlnoticfileExample example) {
		// TODO Auto-generated method stub
		return dlNoticeFile.selectByExample(example);
	}

	@Override
	public int updateByPrimaryKeySelective(Dlnoticfile record) {
		// TODO Auto-generated method stub
		return dlNoticeFile.updateByPrimaryKey(record);
	}

	
}
