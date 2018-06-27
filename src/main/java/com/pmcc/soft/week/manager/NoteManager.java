package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.NoteAttachmentDao;
import com.pmcc.soft.week.dao.NoteBookDao;
import com.pmcc.soft.week.dao.NoteDao;
import com.pmcc.soft.week.domain.Note;
import com.pmcc.soft.week.domain.NoteAttachment;
import com.pmcc.soft.week.domain.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2016/01/06.
 */
@Transactional
@Service
public class NoteManager {

    @Autowired
    NoteDao noteDao;
    @Autowired
    NoteAttachmentDao noteAttachmentDao;

    public List<Note> findRecycleBin(Note note) {
        return noteDao.findRecycleBin(note);
    }
    public List<Note> findNotes(Note note) {
        return noteDao.findNotes(note);
    }

    public List<Note> searchNoteByNoteName(Note note) {
        return noteDao.searchNoteByNoteName(note);
    }


    public void restoreNote(Note note) {
        NoteAttachment mr = new NoteAttachment();
        mr.setNoteOid(note.getOid());
        noteAttachmentDao.restoreAttachment(mr);
        noteDao.restoreNote(note);
    }

    public Note findNoteDetailsByNoteOid(Note note) {
        return noteDao.findNoteDetailsByNoteOid(note);
    }

    public int addNote(Note note){
        String noteOid = UUIDGenerator.getUUID();
        note.setOid(noteOid);
        note.setNoteCreateTime(new Date());
        note.setNoteLastModifyTime(new Date());
        note.setDelFlag("0");
        int res = noteDao.addNote(note);
        return res;
    }

    public int deleteNote(Note note){
        NoteAttachment mr = new NoteAttachment();
        mr.setNoteOid(note.getOid());
        noteAttachmentDao.deleteAttachmentByNoteOid(mr);
        return noteDao.deleteNote(note);
    }

    public int deleteNoteTrue(Note note){
        NoteAttachment mr = new NoteAttachment();
        mr.setNoteOid(note.getOid());
        noteAttachmentDao.deleteAttachmentByNoteOidTrue(mr);
        return noteDao.deleteNoteTrue(note);
    }
    public List<Note> findNotesByDeFlag(String notebookOid){
        Note note = new Note();
        note.setNotebookOid(notebookOid);
        return noteDao.findNotesByDeFlag(note);
    }


    public void modifyNoteDetails(Note note){
        noteDao.modifyNoteDetails(note);
    }

    public List<Note>findNotesTitle(Note note){
      return   noteDao.findNotesTitle(note);
    }
    public List<Note>findRecoverNote(Note note){
        return   noteDao.findRecoverNote(note);
    }
}
