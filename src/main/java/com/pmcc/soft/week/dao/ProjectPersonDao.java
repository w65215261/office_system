package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ProjectPersonRela;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
@Repository
public class ProjectPersonDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/projectPersonMapper";
    public List<ProjectPersonRela> findByProjectOid(ProjectPersonRela ppr) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findByProjectOid", ppr);
    }

    public ProjectPersonRela findByOid(ProjectPersonRela ppr) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findByOid", ppr);
    }

    public int insert(ProjectPersonRela ppr) {
        ppr.setDelFlag("0");
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", ppr);
        return res;
    }

    public int delete(ProjectPersonRela ppr) {
        ppr.setDelFlag("1");
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".delete", ppr);
        return res;
    }
    public List<ProjectPersonRela> findByPersonOid(ProjectPersonRela ppr) {
        return this.getSqlSession().selectList(NAME_SPACE + ".findByPersonOid", ppr);
    }
    public List<ProjectPersonRela>queryProjectPersonRela(ProjectPersonRela projectPersonRela){
        return this.getSqlSession().selectList(NAME_SPACE + ".queryProjectPersonRela", projectPersonRela);
    }
    public List<ProjectPersonRela>queryProjectPersonRelaStatus(ProjectPersonRela projectPersonRela){
        return  this.getSqlSession().selectList(NAME_SPACE + ".queryProjectPersonRelaStatus", projectPersonRela);
    }
}


