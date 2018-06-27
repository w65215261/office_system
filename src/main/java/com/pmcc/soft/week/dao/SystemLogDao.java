package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.SystemLog;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统操作记录表
 * Created by LvXL on 2016/5/9.
 */
@Repository
public class SystemLogDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/SystemLogMapper";

    public List<SystemLog> query(SystemLog systemLog){
        return this.getSqlSession().selectList(NAME_SPACE + ".query", systemLog);
    }

    public int insert(SystemLog log) {
        return this.getSqlSession().insert(NAME_SPACE + ".insert", log);
    }
}
