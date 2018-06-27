package com.pmcc.soft.week.web;

import com.pmcc.soft.week.domain.TaskAttachment;
import com.pmcc.soft.week.manager.TaskAttachmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by asus on 2015/10/26.
 */
@Controller
@RequestMapping("projectAttachment")
public class ProjectAttachmentController {

    @Autowired
    TaskAttachmentManager taskAttachmentManager;

    /**
     * 全部附件的展示
     * @param ta
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(TaskAttachment ta) {
        ModelAndView mav = new ModelAndView("/project/projectAttachment");
        List<TaskAttachment> atts = taskAttachmentManager.findTaskAttachmentByProjectOid(ta);
        mav.addObject("taskAttachmentList", atts);
        return mav;
    }
}
