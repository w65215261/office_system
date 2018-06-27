package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ApprovalData;
import com.pmcc.soft.week.domain.SystemAudit;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统审核
 * Created by LvXL on 2016/4/27.
 */
@Repository
public class SystemAuditDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/SystemAuditMapper";

    /**
     * 修改
     * @param systemAudit
     * @return
     */
    public int save(SystemAudit systemAudit){
        return this.getSqlSession().insert(NAME_SPACE + ".save", systemAudit);
    }

    public List<SystemAudit> query(SystemAudit systemAudit){
        return this.getSqlSession().selectList(NAME_SPACE + ".query", systemAudit);
    }
    public List<SystemAudit> queryAuditPerson(SystemAudit systemAudit){
        return this.getSqlSession().selectList(NAME_SPACE + ".queryAuditPerson", systemAudit);
    }

    public List<SystemAudit> queryAuditRemark(SystemAudit systemAudit){
        return this.getSqlSession().selectList(NAME_SPACE + ".queryAuditRemark", systemAudit);
    }

}
