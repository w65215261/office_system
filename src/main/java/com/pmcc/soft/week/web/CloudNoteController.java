package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.week.domain.*;
import com.pmcc.soft.week.manager.NoteBookManager;
import com.pmcc.soft.week.manager.NoteManager;
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
import java.util.*;

/**
 * Created by asus on 2016/01/05.
 */
@Controller
@RequestMapping("cloudNote")
public class CloudNoteController {

    @Autowired
    NoteBookManager noteBookManager;

    @Autowired
    NoteManager noteManager;

    /**
     * 笔记总览
     * @param session
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(HttpSession session) {
        ModelAndView mav = new ModelAndView("/cloudNote/cloudNote");
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String userId = loginUser.getId();
        Note note = new Note();
        note.setUserOid(userId);
        List<Note> noteList = noteManager.findNotes(note);
        //查找所有的笔记本数
        int bookSize = noteBookManager.findBooksLength(userId);
        mav.addObject("noteList",noteList);
        mav.addObject("bookSize",bookSize);
        return mav;
    }

    @RequestMapping(value = "/findBooksLength", method = RequestMethod.GET)
    @ResponseBody
    public int findBooksLength(HttpSession session) {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String userId = loginUser.getId();
        return noteBookManager.findBooksLength(userId);
    }



    @RequestMapping(value = "/queryAlltree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> findNoteBooks(HttpSession session){
        PersonManage  user = (PersonManage) session.getAttribute("loginUser");
        String userId = user.getId();
        NoteBook nb = new NoteBook();
        nb.setUserOid(userId);
        nb.setDelFlag("0");
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        TreeNode node=new TreeNode();
        node=noteBookManager.findNodeBooks(nb);
        treeNodes.add(node);
        return treeNodes;
    }

    /**
     * 根据笔记本ID查找笔记
     * @param note
     * @param session
     * @return
     */
    @RequestMapping(value = "/findNotesByNotebookOid", method = RequestMethod.POST)
    @ResponseBody
    public List<Note> findNotesByNotebookOid(Note note,HttpSession session) {
        PersonManage loginUser = (PersonManage) session.getAttribute("loginUser");
        String userId = loginUser.getId();
        note.setUserOid(userId);
        List<Note> notesList = noteManager.findNotes(note);
        return notesList;
    }

    /**
     * 根据笔记名字查找名字相似的笔记
     * @param note
     * @param session
     * @return
     */
    @RequestMapping(value = "/searchNote", method = RequestMethod.POST)
    @ResponseBody
    public List<Note> searchNoteByNoteName(Note note,HttpSession session) {
        PersonManage loginUser = (PersonManage) session.getAttribute("loginUser");
        String userId = loginUser.getId();
        note.setUserOid(userId);
        List<Note> notesList = noteManager.searchNoteByNoteName(note);
        return notesList;
    }




    /**
     * 根据笔记ID查找对应的详情
     * @param note
     * @param session
     * @return
     */
    @RequestMapping(value = "/findNoteDetailsByNoteOid", method = RequestMethod.POST)
    @ResponseBody
    public Note findNoteDetailsByNoteOid(Note note,HttpSession session) {
        PersonManage loginUser = (PersonManage) session.getAttribute("loginUser");
        String userId = loginUser.getId();
        note.setUserOid(userId);
        Note nt = noteManager.findNoteDetailsByNoteOid(note);
        if(nt !=null){
            nt.setNoteBookName(noteBookManager.findNoteBookByOid(nt.getNotebookOid()).getNotebookName());
        }

        return nt;
    }

    /**
     * 添加笔记本
     * @param nb
     * @param session
     * @return
     */
    @RequestMapping(value = "/addNoteBook", method = RequestMethod.POST)
    @ResponseBody
    public NoteBook addNoteBook(NoteBook nb,HttpSession session) {
        PersonManage loginUser = (PersonManage) session.getAttribute("loginUser");
        String userId = loginUser.getId();
        nb.setUserOid(userId);
        NoteBook noteBook = noteBookManager.addNoteBook(nb);
        return noteBook;
    }

    /**
     * 删除笔记本
     * @param nb
     * @return
     */
    @RequestMapping(value = "/deleteNoteBook", method = RequestMethod.POST)
    @ResponseBody
    public String deleteNoteBook(NoteBook nb) {
        int res = 0;
        int i = noteBookManager.deleteNoteBook(nb);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 修改笔记本名称
     * @param nb
     * @return
     */
    @RequestMapping(value = "/modifyNoteBook", method = RequestMethod.POST)
    @ResponseBody
    public String modifyNoteBook(NoteBook nb) {
        int res = 0;
        int i = noteBookManager.modifyNoteBook(nb);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 添加笔记
     * @param note
     * @param session
     * @return
     */
    @RequestMapping(value = "/addNote", method = RequestMethod.POST)
    @ResponseBody
    public String addNote(Note note,HttpSession session) {
        PersonManage loginUser = (PersonManage) session.getAttribute("loginUser");
        String userId = loginUser.getId();
        note.setUserOid(userId);
        int res = 0;
        int i = noteManager.addNote(note);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 删除笔记
     * @param note
     * @return
     */
    @RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
    @ResponseBody
    public String deleteNote(Note note) {
        int res = 0;
        int i = noteManager.deleteNote(note);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 修改笔记内容
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
    @RequestMapping(value = "/findRecycleBin", method = RequestMethod.POST)
    @ResponseBody
    public List<Note> findRecycleBin(Note note,HttpSession session) {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String userId = loginUser.getId();
        note.setUserOid(userId);
        List<Note> notesList = noteManager.findRecycleBin(note);
        return notesList;
    }

    @RequestMapping(value = "/restoreNote", method = RequestMethod.POST)
    @ResponseBody
    public NoteBook restoreNote(Note note) {
        noteManager.restoreNote(note);
        NoteBook nb = noteBookManager.findNoteBookByDeFlag(note.getNotebookOid());
        if(!"".equals(nb) && nb != null){
            NoteBook notebook = new NoteBook();
            notebook.setOid(note.getNotebookOid());
            notebook.setNotebookCreateTime(new Date());
            noteBookManager.restoreNoteBook(notebook);
        }
        return nb;
    }

    @RequestMapping(value = "/deleteNoteTrue", method = RequestMethod.POST)
    @ResponseBody
    public String deleteNoteTrue(Note note) {
        int res = 0;
        int i = noteManager.deleteNoteTrue(note);
        List<Note> notes = noteManager.findNotesByDeFlag(note.getNotebookOid());
        if(notes.size() == 0){
            noteBookManager.deleteNoteBookTrue(note.getNotebookOid());
        }
        if (res == i) {
            return "fail";
        } else {
            return "success";
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
