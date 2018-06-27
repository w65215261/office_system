package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.SystemLogDao;
import com.pmcc.soft.week.domain.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统操作记录表
 * Created by LvXL on 2016/5/9.
 */
@Transactional
@Service
public class SystemLogManager {

    @Autowired
    SystemLogDao systemLogDao;

    /**
     * 新增保存
     * @param log
     * @return
     */
    public int save(SystemLog log){
        return systemLogDao.insert(log);
    }

    public List<SystemLog> query(SystemLog log){
        return systemLogDao.query(log);
    }

}
