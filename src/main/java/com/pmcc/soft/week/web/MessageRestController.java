package com.pmcc.soft.week.web;

import com.pmcc.soft.core.organization.manager.PersonManageManager;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.Message;
import com.pmcc.soft.week.manager.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/9/11.
 */

@Controller
@RequestMapping("restMessage")
public class MessageRestController {

    @Autowired
    MessageManager messageManager;
    @Autowired
    private PersonManageManager personManageManager;

    @RequestMapping(value = "/queryInbox", method = RequestMethod.GET)
    @ResponseBody
    public List<Message>  query(String messageTo){
        Message message =new Message();
        message.setMessageTo(messageTo);
        message.setDelFlag("0");
        message.setInitPage(0);
        List<Message> list =  messageManager.query(message) ;
        return list;
    }


    @RequestMapping(value = "/queryOutbox", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> queryOutbox(String messageFrom){
        Message message =new Message();
        message.setDelFlag("0");
        message.setMessageFrom(messageFrom);
        message.setInitPage(0);
        List<Message>  list =  messageManager.query(message);
        return list;

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(Message message){
        message.setId(UUIDGenerator.getUUID());
        String name =  personManageManager.queryPersonById(message.getMessageFrom()).getUserCname();
        message.setMessageFromName(name );
        messageManager.insert(message);
        return "success";
    }


    @RequestMapping(value="read", method=RequestMethod.GET)
    public Message read(Message message){
        Message messages=messageManager.findByid(message.getId());
        return messages;
    }



}
