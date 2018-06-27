package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlspacemanagerMapper;
import com.hndl.mobileplatform.model.Dlspacemanager;
import com.hndl.mobileplatform.model.DlspacemanagerExample;
import com.hndl.mobileplatform.service.DlSpaceManagerService;
import com.pmcc.soft.core.utils.UUIDGenerator;
@Service
public class DlSpaceManagerServiceImpl implements DlSpaceManagerService {
	@Resource
	DlspacemanagerMapper dlspacemanagerMapper;
	@Override
	public List<Dlspacemanager> selectByExample(DlspacemanagerExample example){
		return dlspacemanagerMapper.selectByExample(example);
	 }
	
	 @Override
	 public int inster (Dlspacemanager dsm){
		 dsm.setSpaceid(UUIDGenerator.getUUID());
		 return dlspacemanagerMapper.insert(dsm);
	 }

	@Override
	public int updateByPrimaryKey(Dlspacemanager record) {
		// TODO Auto-generated method stub
		return dlspacemanagerMapper.updateByPrimaryKey(record);
	}


	



	
	

}
