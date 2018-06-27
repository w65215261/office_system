package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.SystemFolder;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件夹表
 * Created by LvXL on 2016/5/9.
 */
@Repository
public class SystemFolderDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/SystemFolderMapper";

    public List<SystemFolder> query(SystemFolder systemFolder){
        return this.getSqlSession().selectList(NAME_SPACE + ".query", systemFolder);
    }

    public int insert(SystemFolder systemFolder){
        return this.getSqlSession().insert(NAME_SPACE + ".insert", systemFolder);
    }

    public int update(SystemFolder systemFolder){
        return this.getSqlSession().update(NAME_SPACE + ".update", systemFolder);
    }



}
