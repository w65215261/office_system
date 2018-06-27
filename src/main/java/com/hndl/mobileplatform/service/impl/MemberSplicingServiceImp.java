package com.hndl.mobileplatform.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hndl.mobileplatform.service.MemberSplicingService;
import com.hndl.mobileplatform.service.PublicExcuteSqlService;
@Service
public class MemberSplicingServiceImp implements MemberSplicingService{
	@Resource
    private PublicExcuteSqlService publicExcuteSqlService;
	@Override
	public String UserId() {
		String sql = "SELECT o.propValue from ofProperty o WHERE o.name='xmpp.domain'";
		List<Map<String,String>> list = publicExcuteSqlService.executeSql(sql);
		return "@" + list.get(0).get("propValue");
	}

}
