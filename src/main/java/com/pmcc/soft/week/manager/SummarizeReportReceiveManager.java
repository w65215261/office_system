package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.SummarizeReportDao;
import com.pmcc.soft.week.dao.SummarizeReportReceiveDao;
import com.pmcc.soft.week.domain.Report;
import com.pmcc.soft.week.domain.SummarizeReportReceive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Transactional
@Service
public class SummarizeReportReceiveManager {
    @Autowired
    private SummarizeReportReceiveDao summarizeReportReceiveDao;

    public int insert(SummarizeReportReceive srre){
        srre.setId(UUIDGenerator.getUUID());
        return summarizeReportReceiveDao.insert(srre);
    }

    public List<SummarizeReportReceive> queryByReceivePersonId(SummarizeReportReceive summarizeReportReceive){
        return summarizeReportReceiveDao.queryByReceivePersonId(summarizeReportReceive);
    }

    public List<SummarizeReportReceive> query(SummarizeReportReceive summarizeReportReceive){
        return summarizeReportReceiveDao.query(summarizeReportReceive);
    }

    public int update(SummarizeReportReceive srre){
        return summarizeReportReceiveDao.update(srre);
    }
    public List<SummarizeReportReceive> queryReportByReceivePersonId(SummarizeReportReceive summarizeReportReceive){
        return summarizeReportReceiveDao.queryReportByReceivePersonId(summarizeReportReceive);
    }

}
