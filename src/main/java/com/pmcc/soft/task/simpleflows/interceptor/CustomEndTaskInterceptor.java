package com.pmcc.soft.task.simpleflows.interceptor;

import com.pmcc.soft.core.common.ApplicationContextUtil;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.rest.JPush;
import com.pmcc.soft.week.domain.ApprovalHead;
import com.pmcc.soft.week.manager.ApprovalHeadManager;
import com.pmcc.soft.week.manager.SystemAuditPersonConfigManager;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;

import java.util.Map;

public class CustomEndTaskInterceptor implements SnakerInterceptor {

    // 审批人员配置表
    private SystemAuditPersonConfigManager auditPersonConfigManager = (SystemAuditPersonConfigManager) ApplicationContextUtil.getBean("systemAuditPersonConfigManager");
    // 业务主表
    private ApprovalHeadManager approvalHeadManager = (ApprovalHeadManager) ApplicationContextUtil.getBean("approvalHeadManager");

    public void intercept(Execution execution) {
        Map<String, Object> variables = execution.getArgs();
        String headId = (String) variables.get("headId");
        String res = (String) variables.get("res");
        Order order = execution.getOrder();
        String orderId = order.getId();

        if ((CommonVariables.APPROVEL_NO_PASS + "").equals(res)) {

            if (execution.getTask().getTaskName().equals("task2")) {
                //1.强制终止任务
                execution.getEngine().order().terminate(execution.getOrder().getId(), SnakerEngine.ADMIN);
            }
                //2.审核不通过
                approvalHeadManager.check(headId, CommonVariables.APPROVEL_NO_PASS + "", orderId);

        } else {
            if (execution.getTask().getTaskName().equals("task3")) {
                approvalHeadManager.check(headId, CommonVariables.APPROVEL_PASS + "", orderId);
            }
//            if (execution.getTask().getTaskName().equals("task2")) {
//
//                String personId = auditPersonConfigManager.findConfigPerson(headId, CommonVariables.APPROVEL_GROUP_2NG_CODE);
//                ApprovalHead head = approvalHeadManager.get(headId);
//                //加入推送
//                Map<String, String> extras =  CommonUtils.getExtras("PushJsonType_SP", headId, execution.getTask().getId(), "3", "ok", "1", head.getType());
//                JPush.sendPushToAndroidWithAlias(personId, CommonVariables.SendPushToAndroid_Task1Assign_Alert, CommonVariables.SendPushToAndroid_Title, extras);
//
//            }


        }
    }


}
