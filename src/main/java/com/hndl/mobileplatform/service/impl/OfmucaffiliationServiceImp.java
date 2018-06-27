package com.hndl.mobileplatform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.dao.OfmucaffiliationMapper;
import com.hndl.mobileplatform.model.Ofmucaffiliation;
import com.hndl.mobileplatform.model.OfmucaffiliationExample;
import com.hndl.mobileplatform.service.OfmucaffiliationService;
@Service
public class OfmucaffiliationServiceImp implements OfmucaffiliationService {
	@Resource
	OfmucaffiliationMapper ofmucaffiliationMapper;
	@Override
	public int insert(Ofmucaffiliation record) {
		return ofmucaffiliationMapper.insert(record);
	}
	@Override
	public List<Ofmucaffiliation> selectByExample(
			OfmucaffiliationExample example) {
		return ofmucaffiliationMapper.selectByExample(example);
	}

}
