package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.SignInfoDao;
import com.pmcc.soft.week.domain.SignInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sunyake on 15/10/27.
 */
@Transactional
@Service
public class SignInfoManager {
    @Autowired
    private SignInfoDao signInfoDao;

    public List<SignInfo> query(SignInfo signInfo){


        return signInfoDao.query(signInfo);
    }

    public int insert(SignInfo signInfo){

        return signInfoDao.insert(signInfo);
    }

    public SignInfo findById(String id){

        return signInfoDao.findById(id);
    }

    public int delete(String id){
        return signInfoDao.delete(id);

    }

    public int update(SignInfo signInfo){
        return signInfoDao.update(signInfo);

    }

    public List<SignInfo> queryOneDay(SignInfo signInfo){


        return signInfoDao.queryOneDay(signInfo);
    }








}
