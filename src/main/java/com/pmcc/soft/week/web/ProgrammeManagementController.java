package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.week.domain.ProgrammeManagement;
import com.pmcc.soft.week.domain.ProjectTask;
import com.pmcc.soft.week.manager.ProgrammeManagementManager;
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
 * Created by asus on 2016/01/15.
 */
@Controller
@RequestMapping("programmeManagement")
public class ProgrammeManagementController {

    @Autowired
    ProgrammeManagementManager programmeManagementManager;

    /**
     * 日程的展示
     * @param session
     * @param pm
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(HttpSession session,ProgrammeManagement pm) {
        ModelAndView mav = new ModelAndView("/programmeManagement/programmeManagement");
        return mav;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public List<ProgrammeManagement> query(HttpSession session,ProgrammeManagement pm) {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String userId = loginUser.getId();
        pm.setUserOid(userId);
        List<ProgrammeManagement> pmList = programmeManagementManager.queryProgramme(userId);
        return pmList;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpSession session,ProgrammeManagement pm) {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String userId = loginUser.getId();
        pm.setUserOid(userId);
        programmeManagementManager.insert(pm);
        return "success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(HttpSession session,ProgrammeManagement pm) {
        PersonManage loginUser = (PersonManage)session.getAttribute("loginUser");
        String userId = loginUser.getId();
        pm.setUserOid(userId);
        programmeManagementManager.update(pm);
        return "success";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(HttpSession session,String oid) {
        programmeManagementManager.delete(oid);
        return "success";
    }
    @RequestMapping(value = "/queryProgra", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryProgra(String oid) {
        ModelAndView mav=new ModelAndView("/login/prograDetail");
        ProgrammeManagement programmeManagement=programmeManagementManager.queryProgra(oid);
        mav.addObject("programmeManagement",programmeManagement);
        return mav;
    }
    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
