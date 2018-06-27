package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.DlnoticemanageMapper;
import com.hndl.mobileplatform.model.Dlnoticemanage;
import com.hndl.mobileplatform.model.DlnoticemanageExample;
import com.hndl.mobileplatform.model.DlroomnoticemessageWithBLOBs;
import com.hndl.mobileplatform.service.DlNoticeManageService;
import com.pmcc.soft.core.common.BaseVO;
@Service
public class DlNoticeManageServiceImpl implements DlNoticeManageService{
	@Resource
	DlnoticemanageMapper dlnoticemanageMapper;

	@Override
	public List<Dlnoticemanage> selectByExample(DlnoticemanageExample example) {
		// TODO Auto-generated method stub
		return dlnoticemanageMapper.selectByExample(example);
	}

	@Override
	public int insert(Dlnoticemanage record) {
		// TODO Auto-generated method stub
		return dlnoticemanageMapper.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(String fileid) {
		// TODO Auto-generated method stub
		return dlnoticemanageMapper.deleteByPrimaryKey(fileid);
	}


	@Override
	public List<Dlnoticemanage> selectByExampleWithBLOBs(
			DlnoticemanageExample example,BaseVO vo) {
		vo.setOrderField("createtime");
		vo.setStatus("typeasc");
		example.setVo(vo);
		return dlnoticemanageMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int updateByPrimaryKeySelective(Dlnoticemanage record) {
		
		return dlnoticemanageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Dlnoticemanage selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dlnoticemanageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int countByExample(DlnoticemanageExample example) {
		// TODO Auto-generated method stub
		return dlnoticemanageMapper.countByExample(example);
	}




	
}
