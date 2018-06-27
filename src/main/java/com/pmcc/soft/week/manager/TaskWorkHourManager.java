package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.TaskAttachmentDao;
import com.pmcc.soft.week.dao.TaskWorkHourDao;
import com.pmcc.soft.week.domain.ProjectTaskWorkHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2015/11/11.
 */
@Transactional
@Service
public class TaskWorkHourManager {
    @Autowired
    private TaskWorkHourDao taskWorkHourDao;

    @Autowired
    private TaskAttachmentDao taskAttachmentDao;

    public int insertWorkHour(ProjectTaskWorkHour twh){
        return taskWorkHourDao.insertWorkHour(twh);
    }

    public List<ProjectTaskWorkHour> findWorkHourByTaskOid(String taskOid){
        return taskWorkHourDao.findWorkHourByTaskOid(taskOid);
    }

    public void deleteWorkHourByTaskOid(String taskOid){
        taskWorkHourDao.deleteWorkHourByTaskOid(taskOid);
    }

    public int deleteWorkHour(String oid){
        taskAttachmentDao.deleteAttachmentByWorkHourOid(oid);
        return taskWorkHourDao.deleteWorkHour(oid);
    }

    public int updateWorkHour(ProjectTaskWorkHour twh){
        return taskWorkHourDao.updateWorkHour(twh);
    }

    public ProjectTaskWorkHour findWorkHourByOid(ProjectTaskWorkHour projectTaskWorkHour){
        return taskWorkHourDao.findWorkHourByOid(projectTaskWorkHour);
    }
}
