package com.pmcc.soft.task.simpleflows.interceptor;

import com.pmcc.soft.core.common.ApplicationContextUtil;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.week.manager.ApprovalHeadManager;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import java.util.Map;

public class CustomTerminateTaskInterceptor implements SnakerInterceptor {

    private ApprovalHeadManager approvalHeadManager = (ApprovalHeadManager) ApplicationContextUtil.getBean("approvalHeadManager");

    public void intercept(Execution execution) {
        String headId = (String) (execution.getArgs().get("headId"));
        Map<String, Object> variables = execution.getArgs();
        Order order = execution.getOrder();
        String orderId = order.getId();
        String res = (String) variables.get("res");
        if ((CommonVariables.APPROVEL_NO_PASS + "").equals(res)) {
            //1.强制终止任务
            execution.getEngine().order().terminate(execution.getOrder().getId(), SnakerEngine.ADMIN);
            //2.审核不通过
            approvalHeadManager.check(headId, CommonVariables.APPROVEL_NO_PASS + "", orderId);
        }
    }
}
