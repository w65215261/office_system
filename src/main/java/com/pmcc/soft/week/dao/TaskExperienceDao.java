package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.TaskExperience;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/11/13.
 */
@Repository
public class TaskExperienceDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/taskExperienceMapper";

    public int insertExperience(TaskExperience te) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insertExperience", te);
        return res;
    }

    public int deleteExperience(String oid){
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteExperience", oid);
        return res;
    }
    public void deleteExperienceByTaskOid(String taskOid){
        this.getSqlSession().delete(NAME_SPACE + ".deleteExperienceByTaskOid", taskOid);
    }

    public int updateExperience(TaskExperience te){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updateExperience", te);
        return res;
    }

    public List<TaskExperience> findExperienceByTaskOid(String oid){
        return this.getSqlSession().selectList(NAME_SPACE + ".findExperienceByTaskOid", oid);
    }
    public TaskExperience findExperienceByOid(String oid){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findExperienceByOid", oid);
    }
}
