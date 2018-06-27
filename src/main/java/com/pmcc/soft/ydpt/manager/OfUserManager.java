package com.pmcc.soft.ydpt.manager;

import com.pmcc.soft.ydpt.dao.OfMucRoomDao;
import com.pmcc.soft.ydpt.dao.OfUserDao;
import com.pmcc.soft.ydpt.domain.OfMucRoom;
import com.pmcc.soft.ydpt.domain.OfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ZhanYanChang on 2015/6/5.
 */
@Service
@Transactional
public class OfUserManager {
    @Autowired
    OfUserDao ofUserDao;

    public void insert(OfUser param){
        //ofUserDao.setCreationDate(new Long(new Date().getTime()).toString());
        ofUserDao.insert(param);
    }
    public void update(OfUser param){
        //omr.setModificationDate(new Long(new Date().getTime()).toString());
        ofUserDao.update(param);
    }
    public void delete(OfUser param){
        ofUserDao.delete(param);
    }
    public List<OfUser> query(OfUser param){
        return ofUserDao.query(param);
    }
    public OfUser findById(OfUser param){
        return ofUserDao.findById(param);
    }
    
}
