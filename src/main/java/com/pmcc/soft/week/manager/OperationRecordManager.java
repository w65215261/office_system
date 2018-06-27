package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.OperationRecordDao;
import com.pmcc.soft.week.domain.OperationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
@Transactional
@Service
public class OperationRecordManager {

    @Autowired
    OperationRecordDao operationRecordDao;

    public int insert(OperationRecord or){
        or.setOid(UUIDGenerator.getUUID());
        return operationRecordDao.insert(or);
    }


    public List<OperationRecord> findByProjectOid(OperationRecord or) {
        return operationRecordDao.findByProjectOid(or);

    }

}
