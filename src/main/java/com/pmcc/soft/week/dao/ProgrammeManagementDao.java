package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ProgrammeManagement;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus on 2016/01/18.
 */
@Repository
public class ProgrammeManagementDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/programmeManagementMapper";

    public List<ProgrammeManagement> queryProgramme(String userOid) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryProgramme", userOid);
    }

    public int insert(ProgrammeManagement pm) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", pm);
        return res;
    }

    public int update(ProgrammeManagement pm) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", pm);
        return res;
    }

    public int delete(String oid) {
        int res = 0;
        res = this.getSqlSession().delete(NAME_SPACE + ".delete", oid);
        return res;
    }

    /**
     * 查询日程的前4条记录用于首页面展示
     * @param userOid
     * @return
     */
    public List<ProgrammeManagement> query(String userOid) {
        return this.getSqlSession().selectList(NAME_SPACE + ".query", userOid);
    }
    public ProgrammeManagement queryProgra(String oid){
        return this.getSqlSession().selectOne(NAME_SPACE + ".queryProgra", oid);
    }
}


