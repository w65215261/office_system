package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.OperationRecord;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
@Repository
public class OperationRecordDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/operationRecordMapper";
    public List<OperationRecord> findByProjectOid(OperationRecord or) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findByProjectOid", or);
    }

    public int insert(OperationRecord or) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", or);
        return res;
    }
}


