package com.hndl.mobileplatform.service;

import java.util.List;

import com.hndl.mobileplatform.model.Dlspacemanager;
import com.hndl.mobileplatform.model.DlspacemanagerExample;

public interface DlSpaceManagerService {
	List<Dlspacemanager> selectByExample(DlspacemanagerExample exampl);
	int inster(Dlspacemanager dsm);
	int updateByPrimaryKey(Dlspacemanager record);

}
