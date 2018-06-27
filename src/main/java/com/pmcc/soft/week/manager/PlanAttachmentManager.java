package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.PlanAttachmentDao;
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
public class PlanAttachmentManager {
    @Autowired
    private PlanAttachmentDao planAttachmentDao;

    public List<TaskAttachment> findPlanAttachmentByTaskOId(TaskAttachment taskAttachment) {
        return planAttachmentDao.findPlanAttachmentByTaskOId(taskAttachment);
    }

    public List<TaskAttachment> findPlanAttachmentByOId(TaskAttachment taskAttachment) {
        return planAttachmentDao.findPlanAttachmentByOId(taskAttachment);
    }

    public int deleteAttachment(TaskAttachment taskAttachment){
        return planAttachmentDao.deleteAttachment(taskAttachment);
    }

    public TaskAttachment findPlanAttachment(TaskAttachment taskAttachment){
        return planAttachmentDao.findPlanAttachment(taskAttachment);
    }
}
