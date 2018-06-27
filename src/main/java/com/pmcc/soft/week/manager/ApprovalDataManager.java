package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.ApprovalDataDao;
import com.pmcc.soft.week.domain.ApprovalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Transactional
@Service
public class ApprovalDataManager {
    @Autowired
    private ApprovalDataDao approvalDataDao;

    public List<ApprovalData> queryByApprovalHeadId(ApprovalData approvalData){
        List<ApprovalData> list = approvalDataDao.queryByApprovalHeadId(approvalData);
        return list;
    }

}
