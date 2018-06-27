package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ProjectTask;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/10/26.
 */
@Repository
public class PlanDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/ProjectTaskMapper";


    /**
     * 根据OID查找任务
     * @param oid
     * @return
     */
    public ProjectTask findByOid(String oid){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findByOid", oid);
    }

    /**
     * 根据OID查找所有计划
     * @param oid
     * @return
     */
    public List<ProjectTask> findPlanByOid(String oid){
        return this.getSqlSession().selectList(NAME_SPACE + ".findTaskByOid", oid);
    }

    /**
     * 新增计划
     * @param pt
     * @return
     */
    public int save(ProjectTask pt) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".save", pt);
        return res;
    }

    /**
     * 查找所有计划
     * @param pt
     * @return
     */
    public List<ProjectTask> queryAllPlan(ProjectTask pt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryAllPlan", pt);
    }

    /**
     * 查找所有的子计划
     * @param parentTaskOid
     * @return
     */
    public List<ProjectTask> queryPlan(String parentTaskOid) {
        ProjectTask pt = new ProjectTask();
        pt.setParentTaskOid(parentTaskOid);
        return this.getSqlSession().selectList(NAME_SPACE + ".queryPlan", pt);
    }

    /**
     * 修改星标标志
     * @param projectTask
     */
    public void updateHasStar(ProjectTask projectTask){
        this.getSqlSession().update(NAME_SPACE + ".updateHasStar", projectTask);
    }

    /**
     * 修改计划
     * @param projectTask
     * @return
     */
    public int updateProjectPlan(ProjectTask projectTask){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updateProjectPlan", projectTask);
        return res;
    }
}
