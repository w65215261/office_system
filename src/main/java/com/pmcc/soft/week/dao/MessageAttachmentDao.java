package com.pmcc.soft.week.dao;

/**
 * Created by sunyake on 15/8/11.
 */

import com.pmcc.soft.week.domain.MessageAttachment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MessageAttachmentDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/messageAttachmentMapper";

    public List<MessageAttachment> query(MessageAttachment object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".query", object);
    }

    public int insert(MessageAttachment mr) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", mr);
        return res;
    }

    public int update(MessageAttachment mr) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", mr);
        return res;
    }

    public int delete(String  id) {
        int res = 0;
        res = getSqlSession().delete(NAME_SPACE + ".delete", id);
        return res;
    }

    public MessageAttachment findById(String  id) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findById", id);
    }
}
