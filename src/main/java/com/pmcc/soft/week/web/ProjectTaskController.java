package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.*;
import com.pmcc.soft.week.manager.ProjectTaskManager;
import com.pmcc.soft.week.manager.TaskExperienceManager;
import com.pmcc.soft.week.manager.TaskWorkHourManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by asus on 2015/10/26.
 */
@Controller
@RequestMapping("projectTask")
public class ProjectTaskController {

    @Autowired
    ProjectTaskManager projectTaskManager;

    @Autowired
    private PersonManageManager personManageManager;

    @Autowired
    private TaskWorkHourManager taskWorkHourManager;

    @Autowired
    private TaskExperienceManager taskExperienceManager;

    /**
     * 任务总览
     * @param pt
     * @param request
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ProjectTask pt,HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView("/project/projectTask");
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String loginPerson = loginUser.getUserCname();
        String projectOid = pt.getProjectOid();
        pt.setProjectOid(projectOid);
        request.setAttribute("projectOid",projectOid);
        request.setAttribute("loginPerson",loginPerson);
        String taskLevel = "0";
        pt.setTaskLevel(taskLevel);
        List<TreeTask> treeTasks = projectTaskManager.queryAllTask(pt,"",new TreeTask());

        String taskOid  = UUIDGenerator.getUUID();
        request.setAttribute("taskOid",taskOid);
        mav.addObject("treeTasks", treeTasks);
        return mav;
    }

    /**
     * 查找父任务的所有子任务
     * @param oid
     * @return
     */
    @RequestMapping(value = "/findTaskByOid", method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectTask> findTaskByOid(String oid) {
        List<ProjectTask> taskList = projectTaskManager.findTaskByOid(oid);
        return taskList;
    }

    /**
     * 查找工时
     * @param taskOid
     * @return
     */
    @RequestMapping(value = "/findWorkHourByTaskOid", method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectTaskWorkHour> findWorkHourByTaskOid(String taskOid) {
        List<ProjectTaskWorkHour> workHourList = taskWorkHourManager.findWorkHourByTaskOid(taskOid);
        return workHourList;
    }

    /**
     * 选择工时的弹出框
     * @param flag
     * @param oid
     * @return
     */
    @RequestMapping(value = "/loadPopover", method = RequestMethod.GET)
    public ModelAndView loadPopover(String flag,String oid) {
        ModelAndView mav = new ModelAndView("project/taskPopover");
        if(!"".equals(flag)&&flag != null){
            mav.addObject("flag", flag);
            mav.addObject("oid", oid);
        }else{
            mav.addObject("flag", "-1");
        }
        return mav;
    }

    /**
     * 任务进度状态的弹出框
     * @param taskOid
     * @return
     */
    @RequestMapping(value = "/loadPopoverStatus", method = RequestMethod.POST)
    public ModelAndView loadPopoverStatus(String taskOid) {
        String i = "";
        String taskOidF;
        ModelAndView mav = new ModelAndView("/project/taskStatusPopover");
        if(taskOid.length()>32){
            taskOidF = taskOid.substring(0,32);
            i = taskOid.substring(32,33);
            mav.addObject("taskOid", taskOidF);
            mav.addObject("taskOidI", i);
            return mav;
        }
        mav.addObject("taskOid", taskOid);
        mav.addObject("taskOidI", i);
        return mav;

    }


    /**
     * 任务的添加
     * @param pt
     * @param request
     * @return
     * @throws java.io.IOException
     */
     @RequestMapping(value = "/save", method = RequestMethod.POST)
     @ResponseBody
     public String save(ProjectTask pt,HttpServletRequest request,HttpSession session) throws IOException {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String rptPerson = loginUser.getUserCname();//发布人
        String taskOid = request.getParameter("oid");//任务ID
        String flag = request.getParameter("flag");//层级flag
        Date rptTime = new Date();//发布时间
        String taskStatus = "0";//任务状态
        String taskLevel = "0";//任务层级
        pt.setRptPerson(rptPerson);
        pt.setRptTime(rptTime);
        pt.setTaskStatus(taskStatus);
        pt.setTaskLevel(taskLevel);
        if(flag ==null || "".equals(flag)){
            pt.setParentTaskOid(taskOid);
            String taskOidFalg  = request.getParameter("taskOid");
            pt.setOid(taskOidFalg);
            pt.setTaskLevel("1");
        }
        int res = 0;
        int i = projectTaskManager.insert(pt);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 工时的添加
     * @param twh
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/saveWorkHour", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveWorkHour(ProjectTaskWorkHour twh) throws IOException {
        Map<String,Object> map = new HashMap<String, Object>();
        int res = 0;
        int i;
        String workHourOid = UUIDGenerator.getUUID();
        i = taskWorkHourManager.insertWorkHour(twh);
        if (res == i) {
            map.put("fail","fail");
            return map;
        } else {
            map.put("success","success");
            map.put("workHourOid",workHourOid);
            return map;
        }

    }

    /**
     * 删除工时
     * @param oid
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/deleteWorkHour", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWorkHour(String oid) throws IOException {
        int res = 0;
        int i = taskWorkHourManager.deleteWorkHour(oid);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 工时的更新
     * @param twh
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/updateWorkHour", method = RequestMethod.POST)
    @ResponseBody
    public String updateWorkHour(ProjectTaskWorkHour twh) throws IOException {
        int res = 0;
        int i = taskWorkHourManager.updateWorkHour(twh);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }


    /**
     * 任务的更新
     * @param pt
     * @param request
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(ProjectTask pt,HttpServletRequest request,HttpSession session) throws IOException {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String rptPerson = loginUser.getUserCname();//发布人
        Date rptTime = new Date();//发布时间
        pt.setRptPerson(rptPerson);
        pt.setRptTime(rptTime);
        int res = 0;
        int i = projectTaskManager.update(pt);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 修改任务状态
     * @param pt
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
    @ResponseBody
    public String modifyStatus(ProjectTask pt) throws IOException {
        int res = 0;
        int i = projectTaskManager.modifyStatus(pt);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 所有负责人
     * @param request
     * @return
     */
     @RequestMapping(value = "/approvePerson", method = RequestMethod.GET)
     @ResponseBody
     public List<ResponsiblePerson> approvePerson(HttpServletRequest request,HttpSession session) {
         PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String userId = loginUser.getId();
         //       删除此模块
        //        Leave leave = new Leave();
        //        leave.setPersonId(userId);
        //        List<PersonManage> list = personManageManager.findByOid(leave.getPersonId());
        List<PersonManage> list = new ArrayList<>();
        List<ResponsiblePerson>  responsiblePersons= new ArrayList<ResponsiblePerson>();
        for(int i = 0;i<list.size();i++){
            ResponsiblePerson responsiblePerson = new ResponsiblePerson();
            responsiblePerson.setValue(list.get(i).getId());
            responsiblePerson.setText(list.get(i).getUserCname());
            responsiblePersons.add(i,responsiblePerson);
        }


        return responsiblePersons;
    }

    /**
     * 任务详情
     * @param oid
     * @return
     */
    @RequestMapping(value = "/findByOid", method = RequestMethod.POST)
    @ResponseBody
    public ProjectTask findByOid(String oid) {
        String taskOid  = UUIDGenerator.getUUID();
        String workHourOid  = UUIDGenerator.getUUID();
        String experienceOid  = UUIDGenerator.getUUID();
        ProjectTask projectTask = projectTaskManager.findByOid(oid);
        projectTask.setTaskOidFlag(taskOid);
        projectTask.setWorkHourOidFlag(workHourOid);
        projectTask.setExperienceOidFlag(experienceOid);
        return projectTask;
    }

    /**
     * 删除任务
     * @param oid
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTask(String oid) throws IOException {
        int res = 0;
        int i = projectTaskManager.deleteTask(oid);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }


    /**
     * 添加心得
     * @param te
     * @param request
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/saveExperience", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveExperience(TaskExperience te,HttpServletRequest request,HttpSession session) throws IOException {

        Map<String,Object> map = new HashMap<String, Object>();
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String fillInPerson = loginUser.getUserCname();
        te.setFillInPerson(fillInPerson);
        int res = 0;
        int i;
        String experienceOid = UUIDGenerator.getUUID();
        i = taskExperienceManager.insertExperience(te);
        if (res == i) {
            map.put("fail","fail");
            return map;
        } else {
            map.put("success","success");
            map.put("experienceOid",experienceOid);
            return map;
        }
    }

    /**
     * 查找心得
     * @param taskOid
     * @return
     */
    @RequestMapping(value = "/findExperienceByTaskOid", method = RequestMethod.POST)
    @ResponseBody
    public List<TaskExperience> findExperienceByTaskOid(String taskOid) {
        List<TaskExperience> ExperienceList = taskExperienceManager.findExperienceByTaskOid(taskOid);

        return ExperienceList;
    }

    /**
     * 删除心得
     * @param oid
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/deleteExperience", method = RequestMethod.POST)
    @ResponseBody
    public String deleteExperience(String oid) throws IOException {
        int res = 0;
        int i = taskExperienceManager.deleteExperience(oid);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 更新心得
     * @param te
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/updateExperience", method = RequestMethod.POST)
    @ResponseBody
    public String updateExperience(TaskExperience te) throws IOException {
        int res = 0;
        int i = taskExperienceManager.updateExperience(te);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 生成日报
     * @param projectTask
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/findTaskForReport", method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectTask> findTaskForReport(ProjectTask projectTask,HttpServletRequest request,HttpSession session){
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId=user.getId();
        String reportDate=request.getParameter("reportDate");
        Date dates=null;
        try {
           dates=new SimpleDateFormat("yyyy-MM-dd").parse(reportDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        projectTask.setRptTime(dates);
        projectTask.setResponsiblePersonOid(userId);
        //查询每个人的任务
        List<ProjectTask> projectTasks=projectTaskManager.findTaskForReport(projectTask);
        return projectTasks;
    }

    /**
     * 生成周报
     * @param request
     */
    @RequestMapping(value = "/findTaskForWeek", method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectTask> findTaskForWeek(ProjectTask projectTask,HttpServletRequest request,HttpSession session){
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId=user.getId();
        String weekNum=request.getParameter("weekNum");
        int s=Integer.parseInt(weekNum);
        String weekDate=request.getParameter("weekDate");
        Date dates=null;
        try {
            dates=new SimpleDateFormat("yyyy-MM").parse(weekDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        cal.setTime(dates);
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
        cal.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
        cal.set(Calendar.WEEK_OF_MONTH, s);//设置当前是第几周
        cal.add(Calendar.DATE,+1);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);//当前周一
        Date mon=cal.getTime();
        cal.add(Calendar.DATE, 6);
        Date sun=cal.getTime();//当前周日
        projectTask.setFirstDate(mon);
        projectTask.setLastDate(sun);
        projectTask.setResponsiblePersonOid(userId);
        //查询在本周时间内开始的所有任务和未结束的任务
        List<ProjectTask> projectTasks=projectTaskManager.findTaskForWeek(projectTask);
        return projectTasks;
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
