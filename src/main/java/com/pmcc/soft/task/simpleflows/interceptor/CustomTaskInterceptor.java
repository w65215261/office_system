package com.pmcc.soft.task.simpleflows.interceptor;

import com.pmcc.soft.core.common.ApplicationContextUtil;
import com.pmcc.soft.core.common.CommonVariables;
import com.pmcc.soft.core.utils.CommonUtils;
import com.pmcc.soft.rest.JPush;
import com.pmcc.soft.week.domain.ApprovalHead;
import com.pmcc.soft.week.manager.ApprovalHeadManager;
import com.pmcc.soft.week.manager.SystemAuditPersonConfigManager;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;

import java.util.Map;

public class CustomTaskInterceptor implements SnakerInterceptor {


	// 审批人员配置表
	private SystemAuditPersonConfigManager auditPersonConfigManager = (SystemAuditPersonConfigManager) ApplicationContextUtil.getBean("systemAuditPersonConfigManager");
	// 业务主表
	private ApprovalHeadManager approvalHeadManager = (ApprovalHeadManager) ApplicationContextUtil.getBean("approvalHeadManager");

	public void intercept(Execution execution) {
		Order order = execution.getOrder();
		String orderId = order.getId();
		Map<String, Object> variables = execution.getArgs();
		String headId = (String) variables.get("headId");
		approvalHeadManager.check(headId, CommonVariables.APPROVEL_RUN + "", orderId);


//		String personId = auditPersonConfigManager.findConfigPerson(headId, CommonVariables.APPROVEL_GROUP_1TH_CODE);
//		ApprovalHead head = approvalHeadManager.get(headId);
//		//加入推送
//		Map<String, String> extras =  CommonUtils.getExtras("PushJsonType_SP", headId, execution.getTask().getId(), "3", "ok", "1", head.getType());
//		JPush.sendPushToAndroidWithAlias(personId, CommonVariables.SendPushToAndroid_Task1Assign_Alert, CommonVariables.SendPushToAndroid_Title, extras);
	}
}
