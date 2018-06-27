package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.ProgrammeManagementDao;
import com.pmcc.soft.week.domain.ProgrammeManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2016/01/18.
 */
@Transactional
@Service
public class ProgrammeManagementManager {

    @Autowired
    ProgrammeManagementDao programmeManagementDao;

    public List<ProgrammeManagement> queryProgramme(String userOid) {
        return programmeManagementDao.queryProgramme(userOid);
    }

    public void insert(ProgrammeManagement pm){
        String oid = UUIDGenerator.getUUID();
        pm.setOid(oid);
        programmeManagementDao.insert(pm);
    }

    public void delete(String oid){
        programmeManagementDao.delete(oid);
    }

    public void update(ProgrammeManagement pm){
        programmeManagementDao.update(pm);
    }
    public List<ProgrammeManagement> query(String userOid) {
        return programmeManagementDao.query(userOid);
    }
    public ProgrammeManagement queryProgra(String oid){
        return programmeManagementDao.queryProgra(oid);
    }
}
