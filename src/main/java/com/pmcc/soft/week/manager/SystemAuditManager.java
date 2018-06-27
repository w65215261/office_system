package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.SystemAuditDao;
import com.pmcc.soft.week.domain.SystemAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 审批业务主表
 * Created by wangbin on 2016/4/25.
 */
@Transactional
@Service
public class SystemAuditManager {

    @Autowired
    SystemAuditDao systemAuditDao;

    /**
     * 新增
     * @param systemAudit
     * @return
     */
    public int save(SystemAudit systemAudit){

        systemAudit.setId(UUIDGenerator.getUUID());
        systemAudit.setAuditDate(new Date());
        return systemAuditDao.save(systemAudit);
    }

    public List<SystemAudit> query(SystemAudit systemAudit){
        return systemAuditDao.query(systemAudit);
    }

    public List<SystemAudit> queryAuditRemark(SystemAudit systemAudit){
        return systemAuditDao.queryAuditRemark(systemAudit);
    }

}
