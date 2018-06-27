package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.OperationRecordPlanDao;
import com.pmcc.soft.week.domain.OperationRecordPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2015/12/07.
 */
@Transactional
@Service
public class OperationRecordPlanManager {

    @Autowired
    OperationRecordPlanDao operationRecordPlanDao;

    public int insert(OperationRecordPlan or){
        or.setOid(UUIDGenerator.getUUID());
        return operationRecordPlanDao.insert(or);
    }


    public List<OperationRecordPlan> findOperation(OperationRecordPlan or) {
        return operationRecordPlanDao.findOperation(or);

    }

}
