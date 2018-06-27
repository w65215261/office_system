package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.SignInfoDao;
import com.pmcc.soft.week.dao.SignInfoReportDao;
import com.pmcc.soft.week.domain.SignInfo;
import com.pmcc.soft.week.domain.SignInfoReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunyake on 15/10/27.
 */
@Transactional
@Service
public class SignInfoReportManager {
    @Autowired
    private SignInfoReportDao signInfoReportDao;

    public List<SignInfoReport> selectAll(SignInfoReport signInfoReport) {
        return signInfoReportDao.query(signInfoReport);
    }


    public  int insertSignInfoReport(SignInfoReport signInfoReport){
        return signInfoReportDao.insertSignInfoReport(signInfoReport);
    }

    public int updateSignInfoReport(SignInfoReport signInfoReport){
        return  signInfoReportDao.updateSignInfoReport(signInfoReport);
    }
}
