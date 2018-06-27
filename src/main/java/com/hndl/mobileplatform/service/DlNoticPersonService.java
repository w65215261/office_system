package com.hndl.mobileplatform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.model.Dlnoticeperson;
import com.hndl.mobileplatform.model.DlnoticepersonExample;
import com.pmcc.soft.core.common.BaseVO;
@Service
public interface DlNoticPersonService {
	List<Dlnoticeperson> selectByExample(DlnoticepersonExample example);
	
	int insert(Dlnoticeperson record);
	
	int deleteByPrimaryKey(String fileid);
	 int countByExample(DlnoticepersonExample example);

	String sendWeiXin(String infoid, String personid,String uri,String token,String appId);

	List<Dlnoticeperson> selectByExample(DlnoticepersonExample example,BaseVO vo);
	
	 int updateByPrimaryKey(Dlnoticeperson record);
	
	
	
	
}
