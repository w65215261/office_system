package com.pmcc.soft.core.organization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.ContactGroupInfoDao;
import com.pmcc.soft.core.organization.domain.ContactGroupInfo;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Service
@Transactional
public class ContactGroupInfoManager {
	
	@Autowired
	ContactGroupInfoDao contactGroupInfoDao;
	
	/**
	 * 新增或修改
	 * @param ei
	 */
	public void saveOrUpdate(ContactGroupInfo cgi){
		if (cgi.getId() != null && !cgi.getId().toString().equals("")) {
			contactGroupInfoDao.update(cgi);
		} else {
			cgi.setId(UUIDGenerator.getUUID());
			cgi.setDelFlag(0);
			contactGroupInfoDao.insert(cgi);
		}
		
	}
	
	/**
	 * 删除
	 * @param ei
	 */
	public void delete(ContactGroupInfo cgi){
		contactGroupInfoDao.delete(cgi);
	}
	
	/**
	 * 获取分组列表
	 * @param ei
	 * @return
	 */
	public List<ContactGroupInfo> query(ContactGroupInfo cgi){
		return contactGroupInfoDao.query(cgi);
	}
	
	/**
	 * 获取一条分组信息
	 * @param ei
	 * @return
	 */
	public ContactGroupInfo find(ContactGroupInfo cgi) {
		return contactGroupInfoDao.query(cgi).get(0);
	}

}
