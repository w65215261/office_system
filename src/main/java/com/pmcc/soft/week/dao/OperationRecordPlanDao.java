package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.OperationRecordPlan;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
@Repository
public class OperationRecordPlanDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/OperationRecordPlanMapper";
    public List<OperationRecordPlan> findOperation(OperationRecordPlan or) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findOperation", or);
    }

    public int insert(OperationRecordPlan or) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", or);
        return res;
    }
}


