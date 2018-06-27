package com.pmcc.soft.week.web;

/**
 * Created by sunyake on 15/8/11.
 */

import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.Message;
import com.pmcc.soft.week.domain.MessageAttachment;
import com.pmcc.soft.week.manager.MessageAttachmentManager;
import com.pmcc.soft.week.manager.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("message")
public class MessageController {

    @Autowired
    MessageManager messageManager;
    @Autowired
    PersonManageManager personManageManager;

    @Autowired
    MessageAttachmentManager messageAttachmentManager;
    /**
     * 查询发件箱的列表
     * @param message
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryInbox", method = RequestMethod.GET)
    public ModelAndView  query(Message   message ,HttpServletRequest request,HttpSession session){
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId =user.getId();
        message.setMessageTo(userId);
        message.setMessageToFlag("0");
        ModelAndView mav =new ModelAndView("letter/insideLetterList");
        message.setInitPage(0);
        List<Message>  list =  messageManager.query(message) ;
        //查询全部，计算页面参数
        message.setInitPage(1);
        int totalRecord= messageManager.query(message).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  message.getRows());
        int currentPage= message.getPage();
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);


        mav.addObject("letterList", list);

        return mav;
    }

    /**
     * 查询收件箱列表
     * @param message
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryOutbox", method = RequestMethod.GET)
    public ModelAndView queryOutbox(Message message,HttpServletRequest request,HttpSession session){
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId =user.getId();
        message.setMessageFromFlag("0");
        message.setMessageFrom(userId);
        ModelAndView mav =new ModelAndView("letter/insideLetterOutList");
        message.setInitPage(0);
        List<Message>  list =  messageManager.queryOut(message) ;
        //查询全部，计算页面参数
        message.setInitPage(1);
        int totalRecord= messageManager.queryOut(message).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  message.getRows());
        int currentPage= message.getPage();
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);

        mav.addObject("letterOutList", list);
        return mav;

    }
    /**
     * 查询垃圾列表
     * @param message
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryGarbage", method = RequestMethod.GET)
    public ModelAndView queryGarbage(Message message,HttpServletRequest request,HttpSession session){
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId =user.getId();
        message.setMessageFrom(userId);
        message.setMessageTo(userId);
        message.setDelFlag("1");
        ModelAndView mav =new ModelAndView("letter/insideLetterGarbage");
        message.setInitPage(0);
        List<Message>  list =  messageManager.queryGarbage(message);
        //查询全部，计算页面参数
        message.setInitPage(1);
        int totalRecord= messageManager.queryGarbage(message).size();
        int totalPage=(int) Math.ceil(totalRecord * 1.0 /  message.getRows());
        int currentPage= message.getPage();
        mav.addObject("totalPage", totalPage);
        mav.addObject("currentPage", currentPage);

        mav.addObject("letterGarbage", list);
        return mav;

    }
    /**
     * 显示菜单
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String inboxShow() {
        return "letter/insideLetter";
    }


    /**
     * 显示菜单
     * @return
     */
    @RequestMapping(value = "/composeEmail", method = RequestMethod.GET)
    public String composeEmail() {
        return "letter/composeEmail";
    }


    /**
     * 发送邮件
     * @param message
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Message message,HttpServletRequest request,HttpSession session){

        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String userId =user.getId();
        message.setMessageFrom(userId);
        message.setMessageFromName(user.getUserCname());
        message.setShowFlag("1");
        message.setMessageFromFlag("0");
        message.setMessageToFlag("0");
        messageManager.insert(message);

        return "success";
    }


    @RequestMapping(value = "/deleteStatus", method = RequestMethod.GET)
    @ResponseBody
    public String deleteStatus(String stri,HttpServletRequest request) {
        Message message =new Message();
        String[]  str = stri.split("_");
        String flag=request.getParameter("flag");
        message.setDelFlag("1");
        if(flag.equals("1")) {
            message.setMessageToFlag("1");
        }else if (flag.equals("2")){
            message.setMessageFromFlag("1");
        }

        for (int i = 1; i < str.length; i++) {
            message.setId(str[i]);
            messageManager.update(message);//改变计划主表字段
        }

        String  data= "success";
        return data;


    }

    /**
     * 还原站内信
     * @param stri
     * @param session
     * @return
     */
    @RequestMapping(value = "/restoreStatus", method = RequestMethod.GET)
    @ResponseBody
    public String restoreStatus(String stri,HttpSession session) {
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        Message message =new Message();
        String[]  str = stri.split("_");

        for (int i = 1; i < str.length; i++) {
            message.setId(str[i]);
            Message message1= messageManager.findByid(str[i]);
            String messageTo=message1.getMessageTo();
            String messageFrom=message1.getMessageFrom();
            if(user.getId().equals(messageTo)){
                if(message1.getMessageFromFlag().equals("0")){
                    message.setMessageToFlag("0");
                }else{
                    message.setMessageToFlag("0");
                }

            }
            if(user.getId().equals(messageFrom)){
                if(message1.getMessageToFlag().equals("0")){
                    message.setMessageFromFlag("0");
                }
                message.setMessageFromFlag("0");
            }

                messageManager.update(message);//改变计划主表字段
        }

        String  data= "success";
        return data;


    }

    /**
     * 彻底删除
     * @param stri
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(String  stri,HttpServletRequest request,HttpSession session) {
        PersonManage user = (PersonManage)session.getAttribute("loginUser");
        String[]  str = stri.split("_");
        String flag=request.getParameter("flag");
        for (int i = 1; i < str.length; i++) {
            Message message=messageManager.findByid(str[i]);
            if(flag.equals("1")){
                String messageFromFlag=message.getMessageFromFlag();
                if(messageFromFlag.equals("2")){
                    messageManager.delete(str[i]);
                }else{
                    message.setDelFlag("1");
                    message.setMessageToFlag("2");
                    message.setId(str[i]);
                    messageManager.update(message);
                }
            }else if(flag.equals("2")){
                String messageToFlag=message.getMessageToFlag();
                if(messageToFlag.equals("2")){
                    messageManager.delete(str[i]);
                }else{
                    message.setDelFlag("1");
                    message.setMessageFromFlag("2");
                    message.setId(str[i]);
                    messageManager.update(message);
                }
            }else if(flag.equals("3")){
            String messageFrom=message.getMessageFrom();
            String messageTo=message.getMessageTo();
                if(user.getId().equals(messageFrom)){
                    String messageToFlag=message.getMessageToFlag();
                    if(messageToFlag.equals("2")){
                        messageManager.delete(str[i]);
                    }else{
                        message.setMessageFromFlag("2");
                        message.setId(str[i]);
                        messageManager.update(message);
                    }
                }
                if(user.getId().equals(messageTo)){
                    String messageFromFlag=message.getMessageToFlag();
                    if(messageFromFlag.equals("2")){
                        messageManager.delete(str[i]);
                    }else{
                        message.setMessageToFlag("2");
                        message.setId(str[i]);
                        messageManager.update(message);
                    }
                }

            }
        }

        String  data= "success";
        return data;
    }

    /**
     * 得到ID
     * @param message
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUUID", method = RequestMethod.GET)
    @ResponseBody
    public String getUUID(Message message,HttpServletRequest request) {
        return UUIDGenerator.getUUID();
    }


    /**
     * 查找详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value="read", method=RequestMethod.GET)
    public ModelAndView read(HttpServletRequest request,String id){
        String flag=request.getParameter("flag");
        ModelAndView model =new ModelAndView();
        if(flag.equals("2")){
            model =new ModelAndView("letter/openOutLetter");
        }else if(flag.equals("1")){
         model =new ModelAndView("letter/insideLetterOpen");
            messageManager.updateRead(id);
        }

        Message message=messageManager.findByid(id);

        MessageAttachment ma=new MessageAttachment();
        ma.setMessageId(id);
        ma.setInitPage(1);
        List<MessageAttachment> messageAttachmentList=  messageAttachmentManager.query(ma);
        model.addObject("attList",messageAttachmentList);
        model.addObject("message", message);

        return model;
    }

    /**
     * 回复邮件
     * @param request
     * @param messageTo
     * @return
     */
    @RequestMapping(value="showReplyEmail")
    public ModelAndView showReplyEmail(HttpServletRequest request,String messageTo){
        PersonManage pm=personManageManager.queryPersonById(messageTo);

        ModelAndView model=new ModelAndView("/letter/replyLetter");
        model.addObject("messageTo",messageTo);
        model.addObject("messageToName",pm.getUserCname());

        return model;
    }



    @InitBinder
    protected void initBinder(WebRequest request,
                              WebDataBinder binder) throws Exception {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }




}
