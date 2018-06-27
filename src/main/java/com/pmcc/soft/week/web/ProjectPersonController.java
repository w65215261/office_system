package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.week.domain.Project;
import com.pmcc.soft.week.domain.ProjectPersonRela;
import com.pmcc.soft.week.manager.ProjectAndPersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
@Controller
@RequestMapping("projectPerson")
public class ProjectPersonController {

    @Autowired
    ProjectAndPersonManager projectAndPersonManager;
    @Autowired
    com.pmcc.soft.week.manager.ProjectManager ProjectManager;
    @Autowired
    com.pmcc.soft.week.manager.ProjectManager projectManager;

    /**
     * 人员的展示
     * @param ppr
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ProjectPersonRela ppr) {
        ModelAndView mav = new ModelAndView("/project/projectPerson");
        String projectOid = ppr.getProjectOid();
        Project project = projectManager.findProjectByOid(projectOid);
        String projectManagerPerson = project.getProjectManager();
        String projectManagerId = project.getProjectManagerId();
        ProjectPersonRela pr = new ProjectPersonRela();
        pr.setPersonOid(projectManagerId);
        pr.setProjectOid(projectOid);
        ProjectPersonRela prr  = projectAndPersonManager.findByOid(pr);
        if(prr != null){
            projectAndPersonManager.delete(prr);
        }
        List<ProjectPersonRela> pprs = projectAndPersonManager.findByProjectOid(ppr);
        int perSonNumber = pprs.size()+1;
        mav.addObject("perSonNumber", perSonNumber);
        mav.addObject("projectManagerPerson", projectManagerPerson);
        mav.addObject("projectOid", projectOid);
        mav.addObject("projectPersonList", pprs);
        return mav;
    }

    /**
     * 添加人员
     * @param ppr
     * @param request
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(ProjectPersonRela ppr, HttpServletRequest request,HttpSession session) throws IOException {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String name = loginUser.getUserCname();
        ppr.setRptPerson(name);
        ppr.setRptTime(new Date());
        ProjectPersonRela pr = projectAndPersonManager.findByOid(ppr);
        Project project = new Project();
        project.setId(ppr.getProjectOid());
        project.setProjectManagerId(ppr.getPersonOid());
        Project pj = ProjectManager.findProjectByOidAndByPersonId(project);
        if(pr != null||pj != null){
            return "already";
        }
        int res = 0;
        int i = projectAndPersonManager.insert(ppr);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 删除人员
     * @param ppr
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(ProjectPersonRela ppr) {
        projectAndPersonManager.delete(ppr);
        return "success";
    }
}
