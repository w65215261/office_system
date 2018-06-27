package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlfilesmanagerMapper;
import com.hndl.mobileplatform.model.Dlfilesmanager;
import com.hndl.mobileplatform.model.DlfilesmanagerExample;
import com.hndl.mobileplatform.service.DlFilesManagerService;
import com.pmcc.soft.core.common.BaseVO;
@Service
public class DlFilesManagerServiceImpl implements DlFilesManagerService {
	@Resource
	DlfilesmanagerMapper dlfilesmanagerMapper;
	@Override
	public List<Dlfilesmanager> selectByExample(DlfilesmanagerExample example, BaseVO vo){
		vo.setOrderField("createtime");
		example.setVo(vo);
		return dlfilesmanagerMapper.selectByExample(example);
	 }
	@Override
	public int insert(Dlfilesmanager record) {
		return dlfilesmanagerMapper.insert(record);
	}
	@Override
	public int deleteByPrimaryKey(String fileid){
		return dlfilesmanagerMapper.deleteByPrimaryKey(fileid);
	}
	@Override
	public Dlfilesmanager selectByPrimaryKey(String fileid) {
		// TODO Auto-generated method stub
		return dlfilesmanagerMapper.selectByPrimaryKey(fileid);
	}

	@Override
	public int updateByPrimaryKey(Dlfilesmanager record) {
		// TODO Auto-generated method stub
		return dlfilesmanagerMapper.updateByPrimaryKey(record);
	}
	@Override
	public int countByExample(DlfilesmanagerExample example) {
		
		return dlfilesmanagerMapper.countByExample(example);
	}
	@Override
	public List<Dlfilesmanager> selectByExample(DlfilesmanagerExample example) {
		// TODO Auto-generated method stub
		return dlfilesmanagerMapper.selectByExample(example);
	}
	
	
}
