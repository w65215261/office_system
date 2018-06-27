package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Report;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Repository
public class SummarizeReportDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/SummarizeReportMapper";

    public int insert(Report report) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".insert", report);
        return res;
    }

    public int update(Report report) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", report);
        return res;
    }

    public Report queryByReportId(String reportId) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".queryByReportId", reportId);
    }

    public List<Report> queryByRtpPersonId(Report rt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByRtpPersonId", rt);
    }

    public List<Report> queryReport(Report rt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryReport", rt);
    }

    public List<Report> queryByBelongsDate(Report rt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByBelongsDate", rt);
    }

    public List<Report> searchByOrgIdMap(Report rt) {
        return this.getSqlSession().selectList(NAME_SPACE + ".searchByOrgIdMap", rt);
    }
    public List<Report> searchReport(Report report){
        return this.getSqlSession().selectList(NAME_SPACE+".search",report);
    }
}
