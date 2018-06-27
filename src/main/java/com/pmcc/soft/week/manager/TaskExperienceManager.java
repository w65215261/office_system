package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.TaskAttachmentDao;
import com.pmcc.soft.week.dao.TaskExperienceDao;
import com.pmcc.soft.week.domain.TaskExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2015/11/12.
 */
@Transactional
@Service
public class TaskExperienceManager {
    @Autowired
    private TaskExperienceDao taskExperienceDao;

    @Autowired
    private TaskAttachmentDao taskAttachmentDao;

    public int insertExperience(TaskExperience te){
        return taskExperienceDao.insertExperience(te);
    }

    public List<TaskExperience> findExperienceByTaskOid(String taskOid){
        return taskExperienceDao.findExperienceByTaskOid(taskOid);
    }

    public void deleteExperienceByTaskOid(String taskOid){
        taskExperienceDao.deleteExperienceByTaskOid(taskOid);
    }

    public int deleteExperience(String oid){
        taskAttachmentDao.deleteAttachmentByExperienceOid(oid);
        return taskExperienceDao.deleteExperience(oid);
    }

    public int updateExperience(TaskExperience te){
        return taskExperienceDao.updateExperience(te);
    }

    public TaskExperience findExperienceByOid(String oid){
        return taskExperienceDao.findExperienceByOid(oid);
    }
}
