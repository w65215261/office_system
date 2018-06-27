package com.pmcc.soft.week.manager;

import com.pmcc.soft.week.dao.*;
import com.pmcc.soft.week.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 审批模板
 * Created by wangbin on 2016/4/25.
 */
@Transactional
@Service
public class ApprovalTemplateManager {

    @Autowired
    private ApprovalTemplateDao approvalTemplateDao;

    public ApprovalTemplate queryByTemplateCode(String templateCode){
        return approvalTemplateDao.queryByTemplateCode(templateCode);
    }

    public List<ApprovalTemplate> query(){
        return approvalTemplateDao.query();
    }

}
