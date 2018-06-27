package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.TaskAttachmentDao;
import com.pmcc.soft.week.domain.TaskAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2015/11/16.
 */
@Transactional
@Service
public class TaskAttachmentManager {
    @Autowired
    TaskAttachmentDao taskAttachmentDao;

    public int insert(TaskAttachment taskAttachment){
        taskAttachment.setId(UUIDGenerator.getUUID());
        return taskAttachmentDao.insert(taskAttachment);
    }

    public int deleteAttachment(TaskAttachment taskAttachment){
        return taskAttachmentDao.deleteAttachment(taskAttachment);
    }

    public void deleteAttachmentByTaskOid(String taskOid){
        taskAttachmentDao.deleteAttachmentByTaskOid(taskOid);
    }

    public void deleteAttachmentByExperienceOid(String experienceOid){
        taskAttachmentDao.deleteAttachmentByExperienceOid(experienceOid);
    }

    public void deleteAttachmentByWorkHourOid(String workHourOid){
        taskAttachmentDao.deleteAttachmentByWorkHourOid(workHourOid);
    }

    public List<TaskAttachment> findTaskAttachmentByProjectOid(TaskAttachment taskAttachment) {
        taskAttachment.setDelFlag("0");
        return taskAttachmentDao.findTaskAttachmentByProjectOid(taskAttachment);

    }

    public List<TaskAttachment> findTaskAttachmentByTaskOid(TaskAttachment taskAttachment) {
        return taskAttachmentDao.findTaskAttachmentByTaskOid(taskAttachment);

    }

    public List<TaskAttachment> findWorkHourAttachmentByTaskOid(TaskAttachment taskAttachment) {
        return taskAttachmentDao.findWorkHourAttachmentByTaskOid(taskAttachment);

    }

    public List<TaskAttachment> findExperienceAttachmentByTaskOid(TaskAttachment taskAttachment) {
        return taskAttachmentDao.findExperienceAttachmentByTaskOid(taskAttachment);

    }

}
