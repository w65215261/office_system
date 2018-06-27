package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.NoteAttachment;
import com.pmcc.soft.week.domain.TaskAttachment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangbin on 2016/3/25.
 */
@Repository
public class NoteAttachmentDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/NoteAttachmentMapper";

    public int insert(NoteAttachment mr) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", mr);
        return res;
    }

    public int deleteAttachment(NoteAttachment mr) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteAttachment", mr);
        return res;
    }

    public int deleteAttachmentByNoteOid(NoteAttachment mr) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteAttachmentByNoteOid", mr);
        return res;
    }

    public void restoreAttachment(NoteAttachment mr){
        this.getSqlSession().update(NAME_SPACE + ".restoreAttachment", mr);
    }

    public int deleteAttachmentByNoteOidTrue(NoteAttachment mr) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteAttachmentByNoteOidTrue", mr);
        return res;
    }

    public List<NoteAttachment> findNoteAttachmentByNoteOid(NoteAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findNoteAttachmentByNoteOid", object);
    }

}
