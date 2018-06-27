package com.pmcc.soft.week.web;

import com.pmcc.soft.core.common.OnlineUser;
import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.OrganizationInfoManager;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.AppUtils;
import com.pmcc.soft.week.domain.GroupDiscuss;
import com.pmcc.soft.week.domain.GroupInfo;
import com.pmcc.soft.week.domain.ReplyDiscuss;
import com.pmcc.soft.week.manager.GroupDiscussManager;
import com.pmcc.soft.week.manager.GroupInfoManager;
import com.pmcc.soft.week.manager.ReplyDiscussManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaonan on 2015/7/20.
 */
@RequestMapping("groupDiscuss")
@Controller
public class GroupDiscussController {

    @Autowired
    GroupDiscussManager groupDiscussManager;
    @Autowired
    GroupInfoManager groupInfoManager;
    @Autowired
    ReplyDiscussManager replyDiscussManager;
    @Autowired
    PersonManageManager personManageManager;

    @Autowired
    OrganizationInfoManager organizationInfoManager;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(GroupDiscuss gd) {
        ModelAndView mav = new ModelAndView("/groupDiscuss/groupList");
        int page=gd.getPage();
        //gd.setInitPage(0);
        //查询出数据库中的该对象
        gd= groupDiscussManager.findById(gd);
        //增加浏览次数
        int browseNumber=gd.getBrowseNumber();
        browseNumber++;
        gd.setBrowseNumber(browseNumber);
        groupDiscussManager.update(gd);

        GroupInfo gi=groupInfoManager.findById(gd.getGroupInfoOid());
        gd.setGroupName(gi.getGroupName());
        //查询回帖
        ReplyDiscuss rd=new ReplyDiscuss();
        rd.setPage(page);
        rd.setInitPage(0);
        rd.setGroupDiscussOid(gd.getId());
        List<ReplyDiscuss> rdlist=replyDiscussManager.query(rd);
        //翻页
        rd.setInitPage(1);
        int totalRecord= replyDiscussManager.query(rd).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 / rd.getRows());
        int currentPage=rd.getPage();
        mav.addObject("totalRecord", totalRecord);
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);

        List<GroupDiscuss> list= new ArrayList<GroupDiscuss>();
        //转译
        PersonManage pi=new PersonManage();
        pi=personManageManager.queryPersonById(gd.getRptPerson());
        gd.setRptPerson(pi.getUserCname());
        //获取讨论组id
           String  groupInfoOid = gd.getGroupInfoOid();
        GroupInfo groupInfo=  groupInfoManager.findById(groupInfoOid );
        mav.addObject("groupInfo", groupInfo);
        mav.addObject("gd", gd);

        //添加
       // list.add(gd);
//        if(rdlist !=null && rdlist.size()>0) {
//            for (int i = 0; i < rdlist.size(); i++) {
//               PersonManage pm=personManageManager.queryPersonById(rdlist.get(i).getPeplyOid());
//                rdlist.get(i).setPeplyOid(pm.getUserCname());
//            }
//        }
        mav.addObject("discussList",rdlist);
        return mav;
    }

    /**
     * 跳转到组讨论列表页面
     * @return
     */
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public ModelAndView toList(GroupDiscuss groupDiscuss){
        ModelAndView modelAndView = new ModelAndView("groupDiscuss/groupDiscuss");
        String groupId=groupDiscuss.getGroupInfoOid();
        GroupInfo groupInfo=  groupInfoManager.findById(groupId);
        modelAndView.addObject("groupInfo", groupInfo);

        groupDiscuss.setInitPage(0);
        List<GroupDiscuss> groupDiscusses = groupDiscussManager.query(groupDiscuss);
//        for(int i=0;i<groupDiscusses.size();i++ ) {
//            PersonManage pm = personManageManager.queryPersonById(groupDiscusses.get(i).getRptPerson());
//            groupDiscusses.get(i).setRptPerson(pm.getUserCname());
//        }


        groupDiscuss.setInitPage(1);
        int totalRecord= groupDiscussManager.query(groupDiscuss).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 / groupDiscuss.getRows());
        int currentPage=groupDiscuss.getPage();



        modelAndView.addObject("totalRecord", totalRecord);
        modelAndView.addObject("totalPage", totalPage);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("groupDiscuss", groupDiscusses);
        return modelAndView;
    }




    /**
     * 跳转到新增小组讨论列表页面
     * @return
     */
    @RequestMapping(value = "/toAddDiscuss", method = RequestMethod.GET)
    public ModelAndView toAddDiscuss(String groupId){
        ModelAndView modelAndView = new ModelAndView("groupDiscuss/addGroupDiscuss");
          GroupInfo groupInfo=  groupInfoManager.findById(groupId);

        modelAndView.addObject("groupInfo",groupInfo);
        return modelAndView;
    }

//发帖
    @RequestMapping(value = "/postTopic", method = RequestMethod.POST)
    @ResponseBody
    public String   postTopic(GroupDiscuss groupDiscuss, HttpServletRequest request){

        String sessionId = WebUtils.getSessionId(request);
        OnlineUser online = (OnlineUser) AppUtils.getOnlineUser(sessionId);
        String  userId = online.getUserId();
        groupDiscuss.setRptPerson(userId);

         groupDiscussManager.insert(groupDiscuss);


        return "success";


    }

//回复
    @RequestMapping(value = "/postReply", method = RequestMethod.POST)
    @ResponseBody
    public String   postReply(ReplyDiscuss replyDiscuss, HttpServletRequest request){


        String sessionId = WebUtils.getSessionId(request);
        OnlineUser online = (OnlineUser) AppUtils.getOnlineUser(sessionId);
        String  userId = online.getUserId();
        replyDiscuss.setPeplyOid(userId);
        replyDiscussManager.insert(replyDiscuss);


        return "success";


    }

    /**
     *查询全部的帖子标题
     * @param gd
     * @return
     */
    @RequestMapping(value = "/showNew", method = RequestMethod.GET)
    public ModelAndView showNew(GroupDiscuss gd) {
        ModelAndView mav = new ModelAndView("groupDiscuss/groupNew");
        //分页查询
        gd.setInitPage(0);
         List<GroupDiscuss> list= groupDiscussManager.query(gd);
        mav.addObject("discussList",list);
        //查询全部，计算页面参数
        gd.setInitPage(1);
        int totalRecord= groupDiscussManager.query(gd).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 / gd.getRows());
        int currentPage=gd.getPage();

        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);

        return mav;
    }




}
