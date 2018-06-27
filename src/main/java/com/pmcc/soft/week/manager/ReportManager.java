package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.ReportDao;
import com.pmcc.soft.week.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by H on 2015/12/16.
 */
@Transactional
@Service
public class ReportManager {
    @Autowired
    private ReportDao reportDao;

    public  int insert(Report report){
        report.setId(UUIDGenerator.getUUID());
        return reportDao.insert(report);
    }

    public int update(Report report){
        return reportDao.update(report);
    }


    public  Report findById(String id){
        return reportDao.findById(id);
    }


    public List<Report> query(Report report){
        return reportDao.query(report);
    }

    public List<Report> queryShow(Report report){
        return reportDao.queryShow(report);
    }


    public int  queryByDate(String id, Date date){
        Report report=new Report();
        report.setRtpPersonId(id);
        report.setBelongsDate(date);
        report.setType("0");
        List<Report> reports=reportDao.queryByDate(report);//查询数据
        if (reports!=null&&reports.size()>0){
            String rptDate=null;//赋值判断
            String belongsDate =null;//赋值判断
            for (int i=0;i<reports.size();i++){
                Date rtime=reports.get(i).getRptDate();
                Date btime=reports.get(i).getBelongsDate();
                String rDate=new SimpleDateFormat("yyyy-MM-dd").format(rtime);
                String bDate=new SimpleDateFormat("yyyy-MM-dd").format(btime);
                rptDate=rDate;
                belongsDate=bDate;
            }
            if (rptDate.equals(belongsDate)) {
                return 0;
            }else{
                return 1;
            }
        }else {
            return 2;
        }
    }
    public List queryCalendar(Report report){
        return reportDao.queryCalendar(report);
    }

    public List findDayReportStatus(Report report){
        return reportDao.findDayReportStatus(report);
    }

    public List findByWeek(Report report){
        return reportDao.findByWeek(report);
    }

    public List searchReport(Report report){
        return reportDao.searchReport(report);
    }

    public int searchByDate(Date date,String rptPersonId){
        Report report=new Report();
        report.setBelongsDate(date);
        report.setType("0");
        report.setRtpPersonId(rptPersonId);
        List<Report> list=reportDao.searchByDate(report);
        if (list.size()>0) {
            return 0;
        }else{
            return 1;
        }
    }
    public List<Report> searchByOrgIdMap(Report report) {
        return reportDao.searchByOrgIdMap(report);
    }
}
