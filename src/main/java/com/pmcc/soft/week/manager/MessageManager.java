package com.pmcc.soft.week.manager;

/**
 * Created by sunyake on 15/8/11.
 */

import com.pmcc.soft.week.dao.MessageDao;
import com.pmcc.soft.week.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class MessageManager {
    @Autowired
    MessageDao messageDao;


    public List<Message> query(Message message){

        return messageDao.query(message);
    }
    public List<Message> queryGarbage(Message message){

        return messageDao.queryGarbage(message);

    }
    public int insert(Message message){

        message.setCreateDate(new Date());
        message.setDelFlag("0");
        return  messageDao.insert(message);
    }

    public int update(Message message){

        return messageDao.update(message);
    }

    public Message findByid(String id){
        return messageDao.findById(id);
    }

    public int delete(String id){
        return messageDao.delete(id);
    }

    public int updateRead(String id ){

        return messageDao.updateRead(id);
    }
    public List<Message> queryOut(Message message){
        return messageDao.queryOut(message);
    }
}
