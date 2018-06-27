package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ProjectDynamics;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/10/22.
 */
@Repository
public class ProjectDynamicsDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/ProjectDynamicsMapper";
    public List<ProjectDynamics> findById(ProjectDynamics pd) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findById", pd);
    }

    public int insert(ProjectDynamics pd) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", pd);
        return res;
    }

    public int delete(ProjectDynamics pd) {
        pd.setDelFlag("1");
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".delete", pd);
        return res;
    }
}


