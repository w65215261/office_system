package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.week.domain.ProjectDynamics;
import com.pmcc.soft.week.domain.ProjectPersonRela;
import com.pmcc.soft.week.domain.TaskAttachment;
import com.pmcc.soft.week.manager.ProjectAndPersonManager;
import com.pmcc.soft.week.manager.ProjectDynamicsManager;
import com.pmcc.soft.week.manager.TaskAttachmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2015/10/21.
 */
@RequestMapping("projectDynamics")
@Controller
public class ProjectDynamicsController {

    @Autowired
    ProjectDynamicsManager projectDynamicsManager;
    @Autowired
    TaskAttachmentManager taskAttachmentManager;
    @Autowired
    ProjectAndPersonManager projectAndPersonManager;

    /**
     * 动态的展示
     * @param pd
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ProjectDynamics pd) {
        ModelAndView mav = new ModelAndView("/project/projectDynamics");
        int page = pd.getPage();
        String projectOid = pd.getProjectOid();
        //查询回帖
        pd.setPage(page);
        pd.setInitPage(0);
        List<ProjectDynamics> pds = projectDynamicsManager.findById(pd);
        //翻页
        pd.setInitPage(1);
        int totalRecord= projectDynamicsManager.findById(pd).size();
        int totalPage;
        if(totalRecord == 0){
            totalPage = 1;
        }else{
            totalPage = (int) Math.ceil(totalRecord * 1.0 / pd.getRows());
        }
        int currentPage = pd.getPage();
        TaskAttachment ta = new TaskAttachment();
        ProjectPersonRela ppr = new ProjectPersonRela();
        ppr.setProjectOid(projectOid);
        ta.setProjectOid(projectOid);
        List<ProjectPersonRela> pprs = projectAndPersonManager.findByProjectOid(ppr);
        List<TaskAttachment> atts = taskAttachmentManager.findTaskAttachmentByProjectOid(ta);
        int perSonNumber = pprs.size()+1;
        mav.addObject("personNumber", perSonNumber);
        mav.addObject("fileNumber", atts.size());
        mav.addObject("projectOid", projectOid);
        mav.addObject("totalRecord", totalRecord);
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);
        mav.addObject("dynamicsList", pds);
        return mav;
    }

    /**
     * 动态的保存
     * @param pd
     * @param request
     * @param session
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(ProjectDynamics pd, HttpServletRequest request,HttpSession session) throws IOException {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String name = loginUser.getUserCname();
        String content = request.getParameter("content");
        pd.setContent(content);
        pd.setRptPerson(name);
        pd.setRptTime(new Date());
        pd.setDelFlag("0");
        int res = 0;
        int i = projectDynamicsManager.insert(pd);
        if (res == i) {
            return "fail";
        } else {
            return "success";
        }

    }

    /**
     * 动态的删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(String id) {
        ProjectDynamics pd = new ProjectDynamics();
        pd.setId(id);
        projectDynamicsManager.delete(pd);
        return "success";
    }
}
