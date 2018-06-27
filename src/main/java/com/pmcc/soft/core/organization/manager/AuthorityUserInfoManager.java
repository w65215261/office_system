package com.pmcc.soft.core.organization.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmcc.soft.core.organization.dao.AuthorityUserInfoDao;
import com.pmcc.soft.core.organization.domain.AuthorityRoleInfo;
import com.pmcc.soft.core.organization.domain.AuthorityUserInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.UUIDGenerator;

@Transactional
@Service
public class AuthorityUserInfoManager {

	@Autowired
	private AuthorityUserInfoDao authorityUserInfoDao;

	public List<AuthorityUserInfo> query(String person,String role) {

		return authorityUserInfoDao.query(person,role);
	}
	
	public boolean isChecked(String person,String role){
		List<AuthorityUserInfo>  res =  authorityUserInfoDao.query(person,role);
		if(res!=null && res.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean hasMenu(String person){
		int res = authorityUserInfoDao.hasMenu(person);
		if(res>0){
			return true;
		}
		return false;
	}

	public AuthorityUserInfo findByid(AuthorityUserInfo userInfo){
		String person =userInfo.getId();
		return authorityUserInfoDao.findByid(person);
	}
	
	
	public void save(String roleItems, AuthorityUserInfo userInfo) {

		authorityUserInfoDao.deleteByUser(userInfo.getPerson().getId());

		if (roleItems != null) {
			String[] res = roleItems.split(",");
			AuthorityUserInfo o = null;
			AuthorityRoleInfo orole = null;
			PersonManage operson = null;
			for (String roleId : res) {
				o = new AuthorityUserInfo();
				orole = new AuthorityRoleInfo();
				operson = new PersonManage();
				o.setId(UUIDGenerator.getUUID());
				orole.setId(roleId);
				operson.setId(userInfo.getPerson().getId());
				o.setRole(orole);
				o.setPerson(operson);
				o.setRptPerson(userInfo.getRptPerson());
				o.setCreateTime(userInfo.getCreateTime());
				authorityUserInfoDao.insert(o);
			}

		}
	}

}
