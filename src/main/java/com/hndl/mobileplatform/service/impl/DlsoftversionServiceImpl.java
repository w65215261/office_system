package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlsoftversionMapper;
import com.hndl.mobileplatform.model.Dlsoftversion;
import com.hndl.mobileplatform.model.DlsoftversionExample;
import com.hndl.mobileplatform.service.DlsoftversionService;
import com.pmcc.soft.core.common.BaseVO;
@Service("ddd")
public class DlsoftversionServiceImpl implements DlsoftversionService{
	@Resource
    private DlsoftversionMapper llsoftversionMapper;

	@Override
	public Dlsoftversion selectByPrimaryKey(String id) {
		return llsoftversionMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Dlsoftversion> selectByExample(DlsoftversionExample example,BaseVO baseVo){
		baseVo.setOrderField("createtime");
		example.setVo(baseVo);
		return llsoftversionMapper.selectByExample(example);
	}
	@Override
	public int insert(Dlsoftversion record){
		return llsoftversionMapper.insert(record);
	}
	
	@Override
	public int updateByPrimaryKey(Dlsoftversion record){
		return llsoftversionMapper.updateByPrimaryKey(record);
	}
	@Override
	 public int deleteByPrimaryKey(String versionid){
		
		return llsoftversionMapper.deleteByPrimaryKey(versionid);
	}
	@Override
	public int countByExample(DlsoftversionExample example) {
		
		return llsoftversionMapper.countByExample(example);
	}
	
}
