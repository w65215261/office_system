package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.SummarizeReportDao;
import com.pmcc.soft.week.dao.SummarizeReportReplayDao;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SummarizeReportReplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Transactional
@Service
public class SummarizeReportReplayManager {
    @Autowired
    private SummarizeReportReplayDao summarizeReportReplayDao;

    public int insert(SummarizeReportReplay summarizeReportReplay){
        summarizeReportReplay.setId(UUIDGenerator.getUUID());
        return summarizeReportReplayDao.insert(summarizeReportReplay);
    }

    public List<SummarizeReportReplay> query(SummarizeReportReplay summarizeReportReplay){
        return summarizeReportReplayDao.query(summarizeReportReplay);
    }

}
