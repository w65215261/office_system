package com.pmcc.soft.week.dao;

import com.pmcc.soft.week.domain.ApprovalTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangbin on 2016/5/25.
 */
@Repository
public class ApprovalTemplateDao extends SqlSessionDaoSupport {
    public static final String NAME_SPACE = "com/pmcc/soft/week/ApprovalTemplateMapper";

    public ApprovalTemplate queryByTemplateCode(String templateCode){
        return this.getSqlSession().selectOne(NAME_SPACE + ".queryByTemplateCode", templateCode);
    }

    public List<ApprovalTemplate> query(){
        return this.getSqlSession().selectList(NAME_SPACE + ".query");
    }
}
