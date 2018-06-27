package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Report;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by H on 2015/12/16.
 */
@Repository
public class ReportDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/reportMapper";

    public int insert(Report report) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", report);
        return res;
    }
    public int update(Report report){
        int res=0;
        res=this.getSqlSession().update(NAME_SPACE+".update",report);
        return res;
    }


    public Report findById(String id){
        return this.getSqlSession().selectOne(NAME_SPACE + ".findById", id);

    }

    public List<Report> query(Report report){
        return this.getSqlSession().selectList(NAME_SPACE + ".query", report);
    }

    public  List<Report> queryShow(Report report){
        return this.getSqlSession().selectList(NAME_SPACE + ".queryShow", report);
    }
    public List<Report> queryByDate(Report report) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByDate", report);
    }

    public  List<Report> queryCalendar(Report report){
        return this.getSqlSession().selectList(NAME_SPACE+".queryCalendar",report);
    }
    public  List<Report> findDayReportStatus(Report report){
        return this.getSqlSession().selectList(NAME_SPACE+".findDayReportStatus",report);
    }
    public List<Report> findByWeek(Report report){
        return this.getSqlSession().selectList(NAME_SPACE+".findByWeek",report);
    }

    public List<Report> searchReport(Report report){
        return this.getSqlSession().selectList(NAME_SPACE+".search",report);
    }

    public List<Report> searchByDate(Report report){
        return this.getSqlSession().selectList(NAME_SPACE+".searchReport",report);
    }
    public List<Report> searchByOrgIdMap(Report report) {
        return this.getSqlSession().selectList(NAME_SPACE + ".searchByOrgIdMap", report);
    }
}
