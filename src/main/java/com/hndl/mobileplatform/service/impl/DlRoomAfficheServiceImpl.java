package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlroomafficheMapper;
import com.hndl.mobileplatform.model.Dlroomaffiche;
import com.hndl.mobileplatform.model.DlroomafficheExample;
import com.hndl.mobileplatform.service.DlRoomAfficheService;
import com.pmcc.soft.core.common.BaseVO;
@Service
public class DlRoomAfficheServiceImpl implements DlRoomAfficheService{
	@Resource
	DlroomafficheMapper  dlroomafficheMapper;  
	@Override
	public List<Dlroomaffiche> selectByExample(DlroomafficheExample example) {
		return dlroomafficheMapper.selectByExample(example);
	}

	@Override
	public int insert(Dlroomaffiche record) {
		return dlroomafficheMapper.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(String afficheid) {
		return dlroomafficheMapper.deleteByPrimaryKey(afficheid);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Dlroomaffiche record) {
		
		return dlroomafficheMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public List<Dlroomaffiche> selectByExampleWithBLOBs(
			DlroomafficheExample example) {
		return dlroomafficheMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public List<Dlroomaffiche> selectByExampleWithBLOBs(
			DlroomafficheExample example,BaseVO vo) {
		vo.setOrderField("createtime");
		example.setVo(vo);
		return dlroomafficheMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public int countByExample(DlroomafficheExample example) {
		return dlroomafficheMapper.countByExample(example);
	}

	@Override
	public List<Dlroomaffiche> selectByExample(DlroomafficheExample example,
			BaseVO vo) {
		vo.setOrderField("createtime");
		example.setVo(vo);
		return dlroomafficheMapper.selectByExample(example);
	}



}
