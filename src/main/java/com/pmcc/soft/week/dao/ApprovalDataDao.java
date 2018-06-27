package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ApprovalData;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 审批业务数据表
 * Created by LvXL on 2016/4/27.
 */
@Repository
public class ApprovalDataDao extends SqlSessionDaoSupport {

    public static final String NAME_SPACE = "com/pmcc/soft/week/ApprovalDataMapper";

    public int save(ApprovalData approvalData){
        return this.getSqlSession().insert(NAME_SPACE + ".save", approvalData);
    }

    public List<ApprovalData> queryByApprovalHeadId(ApprovalData approvalData){
        return this.getSqlSession().selectList(NAME_SPACE + ".queryByApprovalHeadId", approvalData);
    }

}
