package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.TaskAttachment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/11/20.
 */
@Repository
public class PlanAttachmentDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/TaskAttachmentMapper";


    public List<TaskAttachment> findPlanAttachmentByTaskOId(TaskAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findPlanAttachmentByTaskOId", object);
    }
    public List<TaskAttachment> findPlanAttachmentByOId(TaskAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findPlanAttachmentByOId", object);
    }
    public int deleteAttachment(TaskAttachment mr) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteAttachment", mr);
        return res;
    }
    public TaskAttachment findPlanAttachment(TaskAttachment taskAttachment){
        return  this.getSqlSession().selectOne(NAME_SPACE + ".findPlanAttachment", taskAttachment);
    }
}
