package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.Dict;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.DictManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.*;
import com.pmcc.soft.week.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ASUS on 2015/11/3.
 */
@Controller
@RequestMapping("plan")
public class PlanController {
    @Autowired
    private PlanManager planManager;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private DictManager dictManager;
    @Autowired
    private TaskAttachmentManager taskAttachmentManager;
    @Autowired
    private TaskExperienceManager taskExperienceManager;
    @Autowired
    private ProjectTaskManager projectTaskManager;
    @Autowired
    private TaskWorkHourManager taskWorkHourManager;
    @Autowired
    private PlanAttachmentManager planAttachmentManager;
    @Autowired
    private OperationRecordPlanManager operationRecordPlanManager;
    @Autowired
    private PersonManageManager personManageManager;
    /**
     *永远 前台遍历输出月份
     * @param pt
     * @return
     */
    @RequestMapping(value = "/showPlanDate", method = RequestMethod.GET)
    public ModelAndView showPlanDate(ProjectTask pt) {
        ModelAndView mav = new ModelAndView("/plan/showPlanDate");
        String[] arr={"1","2","3","4","5","6","7","8","9","10","11","12"};
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        mav.addObject("month",arr);
        mav.addObject("year",year);
        return mav;
    }

    /**
     * 根据前台传入的参数查询相应的计划
     * @param pt
     * @param request
     * @return
     */
    @RequestMapping(value = "/showAddPlan", method = RequestMethod.GET)
    public ModelAndView addPlanShow(ProjectTask pt,HttpServletRequest request,HttpSession session) {
            ModelAndView mav = new ModelAndView("plan/showPlanAdd");
            Calendar c = Calendar.getInstance();
            int flag=Integer.parseInt(request.getParameter("flag"));
            if(flag==0){
            String projectName=request.getParameter("projectName");
            int month = c.get(Calendar.MONTH) ;//月
            int year = c.get(Calendar.YEAR);
            c.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
            c.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
            c.set(Calendar.YEAR, year); // 2015年
            c.set(Calendar.MONTH, month);//11月 calender实际是从0-11算的
            //得到当前有几周
            int mCount = c.getActualMaximum(Calendar.WEEK_OF_MONTH);

            //d得到当前是第几周
            int currWeek = c.get(Calendar.WEEK_OF_MONTH);
            if(currWeek==0){
                currWeek=1;
            }
            mav.addObject("mCount", mCount);
            mav.addObject("currWeek", currWeek);
            mav.addObject("year",year);
            mav.addObject("month",month+1);
            mav.addObject("projectName",projectName);
            }else{
                String years=request.getParameter("year");
                String months = request.getParameter("month");
                String week=request.getParameter("week");


                int year = Integer.parseInt(years);
                String currWeek="0";
                int month1 = Integer.parseInt(months) - 1;
                int month2 = c.get(Calendar.MONTH) ;//月
                c.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
                c.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
                c.set(Calendar.YEAR, year); // 2015年
                c.set(Calendar.MONTH, month1);//11月 calender实际是从0-11算的
                //得到当前周
                int currWeek1 = c.get(Calendar.WEEK_OF_MONTH);
                String chooseWeek=request.getParameter("chooseWeek");
                //初始化月份不是当前月的当前周
               if(month1!=month2){
                   //初始化的时候当前周必须为1
                   if(chooseWeek.equals("1")){
                       currWeek="1";
                   }else if(chooseWeek.equals("0")){
                       //选择周的时候传入的当前周
                       if("".equals(week)||null==week){
                           currWeek="1";
                       }else{
                           currWeek=week;
                       }

                   }
                   //初始化月份是当前月的当前周
               }else if(month1==month2) {
                   if (chooseWeek.equals("1")) {
//                       if ("".equals(week) || null == week) {
                           currWeek = String.valueOf(currWeek1);
//                       } else {
//                           currWeek = week;
//                       }
                   }else{
                       currWeek = week;
                   }
               }
                //d得到当前有几周
                int mCount = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
                mav.addObject("mCount", mCount);
                mav.addObject("currWeek", currWeek);
                mav.addObject("year",year);
                mav.addObject("month",month1+1);
            }
        //在页面进入前需要生成计划,心得,工时的ID
            String taskOid  = UUIDGenerator.getUUID();
            String taskOid1  = UUIDGenerator.getUUID();
            String taskOid2  = UUIDGenerator.getUUID();
            String taskOid3  = UUIDGenerator.getUUID();
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userName=user.getUserCname();
        mav.addObject("userName",userName);
        mav.addObject("taskOid",taskOid);
        mav.addObject("taskOid1",taskOid1);
        mav.addObject("taskOid2",taskOid2);
        mav.addObject("taskOid3",taskOid3);
        return mav;
    }

    /**
     * 遍历所有项目的名称和分类信息
     * @param project
     * @param dict
     * @return
     */
    @RequestMapping(value = "/showProjectName", method = RequestMethod.GET)
    public ModelAndView showProject(Project project,Dict dict,HttpSession session) {
        ModelAndView mav = new ModelAndView("plan/showProjectName");
        PersonManage user1 = (PersonManage)session.getAttribute("loginUser");
        project.setProjectManager(user1.getUserCname());
        project.setInitPage(1);
        List<Project>  list =  projectManager.queryProject(project);
        List<Dict> dictList=dictManager.query(dict);
        mav.addObject("projectList1",list);
        mav.addObject("dictList",dictList);
        return mav;
    }


    /**
     *
     * @param pt 计划实体
     * @param request
     * @param personManage 人员实体
     * @param projects 项目实体
     * @param dict 字典表实体
     * @param operationRecordPlan 操作记录实体
     * @param session
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(ProjectTask pt,HttpServletRequest request,PersonManage personManage,Project projects,Dict dict,OperationRecordPlan operationRecordPlan,HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        String projectName=request.getParameter("projectName");
        String approvalPerson=request.getParameter("approvalPerson");
        PersonManage user1 = (PersonManage)session.getAttribute("loginUser");
        Date date= new Date();

                pt.setApprovalPerson(approvalPerson);
                pt.setResponsiblePersonOid(user1.getId());
                pt.setResponsiblePersonName(user1.getUserCname());
                pt.setRptPerson(user1.getUserCname());
                pt.setRptTime(date);
                pt.setHasStar("0");
                pt.setTaskStatus("0");
                pt.setTaskLevel("0");
        //根据名称判断输入的是跟项目有关的计划还是分类的计划
        String planType="";
        String projectId="";
        Project project=projectManager.findProjectByProjectName(projectName);

        if(project!=null){
            planType="0";//属于项目
            projectId=project.getId();

        }else{

             dict.setDictName(projectName);
             dict=dictManager.findDictByName(projectName);
            if(dict!=null){
             planType=dict.getId();}
            else{planType=null;}
        }
        String timeType=request.getParameter("timeType");
        String quarter=request.getParameter("quarter");
        pt.setPlanTypeOid(planType);
        pt.setProjectOid(projectId);
        pt.setPlanTimeType(timeType);
        pt.setQuarter(quarter);
        String taskOId=request.getParameter("taskOid");
        pt.setOid(taskOId);
        int res=planManager.save(pt);
        String flag="";
        if(res>0) {
            flag="success";
            //插入操作记录
            operationRecordPlan.setOperationType("0");
            if(projectId!=null){
            operationRecordPlan.setProjectOid(projectId);
            }
            operationRecordPlan.setTaskOid(taskOId);
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            //取到当前操作员
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());

            operationRecordPlan.setOperationContent("7");
             operationRecordPlanManager.insert(operationRecordPlan);

        }
        String taskOid=UUIDGenerator.getUUID();
        map.put("success",flag);
        map.put("taskOid",taskOid);
        return map;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/findPlanByOid", method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectTask> findPlanByOid(String oid) {
        List<ProjectTask> taskList = planManager.findPlanByOid(oid);
        return taskList;
    }

    /**
     * 根据条件循环遍历所有计划
     * @param pt
     * @param request
     * @return
     */
@RequestMapping(value = "/showPlan", method = RequestMethod.GET)
public ModelAndView showPlan(ProjectTask pt,HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("plan/showPlan");
    String taskLevel = "0";
    String year=request.getParameter("year");
    String month=request.getParameter("month");
    String week=request.getParameter("week");
    String quarter=request.getParameter("qua");
    String planTimeType=request.getParameter("planTimeType");
    pt.setTaskLevel(taskLevel);
    pt.setYear(year);
    pt.setMonth(month);
    pt.setWeek(week);
    pt.setQuarter(quarter);
    pt.setPlanTimeType(planTimeType);
    List<TreeTask> treeTasks = planManager.queryAllPlan(pt, "", new TreeTask());
    mav.addObject("treeTasks", treeTasks);
    return mav;
    }
    /**
     * 增加星标标志
     * @param projectTask
     * @param request
     */
    @RequestMapping(value = "/updateHasStar", method = RequestMethod.POST)
    public void updateHasStar(ProjectTask projectTask,HttpServletRequest request) {
        int res = 0;
        String oid=request.getParameter("oid");
        String hasStar=request.getParameter("hasStar");
        projectTask.setHasStar(hasStar);
        projectTask.setOid(oid);
        planManager.updateHasStar(projectTask);

    }

    /**
     * 附件上传功能
     * @param fileToUpload
     * @param request
     * @param response
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile[] fileToUpload,
            HttpServletRequest request, HttpSession session,HttpServletResponse response,OperationRecordPlan operationRecordPlan) throws IOException {

        Map<String, Object> res = new HashMap<String, Object>();

        DateUtil du = new DateUtil();
        // 附加实体
        TaskAttachment att = new TaskAttachment();
        // 合同子表主键值
        String uuid = request.getParameter("uuid");

        if(uuid == null || "".equals(uuid)){
            uuid = UUIDGenerator.getUUID();
        }
        System.out.println(fileToUpload);
        att.setTaskOid(uuid);

        String kk = new String(fileToUpload[0].getOriginalFilename());
        String[] km = kk.split("\\.");
        kk = km[km.length - 1];
        // 上传到服务器文件名称
        String fname = UUIDGenerator.getUUID();
        String fmn = fname + "." + kk;
        String mm = du.DateToString(new Timestamp(new Date().getTime()),
                "yyyy-MM-dd");
        String fileName = mm + "/" + fmn;
        // 服务器存放附件文件夹
        String path = path = request.getSession().getServletContext().getRealPath("/filePlan");
        att.setFileUrl("filePlan/" + fileName);
        att.setFileName(fileToUpload[0].getOriginalFilename());
        att.setFileMathName(fmn);
        att.setCreateDate(new Date());
        att.setDelFlag("0");
        String workHourId=request.getParameter("workId");
        att.setWorkHourOid(workHourId);
        String experienceId=request.getParameter("experienceId");
        att.setExperienceOid(experienceId);
        taskAttachmentManager.insert(att);

        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存
        try {
            fileToUpload[0].transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.put("uuid", uuid);
//上传附件的操作记录
        String flag=request.getParameter("flag");
        if("1".equals(flag)){
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(uuid);
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("10,上传了新附件  "+ fileToUpload[0].getOriginalFilename());
            operationRecordPlanManager.insert(operationRecordPlan);
        }
        response.getWriter().write(uuid);
        response.getWriter().flush();

    }

    /**
     * 分解任务
     * @param pt
     * @param request
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/savePlan", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> savePlan(ProjectTask pt,HttpServletRequest request,HttpSession session,OperationRecordPlan operationRecordPlan) throws IOException {
        Map<String,Object> map = new HashMap<String, Object>();
        PersonManage user = (PersonManage)session.getAttribute("loginUser");

        String taskOid = request.getParameter("oid");//任务ID
        String rptPerson =user.getUserCname();     //发布人
        Date rptTime = new Date();//发布时间
        String taskStatus = "0";//任务状态
        String taskLevel = "0";//任务层级
        pt.setRptPerson(rptPerson);
        pt.setRptTime(rptTime);
        pt.setTaskStatus(taskStatus);
        pt.setTaskLevel(taskLevel);
        pt.setHasStar("0");
        pt.setParentTaskOid(taskOid);
        pt.setProjectOid(pt.getProjectOid());
        String oid  = request.getParameter("coid");
        pt.setOid(oid);
        pt.setTaskLevel("1");
        int res = 0;
        int i = projectTaskManager.insert(pt);
        String projectPlanOid = UUIDGenerator.getUUID();
        if (res == i) {
            map.put("fail","fail");
            return map;
        } else {
            map.put("success","success");
            map.put("projectPlanOid",projectPlanOid);
            //保存分解计划的操作记录
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(pt.getParentTaskOid());
            operationRecordPlan.setProjectOid(pt.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());

            operationRecordPlan.setOperationContent("5,"+pt.getTaskName());
            int s= operationRecordPlanManager.insert(operationRecordPlan);
            //需要重新生成一条创建计划的操作记录
            if(s>0){
                operationRecordPlan.setOperationType("0");
                operationRecordPlan.setTaskOid(pt.getOid());
                operationRecordPlan.setProjectOid(pt.getProjectOid());
                operationRecordPlan.setOperationTime(dates);
                operationRecordPlan.setOperationPersonOid(user.getId());
                operationRecordPlan.setOperationPersonName(user.getUserCname());

                operationRecordPlan.setOperationContent("7,"+pt.getTaskName());
                operationRecordPlanManager.insert(operationRecordPlan);
            }
            return map;
        }
    }

    /**
     * 保存工时
     * @param twh
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/saveWorkHour", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveWorkHour(ProjectTaskWorkHour twh,OperationRecordPlan operationRecordPlan,HttpSession session) throws IOException {
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
            map.put("workHourOid", workHourOid);
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(twh.getTaskOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("10,添加工时  "+twh.getWorkHourName());
            operationRecordPlanManager.insert(operationRecordPlan);
            return map;
        }

    }

    /**
     * 保存心得
     * @param te
     * @param session
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/saveExperience", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveExperience(TaskExperience te,HttpSession session,OperationRecordPlan operationRecordPlan) throws IOException {

        Map<String,Object> map = new HashMap<String, Object>();
        PersonManage online = (PersonManage)session.getAttribute("loginUser");
        String fillInPerson = online.getUserCname();//添加人
        te.setFillInPerson(fillInPerson);
        int res = 0;
        int i;
        String experienceOid = UUIDGenerator.getUUID();
        i = taskExperienceManager.insertExperience(te);
        if (res == i) {
            map.put("fail","fail");
            return map;
        } else {
            map.put("success", "success");
            map.put("coid4", experienceOid);
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(te.getTaskOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("10,添加一条心得  ");
            operationRecordPlanManager.insert(operationRecordPlan);
            return map;
        }
    }

    /**
     * 查询计划的详情
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
     * 根据oid分别查询心得和工时里的附件
     * @param taskAttachment
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAttachmentByOid", method = RequestMethod.POST)
    @ResponseBody
    public List<TaskAttachment> findAttachmentByOid(TaskAttachment taskAttachment,HttpServletRequest request){
        String oid=request.getParameter("res");
        String workHourOid=request.getParameter("workHourId");
        String experienceId = request.getParameter("experienceId");
        taskAttachment.setWorkHourOid(workHourOid);
        taskAttachment.setTaskOid(oid);
        taskAttachment.setWorkHourOid(workHourOid);
        taskAttachment.setExperienceOid(experienceId);
        List<TaskAttachment> planList=planAttachmentManager.findPlanAttachmentByOId(taskAttachment);
        return planList;
    }

    /**
     * 根据计划的ID查找附件
     * @param taskAttachment
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAttachmentByTaskOid", method = RequestMethod.POST)
    @ResponseBody
    public List<TaskAttachment> findAttachmentByTaskOid(TaskAttachment taskAttachment,HttpServletRequest request){

            String taskOid=request.getParameter("taskOid");
        taskAttachment.setTaskOid(taskOid);
        List<TaskAttachment> planList=planAttachmentManager.findPlanAttachmentByTaskOId(taskAttachment);
        return planList;
    }

    /**
     * 删除附件功能
     * @param taskAttachment
     * @param request
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/deleteAttachment", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAttachment(HttpSession session ,TaskAttachment taskAttachment,HttpServletRequest request,OperationRecordPlan operationRecordPlan) throws IOException {
        String oid=request.getParameter("id");

        taskAttachment.setId(oid);
        TaskAttachment taskAttachment1=planAttachmentManager.findPlanAttachment(taskAttachment);
        int res = 0;
        int i = planAttachmentManager.deleteAttachment(taskAttachment);
        if (res == i) {
            return "fail";
        } else {
            String flag=request.getParameter("flag");
            if("1".equals(flag)){
                operationRecordPlan.setOperationType("0");
                operationRecordPlan.setTaskOid(taskAttachment1.getTaskOid());
                Date dates=new Date();
                operationRecordPlan.setOperationTime(dates);
                PersonManage user = (PersonManage)session.getAttribute("loginUser");
                operationRecordPlan.setOperationPersonOid(user.getId());
                operationRecordPlan.setOperationPersonName(user.getUserCname());
                operationRecordPlan.setOperationContent("10,删除了附件  "+ taskAttachment1.getFileName());
                operationRecordPlanManager.insert(operationRecordPlan);
            }
            return "success";
        }
    }

    /**
     * 查找计划的操作记录
     * @param operationRecordPlan
     * @param request
     * @return
     */
    @RequestMapping(value = "/findOperationRecoadPlan", method = RequestMethod.POST)
    @ResponseBody
    public   List<OperationRecordPlan> findOperationRecoadPlan(OperationRecordPlan operationRecordPlan, HttpServletRequest request) {
        String taskOid = request.getParameter("taskOid");
        operationRecordPlan.setTaskOid(taskOid);
        List<OperationRecordPlan> operationRecordPlanList = operationRecordPlanManager.findOperation(operationRecordPlan);
        List<OperationRecordPlan> operationRecordList = new ArrayList<OperationRecordPlan>();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        String dateFlag = "";
        int i = 0;
        for (OperationRecordPlan operation : operationRecordPlanList) {
            OperationRecordPlan orf = new OperationRecordPlan();
            String content = operation.getOperationContent();
            if (content != null && !content.equals("")) {
                if (!content.equals("7")) {
                    String[] ct = content.split(",");
                    if(ct[0].equals("0")||ct[0].equals("1")||ct[0].equals("6")||ct[0].equals("8")||ct[0].equals("9")){
                        orf.setDistinguish(ct[0]);
                        orf.setNewContent(ct[2]);
                        orf.setOldContent(ct[1]);

                    }else{
                        orf.setDistinguish(ct[0]);
                        orf.setNewContent(ct[1]);
                    }
                }else{
                    orf.setDistinguish(content);
                }
            } else {
                orf.setDistinguish(content);
            }
            Date dateTime = operation.getOperationTime();
            String date = sdfDate.format(dateTime);
            String time = sdfTime.format(dateTime);
            if(i==0){
                orf.setModifyDate(date);
                dateFlag = date;
            }else {
                if(!dateFlag.equals(date)){
                    orf.setModifyDate(date);
                    dateFlag = date;
                }else{
                    orf.setModifyDate("");
                }
            }
            orf.setOperationPersonName(operation.getOperationPersonName());
            orf.setModifyTime(time);
            operationRecordList.add(orf);
            i++;
        }
//        map.put("operationRecords",operationRecordList);
        return operationRecordList;
    }

    /**
     * 遮罩层里修改计划名称
     * @param projectTask
     * @param session
     * @param operationRecordPlan
     * @return
     */
    @RequestMapping(value = "updateProjectTaskName",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectTaskName(ProjectTask projectTask,HttpSession session,OperationRecordPlan operationRecordPlan){
        ProjectTask pt=planManager.findByOid(projectTask.getOid());
        String flag="";
        int res=planManager.updateProjectPlan(projectTask);
        if(res>0){
            flag="success";
            //保存操作记录
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(projectTask.getOid());
            operationRecordPlan.setProjectOid(projectTask.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());

            operationRecordPlan.setOperationContent("1,"+pt.getTaskName()+","+projectTask.getTaskName());
            operationRecordPlanManager.insert(operationRecordPlan);
        }
        return flag;
    }

    /**
     * 遮罩层修改计划详情
     * @param projectTask
     * @param session
     * @param operationRecordPlan
     * @return
     */
    @RequestMapping(value = "updateProjectTaskCount",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectTaskCount(ProjectTask projectTask,HttpSession session,OperationRecordPlan operationRecordPlan){
        String flag="";
        int res=planManager.updateProjectPlan(projectTask);
        if(res>0){
            flag="success";
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(projectTask.getOid());
            operationRecordPlan.setProjectOid(projectTask.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());

            operationRecordPlan.setOperationContent("2,"+projectTask.getTaskContent());
            operationRecordPlanManager.insert(operationRecordPlan);
        }
        return flag;
    }

    /**
     * 遮罩层修改开始时间
     * @param projectTask
     * @param session
     * @param operationRecordPlan
     * @return
     */
    @RequestMapping(value = "updateProjectStartTime",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectStartTime(ProjectTask projectTask,HttpSession session,OperationRecordPlan operationRecordPlan){
        String flag="";
        int res=planManager.updateProjectPlan(projectTask);
        if(res>0){
            flag="success";
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(projectTask.getOid());
            operationRecordPlan.setProjectOid(projectTask.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String newStartTime=dateFormat.format(projectTask.getStartTime());
            operationRecordPlan.setOperationContent("3,"+newStartTime);
            operationRecordPlanManager.insert(operationRecordPlan);
        }
        return flag;
    }
    /**
     * 遮罩层修改开始时间
     * @param projectTask
     * @param session
     * @param operationRecordPlan
     * @return
     */
    @RequestMapping(value = "updateProjectEndTime",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectEndTime(ProjectTask projectTask,HttpSession session,OperationRecordPlan operationRecordPlan){
        String flag="";
        int res=planManager.updateProjectPlan(projectTask);
        if(res>0){
            flag="success";
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(projectTask.getOid());
            operationRecordPlan.setProjectOid(projectTask.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           String newEndTime=dateFormat.format(projectTask.getEndTime());
            operationRecordPlan.setOperationContent("4,"+newEndTime);
            operationRecordPlanManager.insert(operationRecordPlan);
        }
        return flag;
    }

    /**
     * 遮罩层修改审批人
     * @param projectTask
     * @param session
     * @param operationRecordPlan
     * @return
     */
    @RequestMapping(value = "updateApprovalPerson",method = RequestMethod.POST)
    @ResponseBody
    public String updateApprovalPerson(ProjectTask projectTask,HttpSession session,OperationRecordPlan operationRecordPlan){
        ProjectTask pt=planManager.findByOid(projectTask.getOid());
        String flag="";
        int res=planManager.updateProjectPlan(projectTask);
        if(res>0){
            flag="success";
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(projectTask.getOid());
            operationRecordPlan.setProjectOid(projectTask.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("8," + pt.getApprovalPerson() + "," + projectTask.getApprovalPerson());
            operationRecordPlanManager.insert(operationRecordPlan);
        }
        return flag;
    }

    /**
     * 遮罩层修改负责人
     * @param projectTask
     * @param session
     * @param operationRecordPlan
     * @return
     */
    @RequestMapping(value = "updateResponsiblePerson",method = RequestMethod.POST)
    @ResponseBody
    public String updateResponsiblePerson(ProjectTask projectTask,HttpSession session,OperationRecordPlan operationRecordPlan){
        ProjectTask pt=planManager.findByOid(projectTask.getOid());
        String flag="";
        int res=planManager.updateProjectPlan(projectTask);
        if(res>0){
            flag="success";
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(projectTask.getOid());
            operationRecordPlan.setProjectOid(projectTask.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("9," + pt.getResponsiblePersonName() + "," + projectTask.getResponsiblePersonName());
            operationRecordPlanManager.insert(operationRecordPlan);
        }
        return flag;
    }

    /**
     * 遮罩层删除任务
     * @param oid
     * @param operationRecordPlan
     * @param session
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTask(String oid,OperationRecordPlan operationRecordPlan,HttpSession session) throws IOException {
        ProjectTask pt=planManager.findByOid(oid);
        int res = 0;
        int i = projectTaskManager.deleteTask(oid);
        if (res == i) {
            return "fail";
        } else {
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(pt.getParentTaskOid());
            operationRecordPlan.setProjectOid(pt.getProjectOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("10,删除了计划  "+ pt.getTaskName());
            operationRecordPlanManager.insert(operationRecordPlan);
            return "success";
        }
    }

    /**
     * 遮罩层删除工时
     * @param oid
     * @param projectTaskWorkHour
     * @param operationRecordPlan
     * @param session
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/deleteWorkHour", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWorkHour(String oid,ProjectTaskWorkHour projectTaskWorkHour,OperationRecordPlan operationRecordPlan,HttpSession session) throws IOException {
        projectTaskWorkHour.setOid(oid);
        ProjectTaskWorkHour projectTaskWorkHour1=taskWorkHourManager.findWorkHourByOid(projectTaskWorkHour);
        int res = 0;
        int i = taskWorkHourManager.deleteWorkHour(oid);

        if (res == i) {
            return "fail";
        } else {
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(projectTaskWorkHour1.getTaskOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("10,删除工时  "+ projectTaskWorkHour1.getWorkHourName());
            operationRecordPlanManager.insert(operationRecordPlan);
            return "success";
        }
    }

    /**
     * 遮罩层删除心得
     * @param oid
     * @param operationRecordPlan
     * @param session
     * @param taskExperience
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/deleteExperience", method = RequestMethod.POST)
    @ResponseBody
    public String deleteExperience(String oid,OperationRecordPlan operationRecordPlan,HttpSession session,TaskExperience taskExperience) throws IOException {
       taskExperience=taskExperienceManager.findExperienceByOid(oid);
        int res = 0;
        int i = taskExperienceManager.deleteExperience(oid);
        if (res == i) {
            return "fail";
        } else {
            operationRecordPlan.setOperationType("0");
            operationRecordPlan.setTaskOid(taskExperience.getTaskOid());
            Date dates=new Date();
            operationRecordPlan.setOperationTime(dates);
            PersonManage user = (PersonManage)session.getAttribute("loginUser");
            operationRecordPlan.setOperationPersonOid(user.getId());
            operationRecordPlan.setOperationPersonName(user.getUserCname());
            operationRecordPlan.setOperationContent("10,删除一条心得");
            operationRecordPlanManager.insert(operationRecordPlan);
            return "success";
        }
    }

    /**
     * 选择工时的弹出框
     * @param flag
     * @param oid
     * @return
     */
    @RequestMapping(value = "/loadPopover", method = RequestMethod.GET)
    public ModelAndView loadPopover(String flag,String oid) {
        ModelAndView mav = new ModelAndView("plan/planPopover");
        if(!"".equals(flag)&&flag != null){
            mav.addObject("flag", flag);
            mav.addObject("oid", oid);
        }else{
            mav.addObject("flag", "-1");
        }
        return mav;
    }

    /**
     * 用于点击月度刷新功能
     * @param pt
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/showAddPlanMonth", method = RequestMethod.GET)
    public ModelAndView showAddPlanMonth(ProjectTask pt,HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView("plan/showPlanAddMonth");
        Calendar c = Calendar.getInstance();
            String years=request.getParameter("year");
            String months = request.getParameter("month");
            String timeType=request.getParameter("planTimeType");
            int year = Integer.parseInt(years);
            String currWeek="0";
            int month1 = Integer.parseInt(months) - 1;
            int month2 = c.get(Calendar.MONTH) ;//月
            c.setFirstDayOfWeek(Calendar.MONDAY);//设置起止日为周1
            c.setMinimalDaysInFirstWeek(7);//设置第一周够几天才能算第一周
            c.set(Calendar.YEAR, year); // 2015年
            c.set(Calendar.MONTH, month1);//11月 calender实际是从0-11算的
            //d得到当前有几周
            int mCount = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
            mav.addObject("mCount", mCount);
            mav.addObject("currWeek", currWeek);
            mav.addObject("year",year);
            mav.addObject("month",month1+1);
        //在页面进入前需要生成计划,心得,工时的ID
        String taskOid  = UUIDGenerator.getUUID();
        String taskOid1  = UUIDGenerator.getUUID();
        String taskOid2  = UUIDGenerator.getUUID();
        String taskOid3  = UUIDGenerator.getUUID();
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userName=user.getUserCname();
        mav.addObject("userName",userName);
        mav.addObject("taskOid",taskOid);
        mav.addObject("taskOid1",taskOid1);
        mav.addObject("taskOid2",taskOid2);
        mav.addObject("taskOid3",taskOid3);
        mav.addObject("timeType",timeType);
        return mav;
    }

    /**
     * 用于点击季度
     * @param pt
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/showAddPlanQuater", method = RequestMethod.GET)
    public ModelAndView showAddPlanQuater(ProjectTask pt,HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView("plan/showPlanAddQuater");
        Calendar c = Calendar.getInstance();
        String years=request.getParameter("year");
        int year = Integer.parseInt(years);
        //在页面进入前需要生成计划,心得,工时的ID
        String taskOid  = UUIDGenerator.getUUID();
        String taskOid1  = UUIDGenerator.getUUID();
        String taskOid2  = UUIDGenerator.getUUID();
        String taskOid3  = UUIDGenerator.getUUID();
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userName=user.getUserCname();
        mav.addObject("userName",userName);
        mav.addObject("taskOid",taskOid);
        mav.addObject("taskOid1",taskOid1);
        mav.addObject("taskOid2",taskOid2);
        mav.addObject("taskOid3",taskOid3);
        mav.addObject("year",year);
        return mav;
    }

    /**
     * 用于刷新季度的年
     * @param pt
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/showAddPlanQuaterFlag", method = RequestMethod.GET)
    public ModelAndView showAddPlanQuaterFlag(ProjectTask pt,HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView("plan/showPlanAddQuaterFlag");
        Calendar c = Calendar.getInstance();
        String years=request.getParameter("year");
        String qua=request.getParameter("qua");
        int year = Integer.parseInt(years);
        //在页面进入前需要生成计划,心得,工时的ID
        String taskOid  = UUIDGenerator.getUUID();
        String taskOid1  = UUIDGenerator.getUUID();
        String taskOid2  = UUIDGenerator.getUUID();
        String taskOid3  = UUIDGenerator.getUUID();
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userName=user.getUserCname();
        mav.addObject("userName",userName);
        mav.addObject("taskOid",taskOid);
        mav.addObject("taskOid1",taskOid1);
        mav.addObject("taskOid2",taskOid2);
        mav.addObject("taskOid3",taskOid3);
        mav.addObject("year",year);
        mav.addObject("qua",qua);
        return mav;
    }
}