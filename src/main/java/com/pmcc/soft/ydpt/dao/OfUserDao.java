package com.pmcc.soft.ydpt.dao;

import com.pmcc.soft.ydpt.domain.OfUser;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OfUserDao extends SqlSessionDaoSupport{
    public static final String NAME_SPACE = "com/pmcc/soft/ydpt/ofUserMapper";
    public List<OfUser> query(OfUser param) {
        return getSqlSession().selectList(NAME_SPACE + ".query",param);
    }
    public OfUser findById(OfUser param) {
        return getSqlSession().selectOne(NAME_SPACE + ".query", param.getUsername());
    }
    public void insert(OfUser param) {
        param.setCreationDate(new Long(new Date().getTime()).toString());
        param.setModificationDate(new Long(new Date().getTime()).toString());
        getSqlSession().insert(NAME_SPACE + ".insert", param);
    }

    public int delete(OfUser param) {
        return getSqlSession().delete(NAME_SPACE + ".delete", param.getUsername());
    }

    public void update(OfUser param) {
        getSqlSession().update(NAME_SPACE + ".update", param);
    }

}
