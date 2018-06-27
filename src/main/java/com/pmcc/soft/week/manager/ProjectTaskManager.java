package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.ProjectTaskDao;
import com.pmcc.soft.week.dao.TaskAttachmentDao;
import com.pmcc.soft.week.dao.TaskExperienceDao;
import com.pmcc.soft.week.dao.TaskWorkHourDao;
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
public class ProjectTaskManager {
    @Autowired
    private ProjectTaskDao projectTaskDao;

    @Autowired
    private TaskWorkHourDao taskWorkHourDao;

    @Autowired
    private TaskExperienceDao taskExperienceDao;

    @Autowired
    private TaskAttachmentDao taskAttachmentDao;

    public List<TreeTask> queryAllTask(ProjectTask pt,String id,TreeTask task){
        List<TreeTask>  tasks=new ArrayList<TreeTask>();
        String taskLevel = pt.getTaskLevel();
        String projectOid = pt.getProjectOid();
        //先查出根节点
        if(taskLevel.equals("0")){
            List<ProjectTask> taskList=	projectTaskDao.queryAllTask(pt);
            if(taskList!=null && taskList.size()>0){
                for(int i = 0;i < taskList.size();i++){
                    TreeTask treeTask = new TreeTask();
                    treeTask.setTaskStatus(taskList.get(i).getTaskStatus());
                    treeTask.setResponsiblePersonName(taskList.get(i).getResponsiblePersonName());
                    treeTask.setEndTime(taskList.get(i).getEndTime());
                    treeTask.setOid(taskList.get(i).getOid());
                    treeTask.setTaskName(taskList.get(i).getTaskName());
                    tasks.add(treeTask);
                    pt.setTaskLevel("1");
                    if(hasChild(projectOid,taskList.get(i).getOid())){
                        queryAllTask(pt, taskList.get(i).getOid(), tasks.get(i));
                    }
                }
            }else{
                return tasks;
            }


        }else{
            List<ProjectTask> taskList=	projectTaskDao.query(projectOid,id);

            for(ProjectTask projectTask :taskList){
                TreeTask taskTemp=new TreeTask();
                taskTemp.setTaskStatus(projectTask.getTaskStatus());
                taskTemp.setResponsiblePersonName(projectTask.getResponsiblePersonName());
                taskTemp.setEndTime(projectTask.getEndTime());
                taskTemp.setOid(projectTask.getOid());
                taskTemp.setTaskName(projectTask.getTaskName());
                task.getTasks().add(taskTemp);
                if (hasChild(projectOid,projectTask.getOid())){
                    queryAllTask(pt,projectTask.getOid(), taskTemp);
                }

            }
            return tasks;
        }
        return tasks;


    }

    public ProjectTask findByOid(String oid){
        return projectTaskDao.findByOid(oid);
    }

    public List<ProjectTask> findTaskByOid(String oid){
        return projectTaskDao.findTaskByOid(oid);
    }

    public int insert(ProjectTask pt){
        pt.setPlanTypeOid("0");
        return projectTaskDao.insert(pt);
    }

    public int deleteTask(String oid){
        int res = delete(oid);
        taskAttachmentDao.deleteAttachmentByTaskOid(oid);
        taskExperienceDao.deleteExperienceByTaskOid(oid);
        taskWorkHourDao.deleteWorkHourByTaskOid(oid);
        List<ProjectTask> list = findTaskByOid(oid);
        for(ProjectTask task: list){
            deleteTask(task.getOid());
        }
        return res;
    }

    public int delete(String oid){
        return projectTaskDao.deleteTask(oid);
    }

    public int update(ProjectTask pt){
        return projectTaskDao.update(pt);
    }

    public int modifyStatus(ProjectTask pt){
        return projectTaskDao.modifyStatus(pt);
    }

    public List<ProjectTask> query(String projectOid,String parentTaskOid){
        return  projectTaskDao.query(projectOid,parentTaskOid);
    }

    public boolean hasChild(String projectOid,String parentTaskOid) {
        boolean res = false;
        List<ProjectTask> list = query(projectOid,parentTaskOid);
        if (list != null && list.size() > 0) {
            res = true;
        }
        return res;
    }

    public int save(ProjectTask pt){
        pt.setOid(UUIDGenerator.getUUID());
        return projectTaskDao.save(pt);
    }

    public List<ProjectTask>  findTaskCount(ProjectTask pt){
        return projectTaskDao.findTaskCount(pt);
    }

    public List<ProjectTask>  findTaskCountByResponsiblePersonName(ProjectTask pt){
        return projectTaskDao.findTaskCountByResponsiblePersonName(pt);
    }

    public List<ProjectTask> findTaskForReport(ProjectTask projectTask){
        return projectTaskDao.findTaskForReport(projectTask);
    }
    public List<ProjectTask> findTaskForWeek(ProjectTask projectTask){
        return projectTaskDao.findTaskForWeek(projectTask);
    }
}


