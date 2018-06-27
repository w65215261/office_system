package com.pmcc.soft.week.web;

import com.pmcc.soft.week.domain.ProjectTask;
import com.pmcc.soft.week.domain.TaskCount;
import com.pmcc.soft.week.manager.ProjectTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/10/26.
 */
@Controller
@RequestMapping("projectCount")
public class ProjectCountController {

    @Autowired
    ProjectTaskManager projectTaskManager;

    /**
     * 任务进度的统计
     * @param pt
     * @param request
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ProjectTask pt,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/project/projectCount");
        String projectOid = pt.getProjectOid();
        pt.setProjectOid(projectOid);
        List<ProjectTask> taskCountList = projectTaskManager.findTaskCount(pt);
        int completeSize = 0;
        int unfinishedSize = 0;
        for(ProjectTask pk : taskCountList){
            if(pk.getTaskStatus().equals("1")){
                completeSize++;
            }else{
                unfinishedSize++;
            }
        }
        List<TaskCount> taskCounts = new ArrayList<TaskCount>();
        pt.setTaskStatus("1");
        List<ProjectTask> completeTaskResponsibleList = projectTaskManager.findTaskCountByResponsiblePersonName(pt);
        pt.setTaskStatus("0");
        List<ProjectTask> taskResponsibleCountList = projectTaskManager.findTaskCountByResponsiblePersonName(pt);

        for(ProjectTask pk :taskResponsibleCountList){
            boolean flag = false;
            TaskCount taskCount = new TaskCount();
            taskCount.setResponsibleCount(pk.getTaskCount());
            taskCount.setResponsiblePersonName(pk.getResponsiblePersonName());
            taskCount.setResponsiblePersonOid(pk.getResponsiblePersonOid());
            for(ProjectTask ps : completeTaskResponsibleList){
                if(ps.getResponsiblePersonOid().equals(pk.getResponsiblePersonOid())){
                    flag = true;
                    taskCount.setCompleteCount(ps.getTaskCount());
                }
            }
            if(!flag){
                taskCount.setCompleteCount("0");
            }
            taskCounts.add(taskCount);
        }
        mav.addObject("completeSize", completeSize);
        mav.addObject("unfinishedSize", unfinishedSize);
        mav.addObject("taskCountSize", taskCountList.size());
        mav.addObject("taskCounts", taskCounts);
        return mav;
    }
}
