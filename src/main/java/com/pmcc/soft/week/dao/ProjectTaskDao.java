package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ProjectTask;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/10/26.
 */
@Repository
public class ProjectTaskDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/ProjectTaskMapper";
    public List<ProjectTask> queryAllTask(ProjectTask pt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryAllTask", pt);
    }

    public List<ProjectTask> query(String projectOid,String parentTaskOid) {
        ProjectTask pt = new ProjectTask();
        pt.setProjectOid(projectOid);
        pt.setParentTaskOid(parentTaskOid);
        return this.getSqlSession().selectList(NAME_SPACE + ".queryTask", pt);
    }

    public ProjectTask findByOid(String oid){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findByOid", oid);
    }

    public List<ProjectTask> findTaskByOid(String oid){
        return this.getSqlSession().selectList(NAME_SPACE + ".findTaskByOid", oid);
    }

    public int insert(ProjectTask pt) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", pt);
        return res;
    }

    public int deleteTask(String oid) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteTask", oid);
        return res;
    }

    public int update(ProjectTask pt) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", pt);
        return res;
    }

    public int modifyStatus(ProjectTask pt) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".modifyStatus", pt);
        return res;
    }

    public int save(ProjectTask pt) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".save", pt);
        return res;
    }

    public List<ProjectTask> findTaskCount(ProjectTask pt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findTaskCount", pt);
    }

    public List<ProjectTask> findTaskCountByResponsiblePersonName(ProjectTask pt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findTaskCountByResponsiblePersonName", pt);
    }

    public List<ProjectTask> findTaskForReport(ProjectTask projectTask){
        return  this.getSqlSession().selectList(NAME_SPACE+".findTaskForReport",projectTask);
    }
    public List<ProjectTask> findTaskForWeek(ProjectTask projectTask){
        return  this.getSqlSession().selectList(NAME_SPACE+".findTaskForWeek",projectTask);
    }
}
