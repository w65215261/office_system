package com.pmcc.soft.core.organization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.ContactItemInfoDao;
import com.pmcc.soft.core.organization.domain.ContactItemInfo;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Service
@Transactional
public class ContactItemInfoManager {
	
	@Autowired
	public ContactItemInfoDao contactItemInfoDao;
	
	/**
	 * 新增或修改
	 * @param ei
	 */
	public void saveOrUpdate(ContactItemInfo cii){
		if (cii.getId() != null && !cii.getId().toString().equals("")) {
			contactItemInfoDao.update(cii);
		} else {
			cii.setId(UUIDGenerator.getUUID());
			cii.setDelFlag(0);
			contactItemInfoDao.insert(cii);
		}
		
	}
	
	/**
	 * 删除
	 * @param ei
	 */
	public void delete(ContactItemInfo cii){
		contactItemInfoDao.delete(cii);
	}
	
	/**
	 * 获取通讯录列表
	 * @param ei
	 * @return
	 */
	public List<ContactItemInfo> query(ContactItemInfo cii){
		return contactItemInfoDao.query(cii);
	}
	
	/**
	 * 获取一条通讯信息
	 * @param ei
	 * @return
	 */
	public ContactItemInfo find(ContactItemInfo cii) {
		return contactItemInfoDao.query(cii).get(0);
	}

}
