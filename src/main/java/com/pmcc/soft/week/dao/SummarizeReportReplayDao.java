package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SummarizeReportReplay;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Repository
public class SummarizeReportReplayDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/SummarizeReportReplayMapper";

    public int insert(SummarizeReportReplay summarizeReportReplay) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", summarizeReportReplay);
        return res;
    }
    public List<SummarizeReportReplay> query(SummarizeReportReplay summarizeReportReplay) {
        return this.getSqlSession().selectList(NAME_SPACE + ".query", summarizeReportReplay);
    }
}
