package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ProjectTaskWorkHour;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/11/11.
 */
@Repository
public class TaskWorkHourDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/taskWorkHourMapper";

    public int insertWorkHour(ProjectTaskWorkHour twh) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insertWorkHour", twh);
        return res;
    }

    public int deleteWorkHour(String oid){
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteWorkHour", oid);
        return res;
    }

    public void deleteWorkHourByTaskOid(String taskOid){
        this.getSqlSession().delete(NAME_SPACE + ".deleteWorkHourByTaskOid", taskOid);
    }

    public int updateWorkHour(ProjectTaskWorkHour twh){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updateWorkHour", twh);
        return res;
    }

    public List<ProjectTaskWorkHour> findWorkHourByTaskOid(String oid){
        return this.getSqlSession().selectList(NAME_SPACE + ".findWorkHourByTaskOid", oid);
    }
    public ProjectTaskWorkHour findWorkHourByOid(ProjectTaskWorkHour projectTaskWorkHour){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findWorkHourByOid",projectTaskWorkHour);
    }
}
