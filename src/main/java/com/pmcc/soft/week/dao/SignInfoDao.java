package com.pmcc.soft.week.dao;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.SignInfo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunyake on 15/10/27.
 */
@Repository
public class SignInfoDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/signInfoMapper";

    public List<SignInfo> query(SignInfo object) {

        return this.getSqlSession().selectList(NAME_SPACE + ".query", object);
    }

    public int insert(SignInfo object) {

        String id= UUIDGenerator.getUUID();
        object.setId(id);
        object.setDelFlag("0");
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", object);
        return res;
    }

    public int update(SignInfo mr) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", mr);
        return res;
    }

    public int delete(String  id) {
        int res = 0;
        res = getSqlSession().delete(NAME_SPACE + ".delete", id);
        return res;
    }

    public SignInfo findById(String  id) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findById", id);
    }

    public List<SignInfo> queryByDate(SignInfo mr) {
        return    this.getSqlSession().selectList(NAME_SPACE + ".queryByDate", mr);
    }

    public List<SignInfo>queryOneDay(SignInfo object){
    return    this.getSqlSession().selectList(NAME_SPACE + ".queryOneDay",object);
    }
}
