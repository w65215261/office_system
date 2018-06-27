package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.SummarizeReportDao;
import com.pmcc.soft.week.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Transactional
@Service
public class SummarizeReportManager {
    @Autowired
    private SummarizeReportDao summarizeReportDao;

    public int insert(Report report){
        return summarizeReportDao.insert(report);
    }

    public int update(Report report){
        return summarizeReportDao.update(report);
    }

    public Report queryByReportId(String reportId){
        return summarizeReportDao.queryByReportId(reportId);
    }

    public List<Report> queryByRtpPersonId(Report rt){
        return summarizeReportDao.queryByRtpPersonId(rt);
    }

    public List<Report> queryReport(Report rt){
        return summarizeReportDao.queryReport(rt);
    }
    public List<Report> searchReport(Report report){
        return summarizeReportDao.searchReport(report);
    }
    public List<Report> queryByBelongsDate(Report rt){
        return summarizeReportDao.queryByBelongsDate(rt);
    }
    public List<Report> searchByOrgIdMap(Report report) {
        return summarizeReportDao.searchByOrgIdMap(report);
    }

}
