package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.NoteBook;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2016/01/06.
 */
@Repository
public class NoteBookDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/noteBookMapper";


    public List<NoteBook> findBooksLength(String userId) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findBooksLength", userId);
    }

    public List<NoteBook> findNoteBooks(NoteBook nb) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findNoteBooks", nb);
    }

    public NoteBook findNoteBookByOid(String noteBookOid) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findNoteBookByOid", noteBookOid);
    }

    public int restoreNoteBook(NoteBook nb) {
        return this.getSqlSession().update(NAME_SPACE + ".restoreNoteBook", nb);
    }

    public NoteBook findNoteBookByDeFlag(String noteBookOid) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findNoteBookByDeFlag", noteBookOid);
    }

    public int addNoteBook(NoteBook nb) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".addNoteBook", nb);
        return res;
    }

    public int deleteNoteBook(NoteBook nb) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".deleteNoteBook", nb);
        return res;
    }

    public int deleteNoteBookTrue(String notebookOid) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteNoteBookTrue", notebookOid);
        return res;
    }

    public int modifyNoteBook(NoteBook nb) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".modifyNoteBook", nb);
        return res;
    }

}


