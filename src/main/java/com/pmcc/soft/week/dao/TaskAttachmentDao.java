package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.TaskAttachment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/11/16.
 */
@Repository
public class TaskAttachmentDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/TaskAttachmentMapper";

    public int insert(TaskAttachment mr) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", mr);
        return res;
    }

    public int deleteAttachment(TaskAttachment mr) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteAttachment", mr);
        return res;
    }

    public void deleteAttachmentByExperienceOid(String experienceOid){
        this.getSqlSession().delete(NAME_SPACE + ".deleteAttachmentByExperienceOid", experienceOid);
    }

    public void deleteAttachmentByWorkHourOid(String workHourOid){
        this.getSqlSession().delete(NAME_SPACE + ".deleteAttachmentByWorkHourOid", workHourOid);
    }

    public void deleteAttachmentByTaskOid(String taskOid){
        this.getSqlSession().delete(NAME_SPACE + ".deleteAttachmentByTaskOid", taskOid);
    }

    public List<TaskAttachment> findTaskAttachmentByProjectOid(TaskAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findTaskAttachmentByProjectOid", object);
    }

    public List<TaskAttachment> findTaskAttachmentByTaskOid(TaskAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findTaskAttachmentByTaskOid", object);
    }

    public List<TaskAttachment> findWorkHourAttachmentByTaskOid(TaskAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findWorkHourAttachmentByTaskOid", object);
    }

    public List<TaskAttachment> findExperienceAttachmentByTaskOid(TaskAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findExperienceAttachmentByTaskOid", object);
    }
}
