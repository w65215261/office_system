package com.pmcc.soft.week.web;


import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.PersonInfo;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonInfoManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.OperationRecord;
import com.pmcc.soft.week.domain.Project;
import com.pmcc.soft.week.domain.ProjectPersonRela;
import com.pmcc.soft.week.manager.OperationRecordManager;
import com.pmcc.soft.week.manager.ProjectAndPersonManager;
import com.pmcc.soft.week.manager.ProjectManager;
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
 * Created by xuxiaodong on 2015/10/20.
 */
@Controller
@RequestMapping("project")
public class ProjectController {



    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private PersonInfoManager personInfoManager;
    @Autowired
    private OperationRecordManager operationRecordManager;
    @Autowired
    private PersonManageManager personManageManager;
    @Autowired
    private ProjectAndPersonManager projectAndPersonManager;

    /**
     * 显示当前登录人负责和参与的项目
     */
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public ModelAndView show(HttpSession session,ProjectPersonRela projectPersonRela,Project project) {
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String personName=user.getUserCname();
        //当前登录人负责的项目
        List<Project>  list =  projectManager.queryProjectByChargePersonId(personName);
        int res= projectManager.queryProjectByChargePersonId(personName).size();
        ModelAndView mav =new ModelAndView("/project/show");
        mav.addObject("projectList1", list);
        //当前登录人参与的项目

        projectPersonRela.setInitPage(1);
        projectPersonRela.setPersonOid(user.getId());
        List<ProjectPersonRela>personProjectList=projectAndPersonManager.queryProjectPersonRela(projectPersonRela);
        mav.addObject("personProjectList",personProjectList);
        return mav;
    }
    /**
     * 显示显示登录人正在进行的项目
     * @param project
     * @param session
     * @param per
     * @return
     */
    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    public ModelAndView showList(Project project,HttpSession session,PersonInfo per) {
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        project.setProjectManager(user.getUserCname());
        ModelAndView mav =new ModelAndView("/project/showList");
        project.setInitPage(0);
        List<Project>  list =  projectManager.queryProject(project);


        //查询全部，计算页面参数
        project.setInitPage(1);
        int totalRecord= projectManager.queryProject(project).size();
        int totalRecord1= projectManager.queryProjectByStatus(project).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  project.getRows());
        int currentPage= project.getPage();
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);
        mav.addObject("totalRecord", totalRecord);
        mav.addObject("totalRecord1", totalRecord1);

        mav.addObject("projectList", list);
        return mav;
    }

    /**
     * 显示登录人完成的项目
     * @param project
     * @return
     */
    @RequestMapping(value = "/showFinishProject",method = RequestMethod.GET)
    public ModelAndView showProjectList(Project project,HttpSession session) {
        ModelAndView mav = new ModelAndView("/project/showFinishProjectList");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        project.setProjectManager(user.getUserCname());
        project.setInitPage(0);
        List<Project>  list =  projectManager.queryProjectByStatus(project);
        //查询全部，计算页面参数
        project.setInitPage(1);
        int totalRecord= projectManager.queryProjectByStatus(project).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  project.getRows());
        int currentPage= project.getPage();
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);

        mav.addObject("finishProjectList", list);
        return mav;
    }

    @RequestMapping(value = "/addProject",method = RequestMethod.GET)
    public ModelAndView addProject(HttpSession session) {
        ModelAndView mav =new ModelAndView("/project/addProject");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userName=user.getUserCname();
        mav.addObject("userName",userName);
        return  mav;
    }

    /**
     * 创建新的项目
     * @param pr
     * @param request
     * @param personManage
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Project pr,HttpServletRequest request,PersonManage personManage,OperationRecord operationRecord,HttpSession session) {
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        pr.setProjectManagerId(user.getId());
        personManage.setId(pr.getApprovePersonId());
        List<PersonManage> per=personManageManager.query(personManage);
        pr.setApprovePersonId(per.get(0).getId());
        pr.setRptPerson(user.getUserCname());
        Date date=new Date();
        pr.setRptTime(date);
        //默认删除标志为0 1
        pr.setDelFlag("0");
        //默认项目状态0未完成 1完成
        pr.setProjectStatus("0");
        //默认项目进度为0未完成 (0-100)

        pr.setProjectSchedule(0);
        int res=0;
        String flag="";
        String Oid= UUIDGenerator.getUUID();
        pr.setId(Oid);
        res=projectManager.insert(pr);

        if (res>0) {
            flag= "success";
            operationRecord.setOperationType("0");
            operationRecord.setProjectOid(Oid);
            Date dates=new Date();
            operationRecord.setOperationTime(dates);
            operationRecord.setOperationPersonOid(user.getId());
            operationRecord.setOperationPersonName(user.getUserCname());
            operationRecord.setOperationContent("7" );
            operationRecordManager.insert(operationRecord);
        }else{
            flag="error";
        }
        return flag;
    }

    /**
     * 用于时间转换
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 修改项目状态和删除标记
     * @param request
     * @param p
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "/update" ,method = RequestMethod.GET)
    @ResponseBody

    public String update(HttpServletRequest request,Project p,OperationRecord operationRecord,HttpSession session){
        String flags=request.getParameter("flag");
        String updId = request.getParameter("projectId");
        String delId=request.getParameter("delId");
        int res=0;
        String flag="";
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(updId);
        Date dates = new Date();
        operationRecord.setOperationTime(dates);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        if(updId!=null){
            if("1".equals(flags)) {
                res = projectManager.update(updId);

                if (res > 0) {
                    flag = "success";
                    operationRecord.setOperationContent("1,完成");
                    operationRecordManager.insert(operationRecord);
                }
            }else if("2".equals(flags)) {
                res=projectManager.updates(updId);

                if (res > 0) {
                    flag = "success";
                    operationRecord.setOperationContent("1,重启");
                    operationRecordManager.insert(operationRecord);
                }
            }
        }else if(delId!=null){
           res= projectManager.delete(delId);
            if (res>0){
                flag="success";
                operationRecord.setOperationContent("1,删除" );
                operationRecordManager.insert(operationRecord);
            }
        }
       return flag;
    }
    /**
     * 展示项目详情
     * @param p
     * @param request
     * @param personManage
     * @return
     */
    @RequestMapping(value = "/findProjectByOid",method = RequestMethod.GET)
    public ModelAndView find(Project p,HttpServletRequest request,PersonManage personManage) {

        String oid=request.getParameter("projectId");

         p =  projectManager.findProjectByOid(oid);
        //得到当前开始时间和结束时间将其转换为String类型用于前台展示
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date StartDate=p.getStartTime();
        String startTime=formatter.format(StartDate);
        String endTime=formatter.format(p.getEndTime());
        //将日期格式转换为int类型进行计算天数
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int currDate=Integer.parseInt(dateFormat.format(date));
        int endDate=Integer.parseInt(dateFormat.format(p.getEndTime()));
        int s=endDate-currDate;
        ModelAndView mav =new ModelAndView("/project/projectDetail");
        personManage.setId(p.getApprovePersonId());
        List<PersonManage> per= personManageManager.query(personManage);
        String ApprovePerson=per.get(0).getUserCname();
        mav.addObject("ApprovePerson",ApprovePerson);
        mav.addObject("project", p);
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);
        mav.addObject("s", s);
        return mav;
    }

    /**
     * 修改项目进度
     * @param request
     * @param pr
     * @return
     */
    @RequestMapping(value = "/updateSchedule",method = RequestMethod.POST)
    @ResponseBody
    public String updateSchedule(HttpServletRequest request,Project pr) {

        String id = request.getParameter("projectId");

        String schedules=request.getParameter("projectSchedule");

        int projectSchedule=Integer.parseInt(schedules);
        pr.setId(id);
        pr.setProjectSchedule(projectSchedule);
        int res= projectManager.updateSchedule(pr);
        String flag="";
        if (res>0){
            flag="success";
        }else{
            flag="error";
        }
        return  flag;
    }

    /**
     *
     * @param project
     * @param request
     * @param personInfo
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/showProjectName",method = RequestMethod.GET)
    public ModelAndView showProjectName(Project project,HttpServletRequest request,PersonInfo personInfo) throws IOException {
        ModelAndView mav = new ModelAndView("/project/showProjectName");
           String flag=request.getParameter("flag");
        String projectId=request.getParameter("projectId");
        project =  projectManager.findProjectByOid(projectId);
        //查询所有人员
        List<PersonInfo> per= personInfoManager.query(personInfo);
        mav.addObject("per",per);
        mav.addObject("project",project);
        mav.addObject("flag",flag);
        return mav;
     }

    /**
     * x修改项目名称
     * @param name
     * @param value
     * @param pk
     * @param projectId
     * @param project
     * @param session
     * @param personInfo
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "updateProjectName",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectName(String name,String value,String pk,String projectId,Project project,HttpSession session,PersonInfo personInfo,OperationRecord operationRecord,PersonManage personManage){
        project.setId(projectId);
        project.setProjectName(value);
        Project project1 =  projectManager.findProjectByOid(projectId);
        projectManager.updateProjectDetail(project);
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(project1.getId());
        Date date=new Date();
        operationRecord.setOperationTime(date);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        operationRecord.setOperationContent("0," + value);
        operationRecordManager.insert(operationRecord);
        return "success";
    }

    /**
     * 修改项目目标
     * @param name
     * @param value
     * @param pk
     * @param projectId
     * @param project
     * @param request
     * @param personInfo
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "updateProjectGoal",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectGoal(String name,String value,String pk,String projectId,Project project,HttpServletRequest request,HttpSession session,PersonInfo personInfo,OperationRecord operationRecord){
        project.setId(projectId);
        project.setProjectGoal(value);
        Project project1 =  projectManager.findProjectByOid(projectId);
        projectManager.updateProjectDetail(project);
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(project1.getId());
        Date date=new Date();
        operationRecord.setOperationTime(date);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        String oldProjectGoal=project1.getProjectGoal();
        operationRecord.setOperationContent("2,"+oldProjectGoal+","+value);
        operationRecordManager.insert(operationRecord);
        return "success";
    }

    /**
     * 修改开始时间
     * @param name
     * @param value
     * @param pk
     * @param projectId
     * @param project
     * @param personInfo
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "updateStartTime",method = RequestMethod.POST)
    @ResponseBody
    public String updateStartTime(String name,Date value,String pk,String projectId,Project project,HttpSession session,PersonInfo personInfo,OperationRecord operationRecord){
        project.setId(projectId);
        project.setStartTime(value);
        Project project1 =  projectManager.findProjectByOid(projectId);
        projectManager.updateProjectDetail(project);
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(project1.getId());
        Date date=new Date();
        operationRecord.setOperationTime(date);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String oldEndTime=formatter.format(project1.getEndTime());
        String oldStartTime=formatter.format(project1.getStartTime());
        String newStartTime=formatter.format(value);

        operationRecord.setOperationContent("3,"+oldStartTime+"-"+oldEndTime+","+newStartTime+"-"+oldEndTime);
        operationRecordManager.insert(operationRecord);
        return "success";
    }

    /**
     * 修改结束时间
     * @param name
     * @param value
     * @param pk
     * @param projectId
     * @param project
     * @param personInfo
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "updateEndTime",method = RequestMethod.POST)
    @ResponseBody
    public String updateEndTime(String name,Date value,String pk,String projectId,Project project,HttpSession session,PersonInfo personInfo,OperationRecord operationRecord){
        project.setId(projectId);
        project.setEndTime(value);
        Project project1 =  projectManager.findProjectByOid(projectId);
        projectManager.updateProjectDetail(project);
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(project1.getId());
        Date date=new Date();
        operationRecord.setOperationTime(date);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String oldEndTime=formatter.format(project1.getEndTime());
        String oldStartTime=formatter.format(project1.getStartTime());
        String newEndTime=formatter.format(value);

        operationRecord.setOperationContent("3,"+oldStartTime+"-"+oldEndTime+","+oldStartTime+"-"+newEndTime);
        operationRecordManager.insert(operationRecord);
        return "success";
    }

    /**
     * 修改可见范围
     * @param name
     * @param value
     * @param pk
     * @param projectId
     * @param project
     * @param personInfo
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "updateProjectVisibility",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectVisibility(String name,String value,String pk,String projectId,Project project,HttpSession session,PersonInfo personInfo,OperationRecord operationRecord){
        project.setId(projectId);
        project.setVisibility(value);
        Project project1 =  projectManager.findProjectByOid(projectId);
        projectManager.updateProjectDetail(project);
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(project1.getId());
        Date date=new Date();
        operationRecord.setOperationTime(date);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        String oldVisibility=project1.getVisibility();
        if("0".equals(oldVisibility)){
        operationRecord.setOperationContent("5,私密,公开");
        }else{
            operationRecord.setOperationContent("5,公开,私密");
        }
        operationRecordManager.insert(operationRecord);
        return "success";
    }

    /**
     * 修改项目进度
     * @param name
     * @param value
     * @param pk
     * @param projectId
     * @param project
     * @param personInfo
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "updateProjectSchedule",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  updateProjectSchedule(String name,Integer value,String pk,String projectId,Project project,HttpSession session,PersonInfo personInfo,OperationRecord operationRecord){
        Map<String, Object> map = new HashMap<String, Object>();
        project.setId(projectId);
        project.setProjectSchedule(value);
        Project project1 =  projectManager.findProjectByOid(projectId);
        projectManager.updateProjectDetail(project);
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(project1.getId());
        Date date=new Date();
        operationRecord.setOperationTime(date);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        operationRecord.setOperationContent("6,"+value+"%");
        operationRecordManager.insert(operationRecord);
        map.put("success","success");
        map.put("ProjectSchedule",value);
        return map;
    }

    /**
     * 修改项目负责人
     * @param projectId
     * @param project
     * @param request
     * @param personInfo
     * @param operationRecord
     * @return
     */
    @RequestMapping(value = "updateProjectManager",method = RequestMethod.POST)
    @ResponseBody
    public String updateProjectManager(String projectId,Project project,HttpServletRequest request,PersonInfo personInfo,OperationRecord operationRecord,HttpSession session,ProjectPersonRela ppr){
        String projectOId=request.getParameter("projectId");
        String projectManagers=request.getParameter("projectManager");
        String personId=request.getParameter("personId");
        project.setId(projectOId);
       project.setProjectManager(projectManagers);
        project.setProjectManagerId(personId);
        Project project1 =  projectManager.findProjectByOid(projectId);
        projectManager.updateProjectDetail(project);
        operationRecord.setOperationType("0");
        operationRecord.setProjectOid(project1.getId());
        Date date=new Date();
        operationRecord.setOperationTime(date);
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        operationRecord.setOperationPersonOid(user.getId());
        operationRecord.setOperationPersonName(user.getUserCname());
        operationRecord.setOperationContent("4,"+projectManagers);
        int res=operationRecordManager.insert(operationRecord);
        if (res>0){
            ppr.setOid(user.getId());
            projectAndPersonManager.delete(ppr);
        }
        return "success";
    }

    /**
     * 查找登录人参与的正在进行的项目
     * @param project
     * @param session
     * @param projectPersonRela
     * @return
     */
    @RequestMapping(value = "/showRelaList",method = RequestMethod.GET)
    public ModelAndView showRelaList(Project project,HttpSession session,ProjectPersonRela projectPersonRela,PersonManage personManage) {
        ModelAndView mav =new ModelAndView("/project/showRelaList");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        projectPersonRela.setInitPage(0);
        projectPersonRela.setPersonOid(user.getId());
        List<ProjectPersonRela>personProjectList=projectAndPersonManager.queryProjectPersonRela(projectPersonRela);
            for(ProjectPersonRela prpr:personProjectList){
                   personManage.setId(prpr.getProject().getApprovePersonId());
              List<PersonManage> per=personManageManager.query(personManage);
                prpr.setApprovalPerson(per.get(0).getUserCname());
            }
        //查询全部，计算页面参数
        projectPersonRela.setInitPage(1);
        int totalRecord= personProjectList.size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  projectPersonRela.getRows());
        int totalRecord1= projectAndPersonManager.queryProjectPersonRelaStatus(projectPersonRela).size();
        int currentPage= project.getPage();
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);
        mav.addObject("totalRecord", totalRecord);
        mav.addObject("totalRecord1",totalRecord1);
        mav.addObject("projectRelaList", personProjectList);
        return mav;
    }

    /**
     * 查找登录人参与的已经完成的项目
     * @param project
     * @param session
     * @param projectPersonRela
     * @return
     */
    @RequestMapping(value = "/showFinishRelaList",method = RequestMethod.GET)
    public ModelAndView showRelaListStatus(Project project,HttpSession session,ProjectPersonRela projectPersonRela,PersonManage personManage) {
        ModelAndView mav =new ModelAndView("/project/showFinishRelaList");
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        projectPersonRela.setInitPage(0);
        projectPersonRela.setPersonOid(user.getId());
        List<ProjectPersonRela>personProjectLists=projectAndPersonManager.queryProjectPersonRelaStatus(projectPersonRela);
        for(ProjectPersonRela prpr:personProjectLists){
            personManage.setId(prpr.getProject().getApprovePersonId());
            List<PersonManage> per=personManageManager.query(personManage);
            prpr.setApprovalPerson(per.get(0).getUserCname());
        }
        //查询全部，计算页面参数
        projectPersonRela.setInitPage(1);
        int totalRecord= personProjectLists.size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  projectPersonRela.getRows());
        int currentPage= project.getPage();
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);
        mav.addObject("totalRecord", totalRecord);
        mav.addObject("projectRelaFinishList", personProjectLists);
        return mav;
    }
}
