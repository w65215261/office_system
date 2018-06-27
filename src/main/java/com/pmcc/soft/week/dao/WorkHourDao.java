package com.pmcc.soft.week.dao;

import com.pmcc.soft.core.utils.DateUtil;
import com.pmcc.soft.week.domain.WorkHour;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by sunyake on 15/7/8.
 */
@Repository
public class WorkHourDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/workHourMapper";


    public List<WorkHour> query(WorkHour object) {
        Date date=object.getBelongsDate();
        if(date !=null && !date.equals("")){
            Timestamp[] time= DateUtil.getBetweenDayTime(new Timestamp(date.getTime()));
            object.setStartTime(time[0]);
            object.setEndTime(time[1]);
        }else{
            String datestr = "1999-01-29 04:37:21.453";
            String datestr2 = "1999-01-29 04:37:22.453";
           Timestamp start=Timestamp.valueOf(datestr);
           Timestamp end=Timestamp.valueOf(datestr2);
            object.setStartTime(start);
            object.setEndTime(end);
        }
        return this.getSqlSession().selectList(NAME_SPACE + ".query", object);
    }

    public int insert(WorkHour mr) {
        int res = 0;
        res = this.getSqlSession().insert(NAME_SPACE + ".insert", mr);
        return res;
    }

    public int update(WorkHour mr) {
        int res = 0;
        res = this.getSqlSession().update(NAME_SPACE + ".update", mr);
        return res;
    }

    public int delete(String  id) {
        int res = 0;
        res = getSqlSession().delete(NAME_SPACE + ".delete", id);
        return res;
    }

    public WorkHour findById(String  id) {
        return this.getSqlSession().selectOne(NAME_SPACE + ".findById", id);
    }

    public List<WorkHour> queryByDate(WorkHour mr) {
       return    this.getSqlSession().selectList(NAME_SPACE + ".queryByDate", mr);
    }
}
