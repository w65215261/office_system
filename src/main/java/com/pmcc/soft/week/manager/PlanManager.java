package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.PlanDao;
import com.pmcc.soft.week.domain.ProjectTask;
import com.pmcc.soft.week.domain.TreeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/10/26.
 */
@Transactional
@Service
public class PlanManager {
    @Autowired
    private PlanDao planDao;

    public boolean has(String projectOid,String parentTaskOid) {
        boolean res = false;
        List<ProjectTask> list = queryPlan(parentTaskOid);
        if (list != null && list.size() > 0) {
            res = true;
        }
        return res;
    }
    public int save(ProjectTask pt){
        return planDao.save(pt);
    }
    public List<ProjectTask> queryPlan(String parentTaskOid){
        return  planDao.queryPlan(parentTaskOid);
    }

    public List<TreeTask> queryAllPlan(ProjectTask pt,String id,TreeTask task){
        List<TreeTask>  tasks=new ArrayList<TreeTask>();
        String taskLevel = pt.getTaskLevel();
        String projectOid = pt.getProjectOid();
        //先查出根节点
        if(taskLevel.equals("0")){
            List<ProjectTask> taskList=	planDao.queryAllPlan(pt);
            if(taskList!=null && taskList.size()>0){
                for(int i = 0;i < taskList.size();i++){
                    TreeTask treeTask = new TreeTask();
                    treeTask.setTaskStatus(taskList.get(i).getTaskStatus());
                    treeTask.setResponsiblePersonName(taskList.get(i).getResponsiblePersonName());
                    treeTask.setEndTime(taskList.get(i).getEndTime());
                    treeTask.setOid(taskList.get(i).getOid());
                    treeTask.setTaskName(taskList.get(i).getTaskName());
                    treeTask.setHasStar(taskList.get(i).getHasStar());
                    tasks.add(treeTask);
                    pt.setTaskLevel("1");
                    if(has(projectOid, taskList.get(i).getOid())){
                        queryAllPlan(pt, taskList.get(i).getOid(), tasks.get(i));
                    }
                }
            }else{
                return tasks;
            }


        }else{
            List<ProjectTask> taskList=	planDao.queryPlan(id);

            for(ProjectTask projectTask :taskList){
                TreeTask taskTemp=new TreeTask();
                taskTemp.setTaskStatus(projectTask.getTaskStatus());
                taskTemp.setResponsiblePersonName(projectTask.getResponsiblePersonName());
                taskTemp.setEndTime(projectTask.getEndTime());
                taskTemp.setOid(projectTask.getOid());
                taskTemp.setTaskName(projectTask.getTaskName());
                taskTemp.setHasStar(projectTask.getHasStar());
                task.getTasks().add(taskTemp);
                if (has(projectOid, projectTask.getOid())){
                    queryAllPlan(pt, projectTask.getOid(), taskTemp);
                }

            }
            return tasks;
        }
        return tasks;
    }
    public ProjectTask findByOid(String oid){
        return  planDao.findByOid(oid);
    }

    public List<ProjectTask> findPlanByOid(String oid){
        return planDao.findPlanByOid(oid);
    }

    public void updateHasStar(ProjectTask projectTask){
        planDao.updateHasStar(projectTask);
    }

    public int updateProjectPlan(ProjectTask projectTask ){
        return planDao.updateProjectPlan(projectTask);
    }
}


