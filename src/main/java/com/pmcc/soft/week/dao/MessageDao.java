package com.pmcc.soft.week.dao;

/**
 * Created by sunyake on 15/8/11.
 */

import com.pmcc.soft.week.domain.Message;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunyake on 15/6/26.
 */
@Repository
public class MessageDao extends SqlSessionDaoSupport {


    public static final String NAME_SPACE = "com/pmcc/soft/week/messageMapper";

    public List<Message> query(Message object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".query", object);
    }
    public List<Message> queryGarbage(Message object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryGarbage", object);
    }
    public int insert(Message mr) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", mr);
        return res;
    }

    public int update(Message mr) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", mr);
        return res;
    }

    public int delete(String  id) {
        int res = 0;
        res = getSqlSession().delete(NAME_SPACE + ".delete", id);
        return res;
    }

    public Message findById(String  id) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findById", id);
    }
    public int updateRead(String id ){
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".updateRead", id);
        return res;
    }
    public List<Message> queryOut(Message object) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryOut", object);
    }
}
