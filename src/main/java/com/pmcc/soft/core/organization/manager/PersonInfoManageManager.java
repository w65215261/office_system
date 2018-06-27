package com.pmcc.soft.core.organization.manager;

import com.pmcc.soft.core.organization.dao.PersonInfoManageDao;
import com.pmcc.soft.core.organization.domain.PersonInfoManage;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Transactional
@Service
public class PersonInfoManageManager {
    @Autowired
    PersonInfoManageDao personInfoManageDao;

    public List<PersonInfoManage> query(PersonInfoManage personInfoManage) {
        return personInfoManageDao.query(personInfoManage);
    }

    public List<PersonInfoManage> queryByName(PersonInfoManage personInfoManage) {
        return personInfoManageDao.queryByName(personInfoManage);
    }

    public List<PersonInfoManage> queryByDepartmentId(PersonInfoManage personInfoManage) {
        return personInfoManageDao.queryByDepartmentId(personInfoManage);
    }

    public int insert(PersonInfoManage pm) {
        pm.setId(UUIDGenerator.getUUID());
        return personInfoManageDao.insert(pm);
    }

    public int update(PersonInfoManage pm) {
        return personInfoManageDao.update(pm);
    }

    public PersonInfoManage queryByCname(PersonInfoManage pm) {
        return personInfoManageDao.queryByCname(pm);
    }

    public PersonInfoManage queryByOid(PersonInfoManage pm) {
        return personInfoManageDao.queryByOid(pm);
    }

    public int delete(String id) {
        return personInfoManageDao.delete(id);
    }

    public List<PersonInfoManage> findByOrganizationInfoId(String id) {
        return personInfoManageDao.findByOrganizationInfoId(id);
    }

    /**
     * 移动端更新人员信息
     *
     * @param pm
     * @return
     */
    public int updateApp(PersonInfoManage pm) {
        pm.setNickName(CommonUtils.toEncoding(pm.getNickName() ));
        pm.setAddress(CommonUtils.toEncoding(pm.getAddress()));
        return personInfoManageDao.update(pm);
    }
}
