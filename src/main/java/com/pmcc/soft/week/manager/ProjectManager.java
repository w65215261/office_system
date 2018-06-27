package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.ProjectDao;
import com.pmcc.soft.week.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *Created by xuxiaodong on 2015/10/20.
 */

@Transactional
@Service
public class ProjectManager {

    @Autowired
    private ProjectDao projectDao;

    public int insert(Project project){
        return projectDao.insert(project);
    }
    /**
     * 查找所有的项目
     * @param object
     * @return
     */
    public List<Project> queryProject(Project object) {
        return projectDao.queryProject(object);
    }
    /**
     * 根据负责人查找所有项目
     * @param chargePersonId
     * @return
     */
    public List<Project>   queryProjectByChargePersonId(String chargePersonId){
        return projectDao.queryProjectByChargePersonId(chargePersonId);
    }
    /**
     * 修改项目状态为完成
     * @param oid
     * @return
     */
    public int update(String oid ){
        return projectDao.update(oid);
    }
    /**
     * 修改项目状态,y用于前台重启重启功能
     * @param oid
     * @return
     */
    public int updates(String oid ){
        return projectDao.updates(oid);
    }

    /**
     * 根据项目的OID查找项目
     * @param oid
     * @return
     */
    public Project findProjectByOid(String oid){
        return projectDao.findProjectByOid(oid);
    }

    public int delete(String oid ){
        return projectDao.delete(oid);
    }
    /**
     * 根据项目的状态查找所有项目
     * @param project
     * @return
     */
    public List<Project> queryProjectByStatus(Project project) {
        return projectDao.queryProjectByStatus(project);
    }
    /**
     * 修改项目进度
     * @param project
     * @return
     */
    public int updateSchedule(Project project){
        return projectDao.updateSchedule(project);
    }
    /**
     * 根据项目名称查找项目
     * @param projectName
     * @return
     */
    public Project findProjectByProjectName(String projectName){
        return  projectDao.findProjectByProjectName(projectName);
    };
    /**
     * 修改项目详情(目前未使用)
     * @param project
     * @return
     */
    public int updateProjectDetail(Project project ){
        return projectDao.updateProjectDetail(project);
    }
    /**
     * 根据审批人查找项目的OID
     * @param object
     * @return
     */
    public Project findProjectByOidAndByPersonId(Project object){
        return projectDao.findProjectByOidAndByPersonId(object);
    }
}
