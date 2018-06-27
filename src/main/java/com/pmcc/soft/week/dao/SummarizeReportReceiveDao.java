package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SummarizeReportReceive;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Repository
public class SummarizeReportReceiveDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/SummarizeReportReceiveMapper";

    public int insert(SummarizeReportReceive srre) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".insert", srre);
        return res;
    }

    public List<SummarizeReportReceive> queryByReceivePersonId(SummarizeReportReceive summarizeReportReceive) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByReceivePersonId", summarizeReportReceive);
    }

    public List<SummarizeReportReceive> query(SummarizeReportReceive summarizeReportReceive) {
        return this.getSqlSession().selectList(NAME_SPACE + ".query", summarizeReportReceive);
    }

    public int update(SummarizeReportReceive srre) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", srre);
        return res;
    }
    public List<SummarizeReportReceive> queryReportByReceivePersonId(SummarizeReportReceive summarizeReportReceive) {
        return this.getSqlSession().selectList(NAME_SPACE + ".queryReportByReceivePersonId", summarizeReportReceive);
    }
}
