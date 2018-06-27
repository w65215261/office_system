package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlroompostMapper;
import com.hndl.mobileplatform.model.Dlroompost;
import com.hndl.mobileplatform.model.DlroompostExample;
import com.hndl.mobileplatform.model.DlroompostWithBLOBs;
import com.hndl.mobileplatform.service.DlroompostService;
import com.pmcc.soft.core.common.BaseVO;
@Service
public class DlroompostServiceImpl implements DlroompostService {
	@Resource
	DlroompostMapper dlroompostMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		
		return dlroompostMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DlroompostWithBLOBs record) {
		
		return dlroompostMapper.insert(record);
	}

	@Override
	public List<DlroompostWithBLOBs> selectByExampleWithBLOBs(
			DlroompostExample example) {
		
		return dlroompostMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(DlroompostWithBLOBs record) {
		
		return dlroompostMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public List<Dlroompost> selectByExample(DlroompostExample example, BaseVO vo) {
		vo.setOrderField("createtime");
		example.setVo(vo);
		return dlroompostMapper.selectByExample(example);
	}

	@Override
	public int countByExample(DlroompostExample example) {
		
		return dlroompostMapper.countByExample(example);
	}

	@Override
	public DlroompostWithBLOBs selectByPrimaryKey(String id) {
		
		return dlroompostMapper.selectByPrimaryKey(id);
	}

}
