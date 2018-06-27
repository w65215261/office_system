package com.pmcc.soft.core.organization.dao;

import com.pmcc.soft.core.organization.domain.NewsInfo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunyake on 15/3/19.
 */
@Repository
public class NewsInfoDao extends SqlSessionDaoSupport {


    public static final String NAME_SPACE = "com/pmcc/soft/core/organization/newsInfoMapper";



    public List<NewsInfo> query(NewsInfo ni){
        return getSqlSession().selectList(NAME_SPACE + ".query",ni);
    }
    /**
     * 新增
     * @param ch
     */
    public void insert(NewsInfo ni){
        getSqlSession().insert(NAME_SPACE + ".insert",ni);
    }

    /**
     * 删除
     * @param ch
     */
    public int delete(NewsInfo ni){

        int res = 0;
        String id = ni.getId();
        // 删除主表
        res = getSqlSession().delete(NAME_SPACE + ".delete",id);
        return res;
    }

    /**
     * 修改
     * @param ch
     */
    public void update(NewsInfo ni) {
        getSqlSession().update(NAME_SPACE + ".update", ni);

    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    public NewsInfo findById(String id) {
        return getSqlSession().selectOne(NAME_SPACE + ".findById",id);
    }


    public List<NewsInfo> queryNewsInfo(NewsInfo newsInfo){
        return getSqlSession().selectList(NAME_SPACE + ".queryNewsInfo",newsInfo);
    }



}
