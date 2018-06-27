package com.pmcc.soft.week.dao;

import com.pmcc.soft.unit.AuditData;
import com.pmcc.soft.week.domain.ApprovalHead;
import com.pmcc.soft.week.domain.DiySelectDisplay;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/4/11.
 */
@Repository
public class ApprovalHeadDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/ApprovalHeadMapper";

    public int save(ApprovalHead approvalHead){
        return this.getSqlSession().insert(NAME_SPACE + ".save", approvalHead);
    }

    /**
     * 通过oid获取详细信息
     * @author LvXL
     * @param approvalHead
     * @return
     */
    public ApprovalHead get(ApprovalHead approvalHead){
        return this.getSqlSession().selectOne(NAME_SPACE + ".query", approvalHead);
    }

    /**
     * 同意或拒绝 操作
     * @author LvXL
     * @param approvalHead
     * @return
     */
    public int check(ApprovalHead approvalHead){
        return this.getSqlSession().update(NAME_SPACE + ".check", approvalHead);
    }

    /**
     * 查询所有personId创建的数据
     * @author LvXL
     * @param ah
     * @return
     */
    public List<ApprovalHead> query(ApprovalHead ah) {
        return this.getSqlSession().selectList(NAME_SPACE + ".query", ah);
    }

}
