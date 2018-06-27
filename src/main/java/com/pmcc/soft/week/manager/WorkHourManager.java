package com.pmcc.soft.week.manager;

import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.dao.WorkHourDao;
import com.pmcc.soft.week.domain.WorkHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by sunyake on 15/7/8.
 */
@Transactional
@Service
public class WorkHourManager {


    @Autowired
    private WorkHourDao workHourDao;

    public List<WorkHour> query(WorkHour object) {
        return workHourDao.query(object);
    }


    public int insert(WorkHour workHour){
        workHour.setId(UUIDGenerator.getUUID());
        workHour.setDelFlag("0");
       return workHourDao.insert(workHour);
    }

    public int update(WorkHour workHour){
        return workHourDao.update(workHour);
    }

    public int delete(String id){
      return  workHourDao.delete(id);
    }


    public  int queryByDate(String user,Date date){
        WorkHour wh=new WorkHour();
        wh.setPersonId(user);
        wh.setStartTime(DateUtil.getBetweenDayTime(new Timestamp(date.getTime()))[0]);
        wh.setEndTime(DateUtil.getBetweenDayTime(new Timestamp(date.getTime()))[1]);
        List<WorkHour> li= workHourDao.queryByDate(wh);
        if(li !=null && li.size()>0){
            long test=0;
            for(int i=0;i<li.size();i++){
               long  test1=Math.abs(li.get(i).getEndTime().getTime()-li.get(i).getStartTime().getTime());
                test +=test1;
            }
            if(test>=28800000){
                return 0;
            }else{
                return  1;
            }
        }else{
            return 2;
        }
    }


    public WorkHour findById(String id){
      return  workHourDao.findById(id);
    }
}
