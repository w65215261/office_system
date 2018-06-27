package com.pmcc.soft.week.manager;

/**
 * Created by sunyake on 15/8/11.
 */

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.MessageAttachmentDao;
import com.pmcc.soft.week.domain.MessageAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunyake on 15/6/29.
 */
@Transactional
@Service
public class MessageAttachmentManager {
    @Autowired
    MessageAttachmentDao messageAttachmentDao;


    public List<MessageAttachment> query(MessageAttachment messageAttachment) {
        return messageAttachmentDao.query(messageAttachment);

    }


    public int insert(MessageAttachment messageAttachment){
        messageAttachment.setId(UUIDGenerator.getUUID());
        return messageAttachmentDao.insert(messageAttachment);
    }

    public int update(MessageAttachment messageAttachment){
        return messageAttachmentDao.update(messageAttachment);

    }

    public int delete(String id){

        return messageAttachmentDao.delete(id);

    }



}