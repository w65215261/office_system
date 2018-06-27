package com.pmcc.soft.rest;

import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.week.domain.Note;
import com.pmcc.soft.week.domain.NoteBook;
import com.pmcc.soft.week.manager.NoteBookManager;
import com.pmcc.soft.week.manager.NoteManager;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2016/01/05.
 */
@Controller
@RequestMapping("cloudNote")
public class CloudRestNoteController {

    @Autowired
    NoteBookManager noteBookManager;

    @Autowired
    NoteManager noteManager;

    /**
     * 笔记总览
     * @param request
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public List<Note> show(HttpServletRequest request) {
        String userId =request.getParameter("personId");
        Note note = new Note();
        note.setUserOid(userId);
        List<Note> noteList = noteManager.findNotes(note);
        return noteList;
    }

//    @RequestMapping(value = "/queryAlltree", method = RequestMethod.GET)
//    @ResponseBody
//    public List<TreeNote> queryAlltree(HttpSession session){
//        PersonManage  user = (PersonManage) session.getAttribute("loginUser");
//        String userId = user.getId();
//        NoteBook nb = new NoteBook();
//        nb.setUserOid(userId);
//        TreeNote treeNote = noteBookManager.findNoteBooks(nb);
//        List<TreeNote> treeNotes=new ArrayList<TreeNote>();
//        treeNotes.add(treeNote);
//        return treeNotes;
//    }


    /**
     * 根据笔记本ID查找笔记
     * @param note
     * @param
     * @return
     */
    @RequestMapping(value = "/findNotesByNotebookOid", method = RequestMethod.GET)
    @ResponseBody
    public List<Note> findNotesByNotebookOid(Note note,HttpServletRequest request) {
        String userId =request.getParameter("personId");
        String noteBookId=request.getParameter("noteBookId");
        note.setUserOid(userId);
        note.setNotebookOid(noteBookId);
        List<Note> notesList = noteManager.findNotesTitle(note);

        return notesList;
    }

    /**
     * 根据笔记ID查找对应的详情
     * @param note
     * @param request
     * @return
     */
    @RequestMapping(value = "/findNoteDetailsByNoteOid", method = RequestMethod.GET)
    @ResponseBody
    public Note findNoteDetailsByNoteOid(Note note,HttpServletRequest request) {
        String userId =request.getParameter("personId");
        String oid=note.getOid();
        note.setUserOid(userId);
        note.setOid(oid);
        Note nt = noteManager.findNoteDetailsByNoteOid(note);
        return nt;
    }

    /**
     * 添加笔记本
     * @param nb
     * @param request
     * @return
     */
    @RequestMapping(value = "/addNoteBook", method = RequestMethod.GET)
    @ResponseBody
    public Boolean addNoteBook(NoteBook nb,HttpServletRequest request) {
        String userId =request.getParameter("personId");
        nb.setUserOid(userId);
        NoteBook noteBook = noteBookManager.addNoteBook(nb);
        boolean flag;
        if(noteBook!=null){
            flag=true;
         //   System.out.printf("success");
        }else{
            flag=false;
        }
        //System.out.printf("test="+flag);
        return flag;
    }

    /**
     * 删除笔记本
     * @param nb
     * @return
     */
    @RequestMapping(value = "/deleteNoteBook", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteNoteBook(NoteBook nb) {
        int res = 0;
        int i = noteBookManager.deleteNoteBook(nb);
        if (res == i) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 修改笔记本名称
     * @param nb
     * @return
     */
    @RequestMapping(value = "/modifyNoteBook", method = RequestMethod.GET)
    @ResponseBody
    public Boolean modifyNoteBook(NoteBook nb) {
        int res = 0;
        int i = noteBookManager.modifyNoteBook(nb);
        if (res == i) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 添加笔记
     * @param note
     * @param request
     * @return
     */
    @RequestMapping(value = "/addNote", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addNote(Note note,HttpServletRequest request) {
        String userId =request.getParameter("personId");
        note.setUserOid(userId);
        int res = 0;
        int i = noteManager.addNote(note);
        if (res == i) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 删除笔记
     * @param note
     * @return
     */
    @RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteNote(Note note) {
        int res = 0;
        int i = noteManager.deleteNote(note);
        if (res == i) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 修改笔记详情
     * @param note
     */
    @RequestMapping(value = "/modifyNoteDetails", method = RequestMethod.POST)
    @ResponseBody
    public void modifyNoteDetails(Note note) {
        note.setNoteLastModifyTime(new Date());
        noteManager.modifyNoteDetails(note);
    }
    /**
     * 查找回收站内容
     * @param note
     * @return
     */
    @RequestMapping(value = "/findRecycleBin", method = RequestMethod.GET)
    @ResponseBody
    public List<Note> findRecycleBin(Note note,HttpServletRequest request) {
        String userId =request.getParameter("personId");
        note.setUserOid(userId);
        List<Note> notesList = noteManager.findRecoverNote(note);
        return notesList;
    }

    /**
     * 恢复回收站
     * @param note
     * @return
     */
    @RequestMapping(value = "/restoreNote", method = RequestMethod.GET)
    @ResponseBody
    public Boolean restoreNote(Note note) {
        boolean flag=true;
        noteManager.restoreNote(note);
        String oid=note.getNotebookOid();
        NoteBook nb = noteBookManager.findNoteBookByDeFlag(oid);
        if(!"".equals(nb) && nb != null){
            NoteBook notebook = new NoteBook();
            notebook.setOid(note.getNotebookOid());
            notebook.setNotebookCreateTime(new Date());
            noteBookManager.restoreNoteBook(notebook);
            NoteBook n= noteBookManager.findNoteBookByDeFlag(oid);
            if(!"".equals(n) && n != null){
                flag=false;
            }else{
                flag=true;
            }
        }else{
            flag=true;
        }
        return flag;

    }

    @RequestMapping(value = "/deleteNoteTrue", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteNoteTrue(Note note) {
        int res = 0;
        int i = noteManager.deleteNoteTrue(note);
        List<Note> notes = noteManager.findNotesByDeFlag(note.getNotebookOid());
        if(notes.size() == 0){
            noteBookManager.deleteNoteBookTrue(note.getNotebookOid());
        }
        if (res == i) {
            return false;
        } else {
            return true;
        }
    }
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
