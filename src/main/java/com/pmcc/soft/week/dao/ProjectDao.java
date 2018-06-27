package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Project;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/8/18.
 */
@Repository
public class ProjectDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/projectMapper";


    public int insert(Project pr) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", pr);
        return res;
    }

    public List<Project> queryProject(Project object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryProject", object);
    }

    public List<Project> queryProjectByChargePersonId(String chargePersonId) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryProjectByChargePersonId", chargePersonId);
    }

    public int update(String oid){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", oid);
        return res;
    }

    public int updates(String oid){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updates", oid);
        return res;
    }

    public Project findProjectByOid(String oid){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findProjectByOid", oid);
    }

    public Project findProjectByOidAndByPersonId(Project object){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findProjectByOidAndByPersonId", object);
    }

    public int delete(String oid){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".delete", oid);
        return res;
    }

    public List<Project> queryProjectByStatus(Project project) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryProjectByStatus", project);
    }

    public int updateSchedule(Project project){
        return this.getSqlSession().update(NAME_SPACE + ".updateSchedule", project);
    }

    public Project findProjectByProjectName(String projectName){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findProjectByProjectName", projectName);
    }

    public int updateProjectDetail(Project project){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updateProjectDetail", project);
        return res;
    }
}
