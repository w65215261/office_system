package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Note;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2016/01/06.
 */
@Repository
public class NoteDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/noteMapper";

    public List<Note> findNotes(Note note) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findNotes", note);
    }

    public List<Note> searchNoteByNoteName(Note note) {
        return this.getSqlSession().selectList(NAME_SPACE + ".searchNoteByNoteName", note);
    }

    public List<Note> findRecycleBin(Note note) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findRecycleBin", note);
    }

    public List<Note> findNotesTitle(Note note) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findNotesTitle", note);
    }
    public List<Note> findRecoverNote(Note note) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findRecoverNote", note);
    }
    public Note findNoteDetailsByNoteOid(Note note) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findNoteDetailsByNoteOid", note);
    }

    public List<Note> findNotesByDeFlag(Note note) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findNotesByDeFlag", note);
    }
    public int addNote(Note note) {
        return this.getSqlSession().insert(NAME_SPACE + ".addNote", note);
    }

    public int deleteNote(Note note) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".deleteNote", note);
        return res;
    }

    public int deleteNotes(Note note) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".deleteNotes", note);
        return res;
    }

    public int deleteNoteTrue(Note note) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".deleteNoteTrue", note);
        return res;
    }

    public void modifyNoteDetails(Note note){
        this.getSqlSession().update(NAME_SPACE + ".modifyNoteDetails", note);
    }

    public void restoreNote(Note note){
        this.getSqlSession().update(NAME_SPACE + ".restoreNote", note);
    }
}


