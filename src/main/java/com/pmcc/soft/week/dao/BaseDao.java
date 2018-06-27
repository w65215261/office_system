package com.pmcc.soft.week.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * 公共DAO
 * Created by yongxingsun
 * on 2015/7/20 0008 14:43
 */
public class BaseDao<T> extends SqlSessionDaoSupport {

    public String nameSpace;

    public BaseDao(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    /**
     * 查询全部
     * @param t
     * @return
     */
    public List<T> query(T t){
        return getSqlSession().selectList(nameSpace+".query",t);
    }

    /**
     * 新增
     * @param t
     * @return
     */
    public int insert(T t){
        return getSqlSession().insert(nameSpace + ".insert", t);
    }

    /**
     * 修改
     * @param t
     * @return
     */
    public int update(T t){
        return getSqlSession().update(nameSpace + ".update", t);
    }

    /**
     * 删除
     * @param id
     */
    public int delete(String id) {
        return getSqlSession().delete(nameSpace + ".delete", id);
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    public Object findById(String id){
        return getSqlSession().selectOne(nameSpace + ".findById", id);
    }

}
