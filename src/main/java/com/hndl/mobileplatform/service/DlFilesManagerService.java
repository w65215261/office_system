package com.hndl.mobileplatform.service;

import java.util.List;

import com.hndl.mobileplatform.model.Dlfilesmanager;
import com.hndl.mobileplatform.model.DlfilesmanagerExample;
import com.pmcc.soft.core.common.BaseVO;

public interface DlFilesManagerService {
	int countByExample(DlfilesmanagerExample example);
	List<Dlfilesmanager> selectByExample(DlfilesmanagerExample example, BaseVO vo);
	List<Dlfilesmanager> selectByExample(DlfilesmanagerExample example);
	int insert(Dlfilesmanager record);
	int deleteByPrimaryKey(String fileid);
	Dlfilesmanager selectByPrimaryKey(String fileid);
	int updateByPrimaryKey(Dlfilesmanager record);
}
