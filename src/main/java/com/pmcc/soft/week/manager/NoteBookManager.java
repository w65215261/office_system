package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.NoteAttachmentDao;
import com.pmcc.soft.week.dao.NoteBookDao;
import com.pmcc.soft.week.dao.NoteDao;
import com.pmcc.soft.week.domain.Note;
import com.pmcc.soft.week.domain.NoteAttachment;
import com.pmcc.soft.week.domain.NoteBook;
import com.pmcc.soft.week.domain.TreeNode;
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
public class NoteBookManager {

    @Autowired
    NoteBookDao noteBookDao;
    @Autowired
    NoteDao noteDao;
    @Autowired
    NoteAttachmentDao noteAttachmentDao;

    public int findBooksLength(String userId){
        return noteBookDao.findBooksLength(userId).size();
    }

    public TreeNode findNodeBooks(NoteBook nb) {
        TreeNode treeNode = new TreeNode();
        treeNode.setHref("-1");
        treeNode.setText("所有笔记本");
        treeNode.setIcon("glyphicon glyphicon-briefcase");
            List<NoteBook> nbs = noteBookDao.findNoteBooks(nb);
            for(NoteBook book:nbs){
                TreeNode tn = new TreeNode(book.getOid(),book.getNotebookName(),"glyphicon glyphicon-book");
                treeNode.getNodes().add(tn);
            }
            return treeNode;
    }

    public NoteBook addNoteBook(NoteBook nb){
        String noteBookOid = UUIDGenerator.getUUID();
        nb.setOid(noteBookOid);
        nb.setNotebookCreateTime(new Date());
        nb.setDelFlag("0");
        noteBookDao.addNoteBook(nb);
        NoteBook noteBook = findNoteBookByOid(noteBookOid);
        return noteBook;
    }

    public NoteBook findNoteBookByOid(String noteBookOid){
        NoteBook noteBook = noteBookDao.findNoteBookByOid(noteBookOid);
        return noteBook;
    }

    public NoteBook findNoteBookByDeFlag(String oid){
        NoteBook noteBook = noteBookDao.findNoteBookByDeFlag(oid);
        return noteBook;
    }

    public void restoreNoteBook(NoteBook nb){
        noteBookDao.restoreNoteBook(nb);
    }

    public int deleteNoteBook(NoteBook nb){
        int res = noteBookDao.deleteNoteBook(nb);
        Note note = new Note();
        note.setNotebookOid(nb.getOid());
        noteDao.deleteNotes(note);
        note.setDelFlag("1");
        List<Note> ns = noteDao.findNotesByDeFlag(note);
        for(Note nt : ns){
            NoteAttachment mr = new NoteAttachment();
            mr.setNoteOid(nt.getOid());
            noteAttachmentDao.deleteAttachmentByNoteOid(mr);
        }
        note.setDelFlag("");
        List<Note> notes = noteDao.findNotesByDeFlag(note);
        if(notes.size() == 0){
            deleteNoteBookTrue(nb.getOid());
        }
        return res;
    }

    public void deleteNoteBookTrue(String notebookOid ){
        noteBookDao.deleteNoteBookTrue(notebookOid);
    }

    public int modifyNoteBook(NoteBook nb){
        int res = noteBookDao.modifyNoteBook(nb);
        return res;
    }

}
