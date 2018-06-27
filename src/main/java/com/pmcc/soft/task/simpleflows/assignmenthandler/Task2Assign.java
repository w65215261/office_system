package com.pmcc.soft.task.simpleflows.assignmenthandler;

import com.pmcc.soft.core.common.ApplicationContextUtil;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.rest.JPush;
import com.pmcc.soft.week.domain.ApprovalHead;
import com.pmcc.soft.week.manager.ApprovalHeadManager;
import com.pmcc.soft.week.manager.SystemAuditPersonConfigManager;
import org.snaker.engine.Assignment;
import org.snaker.engine.core.Execution;
import org.snaker.engine.model.TaskModel;

import java.util.HashMap;
import java.util.Map;

public class Task2Assign extends Assignment {

    // 审批人员配置表
    private SystemAuditPersonConfigManager auditPersonConfigManager = (SystemAuditPersonConfigManager) ApplicationContextUtil.getBean("systemAuditPersonConfigManager");

    public Object assign(TaskModel model, Execution execution) {
        Map<String, Object> variables = execution.getArgs();
        String headId = (String) variables.get("headId");
        String personId = auditPersonConfigManager.findConfigPerson(headId, CommonVariables.APPROVEL_GROUP_2ND_CODE);
      return personId;
    }
}


