package com.pmcc.soft.week.dao;

import com.pmcc.soft.core.utils.UUIDGenerator;
import com.pmcc.soft.week.domain.SignInfo;
import com.pmcc.soft.week.domain.SignInfoReport;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunyake on 15/10/27.
 */
@Repository
public class SignInfoReportDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/signInfoReportMapper";

    public List<SignInfoReport> query(SignInfoReport object) {

        return this.getSqlSession().selectList(NAME_SPACE + ".selectAll", object);
    }

    public int insertSignInfoReport(SignInfoReport signInfoReport){
      int res=  this.getSqlSession().insert(NAME_SPACE + ".insertSignInfoReport", signInfoReport);
        return res;
    }
    public int updateSignInfoReport(SignInfoReport signInfoReport){
        int res=this.getSqlSession().update(NAME_SPACE + ".updateSignInfoReport", signInfoReport);
        return res;
    }
}
