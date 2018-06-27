package com.pmcc.soft.core.organization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.PersonInfoDao;
import com.pmcc.soft.core.organization.domain.PersonInfo;
import com.pmcc.soft.core.utils.EncryptMD5;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Transactional
@Service
public class PersonInfoManager {

	@Autowired
	private PersonInfoDao dao;

	// 添加用户
	public void addPerson(PersonInfo person) {
	      person.setId(UUIDGenerator.getUUID());
	      person.setPassWord(EncryptMD5.getMD5(person.getPassWord().trim().getBytes()));
	      person.setDelFlag("0");
	      person.setType("0");
		  dao.insert(person);

	}

	// 查找用户
	public PersonInfo findPerson(PersonInfo person) {

		List<PersonInfo> res = dao.query(person);
		PersonInfo result = null;

		if (res != null && res.size() > 0) {
			result = res.get(0);
		}

		return result;

	}

	// 获取所有用户列表
	public List<PersonInfo> query(PersonInfo person) {

		return dao.query(person);

	}

	// 修改明湖信息
	public void editPerson(PersonInfo person) {

		dao.update(person);

	}

	// 删除用户
	public void delete(PersonInfo person) {
		person.setDelFlag("1");
		dao.update(person);
	}

}
