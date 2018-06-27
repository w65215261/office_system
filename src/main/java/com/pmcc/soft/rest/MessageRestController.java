package com.pmcc.soft.rest;

import com.pmcc.soft.core.organization.domain.PersonManage;
import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.SystemParamsUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.Message;
import com.pmcc.soft.week.domain.MessageAttachment;
import com.pmcc.soft.week.domain.Note;
import com.pmcc.soft.week.domain.NoteBook;
import com.pmcc.soft.week.manager.MessageAttachmentManager;
import com.pmcc.soft.week.manager.MessageManager;
import com.pmcc.soft.week.manager.NoteBookManager;
import com.pmcc.soft.week.manager.NoteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2016/01/05.
 */
@Controller
@RequestMapping("message")
public class MessageRestController {

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
    @ResponseBody
    public List<Message> query(Message message ,HttpServletRequest request,HttpSession session){
        String userId =request.getParameter("personId");
        message.setMessageTo(userId);
        message.setMessageToFlag("0");
        message.setInitPage(1);
        List<Message>  list =  messageManager.query(message) ;
        //查询全部，计算页面参数
        return list;
    }

    /**
     * 查询收件箱列表
     * @param message
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryOutbox", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> queryOutbox(Message message ,HttpServletRequest request,HttpSession session){
        String userId =request.getParameter("personId");
        message.setMessageFromFlag("0");
        message.setMessageFrom(userId);
        message.setInitPage(1);
        List<Message>  list =  messageManager.query(message) ;
        //查询全部，计算页面参数
        return list;
    }
    /**
     * 查询垃圾列表
     * @param message
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/queryGarbage", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> queryGarbage(Message message ,HttpServletRequest request,HttpSession session){
        String userId =request.getParameter("personId");
        message.setMessageFrom(userId);
        message.setMessageTo(userId);
        message.setDelFlag("1");
        message.setInitPage(1);
        List<Message>  list =  messageManager.queryGarbage(message);
        //查询全部，计算页面参数
        return list;
    }

    /**
     * 查找详情
     * @param request
     * @return
     */
    @RequestMapping(value="read", method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> read(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        String id =request.getParameter("id");
        Message message=messageManager.findByid(id);
        map.put("message", message);
        MessageAttachment ma=new MessageAttachment();
        ma.setMessageId(id);
        ma.setInitPage(1);

        List<MessageAttachment> messageAttachmentList=  messageAttachmentManager.query(ma);
        map.put("attList", messageAttachmentList);
        return map;
    }

    /**
     * 删除站内信
     * @param stri
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteStatus", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteStatus(String stri,HttpServletRequest request) {
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
        boolean  data= true;
        return data;
    }

    /**
     * 还原站内信
     * @param stri
     * @param request
     * @return
     */
    @RequestMapping(value = "/restoreStatus", method = RequestMethod.GET)
    @ResponseBody
    public Boolean restoreStatus(String stri,HttpServletRequest request) {
        String personId=request.getParameter("personId");
        Message message =new Message();
        String[]  str = stri.split("_");
        boolean  data= true;
        for (int i = 1; i < str.length; i++) {
            message.setId(str[i]);
            Message message1= messageManager.findByid(str[i]);
            String messageTo=message1.getMessageTo();
            String messageFrom=message1.getMessageFrom();
            if(personId.equals(messageTo)){
                if(message1.getMessageFromFlag().equals("0")){
                    message.setMessageToFlag("0");
                }else{
                    message.setMessageToFlag("0");
                }

            }
            if (personId.equals(messageFrom)){
                if(message1.getMessageToFlag().equals("0")){
                    message.setMessageFromFlag("0");
                }
                message.setMessageFromFlag("0");
            }
        int res=0;
           res= messageManager.update(message);//改变计划主表字段
            if(res>0){
                data=true;
            }else{
                data=false;
            }
        }

        return data;
    }

    /**
     * 彻底删除站内信
     * @param stri
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delete(String  stri,HttpServletRequest request) {
        String[]  str = stri.split("_");
        String personId=request.getParameter("personId");
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
                if(personId.equals(messageFrom)){
                    String messageToFlag=message.getMessageToFlag();
                    if(messageToFlag.equals("2")){
                        messageManager.delete(str[i]);
                    }else{
                        message.setMessageFromFlag("2");
                        message.setId(str[i]);
                        messageManager.update(message);
                    }
                }
                if(personId.equals(messageTo)){
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
        boolean  data=true;
        return data;
    }

    /**
     * 生成Oid
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
     * 附件上传
     * @param fileToUpload
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(
            @RequestParam(value = "fileToUpload", required = false) MultipartFile[] fileToUpload,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> res = new HashMap<String, Object>();

        DateUtil du = new DateUtil();
        // 附加实体
        MessageAttachment att = new MessageAttachment();
        // 合同子表主键值
        String uuid = request.getParameter("uuid");

        if(uuid == null || "".equals(uuid)){
            uuid = UUIDGenerator.getUUID();
        }
        System.out.println(fileToUpload);
        att.setMessageId(uuid);

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
        String path = path = request.getSession().getServletContext().getRealPath("/fileMessage");
        att.setFileUrl("fileMessage/" + fileName);
        att.setFileName(fileToUpload[0].getOriginalFilename());
        att.setFileMathName(fmn);
        att.setCreateDate(new Date());
        att.setDelFlag("0");
        messageAttachmentManager.insert(att);

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

        response.getWriter().write(uuid);
        response.getWriter().flush();
    }
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Boolean insert(Message message,HttpServletRequest request,HttpSession session){

        String id=request.getParameter("id");
        String messageTo=request.getParameter("messageTo");
        String messageToName=request.getParameter("messageToName");
        String messageContent=request.getParameter("messageContent");
        String messageTitle=request.getParameter("messageTitle");
        String messageFromName=request.getParameter("messageFromName");
        String messageFrom=request.getParameter("messageFrom");
        message.setId(id);
        message.setMessageTo(messageTo);
        message.setMessageToName(messageToName);
        message.setMessageContent(messageContent);
        message.setMessageTitle(messageTitle);
        message.setMessageFrom(messageFrom);
        message.setMessageFromName(messageFromName);
        message.setShowFlag("1");
        message.setMessageFromFlag("0");
        message.setMessageToFlag("0");
        int res=0;
        boolean data=true;
        res=messageManager.insert(message);
        if(res>0){
            data=true;
        }else{
            data=false;
        }
        return data;
    }


}